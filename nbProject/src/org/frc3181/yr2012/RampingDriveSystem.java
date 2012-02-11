package org.frc3181.yr2012;

import com.sun.squawk.util.MathUtils;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This is our robot's drive system, which implements mecanum wheels.
 * @author Robbie Markwick
 * @author Ben
 * @author Liam
 */
public class RampingDriveSystem {

    private boolean slow;
    private boolean stop;
    public static final double RAMPING_MAX_CHANGE = .035;
    private SpeedController frontLeftMotor, frontRightMotor, rearLeftMotor, rearRightMotor;
    private double frontLeftSpeed, frontRightSpeed, rearLeftSpeed, rearRightSpeed;

    /**
     * Constructor.
     * @param frontLeftMotor
     * @param rearLeftMotor
     * @param frontRightMotor
     * @param rearRightMotor
     */
    public RampingDriveSystem(SpeedController frontLeft, SpeedController rearLeft, SpeedController frontRight, SpeedController rearRight) {
        frontLeftMotor = frontLeft;
        frontRightMotor = frontRight;
        rearLeftMotor = rearLeft;
        rearRightMotor = rearRight;
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
//            if (Hardware.driveJoystick.getRawButton(2)) { // backwards
//                magnitude = Hardware.driveJoystick.getTwist() / 3 + .333;
//                direction = -180;
//            } else if (Hardware.driveJoystick.getRawButton(3)) { //forwards
//                magnitude = Hardware.driveJoystick.getTwist() / 3 + .333;
//                direction = 0;
//            } else if (Hardware.driveJoystick.getRawButton(4)) { //left
//                magnitude = Hardware.driveJoystick.getTwist() / 3 + .333;
//                direction = -90;
//            } else if (Hardware.driveJoystick.getRawButton(5)) { //right
//                magnitude = Hardware.driveJoystick.getTwist() / 3 + .333;
//                direction = 90;
//            }

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
            magnitude *= (Hardware.driveJoystick.getTwist() + 1) / 2;
            rotation *= (Hardware.driveJoystick.getTwist() + 1) / 2;
        }

        //constrain values to range
        magnitude = Math.max(Math.min(magnitude, 1.0), 0.0);
        rotation = Math.max(Math.min(rotation, .5), -.5);

        //call our drive method, which ramps
        mecanumDrive_Polar(magnitude, direction, rotation);

        Hardware.DSOut.say(4, "Magnitude: " + magnitude);
        Hardware.DSOut.say(5, "Direction: " + direction);
        Hardware.DSOut.say(6, "Rotation:  " + rotation);
        SmartDashboard.putDouble("Magnitude", magnitude * 100);
        SmartDashboard.putDouble("Direction", direction);
        SmartDashboard.putDouble("Rotation", Math.floor(rotation * 100));

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
        double dirInRad = (direction + 45.0) * Math.PI / 180.0;
        double cosD = Math.cos(dirInRad);
        double sinD = Math.sin(dirInRad);

        //Calculate speeds neceessary for mecanum drive.
        double wheelSpeeds[] = new double[4];
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

        //Set the speeds.
        frontLeftMotor.set(Utils.checkForSmall(wheelSpeeds[MotorType.kFrontLeft.value], .1));
        frontRightMotor.set(Utils.checkForSmall(wheelSpeeds[MotorType.kFrontRight.value], .1));
        rearLeftMotor.set(Utils.checkForSmall(wheelSpeeds[MotorType.kRearLeft.value], .1));
        rearRightMotor.set(Utils.checkForSmall(wheelSpeeds[MotorType.kRearRight.value], .1));

        calculateActualParams();
    }

    private void calculateActualParams() {
        double magnitude1 = (frontLeftMotor.get() + rearRightMotor.get()) / 2;
        double magnitude2 = (rearLeftMotor.get() + frontRightMotor.get()) / 2;
        double magnitude = MathUtils.pow(MathUtils.pow(magnitude1, 2) + MathUtils.pow(magnitude2, 2), .5);
        SmartDashboard.putDouble("actual M", magnitude * 100);

        double direction = MathUtils.atan((frontLeftMotor.get() + rearRightMotor.get()) / (frontRightMotor.get() + rearLeftMotor.get()));
        if (Double.isNaN(direction)) {
            SmartDashboard.putDouble("actual D", 0);
        } else {
            direction = Math.toDegrees(direction) - 45.0 + ((frontRightMotor.get() + rearLeftMotor.get()) < 0 ? 180 : 0);
            SmartDashboard.putDouble("actual D", direction);
        }

        double rotation = (frontLeftMotor.get() - rearRightMotor.get()) / 2;
        SmartDashboard.putDouble("actual R", Math.floor(rotation * 100));
    }

    /**
     * Ramp from a given speed to a target speed using default max change.
     * @param target The target speed.
     * @param current The current speed.
     * @return The ramped speed.
     */
    private double rampTo(double target, double current) {
        return rampTo(target, current, RAMPING_MAX_CHANGE);
    }

    /**
     * Ramp from a given speed to a target speed.
     * @param target The target speed.
     * @param current The current speed.
     * @param maxChange The maximum allowed change in speed dt.
     * @return The ramped speed.
     */
    private double rampTo(double target, double current, double maxChange) {
        double delta = target - current; //the proposed change
        if (Math.abs(delta) > maxChange) { //proposed change is too large
            //make actual change the max allowed, accounting for sign
            delta = ((delta < 0) ? -1 : 1) * maxChange;
        }
        current += delta;
        return current;

    }

    /**
     * Limit motor values to the -1.0 to +1.0 range.
     */
    protected static double limit(double num) {
        if (num > 1.0) {
            return 1.0;
        }
        if (num < -1.0) {
            return -1.0;
        }
        return num;
    }

    /**
     * Normalize all wheel speeds if the magnitude of any wheel is greater than 1.0.
     */
    protected static void normalize(double wheelSpeeds[]) {
        double maxMagnitude = Math.abs(wheelSpeeds[0]);
        int i;
        for (i = 1; i < 4; i++) {
            double temp = Math.abs(wheelSpeeds[i]);
            if (maxMagnitude < temp) {
                maxMagnitude = temp;
            }
        }
        if (maxMagnitude > 1.0) {
            for (i = 0; i < 4; i++) {
                wheelSpeeds[i] = wheelSpeeds[i] / maxMagnitude;
            }
        }
    }
}
