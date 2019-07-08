//package org.firstinspires.ftc.teamcode;
//
//import com.qualcomm.hardware.bosch.BNO055IMU;
//import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
//import com.qualcomm.robotcore.eventloop.opmode.OpMode;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.util.ElapsedTime;
//
//import java.math.BigDecimal;
//import java.math.RoundingMode;
//
//
//@TeleOp(name="Ally", group="Outreach")
//
//
//public class Ally extends OpMode {
//    public DcMotor motorFrontRight;
//    public DcMotor motorFrontLeft;
//    public DcMotor motorBackLeft;
//    public DcMotor motorBackRight;
//
//    public int calibToggle;
//    BNO055IMU imu;
//    public void init() {
//
//        motorFrontRight = hardwareMap.dcMotor.get("frontRight");
//        motorFrontLeft = hardwareMap.dcMotor.get("frontLeft");
//        motorBackRight = hardwareMap.dcMotor.get("backRight");
//        motorBackLeft = hardwareMap.dcMotor.get("backLeft");
//
//        motorFrontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
//        motorFrontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
//        motorBackRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
//        motorBackLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
//
//        motorBackLeft.setDirection(DcMotor.Direction.REVERSE);
//        motorFrontLeft.setDirection(DcMotor.Direction.REVERSE);
//        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
//        parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS;
//        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
//        parameters.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
//        parameters.loggingEnabled = true;
//        parameters.loggingTag = "IMU";
//        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();
//        imu = hardwareMap.get(BNO055IMU.class, "imu");
//        imu.initialize(parameters);
//        calibToggle = 0;
//
//        double left = gamepad1.left_stick_y;
//    }
//    public void loop() {
//        motorFrontLeft.setPower(-gamepad1.left_stick_y);
//        motorFrontRight.setPower(-gamepad1.left_stick_y);
//        motorBackLeft.setPower(-gamepad1.left_stick_y);
//        motorBackRight.setPower(-gamepad1.left_stick_y);
//
//        motorFrontLeft.setPower(-gamepad1.right_stick_x);
//        motorFrontRight.setPower(gamepad1.right_stick_x);
//        motorBackLeft.setPower(-gamepad1.right_stick_x);
//        motorBackRight.setPower(gamepad1.right_stick_x);
//
//    }
//    }
