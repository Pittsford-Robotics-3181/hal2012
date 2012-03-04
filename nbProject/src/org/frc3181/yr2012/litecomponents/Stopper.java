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
    private boolean isShooting = false;
    private boolean ballNotPrimed = true;
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
            isShooting = false;
        } else {
            isShooting = true;
        }
        Hardware.DSOut.say(3, "Value: " + Sensors.ballSensor.get());
        if(!Sensors.ballSensor.get())
        {
            ballNotPrimed = true;
        }
        else{
            ballNotPrimed = false;
        }//*/
        updateStopperPosition();
    }
    
        /**
     * rollerController allows the driver to turn the Roller on and off
     */
    public void stopperControllerHybrid(boolean doShoot) {
        if (doShoot) {
            isShooting = false;
        } else {
            isShooting = true;
        }
        Hardware.DSOut.say(3, "Value: " + Sensors.ballSensor.get());
        if(!Sensors.ballSensor.get())
        {
            ballNotPrimed = true;
        }
        else{
            ballNotPrimed = false;
        }//*/
        updateStopperPosition();
    }

    /**
     * Private function to update the rollers velocity.
     * @param isCollecting 
     */
    private void updateStopperPosition() {
        if (isShooting) {
            collector1.setAngle(170); 
        }else{
            collector1.setAngle(0);
            }
        
        if(ballNotPrimed) {
            collector2.setAngle(100);
        }else{
            collector2.setAngle(160);
            }
        //*/
        }

    }
