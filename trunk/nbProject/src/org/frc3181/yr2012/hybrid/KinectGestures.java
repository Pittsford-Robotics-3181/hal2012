package org.frc3181.yr2012.hybrid;

import com.sun.squawk.util.MathUtils;
import edu.wpi.first.wpilibj.Kinect;
import edu.wpi.first.wpilibj.Skeleton;
import edu.wpi.first.wpilibj.Skeleton.Joint;
import edu.wpi.first.wpilibj.Skeleton.tJointTypes;
import org.frc3181.yr2012.Hardware;

/**
 * Our Kinect code, so you don't have to. Helper methods at top, gestures at bottom.
 * @author Ben
 * @author Liam
 * TODO: Implement motion detection based on body proportions from the spine or head joint.
 * TODO: Change the gestures to things that work consistently.
 */
public class KinectGestures {

    //Kinect Class from station;
    private static Kinect kinect;
    //helper array
    private static Skeleton.tJointTypes[] joints = {tJointTypes.kHipCenter, tJointTypes.kSpine,
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
    public static void initKinect() {
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
    public static boolean jointWithinRange(Joint joint1, Joint joint2, double range, int direction) {
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
    public static Skeleton getSkeleton() {
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
    public static boolean isDriving() {
        return (getSkeleton().GetHandLeft().getY() > getSkeleton().GetHead().getY()) && (getSkeleton().GetHandRight().getY() > getSkeleton().GetHead().getY());
    }

    /**
     * If driving and both hands are forward.
     * @return Whether to drive forward.
     */
    public static boolean getDriveForward() {
        if (isDriving()) {
            return (getSkeleton().GetHandLeft().getZ() < getSkeleton().GetHead().getZ() - .25) && (getSkeleton().GetHandRight().getZ() < getSkeleton().GetHead().getZ() - .25);
        }
        return false;
    }

    /**
     * If driving and both hands are back.
     * @return Whether to drive back.
     */
    public static boolean getDriveBackward() {
        if (isDriving()) {
            return (getSkeleton().GetHandLeft().getZ() > getSkeleton().GetHead().getZ() - .25) && (getSkeleton().GetHandRight().getZ() > getSkeleton().GetHead().getZ() - .25);
        }
        return false;
    }

    /**
     * If driving, left hand is back, and right hand is forward.
     * @return Whether to turn left.
     */
    public static boolean getTurnLeft() {
        if (isDriving()) {
            return (getSkeleton().GetHandLeft().getZ() > getSkeleton().GetHead().getZ() - .25) && (getSkeleton().GetHandRight().getZ() < getSkeleton().GetHead().getZ() - .25);
        }
        return false;
    }

    /**
     * If driving, left hand is forward, and right hand is back.
     * @return Whether to turn right.
     */
    public static boolean getTurnRight() {
        if (isDriving()) {
            return (getSkeleton().GetHandLeft().getZ() < getSkeleton().GetHead().getZ() - .25) && (getSkeleton().GetHandRight().getZ() > getSkeleton().GetHead().getZ() - .25);
        }
        return false;
    }

    /**
     * If driving, left hand and right hand are both left of head.
     * @return Whether to strafe left.
     */
    public static boolean getStrafeLeft() {
        if (isDriving()) {
            return (getSkeleton().GetHandLeft().getX() < getSkeleton().GetHead().getX()) && (getSkeleton().GetHandRight().getX() < getSkeleton().GetHead().getX());
        }
        return false;
    }

    /**
     * If driving, left hand and right hand are both left of head.
     * @return Whether to strafes right.
     */
    public static boolean getStrafeRight() {
        if (isDriving()) {
            return (getSkeleton().GetHandLeft().getX() > getSkeleton().GetHead().getX()) && (getSkeleton().GetHandRight().getX() > getSkeleton().GetHead().getX());
        }
        return false;
    }

    /**
     * If left ankle is out enough.
     * @return Whether to shoot.
     */
    public static boolean getShoot() {
        return getSkeleton().GetAnkleLeft().getX() < -.7;
    }

    /**NEWNEWNEWNEWNEWNEWNEWNEWNEWNEWNEWNEW**/
    /**
     * This method returns the angle (in degrees) of the vector pointing from Origin to Measured
     * projected to the XY plane. If the mirrored parameter is true the vector is flipped about the Y-axis.
     * Mirroring is used to avoid the region where the atan2 function is discontinuous
     * @param origin The Skeleton Joint to use as the origin point
     * @param measured The Skeleton Joint to use as the endpoint of the vector
     * @param mirrored Whether to mirror the X coordinate of the joint about the Y-axis
     * @return The angle in degrees
     */
    public static double AngleXY(Skeleton.Joint origin, Skeleton.Joint measured, boolean mirrored) {
        return Math.toDegrees(MathUtils.atan2(measured.getY() - origin.getY(),
                (mirrored) ? (origin.getX() - measured.getX()) : (measured.getX() - origin.getX())));
    }

    /**
     * This method takes an input, an input range, and an output range,
     * and uses them to scale and constrain the input to the output range
     * @param input The input value to be manipulated
     * @param inputMin The minimum value of the input range
     * @param inputMax The maximum value of the input range
     * @param outputMin The minimum value of the output range
     * @param outputMax The maximum value of the output range
     * @return The output value scaled and constrained to the output range
     */
    public static double CoerceToRange(double input, double inputMin, double inputMax, double outputMin, double outputMax) {
        /* Determine the center of the input range and output range */
        double inputCenter = Math.abs(inputMax - inputMin) / 2 + inputMin;
        double outputCenter = Math.abs(outputMax - outputMin) / 2 + outputMin;

        /* Scale the input range to the output range */
        double scale = (outputMax - outputMin) / (inputMax - inputMin);

        /* Apply the transformation */
        double result = (input + -inputCenter) * scale + outputCenter;

        /* Constrain to the output range */
        return Math.max(Math.min(result, outputMax), outputMin);
    }

    public static double getLeftArmValue() {
        return CoerceToRange(AngleXY(kinect.getSkeleton().GetShoulderLeft(), kinect.getSkeleton().GetWristLeft(), true), -70, 70, -1, 1);
    }

    public static double getRightArmValue() {
        return CoerceToRange(AngleXY(kinect.getSkeleton().GetShoulderRight(), kinect.getSkeleton().GetWristRight(), false), -70, 70, -1, 1);
    }
}
