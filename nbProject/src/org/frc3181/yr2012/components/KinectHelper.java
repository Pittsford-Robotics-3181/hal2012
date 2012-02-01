/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.frc3181.yr2012.components;

import edu.wpi.first.wpilibj.Kinect;
import edu.wpi.first.wpilibj.Skeleton;
import edu.wpi.first.wpilibj.Skeleton.Joint;
import edu.wpi.first.wpilibj.Skeleton.tJointTypes;
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

    /**
     * Initialize the Kinect device.
     */
    public void initKinect() {
        kinect = Hardware.kinect;
    }

    private Skeleton getSkeleton() {
        updateSkeleton();
        return kinectSkeleton;
    }

    private void updateSkeleton() {
        kinectSkeleton = kinect.getSkeleton();
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
