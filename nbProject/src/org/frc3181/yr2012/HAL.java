package org.frc3181.yr2012;

import edu.wpi.first.wpilibj.Dashboard;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;

/**
 * The main class for our robot
 * @author Chris Cheng (2012)
 * @author Ben Lowenstein (2012)
 * @author Eric Lee (2012)
 * @author Liam Middlebrook (2012)
 * @author Robbie Markwick (2012)
 */

public class HAL extends IterativeRobot {

    /**
     * This function is run when the robot starts up and should be
     * used for any initialization code.
     */
    public void robotInit() {
       
    }

    /**
     * This function is called periodically during disabled.
     */
    public void disabledPeriodic() {
        
    }


    //------------$*$*$*$*$*$*$*$*AUTONOMOUS METHODS*$*$*$*$*$*$*$*$------------//

    /**
     * This function is run when autonomous mode begins.
     */
    public void autonomousInit() {
        
    }

    /**
     * This function is called periodically during autonomous.
     */
    public void autonomousPeriodic() {
        
    }


    //------------$*$*$*$*$*$*$*$*TELEOP METHODS*$*$*$*$*$*$*$*$------------//

    /**
     * This function is run when teleop mode begins.
     */
    public void teleopInit() {
        
    }

    /**
     * This function is called periodically during operator control.
     */
    public void teleopPeriodic() {
        
    }
    
    /**
     * Updates the Dashboard.  Durrrrr~
     * @author Chris Cheng (2012)
     */
    public void updateDash() {
        Dashboard low = DriverStation.getInstance().getDashboardPackerLow();
        low.addCluster(); {
            
        } low.finalizeCluster();
        low.commit();
    }
}