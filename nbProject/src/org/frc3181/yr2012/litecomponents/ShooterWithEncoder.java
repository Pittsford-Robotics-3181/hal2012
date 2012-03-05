package org.frc3181.yr2012.litecomponents;

import edu.wpi.first.wpilibj.SpeedController;
import org.frc3181.yr2012.ControlScheme;

/**
 * The shooter.
 * @author Ben
 */
public class ShooterWithEncoder {

    /**
     * The speed controllers that control the wheels to launch the balls.
     */
    private SpeedController shooterMotor;

    /**
     * Constructs a new Shooter with the given speed controller.
     * @param shooter The ball shooting controller.
     */
    public ShooterWithEncoder(SpeedController shooter) {
        shooterMotor = shooter;
        Sensors.shooterEncoder.start();
    }

    /**
     * Make the shooter wheel go at a certain speed.
     * @param speed The speed to shoot at.
     */
    public void shootAtSpeed(double speed) {
        shooterMotor.set(speed);
    }
    
    /**
     * Shoot the ball at the speed to make it go the given distance.
     * TODO: Actually write this function to convert distance to speed. TEST!!!!!
     * @param distance Distance to shoot the ball in feet.
     */
    public void shootToDistance(double distance){
        shootAtSpeed(1);
    }

    /**
     * This stops the shooter.
     */
    public void stop() {
        shootAtSpeed(0);
    }
    
    /**
     * Get the current shooter wheel speed.
     * @return The rate of rotation of the shooter wheel got from the encoder.
     */
    public double getSpeed(){
        return Sensors.shooterEncoder.getRate();
    }

    /**
     * Shoot only if is shooting and at the speed read from the twist.
     */
    public void controlShooting() {
        if(ControlScheme.isShooting){
            shootAtSpeed(ControlScheme.shootSpeed); //map from [-1,1] to [0,1]
        }
    }
}
