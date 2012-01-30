package org.frc3181.yr2012;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;

/**
 * This is our robot's drive system, which implements mecanum wheels.
 * @author Robbie Markwick
 * @author Ben
 * @author Liam
 */
public class DriveSystem extends RobotDrive {

    private boolean slow;
    private boolean stop;

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
    }

    /**
     * Drive method for Mecanum wheeled robots. A method for driving with
     * Mecanum wheeled robots. There are 4 wheels on the robot, arranged so that
     * the front and back wheels are toed in 45 degrees. When looking at the
     * wheels from the top, the roller axles should form an X across the robot. 
     * @param magnitude The speed that the robot should drive in a given direction.
     * @param direction The direction the robot should drive in degrees. The direction and magnitude are independent of the rotation rate.
     * @param rotation The rate of rotation for the robot that is completely independent of the magnitude or direction. [-1.0..1.0]
     */
    private void mecanumDrive(double magnitude, double direction, double rotation) {
        //make magnitude and rotation zero if they are small enough
        magnitude = Utils.checkForSmall(magnitude);
        rotation = Utils.checkForSmall(rotation);

        //drive at half speed if trigger is pulled
        if (Hardware.driveJoystick.getTrigger()) {
            magnitude *= .5;
            rotation *= .5;
        }
        //drive at half speed if trigger is pulled
        if (slow) {
            magnitude *= .5;
            rotation *= .5;
        }

        if (stop) {
            mecanumDrive_Polar(0, 0, 0);
        } else {
            //call the drive method inherited from RobotDrive
            mecanumDrive_Polar(magnitude, direction, rotation);

            Hardware.DSOut.say(4, "Magnitude: " + magnitude);
            Hardware.DSOut.say(5, "Direction: " + direction);
            Hardware.DSOut.say(6, "Rotation:  " + rotation);
        }
    }

    /**
     * Converts buttons 4 and 5 on joystick to rotation. There are four cases:
     * neither pushed: no rotation
     * just 4 pushed: counterclockwise rotation
     * just 5 pushed: clockwise rotation
     * both pushed: no rotation
     * @return The calculated rotation. Negative is counterclockwise, positive is clockwise.
     */
    private double calculateRotation() {
        boolean ccw = Hardware.driveJoystick.getRawButton(4);
        boolean cw = Hardware.driveJoystick.getRawButton(5);
        //rotation is -1 for counterclockwise and +1 for clockwise
        //for now, only full speed rotation is possible from this method
        return Utils.toInt(cw) - Utils.toInt(ccw);
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

    /**
     * Allows the Robot to drive in any direction, as well as rotating.
     */
    public void drive() {
        double magnitude = Hardware.driveJoystick.getMagnitude();
        double direction = Hardware.driveJoystick.getDirectionDegrees();
        double rotation = calculateRotation();
        mecanumDrive(magnitude, direction, rotation); //robot drives
    }

    
    //kinect is commented out until we figure out what we want to do
    /*
    /**
     * Use rectangular (Cartesian) coordinates to drive the robot. The Kinect
     * works best with these coordinates, so this method should be used when
     * using Kinect data only.
     * @param x The x speed.
     * @param y The y speed.
     * @param rotation The rotation speed.
     *
    private void mecanumDriveKinect(double x, double y, double rotation) {
        if (stop) {
            x = 0;
            y = 0;
            rotation = 0;
        } else {
            //make magnitude and rotation zero if they are small enough
            x = Utils.checkForSmall(x);
            y = Utils.checkForSmall(y);
            rotation = Utils.checkForSmall(rotation);

            //drive at half speed if trigger is pulled
            if (Hardware.driveJoystick.getTrigger()) {
                x *= .5;
                y *= .5;
                rotation *= .5;
            }
            //drive at half speed if slowness is set
            if (slow) {
                x *= .5;
                y *= .5;
                rotation *= .5;
            }
        }
        
        //call the drive method inherited from RobotDrive
        mecanumDrive_Cartesian(x, y, rotation, 0);

        Hardware.DSOut.say(4, "X: " + x);
        Hardware.DSOut.say(5, "Y: " + y);
        Hardware.DSOut.say(6, "Rotation:  " + rotation);
    }

    /**
     * Converts buttons 4 and 5 on joystick to rotation. There are four cases:
     * neither pushed: no rotation
     * just 4 pushed: counterclockwise rotation
     * just 5 pushed: clockwise rotation
     * both pushed: no rotation
     * @return The calculated rotation. Negative is counterclockwise, positive is clockwise.
     *
    private double calculateRotationKinect() {
        boolean ccw = Hardware.leftArmKinect.getRawButton(4);
        boolean cw = Hardware.leftArmKinect.getRawButton(5);
        //rotation is -1 for counterclockwise and +1 for clockwise
        //for now, only full speed rotation is possible from this method
        return Utils.toInt(cw) - Utils.toInt(ccw);
    }

    /**
     * Allows the Robot to drive in any direction, as well as rotating.
     *
    public void driveKinect() {
        double X = Hardware.leftArmKinect.getX();
        double Y = Hardware.leftArmKinect.getY();
        double rotation = calculateRotationKinect();
        mecanumDriveKinect(X, Y, rotation); //robot drives
    }*/
}
