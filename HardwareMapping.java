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
import com.qualcomm.hardware.bosch.BNO055IMU;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.teamcode.VuforiaStuff;

public class HardwareMapping
{
    /* Public OpMode members. */
    public DcMotor  frontLeftMotor   = null;
    public DcMotor  frontRightMotor  = null;
    public DcMotor  backLeftMotor   = null;
    public DcMotor  backRightMotor  = null;
    public DcMotor  intakeRight   = null;
    public DcMotor  intakeLeft  = null;
    public Servo    leftArmServo    = null;
    public Servo    rightArmServo    = null;
    public VuforiaStuff vuforiaStuff;
    private VuforiaLocalizer vuforia;
    private static final String VUFORIA_KEY = "ATOlfJr/////AAABmUTNuojv10IyojJgb1ipUl5AFc9IdiMS/PX55ILLnxS3ZPIjWu/kKu4fRsmZnfgrOfXcXnYyoPbHFCQOiBSJR1y2voTvDBlVWM1Lq2YNVgaOBT5g+00yR9u7kHuOxaCouUCcQUjbu2T3CFsTeLzk5snuYDnpkERDb//651aurmTW+dlNlmHFiP6P5h2co6MZQNfSQc1/fVKM7bS7STDCsX1Ro4Nyj0rfTVCp8kK/rHzsyZ8JcZ1EvYz746d0Ma6z9+MCoZ7EGHw9XdK3dW3sYlXVXTLGMDVEbqAnfqlfnh7C67SGrpkytPabcbVWAilptCGmzykRg7rZt6HlS/qM10diikwTZL9aIyvZZFIY3yWf";

    public IMU imu;
    //public DigitalChannel digitalTouch = null;
    //public DistanceSensor sensorColorRange = null;

    /* local OpMode members. */
    HardwareMap hwMap           =  null;
    private ElapsedTime period  = new ElapsedTime();

    /* Constructor
    public HardwareMapping(){
    }
    */
    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Motors
        frontLeftMotor  = hwMap.get(DcMotor.class, "frontLeftMotor");
        frontRightMotor = hwMap.get(DcMotor.class, "frontRightMotor");
        backLeftMotor  = hwMap.get(DcMotor.class, "backLeftMotor");
        backRightMotor = hwMap.get(DcMotor.class, "backRightMotor");
        intakeRight  = hwMap.get(DcMotor.class, "intakeRight");
        intakeLeft = hwMap.get(DcMotor.class, "intakeLeft");
       // leftmotor.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors
       // rightmotor.setDirection(DcMotor.Direction.REVERSE);// Set to FORWARD if using AndyMark motors

        // Set all motors to zero power
        frontLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        backLeftMotor.setPower(0);
        backRightMotor.setPower(0);
        intakeRight.setPower(0);
        intakeLeft.setPower(0);

        frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Define and initialize ALL installed servos.
        leftArmServo  = hwMap.get(Servo.class, "leftArmServo");
        rightArmServo  = hwMap.get(Servo.class, "rightArmServo");

        leftArmServo.setPosition(0);
        rightArmServo.setPosition(0.5);

