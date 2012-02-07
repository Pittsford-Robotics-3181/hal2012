/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.frc3181.yr2012.components;

import edu.wpi.first.wpilibj.Kinect;
import edu.wpi.first.wpilibj.Skeleton;
import edu.wpi.first.wpilibj.Skeleton.Joint;
import edu.wpi.first.wpilibj.Skeleton.tJointTypes;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.frc3181.yr2012.Hardware;

/**
 * @author Ben
 * @author Liam Middlebrook 2012
 * //TODO: Implement motion detection based on body proportions from the spine or head joint.
 * 
 */
public class KinectHelper {

    //Kinect Class from station;
    private Kinect kinect;
    private Skeleton kinectSkeleton;
    //helper array
    private Skeleton.tJointTypes[] joints = {tJointTypes.kHipCenter, tJointTypes.kSpine,
        tJointTypes.kShoulderCenter, tJointTypes.kHead, tJointTypes.kShoulderLeft,
        tJointTypes.kElbowLeft, tJointTypes.kWristLeft, tJointTypes.kHandLeft,
        tJointTypes.kShoulderRight, tJointTypes.kElbowRight, tJointTypes.kWristRight,
        tJointTypes.kHandRight, tJointTypes.kHipLeft, tJointTypes.kKneeLeft,
        tJointTypes.kAnkleLeft, tJointTypes.kFootLeft, tJointTypes.kHipRight,
        tJointTypes.kKneeRight, tJointTypes.kAnkleRight, tJointTypes.kFootRight};
   public int X = 0;
   public int Y = 1;
   public int Z = 2;
    /**
     * Initialize the Kinect device.
     */
    public void initKinect() {
        kinect = Hardware.kinect;
        kinect = Kinect.getInstance();
        kinectSkeleton = kinect.getSkeleton();
    }
    public boolean jointWithinRange(Joint joint1, Joint joint2, double range, int direction)
    {
        if(direction == X)
        {
            if(joint1.getX() >= joint2.getX() - range && joint1.getX() <= joint2.getX() + range)
            {
                return true;
            }
        }
        if(direction == Y)
        {
            if(joint1.getY() >= joint2.getY() - range && joint1.getY() <= joint2.getY() + range)
            {
                return true;
            }
        }
        if(direction == Z)
        {
            if(joint1.getZ() >= joint2.getZ() - range && joint1.getZ() <= joint2.getZ() + range)
            {
                return true;
            }
        }
        return false;
    }
    public Skeleton getSkeleton() {
        //updateSkeleton();
        Hardware.DSOut.say(1,"Tracking State: " + kinectSkeleton.GetTrackState().value);
         //SmartDashboard.putInt("track state", kinectSkeleton.GetTrackState().value);
        return kinectSkeleton;
    }

    public void updateSkeleton() {
        kinectSkeleton = kinect.getSkeleton();

        //SmartDashboard.putInt("track state", kinectSkeleton.GetTrackState().value);
    }

    /**
     * Returns a joint object when given a tJointTypes value.
     * @param jointID The joint ID of type tJointTypes.
     * @return Joint The corresponding joint.
     */
    public Joint getJoint(tJointTypes jointID) {
        return kinectSkeleton.GetJoint(jointID);
    }

    /**
     * Returns a joint object when given the integer index.
     * @param jointID The integer index of a joint.
     * @return The corresponding joint.
     */
    public Joint getJoint(int jointID) {
        return kinectSkeleton.GetJoint(joints[jointID]);
    }
}
