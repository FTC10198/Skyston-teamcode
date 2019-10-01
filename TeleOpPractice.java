package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp

public class TeleOpPractice extends LinearOpMode {

    HardwareMapping robot = new HardwareMapping();

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        // run until the end of the match (driver presses STOP)
        double tgtPowerleft = 0;
        double tgtPowerright = 0;
        while (opModeIsActive()) {
            tgtPowerleft = -this.gamepad1.left_stick_y;
            robot.leftmotor.setPower(tgtPowerleft);
            tgtPowerright = -this.gamepad1.right_stick_y;
            robot.rightmotor.setPower(tgtPowerright);
            // check to see if we need to move the servo.
            if (gamepad2.y) {
                // move to 0 degrees.
                robot.platformServo.setPosition(0);
            } else if (gamepad2.x) {
                // move to 90 degrees.
                robot.platformServo.setPosition(0.5);
            } else if (gamepad2.a) {
                // move to 180 degrees.
                robot.platformServo.setPosition(1);
                telemetry.addData("Left Power", tgtPowerleft);
                telemetry.addData("Right Power", tgtPowerright);
                telemetry.addData("Platform Servo Position", robot.platformServo.getPosition());
                telemetry.addData("Status", "Running");
                telemetry.update();
            }
        }
    }
}