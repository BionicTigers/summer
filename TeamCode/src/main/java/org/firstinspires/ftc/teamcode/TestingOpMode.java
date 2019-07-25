package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.ArrayList;

@TeleOp(name = "Traj Testing")
public class TestingOpMode extends OpMode {
    private DcMotorEx frontLeft;
    private double velocity;
    private float speed;
    private ElapsedTime elMili;
    private double start;
    private int startEncoderPosition;
    private float positionError;
    private float t;
    @Override
    public void init() {
        FtcDashboard dashboard = FtcDashboard.getInstance();
        Telemetry dashboardTelemetry = dashboard.getTelemetry();
        Trajectory trajectory = new Trajectory(12.80f, 100f, 200f, 20f);
        ArrayList<ArrayList<Float>> traj = trajectory.getTrajectory();
    }

    @Override
    public void loop() {

    }
}
