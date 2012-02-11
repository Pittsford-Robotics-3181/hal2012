package org.frc3181.yr2012;

import edu.wpi.first.wpilibj.Kinect;
import edu.wpi.first.wpilibj.KinectStick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Skeleton.Joint;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This is our robot's drive system, which implements mecanum wheels.
 * @author Robbie Markwick
 * @author Ben
 * @author Liam
 */
public class DriveSystem extends RobotDrive {

    private boolean slow;
    private boolean stop;
    //KinectHelper kinect = new KinectHelper();
    Kinect theKinect;


    /**
     * Constructor.
     * @param frontLeftMotor
     * @param rearLeftMotor
     * @param frontRightMotor
     * @param rearRightMotor 
     */
    public DriveSystem(SpeedController frontLeftMotor, SpeedController rearLeftMotor, SpeedController frontRightMotor, SpeedController rearRightMotor) {
        super(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor);
        slow = false;
        stop = false;
        //kinect.initKinect();
        theKinect = Kinect.getInstance();
    }

    /**
     * Drive method for Mecanum wheeled robots.
     * A method for driving with Mecanum wheeled robots. There are 4 wheels on
     * the robot, arranged so that the front and back wheels are toed in 45
     * degrees. When looking at the wheels from the top, the roller axles should
     * form an X across the robot.
     * @param magnitude The speed that the robot should drive in a given direction.
     * @param direction The direction the robot should drive in degrees. The direction and magnitude are independent of the rotation rate.
     * @param rotation The rate of rotation for the robot that is completely independent of the magnitude or direction. [-1.0..1.0]
     */
    public void mecanumDrive(double magnitude, double direction, double rotation) {
        SmartDashboard.putInt("Skeleton track state", theKinect.getSkeleton().GetTrackState().value);
        
        if (stop) {
            magnitude = direction = rotation = 0;
        } // <editor-fold defaultstate="collapsed" desc="Kinect Drive Code">
        else if (theKinect.getSkeleton().GetAnkleLeft().getX() < -.7){
            Hardware.DSOut.say(3, "Kinect: shoot");
        }
        //drive forwards
        else if ((theKinect.getSkeleton().GetHandLeft().getY() > theKinect.getSkeleton().GetHead().getY()) && (theKinect.getSkeleton().GetHandRight().getY() > theKinect.getSkeleton().GetHead().getY())) {
            if ((theKinect.getSkeleton().GetHandLeft().getZ() > theKinect.getSkeleton().GetHead().getZ() - .25) && (theKinect.getSkeleton().GetHandRight().getZ() > theKinect.getSkeleton().GetHead().getZ() - .25)) {

                magnitude = .25;
                direction = -180;//*/
                Hardware.DSOut.say(3, "Kinect: forward");
            } else {
                magnitude = .25;
                direction = 0;//  */
                Hardware.DSOut.say(3, "Kinect: backward");
            }

        } // </editor-fold>
        else {
            Hardware.DSOut.say(3, "");
            //perfect strafe
            // backwards
            if (Hardware.driveJoystick.getRawButton(2)) {
                magnitude = Hardware.driveJoystick.getTwist() / 3 + .333;
                direction = -180;
                //forwards
            } else if (Hardware.driveJoystick.getRawButton(3)) {
                magnitude = Hardware.driveJoystick.getTwist() / 3 + .333;
                direction = 0;
                //left
            } else if (Hardware.driveJoystick.getRawButton(4)) {
                magnitude = Hardware.driveJoystick.getTwist() / 3 + .333;
                direction = -90;
                //right
            } else if (Hardware.driveJoystick.getRawButton(5)) {
                magnitude = Hardware.driveJoystick.getTwist() / 3 + .333;
                direction = 90;
            }

            //drive at half speed if trigger is pulled
            if (Hardware.driveJoystick.getTrigger()) {
                magnitude *= .5;
                rotation *= .5;
            }
            //drive at half speed if the variable says so
            if (slow) {
                magnitude *= .5;
                rotation *= .5;
            }

            //ramp
            
        }

        //analyze values and correct if necessary
        //make magnitude and rotation zero if they are small enough
        magnitude = Utils.checkForSmall(magnitude, .15);
        rotation = Utils.checkForSmall(rotation, .15);
        //constrain values to range
        magnitude = Math.max(Math.min(magnitude, 1.0), 0.0);
        rotation = Math.max(Math.min(rotation, .5), -.5);

        //call the drive method inherited from RobotDrive
        mecanumDrive_Polar(magnitude, direction, rotation);

        Hardware.DSOut.say(4, "Magnitude: " + magnitude);
        Hardware.DSOut.say(5, "Direction: " + direction);
        Hardware.DSOut.say(6, "Rotation:  " + rotation);
        SmartDashboard.putDouble("Magnitude", magnitude * 100);
        SmartDashboard.putDouble("Direction", (direction + 360) % 360);

    }

    /**
     * Converts buttons 8 and 9 on joystick to rotation. There are four cases:
     * neither pushed: no rotation
     * just 8 pushed: counterclockwise rotation
     * just 9 pushed: clockwise rotation
     * both pushed: counterclockwise rotation
     * @return The calculated rotation. Negative is counterclockwise, positive is clockwise.
     */
    private double calculateRotation() {
        if (Hardware.driveJoystick.getRawButton(8)) {
            return -(Hardware.driveJoystick.getTwist() / 4 + .25);
        }

        if (Hardware.driveJoystick.getRawButton(9)) {
            return +(Hardware.driveJoystick.getTwist() / 4 + .25);
        }

        return 0;
    }

    /**
     * This method should be called to make the robot drive with joystick input.
     */
    public void mecanumDrive() {
        double magnitude = Hardware.driveJoystick.getMagnitude();
        double direction = Hardware.driveJoystick.getDirectionDegrees();
        double rotation = calculateRotation();
        mecanumDrive(magnitude, direction, rotation); //robot drives
    }

    /**
     * Slows the robot.
     * @param b Whether the robot should slow.
     */
    public void setSlow(boolean b) {
        slow = b;
    }

    /**
     * Stops the robot.
     * @param b Whether the robot should stop.
     */
    public void setStop(boolean b) {
        stop = b;
    }
}
