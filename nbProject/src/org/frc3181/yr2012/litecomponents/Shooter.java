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
    private boolean on=false;

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
     * This will also ramp up and down the shooter motor to a hundred points.
     * @param speed The speed to shoot at.
     */
    public void shootAtSpeed(double speed) {
        double avgSpeed=0;
        if(speed<0&&!on){
            Sensors.timer.reset();
            Sensors.timer.start();
            avgSpeed=Sensors.avgRevsPerMin();
        }

        on=speed!=0;
        if(speed > 0)
            return;
        if(avgSpeed==2000){
        return;
        }
        if(avgSpeed > 2000)  //if it needs to spin up...
        {
            shooterWheel.set(avgSpeed-.01);
        }

        if(avgSpeed < 2000)  //if it needs to slow down...
        {
            shooterWheel.set(avgSpeed +.01);
        }
    }
    
    /**
     * Stop the shooter.
     * @deprecated Use shootAtSpeed(0) instead.
     */
    public void stopShooter(){
        shooterWheel.set(0);
    }
}
