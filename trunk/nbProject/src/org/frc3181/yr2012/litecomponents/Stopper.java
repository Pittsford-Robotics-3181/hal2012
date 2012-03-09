package org.frc3181.yr2012.litecomponents;

import org.frc3181.yr2012.Hardware;
import edu.wpi.first.wpilibj.Servo;
import org.frc3181.yr2012.ControlScheme;

/**
 * This stopper controls the flow of balls once they have passed through the robots roller.
  * The servo motor for lowerHolder controls the mechanism that will release a ball to be rolled into the shooter when activated. Default position looks as drawn crudely below:
  
            * (Ball)
  *Shooter* |____
                 *
  When activated to release a ball for shooting it looks as drawn crudely below:
    
          <-(Ball)* (Another Ball Waiting)
  *Shooter* ______|    (Limit Switch)
           *    
 *The upperHolder is automatically activated by a limit switch crudely in the image above. It controls a flap on the top of the robot's collection unit that will reject balls from rolling down the Hopper if it contains more than 2 balls.
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
  * isShooting: Should the lowerMotor position be changed from its default position to release a ball to the shooter.
  * hopperFilled: Are there 2 balls in the hopper?
     */
    private boolean isShooting = false;
    private boolean hopperFilled = true;

    /**
     * Stopper constructor.
     * @param h1 The lowerServo
     * @param h2 The upperServo
     */
    public Stopper(Servo h1, Servo h2) {
        lowerHolder = h1;
        upperHolder = h2;
    }

    /**
     * Periodically call this function to check how the stopper servos should be manipulated.
     * The isShooting variable controls wgether or not the lower stopper is to allow for the ball to roll through the shooter.
     * The second half of this code detects if there are already two balls in the robot and if so it will lower the upper stopper to minimize weight on the lower stopper.
     */
    public void controlStopper() {
        if (ControlScheme.releaseBallForShooting) {
            isShooting = true;
        } else {
            isShooting = false;
        }
        
        hopperFilled = Sensors.ballSensor.get();
        updateStopperPosition();
    }

    /**
     * Periodically call this function to check how the stopper servos should be manipulated.
     * The isShooting variable controls whether or not the lower stopper is to allow for the ball to roll through the shooter.
     * The second half of this code detects if there are already two balls in the robot and if so it will lower the upper stopper to minimize weight on the lower stopper.
     * @param doShoot Whether or not the ball should be released into the shooter.
     */
    public void controlStopperHybrid(boolean doShoot) {
        isShooting = doShoot;
        
        hopperFilled = Sensors.ballSensor.get();
        updateStopperPosition();
    }

    /**
     * Private function to update the rollers velocity.
     * @param isCollecting 
     */
    private void updateStopperPosition() {
        if (isShooting) {
            lowerHolder.setAngle(0);
        } else {
            lowerHolder.setAngle(170);
        }

        if (hopperFilled) {
            upperHolder.setAngle(160);
        } else {
            upperHolder.setAngle(100);
        }
        //*/
    }
}
