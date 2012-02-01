package org.frc3181.yr2012.components;

import edu.wpi.first.wpilibj.SpeedController;
import org.frc3181.yr2012.Hardware;
import edu.wpi.first.wpilibj.Timer;

/**
 * The shooter.
 * @author Robbie
 * @author Ben
 */
public class Shooter {

    //The speed controller that controls the angle.
    private SpeedController aim;
    /**
     * The speed controller that controls the wheels to launch the balls.
     */
    private SpeedController cannon;
    /**
     * The Timer that delays any shooter action during a shot.
     */
    private Timer delay = new Timer();

    /**
     * constructs a new Shooter with the given victors.
     * @param aimMotor the victor controlling the motor used to aim the shooter
     * @param ballLauncher the victor controlling motor used to launch the balls
     */
    public Shooter(SpeedController aimMotor, SpeedController ballLauncher) {
        aim = aimMotor;
        cannon = ballLauncher;
    }

    /** 
     * Changes the Angle that the shooter is aimed at.
     * @param anglechange the direction the shooter should move and how fast.
     */
    private void aimShot(double anglechange) {
        aim.set(anglechange);
    }

    /** 
     * Turns the shooter into shot mode for 1 second
     * In shot mode, the cannon motor is turned on and the aiming motor is off.
     * @param anglechange the direction the shooter should move and how fast.
     */
    private void Shoot() {
        cannon.set(1);
        delay.reset();
        delay.start();
    }

    public void controlShooting() {
        if (delay.get() >= 1000000) {
            cannon.set(0);
            aimShot(Hardware.ballJoystick.getY() / (-Math.sqrt(2.0000)));
            if (Hardware.ballJoystick.getTrigger()) {
                aimShot(0);
            }
            Shoot();
        }
    }
    
    /*
     * Autonomous shooting
     */
    public void autoShoot(){
        cannon.set(1);
    }
    public void autoStop(){
        cannon.set(0);
    }
}
