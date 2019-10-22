package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.util.Range;

@Autonomous
public class BlueFoundationAuton extends LinearOpMode{
    private org.firstinspires.ftc.teamcode.HardwareMapping robot = new org.firstinspires.ftc.teamcode.HardwareMapping();
    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        ElapsedTime runtime = new ElapsedTime();
        runtime.reset();
        if (runtime.seconds() < 30) {

            //drive backwards
            drive(-0.25,0,0,1000);
            //drop arms
            robot.leftArmServo.setPosition(0.5);
            robot.rightArmServo.setPosition(0.5);
            //sleep probably needed to allow servos to move
            //drive forward, pulling platform
            drive(0.75,0,0,333);
            //raise arms
            robot.platformServo.setPosition(1);
            robot.rightArmServo.setPosition(1);
            //go left and park under bridge
            //sleep probably needed again
            drive(0,-0.25,0,1000);
        }
    }

    public void drive(double LeftY, double LeftX, double RightX, double time){
        double driftCorrect = .01 //*will have to tune, idea is to counteract rotating because of weight distribution, could add to teleop too
        
        LeftY = -LeftY;
        LeftX = -LeftX;
        RightX = -RightX - (driftCorrect*(Math.abs(LeftY)+Math.abs(LeftX))); 
        //probably doesn't math out right, but driftCorrect should get scaled by how fast other motors are going


        ElapsedTime runtime2 = new ElapsedTime();
        runtime2.reset();

        while (runtime2.milliseconds() < time) {
            double FrontLeftPrep = -LeftY - LeftX - RightX;
            double FrontRightPrep = LeftY - LeftX - RightX;
            double BackRightPrep = LeftY + LeftX - RightX;
            double BackLeftPrep = -LeftY + LeftX - RightX;

            // clip the right/left values so that the values never exceed +/- 1
            FrontRightPrep = Range.clip(FrontRightPrep, -1, 1);
            FrontLeftPrep = Range.clip(FrontLeftPrep, -1, 1);
            BackLeftPrep = Range.clip(BackLeftPrep, -1, 1);
            BackRightPrep = Range.clip(BackRightPrep, -1, 1);

            double FrontLeft = Math.pow(FrontLeftPrep, 3);
            double FrontRight = Math.pow(FrontRightPrep, 3);
            double BackLeft = Math.pow(BackLeftPrep, 3);
            double BackRight = Math.pow(BackRightPrep, 3);

            // write the values to the motors
            robot.frontRightMotor.setPower(FrontRight);
            robot.frontLeftMotor.setPower(FrontLeft);
            robot.backLeftMotor.setPower(BackLeft);
            robot.backRightMotor.setPower(BackRight);


        }




        }
}
}
