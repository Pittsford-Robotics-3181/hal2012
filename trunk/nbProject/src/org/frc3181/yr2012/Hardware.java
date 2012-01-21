package org.frc3181.yr2012;
import java.lang.String;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.camera.AxisCamera;

/**
 * This file contains our hardware declarations.
 * @author Ben
 * @author liam middlebrook
 */
public class Hardware {
    // motors
    public static Jaguar frontLeftMotor = new Jaguar(/*slot*/4, /*channel*/ 1);
    public static Jaguar rearLeftMotor = new Jaguar(/*slot*/4, /*channel*/ 2);
    public static Jaguar frontRightMotor = new Jaguar(/*slot*/4, /*channel*/ 3);
    public static Jaguar rearRightMotor = new Jaguar(/*slot*/4, /*channel*/ 4);
    
    //joystick
    public static Joystick driveController = new Joystick(1);
    
    //camera
    public static String cameraAddress = "10.31.81.10";
    public static AxisCamera camera = AxisCamera.getInstance(cameraAddress);
    // drive system
    public static DriveSystem driveSystem = new DriveSystem(Hardware.frontLeftMotor,
            Hardware.rearLeftMotor,
            Hardware.frontRightMotor,
            Hardware.rearRightMotor);
    
    // pseudo-hardware 
    public static DSOutput DSOut = new DSOutput();
}