package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;

/**
 * This class is for setting up all the hardware components of the robot.
 * This will have all the sensors, motors and servos declarations.
 * It will also be used to initialize everything for autonomous
 */
public class Hardware {

    HardwareMap hwMap;


    //drive train motors
    public DcMotor frontLeft = null;
    public DcMotor frontRight = null;
    public DcMotor backLeft = null;
    public DcMotor backRight = null;

    //random
    public DcMotor motor = null;

    //Servos
  //  public Servo  = null;

    //sensors
    public ColorSensor intakeDetector = null;
    public AnalogInput potenimeter = null;
    public TouchSensor touchSensor = null;

    //IMU
    public BNO055IMU imu;

    /**
     * Constructor to set up the Hardwaremap
     */
    public Hardware() {
        hwMap = null;
    }

    /**
     * Method for initializing all the hardware components.
     * Use at the beginning of code initialization
     * @param ahwMap the hardware declaration being passed into this class
     */
    public void init(HardwareMap ahwMap, boolean resetMotors) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        //drive train motor setup
        frontLeft = hwMap.dcMotor.get("frontLeft");
        backLeft = hwMap.dcMotor.get("backleft");
        frontRight = hwMap.dcMotor.get("frontright");
        backRight = hwMap.dcMotor.get("backright");
        //reversing the right side motors
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);



        if(resetMotors) {
            resetMotors();
        }
    }

    public void resetMotors(){
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    }
}
