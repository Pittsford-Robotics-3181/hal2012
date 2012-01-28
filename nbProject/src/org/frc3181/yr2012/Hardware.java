package org.frc3181.yr2012;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.camera.AxisCamera;
import org.frc3181.yr2012.components.*;
import edu.wpi.first.wpilibj.Ultrasonic;



/**
 * This file contains our hardware declarations.
 * @author Robie
 * @author Ben
 * @author liam middlebrook
 */
public class Hardware {
    // Motors
    // Note that slot 1 refers to what appears to be slot 2
    // "slot 1" is the first three slots; "slot 2" is slots 5-7
    // Drive Motors
    public static Jaguar frontLeftMotor = new Jaguar(/*slot*/1, /*channel*/1);
    public static Jaguar rearLeftMotor = new Jaguar(/*slot*/1, /*channel*/2);
    public static Jaguar frontRightMotor = new Jaguar(/*slot*/1, /*channel*/3);
    public static Jaguar rearRightMotor = new Jaguar(/*slot*/1, /*channel*/4);
    // Non-Driving Motors
    public static final Victor aimShooter = new Victor(1,5);
    public static final Victor launchBalls = new Victor(1,6);
    public static final Victor rolling = new Victor (1,7);
    public static final Victor tip = new Victor(1,8);
    public static final Victor lift = new Victor(1,9);
    
    // Joystick for Driving
    public static Joystick driveController = new Joystick(1);
    // Joystick for Shooter
    public static Joystick shotController = new Joystick(2);
    
    // Camera
    public static final String cameraAddress = "10.31.81.10";
    public static AxisCamera camera = AxisCamera.getInstance(cameraAddress);
    // Drive system
    public static DriveSystem driveSystem = new DriveSystem(Hardware.frontLeftMotor,
            Hardware.rearLeftMotor,
            Hardware.frontRightMotor,
            Hardware.rearRightMotor);
    // Shooter
    public static Shooter ballLauncher = new Shooter(aimShooter,launchBalls);
    // Roller
    public static Roller collector = new Roller(rolling);
    // Tipper
    public static Tipper bridgeTip = new Tipper(tip);
    // Wheel
    public static Wheel ballLift = new Wheel(lift);
    
    //sensors
    //front bridge detector
    public static Ultrasonic bridgeSenseFront=new Ultrasonic(1,2);
    //back bridge detector
    public static Ultrasonic bridgeSenseBack=new Ultrasonic(3,4);
    //foot sensor
    public static Ultrasonic findFoot=new Ultrasonic(5,6);
    //all the sensors
    public static Sensors sensorSet=new Sensors(bridgeSenseFront, bridgeSenseBack,findFoot);
    
    
    // Pseudo-hardware 
    public static DSOutput DSOut = new DSOutput();
}