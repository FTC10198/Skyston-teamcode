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
public class TestAuto extends LinearOpMode{
    private org.firstinspires.ftc.teamcode.HardwareMapping robot = new org.firstinspires.ftc.teamcode.HardwareMapping();
    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        if (opModeIsActive()) {
            robot.driveAtDirection(0,2500, .25);
//            robot.driveAtDirection(90, 2500, .25);
//            robot.driveAtDirection(225, 2500, .25);
//            robot.driveAtDirection(315, 2500, .25);

            //possibly test lines for blue foundation
//            robot.turnRight(500,.1);
//            robot.driveAtDirection(90,1000,.25);
//            robot.turnLeft(500,.1);
            //end blue foundation playground

            sleep(100);
        telemetry.addData("heading",robot.imu.readCurrentHeading());
        telemetry.addData("left encoder" ,robot.frontLeftMotor.getCurrentPosition());
        telemetry.update();

        }
    }
}

