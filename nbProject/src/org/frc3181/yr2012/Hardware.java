package org.frc3181.yr2012;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;

/**
 * This file contains our hardware declarations.
 * @author lowenstein benjamin
 */
public class Hardware {
    // motors
    public static Jaguar frontLeftMotor = new Jaguar(/*slot*/2, /*channel*/1);
    public static Jaguar rearLeftMotor = new Jaguar(/*slot*/2, /*channel*/2);
    public static Jaguar frontRightMotor = new Jaguar(/*slot*/2, /*channel*/3);
    public static Jaguar rearRightMotor = new Jaguar(/*slot*/2, /*channel*/4);
    
    //joystick
    public static Joystick driveController=new Joystick(01);
    
    // drive system
    public static DriveSystem driveSystem = new DriveSystem(Hardware.frontLeftMotor,
                                                            Hardware.rearLeftMotor,
                                                            Hardware.frontRightMotor,
                                                            Hardware.rearRightMotor);
}
