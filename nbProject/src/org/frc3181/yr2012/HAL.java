package org.frc3181.yr2012;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick.ButtonType;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.frc3181.yr2012.litecomponents.Sensors;
import org.frc3181.yr2012.hybrid.HybridKinect;
import org.frc3181.yr2012.hybrid.KinectGestures;

/**
 * The main class for our 2012 robot, HAL.
 * @author Chris Cheng
 * @author Ben Lowenstein
 * @author Eric Lee
 * @author Liam Middlebrook
 * @author Robbie Markwick
 */
public class HAL extends IterativeRobot {
    Timer tT = new Timer();
    String state = "";
    double doShoot = 0;
    DriverStation driveStation = DriverStation.getInstance();
    /**
     * This function is run when the robot starts up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        KinectGestures.initKinect();
        Hardware.lights.setAlliance(driveStation.getAlliance());
    }

    /**
     * This function is called as soon as it enters the disabled state.
     */
    public void disabledInit() {
        state = "Disabled";
        Hardware.DSOut.clearOutput();
        Hardware.DSOut.say(1, "State: " + state);
        tT.stop();
        tT.reset();
    }

    /**
     * This function is called periodically during disabled.
     */
    public void disabledPeriodic() {
        updateDash();
    }

    //------------$*$*$*$*$*$*$*$*AUTONOMOUS METHODS*$*$*$*$*$*$*$*$------------//
    /**
     * This function is run when autonomous mode begins.
     */
    public void autonomousInit() {
        tT.start();
        state = "Autonomous";
        Hardware.DSOut.clearOutput();
        Hardware.DSOut.say(1, "State: " + state);
    }

    /**
     * This function is called periodically during autonomous.
     */
    public void autonomousPeriodic() {
        HybridKinect.run();
        updateDash();
    }

    //------------$*$*$*$*$*$*$*$*TELEOP METHODS*$*$*$*$*$*$*$*$------------//
    /**
     * This function is run when teleop mode begins.
     */
    public void teleopInit() {
        tT.start();
        state = "Teleoperated";
        Hardware.DSOut.clearOutput();
        Hardware.DSOut.say(1, "State: " + state);
    }

    /**
     * This function is called periodically during operator control.
     */
    public void teleopPeriodic() {
        
        //update dashboard
        updateDash();

        //drive
        Hardware.driveSystem.mecanumDrive();
        
        //Control Roller
        //Hardware.collector.rollerController();
        
        //Control Shooter
        if(Hardware.driveJoystick.getRawButton(5))
        {
            doShoot = -.5;
        }
        else{
            doShoot = 0;
        }
        Hardware.ballLauncher.shootAtSpeed(doShoot);

        //Tip Bridge if Necessary
        //Hardware.bridgeTip.controlTipper();
        
        //lift balls if present
        //Hardware.ballLift.moveBalls();

    }

    /**
     * Updates the Dashboard.
     * @author Chris Cheng (2012)
     */
    public void updateDash() {
        SmartDashboard.putString("State", state);
        SmartDashboard.putDouble("Front Left", Math.floor(Hardware.frontLeftMotor.get() * 128));
        SmartDashboard.putDouble("Front Right", Math.floor(Hardware.frontRightMotor.get() * 128));
        SmartDashboard.putDouble("Rear Left", Math.floor(Hardware.rearLeftMotor.get() * 128));
        SmartDashboard.putDouble("Rear Right", Math.floor(Hardware.rearRightMotor.get() * 128));
        double t = tT.get();
        SmartDashboard.putString("Time", (int) Math.floor(t / 60) + ":" + ((t % 60) < 10 ? "0" : "") + (int) Math.floor(t % 60));
    }
}
