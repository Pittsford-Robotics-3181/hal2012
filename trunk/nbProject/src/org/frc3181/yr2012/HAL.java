package org.frc3181.yr2012;

import edu.wpi.first.wpilibj.Dashboard;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;

/**
 * The main class for our 2012 robot, HAL.
 * @author Chris Cheng
 * @author Ben Lowenstein
 * @author Eric Lee
 * @author Liam Middlebrook
 * @author Robbie Markwick
 */
public class HAL extends IterativeRobot {

    String state = "";

    /**
     * This function is run when the robot starts up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        //nothing here yet
    }

    /**
     * This function is called as soon as it enters the disabled state.
     */
    public void disabledInit() {
        state = "Disabled";
        Hardware.DSOut.say(1, "State: " + state);
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
        state = "Autonomous";
        Hardware.DSOut.say(1, "State: " + state);
    }

    /**
     * This function is called periodically during autonomous.
     */
    public void autonomousPeriodic() {
        updateDash();
    }

    //------------$*$*$*$*$*$*$*$*TELEOP METHODS*$*$*$*$*$*$*$*$------------//
    /**
     * This function is run when teleop mode begins.
     */
    public void teleopInit() {
        state = "Teleoperated";
        Hardware.DSOut.say(1, "State: " + state);
    }

    /**
     * This function is called periodically during operator control.
     */
    public void teleopPeriodic() {
        //update dashboard
        updateDash();

        //drive
        Hardware.driveSystem.drive();
    }

    /**
     * Updates the Dashboard.
     * @author Chris Cheng (2012)
     */
    public void updateDash() {
        Dashboard low = DriverStation.getInstance().getDashboardPackerLow();
        low.addCluster(); {
            low.addString(state);
        } low.finalizeCluster();
        low.commit();
    }
}