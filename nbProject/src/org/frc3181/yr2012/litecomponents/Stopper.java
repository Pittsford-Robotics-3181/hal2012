package org.frc3181.yr2012.litecomponents;

import edu.wpi.first.wpilibj.Jaguar;
import org.frc3181.yr2012.Hardware;
import edu.wpi.first.wpilibj.Servo;
import org.frc3181.yr2012.ControlScheme;
/**
 * The roller that collects the balls.
 * @author Ben
 * @author Robbie
 */
public class Stopper {

    /** 
     * Speed Controller for motor.
     */
    private Servo collector1;
    private Servo collector2;
    /**
     * Boolean determining, if the robot is in collection mode.
     */
    private boolean isCollecting = false;

    /** 
     * Constructs the Roller using the given motor.
     * @param collect
     */
    public Stopper(Servo collect1, Servo collect2) {
        collector1 = collect1;
        collector2 = collect2;
    }

    /**
     * rollerController allows the driver to turn the Roller on and off
     */
    public void stopperController() {
        if (ControlScheme.releaseBallForShooting) {
            isCollecting = false;
        } else {
            isCollecting = true;
        }
        updateStopperPosition();
        Hardware.DSOut.say(3, "Is blocked " + isCollecting);
    }

    /**
     * Private function to update the rollers velocity.
     * @param isCollecting 
     */
    private void updateStopperPosition() {
        if (isCollecting) {
            collector1.setAngle(170);
            collector2.setAngle(90);
        } else {
            collector1.setAngle(40);
            collector2.setAngle(0);
            }
        }

    }
