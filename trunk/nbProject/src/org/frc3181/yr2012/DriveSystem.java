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
        if (Hardware.driveController.getTrigger()) {
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
        boolean ccw = Hardware.driveController.getRawButton(4);
        boolean cw = Hardware.driveController.getRawButton(5);
        //rotation is -1 for counterclockwise and +1 for clockwise
        //for now, only full speed rotation is possible from this method
        return Utils.toInt(cw) - Utils.toInt(ccw);
    }

    /**
     * Slows Robot as it approaches bridge.
     */
    public void setSlow(boolean b) {
        slow = b;
    }

    /**
     * Stops the robot during tipping.
     * @param b should the driving stop?
     */
    public void setStop(boolean b) {
        stop = b;
    }

    /**
     * Allows the Robot to drive in any direction, as well as rotating.
     */
    public void drive() {
        double magnitude = Hardware.driveController.getMagnitude();
        double direction = Hardware.driveController.getDirectionDegrees();
        double rotation = calculateRotation();
        mecanumDrive(magnitude, direction, rotation); //robot drives
    }
}
