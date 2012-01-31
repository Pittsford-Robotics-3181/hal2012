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
 *
 * @author Liam Middlebrook 2012
 */
public class KinectHelper {
 
    //Kinect Class from station;
    private Kinect kinect;
    private Skeleton kinectSkeleton;
    /**
     * Initialize the Kinect device.
     */
    public void initKinect()
    {
        kinect = Hardware.kinect;
    }
    private Skeleton getSkeleton()
    {
        if(kinectSkeleton != null)
        {
        return kinectSkeleton;
        }
        return null;
    }
    private void updateSkeleton()
    {
        kinectSkeleton = kinect.getSkeleton();
    }
    /**
     * Returns a joint object when given a tJointTypes value.
     * @param jointID
     * @return Joint
     */
    public Joint getJoint(tJointTypes jointID)
    {        
        return kinectSkeleton.GetJoint(jointID);
    }
}
