package org.frc3181.yr2012.litecomponents;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Timer;
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
    private boolean isRejecting = false;
    /**
     * Timer for button pushing.
     */
    private Timer timer = new Timer();

    /** 
     * Constructs the Roller using the given motor.
     * @param collect
     */
    public Roller(SpeedController collect, SpeedController collect2) {
        collector = collect;
        collector2= collect2;
        timer.start();
    }

    /**
     * controlRoller allows the driver to turn the Roller on and off.
     * Buttons will not toggle for .75 seconds after either is pushed.
     */
    public void controlRoller() {
        if (ControlScheme.collectingBalls && timer.get() > .75) {
            isCollecting = !isCollecting;//toggle collecting status
            isRejecting = !isCollecting;//can collect or reject, not both
            timer.reset();
            timer.start();
        }else if(ControlScheme.rejectingBalls && timer.get() > .75) {
            isRejecting = !isRejecting;//toggle rejecting status
            isCollecting = !isRejecting;//can collect or reject, not both
            timer.reset();
            timer.start();
        }
        SmartDashboard.putBoolean("Collecting", isCollecting);
        SmartDashboard.putBoolean("Collecting", isRejecting);
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
        } else if(isRejecting) {
            collector.set(-1);
            collector2.set(-1);
        } else {
            collector.set(0);
            collector2.set(0);
        }

        //Hardware.DSOut.say(2,"Roller: " + isCollecting);

    }
}
