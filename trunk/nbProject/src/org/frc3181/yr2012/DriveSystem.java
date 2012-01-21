/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.frc3181.yr2012;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;

/**
 * This is our robot's drive system, which implements mecanum wheels.
 * 
 * Drive System:
 * Boolean trigger on Joystick:
 * On=Rotate Robot (to align for a crash)
 * Off=Linear movement of  Robot
 * Rotational mode inverts two motors, using RobotDrive.setInvertedMotor(the motor, Joystick.getTrigger());
 * Use RobotDrive.mecanumDrive_Polar(Joystick.getMagnitude(), Joystick.getDirectionDegrees(), Speed);
 * May use another Joystick for the speed, or just a constant.
 * @author Robbie Markwick
 * @author Ben
 */
public class DriveSystem extends RobotDrive {

    /**
     * Constructor
     * @param frontLeftMotor
     * @param rearLeftMotor
     * @param frontRightMotor
     * @param rearRightMotor 
     */
    public DriveSystem(SpeedController frontLeftMotor, SpeedController rearLeftMotor, SpeedController frontRightMotor, SpeedController rearRightMotor) {
        super(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor);
    }

    /**
     * Given a speed between -1.0 and 1.0, drive forward (if positive) or backward (if positive).
     * @param speed The speed to drive at.
     */
    public void driveForwardBackward(double speed) {
        if (speed > 0) {
            mecanumDrive(speed, 0);
        } else {
            mecanumDrive(speed, 180);
        }
    }

    /**
     * Given a speed between -1.0 and 1.0, drive right (if positive) or left (if positive).
     * @param speed The speed to drive at.
     */
    public void driveLeftRight(double speed) {
        if (speed > 0) {
            mecanumDrive(speed, 270);
        } else {
            mecanumDrive(speed, 90);
        }
    }

    /**
     * Drive method for Mecanum wheeled robots. A method for driving with
     * Mecanum wheeled robots. There are 4 wheels on the robot, arranged so that
     * the front and back wheels are toed in 45 degrees. When looking at the
     * wheels from the top, the roller axles should form an X across the robot. 
     * @param magnitude The speed that the robot should drive in a given direction.
     * @param direction The direction the robot should drive in degrees. The direction and magnitude are independent of the rotation rate.
     */
    private void mecanumDrive(double magnitude, double direction) {
        mecanumDrive_Polar(magnitude, direction, 0);
    }

    /**
     * Allows the Robot to drive in any direction, as well as rotating. Can replace driveFowardBackward and driveLeftRight.
     * @param magnitude the Speed that the robot should drive (from Joystick, "r" in polar coordinates)
     * @param direction the direction in degrees that the robot should drive in linear mode, or the direction in rotational mode (for Linear, "theta" in polar coordinates, read from the joystick).
     * @param button the value of the trigger on the joystick. If activated, it tells the Robot to Rotate instead of moving
     * @author Robbie Markwick
     */
    public void mechanumRotate(double magnitude, double direction, boolean button) {
        if (magnitude < 0) {
            magnitude = 0 - magnitude; //fixes magnitude if necessary
            direction = 180 + direction;
        }

        while (direction < 0) {
            direction += 360;
        }  //fixes direction if necessary
        while (direction > 360) {
            direction -= 360;
        }

        if (button) { //if the trigger is on, the robot will rotate instead of crawling around on the floor)
            if (direction <= 45 || direction >= 315) { //if the joystick is right, the robot will spin clockwise
                setInvertedMotor(MotorType.kFrontRight, true); //invert proper motors
                setInvertedMotor(MotorType.kRearRight, true);
                mecanumDrive(magnitude, 90); //drive robot with inverted wheels
                setInvertedMotor(MotorType.kFrontRight, false); //uninvert motors
                setInvertedMotor(MotorType.kRearRight, false);
            }
            if (direction <= 225 && direction >= 135) {
                setInvertedMotor(MotorType.kFrontLeft, true); //invert proper motors
                setInvertedMotor(MotorType.kRearLeft, true);
                mecanumDrive(magnitude, 90);//drive robot with inverted wheels
                setInvertedMotor(MotorType.kFrontLeft, false); //uninvert motors
                setInvertedMotor(MotorType.kRearLeft, false);
            }
        } else {//no trigger produces linear motion
            mecanumDrive(magnitude, direction); //robot drives.
        }
    }
}