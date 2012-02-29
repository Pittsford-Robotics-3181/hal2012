package org.frc3181.yr2012.litecomponents;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
    private SpeedController collector2;
    /**
     * Boolean determining, if the robot is in collection mode.
     */
    private boolean isCollecting = false;

    /** 
     * Constructs the Roller using the given motor.
     * @param collect
     */
    public Roller(SpeedController collect, SpeedController collect2) {
        collector = collect;
        collector2= collect2;
    }

    /**
     * rollerController allows the driver to turn the Roller on and off
     */
    public void rollerController() {
        if (ControlScheme.collectingBalls) {
    isCollecting = !isCollecting;
        }
        SmartDashboard.putBoolean("Collecting", isCollecting);
        updateRollerVelocity();
    }

    /**
     * Private function to update the rollers velocity.
     * @param isCollecting 
     */
    private void updateRollerVelocity() {
        if (isCollecting) {
            collector.set(1);
            collector2.set(1);
        } else {
            collector.set(0);
            collector2.set(0);
        }

        //Hardware.DSOut.say(2,"Roller: " + isCollecting);

    }
}
