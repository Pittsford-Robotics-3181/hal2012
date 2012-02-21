package org.frc3181.yr2012;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/*
 * WE AREN'T USING THIS RIGHT NOW!!!
 * WE ARE USING RAMPINGDRIVESYSTEM INSTEAD!!!
 * WE AREN'T USING THIS RIGHT NOW!!!
 * WE ARE USING RAMPINGDRIVESYSTEM INSTEAD!!!
 * WE AREN'T USING THIS RIGHT NOW!!!
 * WE ARE USING RAMPINGDRIVESYSTEM INSTEAD!!!
 * WE AREN'T USING THIS RIGHT NOW!!!
 * WE ARE USING RAMPINGDRIVESYSTEM INSTEAD!!!
 * WE AREN'T USING THIS RIGHT NOW!!!
 * WE ARE USING RAMPINGDRIVESYSTEM INSTEAD!!!
 * WE AREN'T USING THIS RIGHT NOW!!!
 * WE ARE USING RAMPINGDRIVESYSTEM INSTEAD!!!
 * WE AREN'T USING THIS RIGHT NOW!!!
 * WE ARE USING RAMPINGDRIVESYSTEM INSTEAD!!!
 * WE AREN'T USING THIS RIGHT NOW!!!
 * WE ARE USING RAMPINGDRIVESYSTEM INSTEAD!!!
 * WE AREN'T USING THIS RIGHT NOW!!!
 * WE ARE USING RAMPINGDRIVESYSTEM INSTEAD!!!
 * WE AREN'T USING THIS RIGHT NOW!!!
 * WE ARE USING RAMPINGDRIVESYSTEM INSTEAD!!!
 * WE AREN'T USING THIS RIGHT NOW!!!
 * WE ARE USING RAMPINGDRIVESYSTEM INSTEAD!!!
 */

/**
 * This is our robot's drive system, which implements mecanum wheels.
 * @author Robbie Markwick
 * @author Ben
 * @author Liam
 */
public class DriveSystem extends RobotDrive {

    private boolean slow;
    private boolean stop;
    private static final boolean OVERRIDE_MECANUMDRIVE_POLAR = false;
    public static final double RAMPING_MAX_CHANGE = .025;
    private double frontLeftSpeed, frontRightSpeed, rearLeftSpeed, rearRightSpeed;

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
        if (stop) {
            magnitude = direction = rotation = 0;
        } else {
            //perfect strafe
            if (ControlScheme.perfectStrafeBackwards) { // backwards
                direction = -180;
                magnitude = 1;
            } else if (ControlScheme.perfectStrafeForwards) { //forwards
                direction = 0;
                magnitude = 1;
            } else if (ControlScheme.perfectStrafeLeft) { //left
                direction = -90;
                magnitude = 1;
            } else if (ControlScheme.perfectStrafeRight) { //right
                direction = 90;
                magnitude = 1;
            }

            //drive at half speed if trigger is pulled
            if (ControlScheme.slowRobotDrive || slow) {
                magnitude *= .5;
                rotation *= .5;
            }

            if(ControlScheme.rotateLeft)
            {

                rotation = 1;


            }
            if(ControlScheme.rotateRight)
            {

                rotation  =-1;


            }
            magnitude *=Hardware.driveJoystick.getTwist();
        }

        //analyze values and correct if necessary
        //make magnitude and rotation zero if they are small enough
        magnitude = Utils.checkForSmall(magnitude, .01);
        rotation = Utils.checkForSmall(rotation, .1);
        //constrain values to range
        magnitude = Math.max(Math.min(magnitude, 1.0), 0.0);
        rotation = Math.max(Math.min(rotation, .5), -.5);