        //vuforia things
        int cameraMonitorViewId = hwMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hwMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        vuforiaStuff = new VuforiaStuff(vuforia);
        //  Instantiate the Vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        //Define rest.
        BNO055IMU gyro;
        gyro = hwMap.get(BNO055IMU.class, "imu");
        imu = new IMU(gyro);
        imu.initialize();
        //digitalTouch = hwMap.get(DigitalChannel.class, "digitalTouch");
        //sensorColorRange = hwMap.get(DistanceSensor.class, "sensorColorRange");
    }

    public void drive(double LeftYIn, double LeftXIn, double RightXIn, double driveTime){
        double driftCorrect = 0; //*will have to tune, idea is to counteract rotating because of weight distribution, could add to teleop too

        double LeftY = -LeftYIn;
        double LeftX = -LeftXIn;
        double RightX = -RightXIn - (driftCorrect*(Math.abs(LeftY)+Math.abs(LeftX)));
        //probably doesn't math out right, but driftCorrect should get scaled by how fast other motors are going


        ElapsedTime runtime2 = new ElapsedTime();
        runtime2.reset();

        while (runtime2.milliseconds() < driveTime) {
            double FrontLeftPrep = -LeftY - LeftX - RightX;
            double FrontRightPrep = LeftY - LeftX - RightX;
            double BackRightPrep = LeftY + LeftX - RightX;
            double BackLeftPrep = -LeftY + LeftX - RightX;

            // clip the right/left values so that the values never exceed +/- 1
            double FrontRight = Range.clip(FrontRightPrep, -1, 1);
            double FrontLeft = Range.clip(FrontLeftPrep, -1, 1);
            double BackLeft = Range.clip(BackLeftPrep, -1, 1);
            double BackRight = Range.clip(BackRightPrep, -1, 1);

            // write the values to the motors
            frontRightMotor.setPower(FrontRight);
            frontLeftMotor.setPower(FrontLeft);
            backLeftMotor.setPower(BackLeft);
            backRightMotor.setPower(BackRight);
        }
    }

    public void driveStall(double LeftYIn, double LeftXIn, double RightXIn, double driveTimeStall){
        double driftCorrect = 0; //*will have to tune, idea is to counteract rotating because of weight distribution, could add to teleop too

        double LeftY = -LeftYIn;
        double LeftX = -LeftXIn;
        double RightX = -RightXIn - (driftCorrect*(Math.abs(LeftY)+Math.abs(LeftX)));
        //probably doesn't math out right, but driftCorrect should get scaled by how fast other motors are going


        ElapsedTime runtime2 = new ElapsedTime();
        runtime2.reset();

        while (runtime2.milliseconds() < driveTimeStall) {
            double FrontLeftPrep = -LeftY - LeftX - RightX;
            double FrontRightPrep = LeftY - LeftX - RightX;
            double BackRightPrep = LeftY + LeftX - RightX;
            double BackLeftPrep = -LeftY + LeftX - RightX;

            // clip the right/left values so that the values never exceed +/- 1
            double FrontRight = Range.clip(FrontRightPrep, -1, 1);
            double FrontLeft = Range.clip(FrontLeftPrep, -1, 1);
            double BackLeft = Range.clip(BackLeftPrep, -1, 1);
            double BackRight = Range.clip(BackRightPrep, -1, 1);

            // write the values to the motors
            frontRightMotor.setPower(FrontRight);
            frontLeftMotor.setPower(FrontLeft);
            backLeftMotor.setPower(BackLeft);
            backRightMotor.setPower(BackRight);
            leftArmServo.setPosition(0.5);
            rightArmServo.setPosition(0);
        }
    }
    public void driveAtDirection(double AngleIn, double driveDistance, double motorPower) {
        double LeftYMotorFix = -1;
        double LeftXMotorFix = -1;
        double RightXMotorFix = -1;

        double frontLeftStart = frontLeftMotor.getCurrentPosition();
        double frontRightStart = frontRightMotor.getCurrentPosition();

        double startingHeading = imu.readCurrentHeading();

        while (Math.sqrt(Math.pow((frontLeftMotor.getCurrentPosition() - frontLeftStart),2)+Math.pow((frontRightMotor.getCurrentPosition()-frontRightStart),2)) < driveDistance) {

            double LeftY;
            double LeftX;
            double RightX = 0;

            LeftX = Math.sin(AngleIn)*LeftXMotorFix*motorPower;
            LeftY = Math.cos(AngleIn)*LeftYMotorFix*motorPower;

            if (startingHeading > imu.readCurrentHeading()){
                RightX = .25*RightXMotorFix;
            }else if (startingHeading < imu.readCurrentHeading()){
                RightX = -.25*RightXMotorFix;
            }else if (startingHeading == imu.readCurrentHeading()){
                RightX = 0;
            }

            double FrontLeftPrep = -LeftY - LeftX - RightX;
            double FrontRightPrep = LeftY - LeftX - RightX;
            double BackRightPrep = LeftY + LeftX - RightX;
            double BackLeftPrep = -LeftY + LeftX - RightX;

            // clip the right/left values so that the values never exceed +/- 1
            double FrontRight = Range.clip(FrontRightPrep, -1, 1);
            double FrontLeft = Range.clip(FrontLeftPrep, -1, 1);
            double BackLeft = Range.clip(BackLeftPrep, -1, 1);
            double BackRight = Range.clip(BackRightPrep, -1, 1);

            // write the values to the motors
            frontRightMotor.setPower(FrontRight);
            frontLeftMotor.setPower(FrontLeft);
            backLeftMotor.setPower(BackLeft);
            backRightMotor.setPower(BackRight);
        }
    }

    public void turnToAngle(double angleIn, double motorPower) {
        double RightX = 0;

        while (Math.abs(imu.readCurrentHeading() - angleIn) > 0.5) {
            if (imu.readCurrentHeading() - angleIn > 0.5) {
                RightX = motorPower;
            } else if (imu.readCurrentHeading() - angleIn < 0.5) {
                RightX = -motorPower;
            }

            // write the values to the motors
            frontRightMotor.setPower(RightX);
            frontLeftMotor.setPower(RightX);
            backLeftMotor.setPower(RightX);
            backRightMotor.setPower(RightX);
        }
    }
}
