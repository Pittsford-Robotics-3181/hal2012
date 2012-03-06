package org.frc3181.yr2012.litecomponents;

import edu.wpi.first.wpilibj.Jaguar;
import org.frc3181.yr2012.Hardware;
import edu.wpi.first.wpilibj.Servo;
import org.frc3181.yr2012.ControlScheme;
/**
 * The roller that collects the balls.
 * TODO: Give variables clear names, for the love of all that is holy and sacred and my sanity and anything else you can possibly think of.
 * @author Ben
 * @author Liam
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
     * Periodically call this function to check how the stopper servos should be manipulated.
     * The isShooting variable controls whether or not the lower stopper is to allow for the ball to roll through the shooter.
     * The second half of this code detects if there are already two balls in the robot and if so it will lower the upper stopper to minimize weight on the lower stopper.
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
Periodically call this function to check how the stopper servos should be manipulated.
     * The isShooting variable controls whether or not the lower stopper is to allow for the ball to roll through the shooter.
     * The second half of this code detects if there are already two balls in the robot and if so it will lower the upper stopper to minimize weight on the lower stopper.
     * @param doShoot Whether or not the ball should be released into the shooter.
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