        //We have this in case we need to have more control to setting speeds, e.g., encoders and/or PID/linear ramping.
        /*if (ControlScheme.slowRobotDrive&&OVERRIDE_MECANUMDRIVE_POLAR) {
            //call our drive method, which ramps
            mecanumDrive_Polar(magnitude, direction, rotation);
        } else {//*/
            //call the drive method inherited from RobotDrive
            super.mecanumDrive_Polar(magnitude, direction, rotation);
        //}

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
        return Hardware.driveJoystick.getThrottle();
    }

    /**
     * This method should be called to make the robot drive with joystick input.
     */
    public void mecanumDrive() {
        double magnitude = Hardware.driveJoystick.getMagnitude();
        double direction = Hardware.driveJoystick.getDirectionDegrees();
        double rotation =0;// calculateRotation();
        SmartDashboard.putDouble("Rotation", Math.floor(rotation * 100));
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

    /**
     * Drive method for Mecanum wheeled robots. Overides default mecanumDrive_Polar in RobotDrive.
     *
     * A method for driving with Mecanum wheeled robots. There are 4 wheels
     * on the robot, arranged so that the front and back wheels are toed in 45 degrees.
     * When looking at the wheels from the top, the roller axles should form an X across the robot.
     *
     * @param magnitude The speed that the robot should drive in a given direction.
     * @param direction The direction the robot should drive in degrees. The direction and maginitute are
     * independent of the rotation rate.
     * @param rotation The rate of rotation for the robot that is completely independent of
     * the magnitute or direction. [-1.0..1.0]
     */
    public void mecanumDrive_Polar(double magnitude, double direction, double rotation) {
        // Normalized for full power along the Cartesian axes.
        magnitude = limit(magnitude) * Math.sqrt(2.0);
        // The rollers are at 45 degree angles.
        double dirInRad = (direction + 45.0) * 3.14159 / 180.0;
        double cosD = Math.cos(dirInRad);
        double sinD = Math.sin(dirInRad);

        //Calculate speeds neceessary for mecanum drive.
        double wheelSpeeds[] = new double[kMaxNumberOfMotors];
        wheelSpeeds[MotorType.kFrontLeft.value] = rampTo(sinD * magnitude + rotation, frontLeftSpeed);
        wheelSpeeds[MotorType.kFrontRight.value] = rampTo(cosD * magnitude - rotation, frontRightSpeed);
        wheelSpeeds[MotorType.kRearLeft.value] = rampTo(cosD * magnitude + rotation, rearLeftSpeed);
        wheelSpeeds[MotorType.kRearRight.value] = rampTo(sinD * magnitude - rotation, rearRightSpeed);

        //Normalize all wheel speeds if the magnitude of any wheel is greater than 1.0.
        normalize(wheelSpeeds);

        frontLeftSpeed = wheelSpeeds[MotorType.kFrontLeft.value];
        frontRightSpeed = wheelSpeeds[MotorType.kFrontRight.value];
        rearLeftSpeed = wheelSpeeds[MotorType.kRearLeft.value];
        rearRightSpeed = wheelSpeeds[MotorType.kRearRight.value];

//        SmartDashboard.putDouble("Front Left", Math.floor(frontLeftSpeed * 128));
//        SmartDashboard.putDouble("Front Right", Math.floor(frontRightSpeed * 128));
//        SmartDashboard.putDouble("Rear Left", Math.floor(rearLeftSpeed * 128));
//        SmartDashboard.putDouble("Rear Right", Math.floor(rearRightSpeed * 128));
        //Set the speeds.
        Hardware.frontLeftMotor.set(wheelSpeeds[MotorType.kFrontLeft.value] * m_invertedMotors[MotorType.kFrontLeft.value] * m_maxOutput);
        Hardware.frontRightMotor.set(wheelSpeeds[MotorType.kFrontRight.value] * m_invertedMotors[MotorType.kFrontRight.value] * m_maxOutput);
        Hardware.rearLeftMotor.set(wheelSpeeds[MotorType.kRearLeft.value] * m_invertedMotors[MotorType.kRearLeft.value] * m_maxOutput);
        Hardware.rearRightMotor.set(wheelSpeeds[MotorType.kRearRight.value] * m_invertedMotors[MotorType.kRearRight.value] * m_maxOutput);

    }
    
    /**
     * Ramp from a given speed to a target speed using default max change.
     * @param target The target speed.
     * @param current The current speed.
     * @return The ramped speed.
     */
    private double rampTo(double target, double current){
        return rampTo(target, current, RAMPING_MAX_CHANGE);
    }
    
    /**
     * Ramp from a given speed to a target speed.
     * @param target The target speed.
     * @param current The current speed.
     * @param maxChange The maximum allowed change in speed dt.
     * @return The ramped speed.
     */
    private double rampTo(double target, double current, double maxChange){
        double delta = target - current; //the proposed change
        if (Math.abs(delta) > maxChange) { //proposed change is too large
            //make actual change the max allowed, accounting for sign
            delta = ((delta < 0) ? -1 : 1) * maxChange;
        }
        current += delta;
        return current;

    }
}
