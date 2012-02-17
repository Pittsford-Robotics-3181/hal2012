package org.frc3181.yr2012.litecomponents;

import edu.wpi.first.wpilibj.SpeedController;

/**
 * The shooter.
 * @author Ben
 */
public class Shooter {
    /**
     * The speed controller that controls the wheel to launch the balls.
     */
    private SpeedController shooterWheel;

    /**
     * Construct a new Shooter with the given speed controller.
     * @param wheel The ball shooting controller.
     */
    public Shooter(SpeedController wheel) {
        shooterWheel = wheel;
    }

    /**
     * Set the shooting motor to a given speed. Varying the speed varies the
     * distance the balls will go!
     * @param speed The speed to shoot at.
     */
    public void shootAtSpeed(double speed) {
        if(speed < 0)
            return;
        shooterWheel.set(speed);
    }
    
    /**
     * Stop the shooter.
     * @deprecated Use shootAtSpeed(0) instead.
     */
    public void stopShooter(){
        shooterWheel.set(0);
    }
}
