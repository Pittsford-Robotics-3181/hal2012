package org.frc3181.yr2012;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;

/**
 * This file contains our hardware declarations.
 * @author Robbie
 * @author Ben
 * @author liam middlebrook
 */
public class Hardware {

    /* TODO: add vision (continued below)
     *
     * Under basis of TCP Socket Server receiving doubles  from C++ :Love, Liam
     * Possibly creating one extra thread to run the server on? or just modifying the sockets to send the real data after they sync in time.
     */
    
    // motors
    // Note that slot 1 refers to what appears to be the second slot
    // "slot 1" is the first three slots; "slot 2" is the fifth through seventh slots; 4 and 8 are always empty
    // Drive Motors
    public static Jaguar frontLeftMotor = new Jaguar(/*slot*/1, /*channel*/ 7);
    public static Jaguar rearLeftMotor = new Jaguar(/*slot*/1, /*channel*/ 6);
    public static Jaguar frontRightMotor = new Jaguar(/*slot*/1, /*channel*/ 4);
    public static Jaguar rearRightMotor = new Jaguar(/*slot*/1, /*channel*/ 5);
    // Non-Driving Motors
//    public static final Victor aimShooter = new Victor(1, 6);
//    public static final Victor leftShooter = new Victor(1, 7);
//    public static final Victor rightShooter = new Victor(1, 1);
//    public static final Jaguar roller = new Jaguar(1, 8);
//    public static final Victor tip = new Victor(1, 9);
//    public static final Jaguar conveyorMotor = new Jaguar(1, 10);

    // Drive system
    public static DriveSystem driveSystem = new DriveSystem(Hardware.frontLeftMotor,
            Hardware.rearLeftMotor,
            Hardware.frontRightMotor,
            Hardware.rearRightMotor);

    // Joystick for Driving
    public static Joystick driveJoystick = new Joystick(1);
    // Joystick for Shooter
    public static Joystick ballJoystick = new Joystick(2);
    
    // Shooter
//    public static Shooter ballLauncher = new Shooter(aimShooter, leftShooter, rightShooter);
//    // Roller
//    public static Roller collector = new Roller(roller);
//    // Tipper
//    public static Tipper bridgeTip = new Tipper(tip);
//    // Conveyor
//    public static Conveyor ballLift = new Conveyor(conveyorMotor);
    

    // Sensors are in Sensors.java

    // Pseudo-hardware 
    public static DSOutput DSOut = new DSOutput();
    
    // Globally-accessed variables
    public static int autonoSelect = 0;
}