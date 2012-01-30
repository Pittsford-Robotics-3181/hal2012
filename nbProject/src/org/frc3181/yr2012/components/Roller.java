package org.frc3181.yr2012.components;

import edu.wpi.first.wpilibj.SpeedController;
import org.frc3181.yr2012.Hardware;

/**
 * The roller that collects the balls.
 * @author Robbie
 * @author Ben
 */
public class Roller {

    /** 
     * Speed Controller for motor.
     */
    private SpeedController collector;
    /**
     * Boolean determining, if the robot is in collection mode.
     */
    private boolean isCollecting = false;
    /** 
     * Constructs the Roller using the given motor.
     * @param collect
     */
    public Roller(SpeedController collect) {
        collector = collect;
    }

    /**
     * rollerController allows the driver to turn the Roller on and off
     */
    public void rollerController() {
        if (Hardware.shotController.getRawButton(2)) {
            isCollecting = true;
        } else {
            isCollecting = false;
        }
    updateRollerVelocity(isCollecting);

    }
    /**
     * Private function to update the rollers velocity.
     * @param isCollecting 
     */
    private void updateRollerVelocity(boolean isCollecting)
    {
        if(isCollecting)
        {
             collector.set(1.00);   
        }else
        {
             collector.set(-1.00);   
         }
       
    }
}

