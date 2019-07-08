//package org.firstinspires.ftc.teamcode;
//
//
//import com.qualcomm.hardware.bosch.BNO055IMU;
//import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
//import com.qualcomm.robotcore.eventloop.opmode.OpMode;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.Servo;
//import com.qualcomm.robotcore.util.ReadWriteFile;
//
//import org.firstinspires.ftc.robotcore.external.Func;
//import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
//import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
//import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
//import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
//import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
//import org.firstinspires.ftc.robotcore.internal.system.AppUtil;
//
//import java.io.File;
//import java.util.Locale;
//
//
//@TeleOp(name="Mercury Mechanum TeleOp", group="Outreach")
//
///**
// * This class is for our outreach robot that uses mecanum wheels.
// * This outreach bot was a prototype drivetrain to our two iterations of our competition robot from last year
// * This class allows the outreach bot to be omnidirectional
// */
//public class TeleOpMercury extends OpMode {
//
//    //DRIVETRAIN\\
//    public DcMotor motorFrontRight;
//    public DcMotor motorFrontLeft;
//    public DcMotor motorBackLeft;
//    public DcMotor motorBackRight;
//    public DcMotor flywheel;
//    public int calibToggle;
//    BNO055IMU imu;
//    public Orientation angles;
//    public Acceleration gravity;
//    public Servo barrier;
//    public DcMotor collext;
//
//    public void init() {
//
//        //DRIVETRAIN\\
//        motorFrontRight = hardwareMap.dcMotor.get("frontRight");
//        motorFrontLeft = hardwareMap.dcMotor.get("frontLeft");
//        motorBackRight = hardwareMap.dcMotor.get("backRight");
//        motorBackLeft = hardwareMap.dcMotor.get("backLeft");
//
//        collext = hardwareMap.dcMotor.get("collext");
//
//        flywheel = hardwareMap.dcMotor.get("flywheel");
//        // barrier = hardwareMap.servo.get("barrier");
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
//    }
//
//
//    public void loop() {
//
//        ///////////////
//        // GAMEPAD 1 //
//        ///////////////
//        if (gamepad1.y) { //orientation calibration
//            // Get the calibration data
//            BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
//            parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS;
//            parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
//            parameters.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
//            parameters.loggingEnabled = true;
//            parameters.loggingTag = "IMU";
//            parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();
//
//            imu.initialize(parameters);
//
//            BNO055IMU.CalibrationData calibrationData = imu.readCalibrationData();
//            String filename = "BNO055IMUCalibration.json";
//            File file = AppUtil.getInstance().getSettingsFile(filename);
//            ReadWriteFile.writeFile(file, calibrationData.serialize());
//            telemetry.log().add("saved to '%s'", filename);
//        }
//
//        if (gamepad1.x) { //toggle on
//            calibToggle = 1;
//        }
//
//        if (gamepad1.b) { //toggle off
//            calibToggle = 0;
//        }
//
//        if (calibToggle == 1) { //when toggled we are oriented with this math
//            angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.RADIANS);
//            double P = Math.hypot(-gamepad1.left_stick_x, -gamepad1.left_stick_y);
//            double robotAngle = Math.atan2(-gamepad1.left_stick_y, gamepad1.left_stick_x);
//            double rightX = -gamepad1.right_stick_x;
//
//
//            final double v5 = P * Math.sin(robotAngle - angles.firstAngle) + P * Math.cos(robotAngle - angles.firstAngle) - rightX;
//            final double v6 = P * Math.sin(robotAngle - angles.firstAngle) - P * Math.cos(robotAngle - angles.firstAngle) + rightX;
//            final double v7 = P * Math.sin(robotAngle - angles.firstAngle) - P * Math.cos(robotAngle - angles.firstAngle) - rightX;
//            final double v8 = P * Math.sin(robotAngle - angles.firstAngle) + P * Math.cos(robotAngle - angles.firstAngle) + rightX;
//
//            motorFrontLeft.setPower(v5);//1
//            motorFrontRight.setPower(v6);//2
//            motorBackLeft.setPower(v7);//3
//            motorBackRight.setPower(v8);//4
//
//        } else if (calibToggle == 0) { //regular drive
//            double P = Math.hypot(-gamepad1.left_stick_x, -gamepad1.left_stick_y);
//            double robotAngle = Math.atan2(-gamepad1.left_stick_y, -gamepad1.left_stick_x);
//            double rightX = -gamepad1.right_stick_x;
//            double sinRAngle = Math.sin(robotAngle);
//            double cosRAngle = Math.cos(robotAngle);
//
//            final double v1 = (P * sinRAngle) + (P * cosRAngle) + rightX;
//            final double v2 = (P * sinRAngle) - (P * cosRAngle) - rightX;
//            final double v3 = (P * sinRAngle) - (P * cosRAngle) + rightX;
//            final double v4 = (P * sinRAngle) + (P * cosRAngle) - rightX;
//
//            motorFrontRight.setPower(v1);
//            motorFrontLeft.setPower(v2);
//            motorBackRight.setPower(v3);
//            motorBackLeft.setPower(v4);
//        }
//
//        telemetry.addData("frontLeft: ", motorFrontLeft.getCurrentPosition());
//        telemetry.addData("frontRight ", motorFrontRight.getCurrentPosition());
//        telemetry.addData("backLeft: ", motorBackLeft.getCurrentPosition());
//        telemetry.addData("backRight ", motorBackRight.getCurrentPosition());
//
//
//        if (gamepad1.dpad_up) {
//            flywheel.setPower(1);
//        } else if (gamepad1.dpad_down) {
//                flywheel.setPower(-1);
//        } else {
//            flywheel.setPower(0);
//        }
//
//        if (gamepad1.y) {
//            barrier.setPosition(0.7);
//        }
//        if (gamepad1.b) {
//            barrier.setPosition(0.2);
//        }
//
//
//        if (gamepad1.right_bumper) {
//            collext.setPower(1);
//        } else if (gamepad1.right_trigger > 0.5) {
//            collext.setPower(-1);
//        } else {
//            collext.setPower(0);
//        }
//
//    }
//
//    private String composeTelemetry() {
//        // At the beginning of each telemetry update, grab a bunch of data
//        // from the IMU that we will then display in separate lines.
//        telemetry.addAction(new Runnable() {
//            @Override
//            public void run() {
//                // Acquiring the angles is relatively expensive; we don't want
//                // to do that in each of the three items that need that info, as that's
//                // three times the necessary expense.
//                angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.RADIANS);
//                gravity = imu.getGravity();
//            }
//        });
//
//        telemetry.addLine()
//                .addData("status", new Func<String>() {
//                    @Override
//                    public String value() {
//                        return imu.getSystemStatus().toShortString();
//                    }
//                })
//                .addData("calib", new Func<String>() {
//                    @Override
//                    public String value() {
//                        return imu.getCalibrationStatus().toString();
//                    }
//                });
//
//        telemetry.addLine()
//                .addData("heading", new Func<String>() {
//                    @Override
//                    public String value() {
//                        return formatAngle(angles.angleUnit, angles.firstAngle);
//                    }
//                })
//                .addData("roll", new Func<String>() {
//                    @Override
//                    public String value() {
//                        return formatAngle(angles.angleUnit, angles.secondAngle);
//                    }
//                })
//                .addData("pitch", new Func<String>() {
//                    @Override
//                    public String value() {
//                        return formatAngle(angles.angleUnit, angles.thirdAngle);
//                    }
//                });
//
//        telemetry.addLine()
//                .addData("grvty", new Func<String>() {
//                    @Override
//                    public String value() {
//                        return gravity.toString();
//                    }
//                })
//                .addData("mag", new Func<String>() {
//                    @Override
//                    public String value() {
//                        return String.format(Locale.getDefault(), "%.3f",
//                                Math.sqrt(gravity.xAccel * gravity.xAccel
//                                        + gravity.yAccel * gravity.yAccel
//                                        + gravity.zAccel * gravity.zAccel));
//                    }
//                });
//        return formatAngle(angles.angleUnit, angles.firstAngle);
//    }
//
//    String formatAngle(AngleUnit angleUnit, double angle) {
//        return formatRadians(AngleUnit.RADIANS.fromUnit(angleUnit, angle));
//    }
//
//    String formatRadians(double radians) {
//        return String.format(Locale.getDefault(), "%.1f", AngleUnit.RADIANS.normalize(radians));
//    } }