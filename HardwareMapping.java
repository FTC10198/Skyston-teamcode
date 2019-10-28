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
    public Gyroscope imu = null;
    //public DigitalChannel digitalTouch = null;
    //public DistanceSensor sensorColorRange = null;

    /* local OpMode members. */
    HardwareMap hwMap           =  null;
    private ElapsedTime period  = new ElapsedTime();

    /* Constructor */
    public HardwareMapping(){
    }

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


        // Define and initialize ALL installed servos.
        leftArmServo  = hwMap.get(Servo.class, "leftArmServo");
        rightArmServo  = hwMap.get(Servo.class, "rightArmServo");

        leftArmServo.setPosition(0.05);
        rightArmServo.setPosition(0.95);


        //Define rest.
        imu = hwMap.get(Gyroscope.class, "imu");
        //digitalTouch = hwMap.get(DigitalChannel.class, "digitalTouch");
        //sensorColorRange = hwMap.get(DistanceSensor.class, "sensorColorRange");
    }
}
