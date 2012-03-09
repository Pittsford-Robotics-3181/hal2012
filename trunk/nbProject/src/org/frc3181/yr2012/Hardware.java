package org.frc3181.yr2012;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Victor;
import org.frc3181.yr2012.litecomponents.AllianceNotifier;
import org.frc3181.yr2012.litecomponents.Conveyor;
import org.frc3181.yr2012.litecomponents.Roller;
import org.frc3181.yr2012.litecomponents.Shooter;
import org.frc3181.yr2012.litecomponents.Stopper;
import org.frc3181.yr2012.litecomponents.Tipper;

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
    public static Jaguar frontLeftMotor = new Jaguar(/*slot*/2, /*channel*/ 1);
    public static Jaguar rearLeftMotor = new Jaguar(/*slot*/1, /*channel*/ 2);
    public static Jaguar frontRightMotor = new Jaguar(/*slot*/1, /*channel*/ 3);
    public static Jaguar rearRightMotor = new Jaguar(/*slot*/1, /*channel*/ 4);
    // Non-Driving Motors
    public static Victor shooter = new Victor(/*slot*/2, /*channel*/ 5);
    public static Jaguar roller = new Jaguar(/*slot*/1, /*channel*/ 6);
    public static final Servo holder = new Servo(1, 5);
    public static final Servo holder2 = new Servo(1, 8);
    public static final Jaguar tip = new Jaguar(1, 1);
    public static final Jaguar conveyorMotor = new Jaguar(1, 10);

    // Drive system
    public static DriveSystem driveSystem = new DriveSystem(Hardware.frontLeftMotor,
            Hardware.rearLeftMotor,
            Hardware.frontRightMotor,
            Hardware.rearRightMotor);

    // Joystick for Driving
    protected static Joystick driveJoystick = new Joystick(1);
    // Joystick for Shooter
    protected static Joystick ballJoystick = new Joystick(2);
    // Shooter
    public static Shooter ballLauncher = new Shooter(shooter);
    //notifier
    public static AllianceNotifier lights = new AllianceNotifier();
    // Roller
    public static Stopper stopper = new Stopper(holder, holder2);
    public static Roller collector = new Roller(roller, roller);
    // Tipper
    public static Tipper bridgeTip = new Tipper(tip);
    // Sensors are in Sensors.java instead of here.

    // Pseudo-hardware 
    public static DSOutput DSOut = new DSOutput();
    
    // Globally-accessed variables
    public static int autonoSelect = 0;
}