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
        
        if (opModeIsActive()) {

            //drive backwards
            robot.drive(-0.2,0,0,3700);
            sleep(200);
            //out arms down
            robot.leftArmServo.setPosition(0.5);
            robot.rightArmServo.setPosition(1);
            sleep(2000);
            //drive forward
            robot.driveStall(0.25,0,0,4000);
            sleep(200);
            //in arm down for left servo
            robot.leftArmServo.setPosition(0);
            // turns left around 90 degrees
            robot.driveStall(0,0,-.7,1050);
            sleep(200);
            //raise arms
            robot.leftArmServo.setPosition(1);
            robot.rightArmServo.setPosition(0);
            //go left and park under bridge
            sleep(2000);
            robot.drive(.3,0,0,4000);
        }
    }
}

