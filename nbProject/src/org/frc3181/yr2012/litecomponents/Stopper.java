package org.frc3181.yr2012.litecomponents;

import edu.wpi.first.wpilibj.Jaguar;
import org.frc3181.yr2012.Hardware;
import edu.wpi.first.wpilibj.Servo;
import org.frc3181.yr2012.ControlScheme;
/**
 * TODO: Give this an accurate summary.
 * TODO: Make it make sense, for the love of all that is holy and sacred and my sanity and anything else you can possibly think of.
 * @author Ben
 * @author Liam
 * @author Robbie
 */
public class Stopper {

    /** 
     * Speed Controller for holder thingies which stop the balls from going into the shooter.
     */
    private Servo lowerHolder;
    private Servo upperHolder;
    
    /**
     * TODO: Give these an accurate summary, for the love of all that is holy and sacred and my sanity and anything else you can possibly think of.
     */
    private boolean isShooting = false;
    private boolean ballNotPrimed = true;

    /**
     * Stopper constructor.
     * @param h1 The first holder?
     * @param h2 The second holder?
     */
    public Stopper(Servo h1, Servo h2) {
        lowerHolder = h1;
        upperHolder = h2;
    }

    /**
     * Periodically call this function to check how the stopper servos should be manipulated.
     * The isShooting variable controls whether or not the lower stopper is to allow for the ball to roll through the shooter.
     * The second half of this code detects if there are already two balls in the robot and if so it will lower the upper stopper to minimize weight on the lower stopper.
     */
    public void controlStopper() {
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
     * Periodically call this function to check how the stopper servos should be manipulated.
     * The isShooting variable controls whether or not the lower stopper is to allow for the ball to roll through the shooter.
     * The second half of this code detects if there are already two balls in the robot and if so it will lower the upper stopper to minimize weight on the lower stopper.
     * @param doShoot Whether or not the ball should be released into the shooter.
     */
    public void controlStopperHybrid(boolean doShoot) {
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
            lowerHolder.setAngle(170); 
        }else{
            lowerHolder.setAngle(0);
            }
        
        if(ballNotPrimed) {
            upperHolder.setAngle(100);
        }else{
            upperHolder.setAngle(160);
            }
        //*/
        }

    }
