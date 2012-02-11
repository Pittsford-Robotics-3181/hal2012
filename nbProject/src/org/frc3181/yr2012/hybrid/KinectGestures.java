package org.frc3181.yr2012.hybrid;

import edu.wpi.first.wpilibj.Kinect;
import edu.wpi.first.wpilibj.Skeleton;
import edu.wpi.first.wpilibj.Skeleton.Joint;
import edu.wpi.first.wpilibj.Skeleton.tJointTypes;
import org.frc3181.yr2012.Hardware;

/**
 * Has replaced KinectHelper. Helper methods at top, gestures at bottom.
 * @author Ben
 * @author Liam
 * //TODO: Implement motion detection based on body proportions from the spine or head joint.
 */
public class KinectGestures {

    //Kinect Class from station;
    private Kinect kinect;
    //helper array
    private Skeleton.tJointTypes[] joints = {tJointTypes.kHipCenter, tJointTypes.kSpine,
        tJointTypes.kShoulderCenter, tJointTypes.kHead, tJointTypes.kShoulderLeft,
        tJointTypes.kElbowLeft, tJointTypes.kWristLeft, tJointTypes.kHandLeft,
        tJointTypes.kShoulderRight, tJointTypes.kElbowRight, tJointTypes.kWristRight,
        tJointTypes.kHandRight, tJointTypes.kHipLeft, tJointTypes.kKneeLeft,
        tJointTypes.kAnkleLeft, tJointTypes.kFootLeft, tJointTypes.kHipRight,
        tJointTypes.kKneeRight, tJointTypes.kAnkleRight, tJointTypes.kFootRight};
    public static final int X = 0;
    public static final int Y = 1;
    public static final int Z = 2;

    /**
     * Initialize the Kinect device.
     */
    public void initKinect() {
        kinect = Kinect.getInstance();
    }

    /**
     * Check if given joints are less than given range apart along given coordinate axis.
     * @param joint1 The first joint. Joints are interchangeable.
     * @param joint2 The second joint. Joints are interchangeable.
     * @param range The maximum range allowed.
     * @param direction Which of the x, y, and z axes to measure along.
     * @return Whether the joints are close enough.
     */
    public boolean jointWithinRange(Joint joint1, Joint joint2, double range, int direction) {
        switch (direction) {
            case X:
                return Math.abs(joint1.getX() - joint2.getX()) <= range;
            case Y:
                return Math.abs(joint1.getY() - joint2.getY()) <= range;
            case Z:
                return Math.abs(joint1.getZ() - joint2.getZ()) <= range;
            default: //invalud direction passed
                return false;
        }
    }

    /**
     * Gets the skeleton from the Kinect.
     * @return The skeleton.
     */
    public Skeleton getSkeleton() {
        return kinect.getSkeleton();
    }

    /**
     * Returns a joint object when given a tJointTypes ID.
     * @param jointID The joint ID of type tJointTypes.
     * @return Joint The corresponding joint.
     */
    public Joint getJoint(tJointTypes jointID) {
        return kinect.getSkeleton().GetJoint(jointID);
    }

    /**
     * Returns a joint object when given the integer index.
     * @param jointID The integer index of a joint.
     * @return The corresponding joint.
     */
    public Joint getJoint(int jointID) {
        Hardware.ballJoystick.getThrottle();
        return kinect.getSkeleton().GetJoint(joints[jointID]);
    }

    
    
    
    /* * * * * * * * * * * * * * * * * * * * * * * * * * *\
     *    ___  ____  ___  ____  __  __  ____  ____  ___  *
     *   / __)( ___)/ __)(_  _)(  )(  )(  _ \( ___)/ __) *
     *  ( (_-. )__) \__ \  )(   )(__)(  )   / )__) \__ \ *
     *   \___/(____)(___/ (__) (______)(_)\_)(____)(___/ *
     *                                                   *
     * This is the meat of our class, the functions that *
     * read certain gestures from the Kinect and tell us *
     * what we should be doing.                          *
    \* * * * * * * * * * * * * * * * * * * * * * * * * * */

    
    
    /* Check if the robot should be driving, i.e. both hands above head.
     * @return Whether to drive.
     */
    public boolean isDriving() {
        return (getSkeleton().GetHandLeft().getY() > getSkeleton().GetHead().getY()) && (getSkeleton().GetHandRight().getY() > getSkeleton().GetHead().getY());
    }

    /**
     * If driving and both hands are back.
     * @return Whether to drive forward.
     */
    public boolean getDriveForward() {
        if (isDriving()) {
            return (getSkeleton().GetHandLeft().getZ() > getSkeleton().GetHead().getZ() - .25) && (getSkeleton().GetHandRight().getZ() > getSkeleton().GetHead().getZ() - .25);
        }
        return false;
    }

    /**
     * If driving and both hands are forward.
     * @return Whether to drive back.
     */
    public boolean getDriveBackward() {
        if (isDriving()) {
            return (getSkeleton().GetHandLeft().getZ() < getSkeleton().GetHead().getZ() - .25) && (getSkeleton().GetHandRight().getZ() < getSkeleton().GetHead().getZ() - .25);
        }
        return false;
    }

    /**
     * If driving, left hand is back, and right hand is forward.
     * @return Whether to turn left.
     */
    public boolean getTurnLeft() {
        if (isDriving()) {
            return (getSkeleton().GetHandLeft().getZ() > getSkeleton().GetHead().getZ() - .25) && (getSkeleton().GetHandRight().getZ() < getSkeleton().GetHead().getZ() - .25);
        }
        return false;
    }

    /**
     * If driving, left hand is forward, and right hand is back.
     * @return Whether to turn right.
     */
    public boolean getTurnRight() {
        if (isDriving()) {
            return (getSkeleton().GetHandLeft().getZ() < getSkeleton().GetHead().getZ() - .25) && (getSkeleton().GetHandRight().getZ() > getSkeleton().GetHead().getZ() - .25);
        }
        return false;
    }

    /**
     * If left ankle is out enough.
     * @return Whether to shoot.
     */
    public boolean getShoot() {
        return getSkeleton().GetAnkleLeft().getX() < -.7;
    }
}