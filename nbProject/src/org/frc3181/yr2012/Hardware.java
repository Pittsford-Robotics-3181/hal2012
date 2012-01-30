package org.frc3181.yr2012;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.KinectStick;
import org.frc3181.yr2012.components.*;

/**
 * This file contains our hardware declarations.
 * @author Robbie
 * @author Ben
 * @author liam middlebrook
 */
public class Hardware {

    // motors
    // Note that slot 1 refers to what appears to be the second slot
    // "slot 1" is the first three slots; "slot 2" is the fifth through seventh slots; 4 and 8 are always empty
    // Drive Motors
    public static Jaguar frontLeftMotor = new Jaguar(/*slot*/1, /*channel*/ 1);
    public static Jaguar rearLeftMotor = new Jaguar(/*slot*/1, /*channel*/ 2);
    public static Jaguar frontRightMotor = new Jaguar(/*slot*/1, /*channel*/ 3);
    public static Jaguar rearRightMotor = new Jaguar(/*slot*/1, /*channel*/ 4);
    // Non-Driving Motors
    public static final Victor aimShooter = new Victor(1, 5);
    public static final Victor launchBalls = new Victor(1, 6);
    public static final Jaguar roller = new Jaguar(1, 7);
    public static final Victor tip = new Victor(1, 8);
    public static final Jaguar conveyorMotor = new Jaguar(1, 9);

    // Drive system
    public static DriveSystem driveSystem = new DriveSystem(Hardware.frontLeftMotor,
            Hardware.rearLeftMotor,
            Hardware.frontRightMotor,
            Hardware.rearRightMotor);

    // Joystick for Driving
    public static Joystick driveJoystick = new Joystick(1);
    // Joystick for Shooter
    public static Joystick ballJoystick = new Joystick(2);
    
    //Shooter
    public static Shooter ballLauncher = new Shooter(aimShooter, launchBalls);
    // Roller
    public static Roller collector = new Roller(roller);
    // Tipper
    public static Tipper bridgeTip = new Tipper(tip);
    // Conveyor
    public static Conveyor ballLift = new Conveyor(conveyorMotor);

    //sensors are in Sensors.java

    //the two Kinect readers
    //public static KinectStick leftArmKinect = new KinectStick(1);
    //public static KinectStick rightArmKinect = new KinectStick(2);

    // Pseudo-hardware 
    public static DSOutput DSOut = new DSOutput();
}