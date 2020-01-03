package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;


//    Robot wheel mapping:
//        X FRONT X
//        X           X
//        X  FL       FR  X
//        X
//        XXX
//        X
//        X  BL       BR  X
//        X           X
//        X       X
//        */
@TeleOp
//@Disabled
public class Testing extends LinearOpMode {
    private HardwareMapping robot = new HardwareMapping();


    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            // left stick controls direction
            // right stick X controls rotation

            double gamepad1LeftY = gamepad1.left_stick_y;
            double gamepad1LeftX = -gamepad1.left_stick_x;
            double gamepad1RightX = -gamepad1.right_stick_x*.7;

            // holonomic formulas

            double FrontLeftPrep = -gamepad1LeftY - gamepad1LeftX - gamepad1RightX;
            double FrontRightPrep = gamepad1LeftY - gamepad1LeftX - gamepad1RightX;
            double BackRightPrep = gamepad1LeftY + gamepad1LeftX - gamepad1RightX;
            double BackLeftPrep = -gamepad1LeftY + gamepad1LeftX - gamepad1RightX;

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


            /*
             * Telemetry for debugging
             */
            telemetry.addData("Text", "*** Robot Data***");
            telemetry.addData("Joy XL YL XR", String.format("%.2f", gamepad1LeftX) + " " +
                    String.format("%.2f", gamepad1LeftY) + " " + String.format("%.2f", gamepad1RightX));
            telemetry.addData("f left pwr", "front left  pwr: " + String.format("%.2f", FrontLeft));
            telemetry.addData("f right pwr", "front right pwr: " + String.format("%.2f", FrontRight));
            telemetry.addData("b right pwr", "back right pwr: " + String.format("%.2f", BackRight));
            telemetry.addData("b left pwr", "back left pwr: " + String.format("%.2f", BackLeft));

            //double intakeLeftPower = robot.intakeLeft.getPower();
            //double intakeRightPower = robot.intakeRight.getPower();

            //boolean IntakeIn;
            //boolean IntakeOut;

            //if (intakeLeftPower < 0){
            //    IntakeIn = true;
            //}else {
            //    IntakeIn = false;
            //}
            //if (intakeLeftPower > 0){
            //    IntakeOut = true;
            //}else {
            //    IntakeOut = false;
            //}
            //false means motor not running


            //for the intake
            //if (gamepad1.a) {
            //    if (IntakeIn == false){
            //    robot.intakeLeft.setPower(-.5);
            //    robot.intakeRight.setPower(.5);}
            //    else {
            //        robot.intakeLeft.setPower(0);
            //        robot.intakeRight.setPower(0);
            //    }telemetry.addData("Intake System", String.format("%.2f", robot.intakeLeft.getPower()) + " " +
            //            String.format("%.2f", robot.intakeRight.getPower()));
            //    telemetry.update();
            //}else if (gamepad1.b) {
            //    if (IntakeOut == false) {
            //    robot.intakeLeft.setPower(.5);
            //    robot.intakeRight.setPower(-.5);}
            //    else {
            //        robot.intakeLeft.setPower(0);
            //        robot.intakeRight.setPower(0);
            //    }telemetry.addData ("Intake System", String.format("%.2f", robot.intakeLeft.getPower()) + " " +
            //            String.format("%.2f", robot.intakeRight.getPower()));
            //    telemetry.update();
            //}
            
            //New In
            robot.intakeLeft.setPower(-gamepad2.left_trigger);
            robot.intakeRight.setPower(gamepad2.left_trigger);
            
            //New Out
            robot.intakeLeft.setPower(gamepad2.right_trigger);
            robot.intakeRight.setPower(-gamepad2.right_trigger);
            
            telemetry.addData ("Intake System", String.format("%.2f", robot.intakeLeft.getPower()) + " " +
                        String.format("%.2f", robot.intakeRight.getPower()));
            telemetry.update();
            
            //For testing positions to put into auton
            if (gamepad2.a) {
                robot.driveAtDirection(270, 2000, .2);
            } else if (gamepad2.b) {
                robot.driveAtDirection(90, 2000, .2);
            } else if (gamepad2.x) {
                robot.turnToAngle(45,.1);
            } else if (gamepad2.y) {
                //robot.driveAtDirection(180, 750, .2);
                robot.turnToAngle(-45,.1);
            } else if (gamepad1.a) {
                robot.driveAtDirection(0, 500, .2);
            } else if (gamepad1.b) {
                robot.driveAtDirection(180, 550, .2);
            } else if (gamepad1.x) {
                robot.driveAtDirection(90, 600, .2);
            } else if (gamepad1.y) {
                robot.driveAtDirection(270, 650, .2);
            }

        }

            telemetry.addData("Status", "Running");
            telemetry.update();
        }
    }