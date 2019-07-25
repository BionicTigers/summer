package org.firstinspires.ftc.teamcode;

import java.util.ArrayList;

/**
 * Class Trajectotory creates and stores a Trajectory
 */
public class Trajectory {
    private static float rampdistance;
    private ArrayList<ArrayList<Float>> trajectory;

    Trajectory(float maxVelocity, float distance, float maxAcceleration, float jerk) {
        trajectory = fineIllCreateAGenMethod(maxVelocity, distance, maxAcceleration, jerk);

    }

    public ArrayList<ArrayList<Float>> getTrajectory() {
        return trajectory;
    }


    /**
     * this is  the method that is called by the constructor and either runs the scurve profile or the short profile
     *
     * @param maxVelocity
     * @param distance
     * @param maxAccel
     * @param jerk
     * @return
     */
    public static ArrayList<ArrayList<Float>>fineIllCreateAGenMethod(float maxVelocity, float distance, float maxAccel, float jerk){
        float velocity = 0;
        float dist = 0;
        float accel = 0;
        float t1VeloChange;
        ArrayList<Float> dataHolder = new ArrayList<>();
        //timeSlice 1

        ArrayList<ArrayList<Float>> trajAL= new ArrayList<ArrayList<Float>>();

        dataHolder.add(0,0f);
        dataHolder.add(0,0f);
        dataHolder.add(0,0f);
        while (accel<maxAccel&&velocity<(maxVelocity/2)){
            accel += jerk/1000f;
            velocity+=accel/1000f;
            dist+=velocity/1000f;
            dataHolder = new ArrayList<Float>();
            dataHolder.add(0,accel);
            dataHolder.add(1,velocity);
            dataHolder.add(2,dist);
            trajAL.add(dataHolder);
            if(dist>distance/2){
                return trajAL;
            }
        }
        t1VeloChange = velocity;
        while (velocity<(maxVelocity-t1VeloChange*2)){
            accel = maxAccel;
            velocity += accel/1000f;
            dist+=velocity/1000f;
            dataHolder = new ArrayList<Float>();
            dataHolder.add(0,accel);
            dataHolder.add(1,velocity);
            dataHolder.add(2,dist);
            trajAL.add(dataHolder);
            if(dist>distance/2){
                return trajAL;
            }
        }
        while (accel<maxAccel&&accel>0){
            accel -= jerk/1000f;
            velocity+=accel/1000f;
            dist+=velocity/1000f;
            dataHolder = new ArrayList<Float>();
            dataHolder.add(0,accel);
            dataHolder.add(1,velocity);
            dataHolder.add(2,dist);
            trajAL.add(dataHolder);
            if(dist>distance/2){
                return trajAL;
            }
        }
        float t4TotalDistance = (distance-(dist*2));
        float t4DistanceTracker = 0;
        while(t4TotalDistance>t4DistanceTracker){
            distance+=velocity/1000f;
            dataHolder = new ArrayList<Float>();
            dataHolder.add(0,accel);
            dataHolder.add(1,velocity);
            dataHolder.add(2,dist);
            trajAL.add(dataHolder);
            t4DistanceTracker+=velocity/1000f;
        }
        return  trajAL;
    }
}