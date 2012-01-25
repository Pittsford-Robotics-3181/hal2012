package org.frc3181.yr2012;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.camera.AxisCamera;
import org.frc3181.yr2012.components.*;


/**
 * This file contains our hardware declarations.
 * @author Ben
 * @author liam middlebrook
 */
public class Hardware {
    // motors
    // note that slot 1 refers to what appears to be slot 2
    // "slot 1" is the first three slots; "slot 2" is slots 5-7
    //Drive Motors
    public static Jaguar frontLeftMotor = new Jaguar(/*slot*/1, /*channel*/1);
    public static Jaguar rearLeftMotor = new Jaguar(/*slot*/1, /*channel*/2);
    public static Jaguar frontRightMotor = new Jaguar(/*slot*/1, /*channel*/3);
    public static Jaguar rearRightMotor = new Jaguar(/*slot*/1, /*channel*/4);
    //Shooting Motors
    public static Victor aimShooter = new Victor(1,5);
    public static Victor launchBalls = new Victor(1,6);
    public static Victor Rolling= new Victor (1,7);
    
    //joystick for Driving
    public static Joystick driveController = new Joystick(1);
    //joystick for Shooter
    public static Joystick shotController = new Joystick(2);
    
    //camera
    public static final String cameraAddress = "10.31.81.10";
    public static AxisCamera camera = AxisCamera.getInstance(cameraAddress);
    // drive system
    public static DriveSystem driveSystem = new DriveSystem(Hardware.frontLeftMotor,
            Hardware.rearLeftMotor,
            Hardware.frontRightMotor,
            Hardware.rearRightMotor);
    // Shooter
    public static Shooter BallLauncher= new Shooter(aimShooter,launchBalls);
    // Roller
    public static Roller Collector=new Roller(Rolling);
    
    // pseudo-hardware 
    public static DSOutput DSOut = new DSOutput();
}