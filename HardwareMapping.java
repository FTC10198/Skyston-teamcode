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
    public DcMotor  leftmotor   = null;
    public DcMotor  rightmotor  = null;
    public Servo    platformServo    = null;

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
        leftmotor  = hwMap.get(DcMotor.class, "leftmotor");
        rightmotor = hwMap.get(DcMotor.class, "rightmotor");
        leftmotor.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors
        rightmotor.setDirection(DcMotor.Direction.REVERSE);// Set to FORWARD if using AndyMark motors

        // Set all motors to zero power
        leftmotor.setPower(0);
        rightmotor.setPower(0);

        // Define and initialize ALL installed servos.
        platformServo  = hwMap.get(Servo.class, "platformServo");

        //Define rest.
        imu = hwMap.get(Gyroscope.class, "imu");
        //digitalTouch = hwMap.get(DigitalChannel.class, "digitalTouch");
        //sensorColorRange = hwMap.get(DistanceSensor.class, "sensorColorRange");
    }
}