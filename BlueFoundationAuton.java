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
            //robot.drive(-0.2,0,0,3700);
            robot.driveAtDirection(90, 350, .2);
            sleep(1000);
            robot.driveAtDirection(180, 1375.1, .2);
            //out arms down
            robot.leftArmServo.setPosition(0.5);
            robot.rightArmServo.setPosition(1);
            sleep(2000);
            //drive forward
//            robot.driveStall(0.25,0,0,4000);
            robot.driveAtDirection(0, 750, .2);
            sleep(2000);
            //in arm down for left servo
            robot.leftArmServo.setPosition(0);
            // turns left around 90 degrees
            robot.turnLeft(600,.15);
            sleep(2000);
            //raise arms
            robot.leftArmServo.setPosition(1);
            robot.rightArmServo.setPosition(0);
            //go left and park under bridge
            sleep(2000);
//            robot.drive(.3,0,0,4000);
            robot.driveAtDirection(0, 1000, .2);
        }
    }
}

