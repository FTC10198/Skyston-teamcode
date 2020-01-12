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
public class RedBlocksAuto extends LinearOpMode{
    private org.firstinspires.ftc.teamcode.HardwareMapping robot = new org.firstinspires.ftc.teamcode.HardwareMapping();

    VuforiaStuff.skystonePos pos;

    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        pos = robot.vuforiaStuff.vuforiascan(true, true);


        telemetry.addData("position",pos);
        telemetry.update();
        if (opModeIsActive()) {
// where the block is
            pos = robot.vuforiaStuff.vuforiascan(true, true);
            telemetry.addData("position",pos);
            telemetry.update();
            ElapsedTime runtime2 = new ElapsedTime();
            switch (pos) {
                case LEFT:
                    //move left about a foot
                    robot.driveAtDirection(270, 500, .3);
                    //drive forward about 30" with intake on
                    robot.intakeRight.setPower(-1);
                    robot.intakeLeft.setPower(1);
                    robot.driveAtDirection(0, 2200, .3);
                    //pause to get block
                    runtime2.reset();
                    while (runtime2.milliseconds() < 1200) {
                        if (robot.touchSensor.getState() == true) {
                            telemetry.addData("Digital Touch", "Is Not Pressed");
                        } else {
                            telemetry.addData("Digital Touch", "Is Pressed");
                            robot.intakeRight.setPower(0);
                            robot.intakeLeft.setPower(0);
                        }
                    }
                    //back up
                    robot.driveAtDirection(180, 1000, .3);
                    //go right past the bridge
                    robot.driveAtDirection(90, 2800, .3);
                    //drop off stone-reverse intake
                    sleep(1000);
                    robot.intakeRight.setPower(1);
                    robot.intakeLeft.setPower(-1);
                    sleep(2000);


                    // go left until 1" from wall
                    robot.driveAtDirection(270, 3000, .3);
                    //rotate 45 degrees to the left
                    robot.turnToAngle(315, .3);
                    //drive forward with intake on
                    robot.intakeRight.setPower(-1);
                    robot.intakeLeft.setPower(1);
                    robot.driveAtDirection(0, 2200, .2);
                    //pause to get block
                    while (runtime2.milliseconds() < 1200) {
                        if (robot.touchSensor.getState() == true) {
                            telemetry.addData("Digital Touch", "Is Not Pressed");
                        } else {
                            telemetry.addData("Digital Touch", "Is Pressed");
                            robot.intakeRight.setPower(0);
                            robot.intakeLeft.setPower(0);
                        }
                    }
                    //back up
                    robot.driveAtDirection(180, 900, .2);
                    // rotate 45 degrees to the right
                    robot.turnToAngle(0, .3);
                    // go left past bridge
                    robot.driveAtDirection(270, 3000, .2);
                    //block out
                    sleep(1000);
                    robot.intakeRight.setPower(1);
                    robot.intakeLeft.setPower(-1);
                    sleep(2000);
                    //park
                    robot.driveAtDirection(270, 600, .3);
                    break;

                case CENTER:
                    //move left to align with stone
                    robot.driveAtDirection(270, 200, .3);
                    //drive forward about 30" with intake on
                    robot.intakeRight.setPower(-1);
                    robot.intakeLeft.setPower(1);
                    robot.driveAtDirection(0, 2400, .3);
                    //pause to get block
                    runtime2.reset();
                    while (runtime2.milliseconds() < 1200) {
                        if (robot.touchSensor.getState() == true) {
                            telemetry.addData("Digital Touch", "Is Not Pressed");
                        } else {
                            telemetry.addData("Digital Touch", "Is Pressed");
                            robot.intakeRight.setPower(0);
                            robot.intakeLeft.setPower(0);
                        }
                    }
                    //back up
                    robot.driveAtDirection(180, 950, .3);
                    //go right past the bridge
                    robot.driveAtDirection(90, 2500, .3);
                    //drop off stone-reverse intake
                    sleep(1000);
                    robot.intakeRight.setPower(1);
                    robot.intakeLeft.setPower(-1);
                    sleep(2000);
                    //stop intake
                    robot.intakeRight.setPower(0);
                    robot.intakeLeft.setPower(0);


                    //go left, touching side wall
                    robot.driveAtDirection(270, 3000, .3);
                    //drive forward with intake on
                    robot.intakeRight.setPower(-1);
                    robot.intakeLeft.setPower(1);
                    robot.driveAtDirection(0, 2400, .2);
                    //pause to get block
                    while (runtime2.milliseconds() < 1200) {
                        if (robot.touchSensor.getState() == true) {
                            telemetry.addData("Digital Touch", "Is Not Pressed");
                        } else {
                            telemetry.addData("Digital Touch", "Is Pressed");
                            robot.intakeRight.setPower(0);
                            robot.intakeLeft.setPower(0);
                        }
                    }
                    //back up
                    robot.driveAtDirection(180, 950, .2);
                    //go right past bridge
                    robot.driveAtDirection(90, 3000, .3);
                    //block out
                    robot.intakeRight.setPower(1);
                    robot.intakeLeft.setPower(-1);
                    //park
                    robot.driveAtDirection(270, 700, .3);
                    break;

                case RIGHT:
                    // move right
                    robot.driveAtDirection(90, 200, .3);
                    //drive forward about 30" with intake on
                    robot.intakeRight.setPower(-1);
                    robot.intakeLeft.setPower(1);
                    robot.driveAtDirection(0, 2000, .3);
                    //pause to get block
                    runtime2.reset();
                    while (runtime2.milliseconds() < 1200) {
                        if (robot.touchSensor.getState() == true) {
                            telemetry.addData("Digital Touch", "Is Not Pressed");
                        } else {
                            telemetry.addData("Digital Touch", "Is Pressed");
                            robot.intakeRight.setPower(0);
                            robot.intakeLeft.setPower(0);
                        }
                    }
                    //back up
                    robot.driveAtDirection(180, 900, .3);
                    //move past right bridge
                    robot.driveAtDirection(90, 1800, .3);
                    //drop off stone-reverse intake
                    sleep(1000);
                    robot.intakeRight.setPower(1);
                    robot.intakeLeft.setPower(-1);
                    sleep(2000);


                    //stop intake
                    robot.intakeRight.setPower(0);
                    robot.intakeLeft.setPower(0);
                    //go left until lined up with second stone
                    robot.driveAtDirection(270, 3400, .3);
                    //drive forward with intake on
                    robot.intakeRight.setPower(-1);
                    robot.intakeLeft.setPower(1);
                    robot.driveAtDirection(0, 900, .3);
                    //pause to get block
                    while (runtime2.milliseconds() < 1200) {
                        if (robot.touchSensor.getState() == true) {
                            telemetry.addData("Digital Touch", "Is Not Pressed");
                        } else {
                            telemetry.addData("Digital Touch", "Is Pressed");
                            robot.intakeRight.setPower(0);
                            robot.intakeLeft.setPower(0);
                        }
                    }
                    //back up
                    robot.driveAtDirection(180, 900, .3);
                    //go right past bridge
                    robot.driveAtDirection(90, 3400, .3);
                    //block out
                    robot.intakeRight.setPower(1);
                    robot.intakeLeft.setPower(-1);
                    //park
                    robot.driveAtDirection(270, 600, .3);
                    break;


            }

        }
    }
}
