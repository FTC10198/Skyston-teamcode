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
public class BlueBlocksAuto extends LinearOpMode{
    private org.firstinspires.ftc.teamcode.HardwareMapping robot = new org.firstinspires.ftc.teamcode.HardwareMapping();

   VuforiaStuff.skystonePos pos;

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        if (opModeIsActive()) {

            // where the block is
            pos = robot.vuforiaStuff.vuforiascan(true, false);
            telemetry.addData("position",pos);
            telemetry.update();

            switch (pos) {
                case LEFT:
                    // move left about 6" to align with left stone
                    robot.driveAtDirection(270, 100, .2);
                    //drive forward about 30" with intake on
                    robot.intakeRight.setPower(-1);
                    robot.intakeLeft.setPower(1);
                    robot.driveAtDirection(0, 1500, .2);
                    //pause to get block
                    sleep(1200);
                    robot.intakeRight.setPower(0);
                    robot.intakeLeft.setPower(0);
                    //back up
                    robot.driveAtDirection(180, 900, .2);
                    //move past left bridge
                    robot.driveAtDirection(270, 1800, .2);
                    //drop off stone-reverse intake
                    robot.intakeRight.setPower(1);
                    robot.intakeLeft.setPower(-1);
                    //stop intake
                    robot.intakeRight.setPower(0);
                    robot.intakeLeft.setPower(0);
                    //go right until lined up with second stone
                    robot.driveAtDirection(90, 2000, .2);
                    //drive forward with intake on
                    robot.intakeRight.setPower(-1);
                    robot.intakeLeft.setPower(1);
                    robot.driveAtDirection(0, 300, .2);
                    //pause to get block
                    sleep(2000);
                    robot.intakeRight.setPower(0);
                    robot.intakeLeft.setPower(0);
                    //back up
                    robot.driveAtDirection(180, 300, .2);
                    //go left past bridge
                    robot.driveAtDirection(270, 3000, .2);
                    //block out
                    robot.intakeRight.setPower(1);
                    robot.intakeLeft.setPower(-1);
                    //park
                    robot.driveAtDirection(90, 600, .2);
                    break;

                case CENTER:
                    //move right to align with stone
                    //drive forward about 30" with intake on
                    robot.intakeRight.setPower(-1);
                    robot.intakeLeft.setPower(1);
                    robot.driveAtDirection(0, 1500, .2);
                    //pause to get block
                    sleep(1200);
                    robot.intakeRight.setPower(0);
                    robot.intakeLeft.setPower(0);
                    //back up
                    robot.driveAtDirection(180, 900, .2);
                    //same, but longer by 8" than left
                    //drop off stone-reverse intake
                    robot.intakeRight.setPower(1);
                    robot.intakeLeft.setPower(-1);
                    //stop intake
                    robot.intakeRight.setPower(0);
                    robot.intakeLeft.setPower(0);
                    //go right, touching side wall
                    //drive forward with intake on
                    robot.intakeRight.setPower(-1);
                    robot.intakeLeft.setPower(1);
                    robot.driveAtDirection(0, 300, .2);
                    //pause to get block
                    sleep(2000);
                    robot.intakeRight.setPower(0);
                    robot.intakeLeft.setPower(0);
                    //back up
                    robot.driveAtDirection(180, 300, .2);
                    //same but longer by 8" than left
                    //block out
                    robot.intakeRight.setPower(1);
                    robot.intakeLeft.setPower(-1);
                    //park
                    robot.driveAtDirection(90, 600, .2);
                    break;

                    case RIGHT:
                    //move right about a foot
                    //drive forward about 30" with intake on
                    robot.intakeRight.setPower(-1);
                    robot.intakeLeft.setPower(1);
                    robot.driveAtDirection(0, 1500, .2);
                    //pause to get block
                    sleep(1200);
                    robot.intakeRight.setPower(0);
                    robot.intakeLeft.setPower(0);
                    //back up
                    robot.driveAtDirection(180, 900, .2);
                    //same, but longer by 16" than left
                    //drop off stone-reverse intake
                    robot.intakeRight.setPower(1);
                    robot.intakeLeft.setPower(-1);
                    // go right until 1" from wall
                    //rotate 45 degrees to the right
                    //drive forward with intake on
                    robot.intakeRight.setPower(-1);
                    robot.intakeLeft.setPower(1);
                    robot.driveAtDirection(0, 300, .2);
                    sleep(1200);
                    robot.intakeRight.setPower(0);
                    robot.intakeLeft.setPower(0);
                    //back up
                    robot.driveAtDirection(180, 900, .2);
                    // rotate 45 degrees to the left
                    // go left past bridge
                    robot.driveAtDirection(270, 3000, .2);
                    //block out
                    robot.intakeRight.setPower(1);
                    robot.intakeLeft.setPower(-1);
                    //park
                    robot.driveAtDirection(90, 600, .2);
                    break;


            }

        }
    }
}

