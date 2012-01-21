package org.frc3181.yr2012;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;

/**
 * This file contains our hardware declarations.
 * @author lowenstein benjamin
 */
public class Hardware {
    // motors
    public static Jaguar frontLeftMotor = new Jaguar(/*slot*/4, /*channel*/1);
    public static Jaguar rearLeftMotor = new Jaguar(/*slot*/4, /*channel*/2);
    public static Jaguar frontRightMotor = new Jaguar(/*slot*/4, /*channel*/3);
    public static Jaguar rearRightMotor = new Jaguar(/*slot*/4, /*channel*/4);
    
    //joystick
    public static Joystick driveController = new Joystick(1);
    
    // drive system
    public static DriveSystem driveSystem = new DriveSystem(Hardware.frontLeftMotor,
                                                            Hardware.rearLeftMotor,
                                                            Hardware.frontRightMotor,
                                                            Hardware.rearRightMotor);
    
 // pseudo-hardware 
    public static DSOutput DSOut = new DSOutput();
}