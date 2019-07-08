//
//        package org.firstinspires.ftc.teamcode;
//
//        import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//        import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//        import com.qualcomm.robotcore.hardware.AnalogInput;
//
///**
// * The relationship between rotation and voltage is linear, so we will return a number betweeen
// * 0 and 100 to tell the percentage of how far the knob has rotated.
// */
//@TeleOp(name = "Sensor: Generic Potentiometer", group = "Sensor")
//
//public class potenimeter extends LinearOpMode {
//    @Override
//    public void runOpMode() throws InterruptedException {
//
//        AnalogInput potentiometer;
//
//        potentiometer = hardwareMap.analogInput.get("potentiometer");
//
//        // wait for the start button to be pressed.
//        waitForStart();
//
//        while (opModeIsActive()) {
//            // Reading voltage
//            double voltreading = (float) potentiometer.getVoltage();
//            //convert voltage to distance (cm)
//
//            double percentTurned = voltreading/5 * 100;
//
//            telemetry.addData("time", "elapsed time: " + Double.toString(this.time));
//            telemetry.addData("raw val", "voltage:  " + Double.toString(voltreading));
//            // this is our calculated value
//            telemetry.addData("PercentRot", "percent: " + Double.toString(percentTurned));
//            telemetry.update();
//        }
//    }
//}
//
