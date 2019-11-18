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
public class BlueFoundationFartherAuton extends LinearOpMode{
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
            robot.drive(-0.2,0,0,3500);
            sleep(200);
            //drop arms
            robot.leftArmServo.setPosition(0.5);
            robot.rightArmServo.setPosition(0);
            sleep(2000);
            //drive forward, pulling platform
            robot.drive(0.25,0.1,0,2500);
            robot.drive(0.25,0,0,2500);
            sleep(200);
            //raise arms
            robot.leftArmServo.setPosition(0);
            robot.rightArmServo.setPosition(0.5);
            //go left and park under bridge
            sleep(2000);
            robot.drive(0,-0.3,0,2000);
            robot.drive(-.2,0,0,3000);
            robot.drive(0,-0.3,0,2000);
        }
    }
}

