package org.frc3181.yr2012.litecomponents;

import edu.wpi.first.wpilibj.SpeedController;
import org.frc3181.yr2012.Hardware;

/**
 * The shooter.
 * @author Ben
 */
public class Shooter {

    /**
     * The speed controller that controls the wheel to launch the balls.
     */
    private SpeedController shooterWheel;
    private boolean on = false;
private double currentSpeed = 0.0;
    /**
     * Construct a new Shooter with the given speed controller.
     * @param wheel The ball shooting controller.
     */
    public Shooter(SpeedController wheel) {
        shooterWheel = wheel;
    }

    public void shootAtSpeed(double speed) {
        currentSpeed = shooterWheel.get();

        speed = Math.min(.6, Math.max(-.6, speed)); //limit speed to [-.5,.5]

        if(currentSpeed< speed)
        {

            shooterWheel.set(currentSpeed +.01);

        }
        if(currentSpeed > speed)
        {
            shooterWheel.set(currentSpeed -=.01);

        }
        Hardware.DSOut.say(4,"Shooter Speed: " + shooterWheel.get());
    }

    /**
     * Stop the shooter.
     * @deprecated Use shootAtSpeed(0) instead.
     */
    public void stopShooter() {
        shooterWheel.set(0);
    }
}
