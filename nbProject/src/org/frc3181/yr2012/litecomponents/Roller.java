package org.frc3181.yr2012.litecomponents;

import edu.wpi.first.wpilibj.SpeedController;
import org.frc3181.yr2012.ControlScheme;
import org.frc3181.yr2012.Hardware;

/**
 * The roller that collects the balls.
 * @author Ben
 * @author Robbie
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
        if (ControlScheme.collectingBalls) {
            isCollecting = true;
        } else {
            isCollecting = false;
        }
        updateRollerVelocity();
    }

    /**
     * Private function to update the rollers velocity.
     * @param isCollecting 
     */
    private void updateRollerVelocity() {
        if (isCollecting) {
            collector.set(1);
        } else {
            collector.set(0);
        }

        Hardware.DSOut.say(4,"Roller: " + isCollecting);

    }
}
