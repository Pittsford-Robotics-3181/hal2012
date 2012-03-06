package org.frc3181.yr2012.hybrid;

import org.frc3181.yr2012.Hardware;

/**
 * Hybrid drive with Kinect.
 * @author Ben
 * @author Liam
 */
public class HybridKinect {

    public static void init(){}
    public static void run() {
        double magnitude = 0;
        double direction = 0;
        double rotation = 0;
        Hardware.ballLauncher.shootAtSpeed(-1.0); //START ROLLING THE SHOOTER MOTOR IN PREPERATION TO SHOOT

        if (KinectGestures.getTipDown()) {
            Hardware.bridgeTip.moveTipperDown(5);
        } else if (KinectGestures.getTipUp()) {
            Hardware.bridgeTip.moveTipperUp(5);
        } else {
            Hardware.tip.set(0);
        }
        Hardware.stopper.stopperControllerHybrid(KinectGestures.getShoot());

        if (KinectGestures.isDriving()) {
            if (KinectGestures.getStrafeLeft()) {
                magnitude = .5;
                direction = -90;
                Hardware.DSOut.say(3, "Kinect: strafe left");
            } else if (KinectGestures.getStrafeRight()) {
                magnitude = .5;
                direction = 90;
                Hardware.DSOut.say(3, "Kinect: strafe right");
            } else if (KinectGestures.getDriveForward()) {
                magnitude = .5;
                direction = 0;
                Hardware.DSOut.say(3, "Kinect: forward");
            } else if (KinectGestures.getDriveBackward()) {
                magnitude = .5;
                direction = 180;
                Hardware.DSOut.say(3, "Kinect: backward");
            } else if (KinectGestures.getTurnLeft()) {
                rotation = -.5;
                Hardware.DSOut.say(3, "Kinect: turn left");
            } else if (KinectGestures.getTurnRight()) {
                rotation = .5;
                Hardware.DSOut.say(3, "Kinect: turn right");
            } else {
                Hardware.DSOut.say(3, "Kinect: driving?");
            }
        } else {
            Hardware.DSOut.say(3, "Kinect: not moving");
        }
        //*/
        Hardware.driveSystem.mecanumDrive(magnitude, direction, rotation);
    }
}
