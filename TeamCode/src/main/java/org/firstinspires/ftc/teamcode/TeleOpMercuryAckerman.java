//package org.firstinspires.ftc.teamcode;
//
//
//import com.qualcomm.robotcore.eventloop.opmode.OpMode;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.util.ElapsedTime;
//
//import java.math.BigDecimal;
//import java.math.RoundingMode;
//
//
//@TeleOp(name="Mercury Ackerman TeleOp", group="Outreach")
///**
// * This class is for our outreach robot that uses mecanum wheels.
// * This outreach bot was a prototype drivetrain to our two iterations of our competition robot from last year, however with a different kind of drive methods
// * This class allows the outreach bot to be very fast and efficient
// */
////
//
//public class TeleOpMercuryAckerman extends OpMode {
//
//    //DRIVETRAIN\\
//    private DcMotor frontRight;
//    private DcMotor frontLeft;
//    private DcMotor backLeft;
//    private DcMotor backRight;
//
//    //Variables//
//    private double leftPower, rightPower;
//    private double leftStick, rightStick, gasPedal;
//    private double coarseDiff, fineDiff;
//    private double calibToggle;
//    private int driveSpeed, driveMode;
//
//    //Objects//
//    public ElapsedTime runtime = new ElapsedTime();
//
//
//    public void init() {
//
//        //DRIVETRAIN\\
//        frontRight = hardwareMap.dcMotor.get("frontRight");
//        frontLeft = hardwareMap.dcMotor.get("frontLeft");
//        backRight = hardwareMap.dcMotor.get("backRight");
//        backLeft = hardwareMap.dcMotor.get("backLeft");
//
//        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
//        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
//        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
//        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
//
//        backRight.setDirection(DcMotor.Direction.REVERSE);
//        frontRight.setDirection(DcMotor.Direction.REVERSE);
//
//        //Variables//
//        calibToggle = 0;
//        driveSpeed = 0;
//        driveMode = 0;
//
//        //Speed Offsets//
//        coarseDiff = 1.0;
//        fineDiff = 0.5;
//    }
//
//
//    public void loop() {
//
//        // TOGGLE BUTTONS //
//        if (gamepad1.a && (runtime.seconds() > calibToggle)) {
//            calibToggle = runtime.seconds() + 1;
//            ++driveSpeed;
//        }
//        if (gamepad1.x) {
//            driveMode = 0;
//        }
//        if (gamepad1.y) {
//            driveMode = 1;
//        }
//        if (gamepad1.b) {
//            driveMode = 2;
//        }
//
//        // DIFFERENT DRIVE MODES //
//        if (driveMode == 0) {
//            // ARCADE DRIVE //
//            leftStick = gamepad1.left_stick_y;
//            rightStick = gamepad1.right_stick_x;
//
//            //Left Side
//            if (Math.abs(rightStick) > 0.5) {
//                leftPower = leftStick / 2 - rightStick / 2;
//            } else {
//                leftPower = leftStick - rightStick / 2;
//            }
//
//            //Right Side
//            if (Math.abs(rightStick) > 0.5) {
//                rightPower = leftStick / 2 + rightStick / 2;
//            } else {
//                rightPower = leftStick + rightStick / 2;
//            }
//
//            if (driveSpeed % 2 == 0) {
//                telemetry.addData("Mode: ", "ARCADE");
//                telemetry.addData("Speed: ", "NORMAL");
//                telemetry.addData("Stick: ", "X = " + round(rightStick) + ", Y = " + round(leftStick));
//                telemetry.addData("Power: ", "L = " + round(leftPower) + ", R = " + round(rightPower));
//
//                backLeft.setPower(leftPower * coarseDiff);
//                backRight.setPower(rightPower * coarseDiff);
//                frontLeft.setPower(leftPower * coarseDiff);
//                frontRight.setPower(rightPower * coarseDiff);
//            } else {
//                telemetry.addData("Mode: ", "ARCADE");
//                telemetry.addData("Speed: ", "SLOW");
//                telemetry.addData("Stick: ", "X = " + round(rightStick) + ", Y = " + round(leftStick));
//                telemetry.addData("Power: ", "L = " + round(leftPower) + ", R = " + round(rightPower));
//
//                backLeft.setPower(leftPower * fineDiff);
//                backRight.setPower(rightPower * fineDiff);
//                frontLeft.setPower(leftPower * fineDiff);
//                frontRight.setPower(rightPower * fineDiff);
//            }
//        } else if (driveMode == 1) {
//            /// TANK DRIVE ///
//            leftPower = gamepad1.left_stick_y;
//            rightPower = gamepad1.right_stick_y;
//
//            if (driveSpeed % 2 == 0) {
//                telemetry.addData("Mode: ", "TANK");
//                telemetry.addData("Speed: ", "NORMAL");
//                telemetry.addData("Stick: ", "X = " + round(rightStick) + ", Y = " + round(leftStick));
//                telemetry.addData("Power: ", "L = " + round(leftPower) + ", R = " + round(rightPower));
//
//                backLeft.setPower(leftPower * coarseDiff);
//                backRight.setPower(rightPower * coarseDiff);
//                frontLeft.setPower(leftPower * coarseDiff);
//                frontRight.setPower(rightPower * coarseDiff);
//            } else {
//                telemetry.addData("Mode: ", "TANK");
//                telemetry.addData("Speed: ", "SLOW");
//                telemetry.addData("Stick: ", "X = " + round(rightStick) + ", Y = " + round(leftStick));
//                telemetry.addData("Power: ", "L = " + round(leftPower) + ", R = " + round(rightPower));
//
//                backLeft.setPower(leftPower * fineDiff);
//                backRight.setPower(rightPower * fineDiff);
//                frontLeft.setPower(leftPower * fineDiff);
//                frontRight.setPower(rightPower * fineDiff);
//            }
//        } else if (driveMode == 2) {
//            /// ACKERMAN DRIVE ///
//            leftStick = (-gamepad1.left_stick_x);
//
//            gasPedal = (gamepad1.left_trigger - gamepad1.right_trigger);
//
//            //Left Side
//            if (Math.abs(leftStick) > 0.5) {
//                leftPower = gasPedal / 2 + leftStick / 2;
//            } else {
//                leftPower = gasPedal + leftStick / 2;
//            }
//
//            //Right Side
//            if (Math.abs(leftStick) > 0.5) {
//                rightPower = gasPedal / 2 - leftStick / 2;
//            } else {
//                rightPower = gasPedal - leftStick / 2;
//            }
//
//            if (driveSpeed % 2 == 0) {
//                telemetry.addData("Mode: ", "ACKERMAN");
//                telemetry.addData("Speed: ", "NORMAL");
//                telemetry.addData("Stick: ", "X = " + round(leftStick) + ", G: " + round(gasPedal));
//                telemetry.addData("Power: ", "L = " + round(leftPower) + ", R = " + round(rightPower));
//
//                backLeft.setPower(leftPower * coarseDiff);
//                backRight.setPower(rightPower * coarseDiff);
//                frontLeft.setPower(leftPower * coarseDiff);
//                frontRight.setPower(rightPower * coarseDiff);
//            } else {
//                telemetry.addData("Mode: ", "ACKERMAN");
//                telemetry.addData("Speed: ", "SLOW");
//                telemetry.addData("Stick: ", "L = " + round(leftStick) + ", G: " + round(gasPedal));
//                telemetry.addData("Power: ", "L = " + round(leftPower) + ", R = " + round(rightPower));
//
//                backLeft.setPower(leftPower * fineDiff);
//                backRight.setPower(rightPower * fineDiff);
//                frontLeft.setPower(leftPower * fineDiff);
//                frontRight.setPower(rightPower * fineDiff);
//            }
//        }
//    }
//
//    private static double round(double value) { //Allows telemetry to display nicely
//        BigDecimal bd = new BigDecimal(value);
//        bd = bd.setScale(3, RoundingMode.HALF_UP);
//        return bd.doubleValue();
//    }
//}