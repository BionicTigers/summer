package org.firstinspires.ftc.teamcode;

public class Trajectory {
    private static float rampdistance;
    private float[][] trajectory;

    Trajectory(float maxVelocity, float distance, float maxAcceleration, float jerk){
        trajectory = fineIllCreateAGeneralGenClass(maxVelocity,distance,maxAcceleration,jerk);

    }
    public float[][] getTrajectory(){
        return trajectory;
    }




    public static float[][] fineIllCreateAGeneralGenClass(float maxVelocity, float distance, float maxAcceleration, float jerk) {

        double timeToPeakVelocity = Math.sqrt(distance/maxAcceleration);
        float[][] tragectory;
        if (timeToPeakVelocity*maxAcceleration<maxVelocity) {
            tragectory = okButwhatIfItsShort(maxVelocity, distance, maxAcceleration, jerk);
        } else {
            tragectory = S_Curve(maxVelocity, distance, maxAcceleration, jerk);
        }
        return tragectory;
    }

    public static float[][] okButwhatIfItsShort(float maxVelocity, float distance, float maxAcceleration, float jerk) {
        float velocity = 0;
        float position = 0;
        float acceleration = 0;
        float t1Time = maxAcceleration / jerk;

        float[] accel;
        if(Math.sqrt((Math.sqrt(distance/maxAcceleration))/jerk)*jerk>maxAcceleration){
            accel = Trapazoid(maxAcceleration,maxVelocity,jerk);
        }else {accel = Triangle(maxVelocity,jerk);}
        float[][] tragectory = new float[(int) (accel.length*2)][3];

        int t1 = 0;
        for (int i = 0; i<accel.length; i++)
        {
            acceleration = accel[i];
            tragectory[t1][0] = acceleration;
            velocity = velocity + acceleration * (1f / 1000f);
            position += velocity / 1000;
            tragectory[t1][1] = velocity;
            tragectory[t1][2]=position;
            t1++;
        }
        for (int i = 0; i < accel.length; i++) {
            acceleration = accel[i];
            tragectory[t1][0] = acceleration;
            velocity = velocity - acceleration * (1f / 1000f);
            position += velocity / 1000;
            tragectory[t1][1] = velocity;
            tragectory[t1][2]= position;
            t1++;

        }
        return tragectory;
    }

    private static float[][] S_Curve(float maxVelocity, float distance, float maxAcceleration, float jerk) {

        float velocity = 0;
        float position = 0;
        float acceleration = 0;
        float[] accel;
        if(Math.sqrt(maxVelocity/jerk)*jerk>maxAcceleration){
            accel = Trapazoid(maxAcceleration,maxVelocity,jerk);
        }else {accel = Triangle(maxVelocity,jerk);}

        float t4Displacement = distance - (rampdistance* 2);
        float t4Time = t4Displacement/maxVelocity;
        float[][] tragectory = new float[(int) (accel.length*2+t4Time*1000f+5)][2];

        int t1 = 0;

        for (int i = 0; i<accel.length; i++)
        {
            acceleration = accel[i];
            tragectory[t1][0] = acceleration;
            velocity = velocity + acceleration * (1f / 1000f);
            tragectory[t1][1] = velocity;
            position += velocity / 1000;
            t1++;
        }
        for (int i = 0; i < t4Time * 1000f; i++) {
            tragectory[t1][0] = 0.0f;
            tragectory[t1][1] = maxVelocity;
            position += velocity / 1000;
            t1++;
        }
        for (int i = 0; i < accel.length; i++) {
            acceleration = accel[i];
            tragectory[t1][0] = acceleration;
            velocity = velocity - acceleration * (1f / 1000f);
            tragectory[t1][1] = velocity;
            position += velocity / 1000;
            t1++;

        }


        return tragectory;
    }

    private static float[] Trapazoid(float maxVelocity, float distance, float maxAcceleration) {
        float rampTime = maxVelocity / maxAcceleration;
        float rampDistance = rampTime * maxAcceleration / 2;
        float sustainDistance = distance - rampDistance * 2;

        float sustainTime = sustainDistance / maxVelocity;
        int timeToCompleteMili = (int) ((rampTime * 2 + sustainTime) * 1000) + 1;
        float[] tragectory = new float[timeToCompleteMili];
        int t = 0;
        float velocity = 0;
        float position = 0;

        for (int i = 0; i < rampTime * 1000; i++) {
            velocity = (i * maxAcceleration) / 1000;
            position += velocity / 1000;
            tragectory[t] = velocity;
            //tragectory[t][1] = position;
            t++;
        }
        for (int i = 0; i < sustainTime * 1000; i++) {
            velocity = 6;
            position += velocity / 1000;
            tragectory[t] = velocity;
            // tragectory[t][1] = position;
            t++;

        }
        for (int i = 0; i < rampTime * 1000; i++) {
            velocity = velocity - (maxAcceleration / 1000);
            position += velocity / 1000;
            tragectory[t] = velocity;
            //tragectory[t] = position;
            t++;
        }
        rampdistance=position;

        return tragectory;
    }

    public static float[] Triangle(float distance, float MaxAcceleration) {
        int timeToCompleteMili = (int) (distance * 2f / MaxAcceleration * 1000);
        float position = 0;
        float velocity = 0;
        float[] Tragectory = new float[timeToCompleteMili];
        for (int t = 0; t < (timeToCompleteMili) / 2; t++) {
            velocity = t * MaxAcceleration / 1000;
            Tragectory[t] = velocity;
            position += velocity / 1000;
        }
        int j = 0;
        for (int t = (int) (timeToCompleteMili) / 2; t < (timeToCompleteMili); t++) {
            velocity = velocity - MaxAcceleration / 1000;
            Tragectory[t] = velocity;
            j++;
            position += velocity / 1000;
        }
        rampdistance=position;
        return Tragectory;
    }
}