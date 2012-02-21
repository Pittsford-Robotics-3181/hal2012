package org.frc3181.yr2012;

import edu.wpi.first.wpilibj.Joystick;

/**
 * This file contains the overall control settings for the robot.
 * @author liam middlebrook
 */
public class ControlScheme {
    /*
     * Drive Joystick Functions And Associated Buttons
     *
     */

public static boolean perfectStrafeBackwards = Hardware.driveJoystick.getRawButton(2);

public static boolean perfectStrafeForwards = Hardware.driveJoystick.getRawButton(3);

public static boolean perfectStrafeLeft = Hardware.driveJoystick.getRawButton(4);

public static boolean perfectStrafeRight = Hardware.driveJoystick.getRawButton(5);

public static boolean slowRobotDrive = Hardware.driveJoystick.getTrigger();
public static boolean rotateLeft = Hardware.driveJoystick.getRawButton(8);
public static boolean rotateRight = Hardware.driveJoystick.getRawButton(9);

/*
 * Accesory Joystick Buttons And Associated Buttons
 */
public static boolean collectingBalls = Hardware.ballJoystick.getRawButton(4);
public static boolean isShooting = Hardware.ballJoystick.getRawButton(5);
public static boolean tipperUp = Hardware.ballJoystick.getRawButton(3);
public static boolean tipperDown = Hardware.ballJoystick.getRawButton(2);
public static boolean releaseBallForShooting = Hardware.ballJoystick.getTrigger();
public static void update()
    {

 perfectStrafeBackwards = Hardware.driveJoystick.getRawButton(2);

 perfectStrafeForwards = Hardware.driveJoystick.getRawButton(3);

 perfectStrafeLeft = Hardware.driveJoystick.getRawButton(4);

 perfectStrafeRight = Hardware.driveJoystick.getRawButton(5);

 slowRobotDrive = Hardware.driveJoystick.getTrigger();
 rotateLeft = Hardware.driveJoystick.getRawButton(8);
 rotateRight = Hardware.driveJoystick.getRawButton(9);

/*
 * Accesory Joystick Buttons And Associated Buttons
 */
 collectingBalls = Hardware.ballJoystick.getRawButton(4);
 isShooting = Hardware.ballJoystick.getRawButton(5);
 tipperUp = Hardware.ballJoystick.getRawButton(2);
 tipperDown = Hardware.ballJoystick.getRawButton(3);
 releaseBallForShooting = Hardware.ballJoystick.getTrigger();
}
}