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
public class RightFartherBridgeAuton extends LinearOpMode{
    private org.firstinspires.ftc.teamcode.HardwareMapping robot = new org.firstinspires.ftc.teamcode.HardwareMapping();
    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        if (opModeIsActive()) {

            sleep(20000);
            //drive forward
            robot.drive(0.2,0,0,3500);
            //drive left
            robot.drive(0,-0.2,0,1500);
        }
    }
}
