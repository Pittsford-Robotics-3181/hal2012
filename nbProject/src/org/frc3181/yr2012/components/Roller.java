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
     * Controls the motor that collects the balls
     */
    private SpeedController collector;

    /**
     * Gives the driver time to pull finger off of button.
     */
    /** 
     * Constructs the Roller using the given motor.
     */
    public Roller(SpeedController Collect) {
        collector = Collect;
    }

    /**
     * rollerController allows the driver to turn the Roller on and off
     */
    public void rollerController() {
        if (Hardware.shotController.getRawButton(2)) {
            collector.set(1.00);
        } else {
            collector.set(-1.00);
        }


    }
}
