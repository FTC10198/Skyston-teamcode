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
        // where the block is
        pos = robot.vuforiaStuff.vuforiascan(true, false);
        telemetry.addData("position",pos);
        telemetry.update();

        switch (pos) {
            case LEFT:
                // move left to align with left stone
                robot.driveAtDirection(270, 350, .2);
                //drive forward with intake on
                robot.intakeRight.setPower(-1);
                robot.intakeLeft.setPower(1);
                robot.driveAtDirection(0, 350, .2);
                //pause to get block
                sleep(2000);
                robot.intakeRight.setPower(0);
                robot.intakeLeft.setPower(0);
                //back up
                robot.driveAtDirection(180, 350, .2);
                //move past left bridge
                robot.driveAtDirection(270, 350, .2);
                //drop off stone-reverse intake
                robot.intakeRight.setPower(1);
                robot.intakeLeft.setPower(-1);
                //go right until lined up with second stone
                robot.driveAtDirection(90, 350, .2);
                //drive forward with intake on
                robot.intakeRight.setPower(-1);
                robot.intakeLeft.setPower(1);
                robot.driveAtDirection(0, 350, .2);
                //pause to get block
                sleep(2000);
                robot.intakeRight.setPower(0);
                robot.intakeLeft.setPower(0);
                //back up
                robot.driveAtDirection(180, 350, .2);
                //go left past bridge
                robot.driveAtDirection(270, 350, .2);
                //block out
                robot.intakeRight.setPower(1);
                robot.intakeLeft.setPower(-1);
                //park
                robot.driveAtDirection(90, 350, .2);
                break;

            case CENTER:
                //move right to align with stone
                //2,3,4 same
                //same, but longer by 8" than left
                //go right, touching side wall
                // 8,9,10, same
                //same but longer by 8" than left
                //12,13, same
                break;

                case RIGHT:
                //move right about a foot
                // 2,3,4: same
                //same, but longer by 16" than left
                //same
                // go right until 1" from wall
                //rotate 45 degrees to the right
                //drive forward with intake on
                // pause to get block
                //back up
                // rotate 45 degrees to the left
                //go past left bridge (around same distance as left block)
                //block out
                //park
                break;


        }









    if (opModeIsActive()) {

        }
    }
}

