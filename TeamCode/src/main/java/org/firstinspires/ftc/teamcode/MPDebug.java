package org.firstinspires.ftc.teamcode;

import java.util.ArrayList;

public class MPDebug {
    public static void main(String args[]){
        Trajectory trajectory = new Trajectory(80,600,16,20);
        ArrayList<ArrayList<Float>> arrOfVectors =  trajectory.getTrajectory();
        for (int i = 0; i < arrOfVectors.size(); i++) {
            System.out.println(i / 1000f + ", " + arrOfVectors.get(i).get(0) + ", " + arrOfVectors.get(i).get(1));
        }
    }

}
