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

            drive(0,.5,0,1000);
            robot.platformServo.setPosition(0);
            drive(0,-.75,0,1000);
            robot.platformServo.setPosition(1);
            drive(-.5,0,0,1000);
        }
    }

    public void drive(double LeftY, double LeftX, double RightX, double time){
        LeftX = -LeftX;
        RightX = -RightX;


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