package org.frc3181.yr2012.components;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SpeedController;
import org.frc3181.yr2012.Hardware;
import edu.wpi.first.wpilibj.Timer;

/**
 * The shooter.
 * @author Robbie
 * @author Ben
 * @deprecated Use litecomponents.Shooter
 */
public class Shooter {

    //The speed controller that controls the angle.
    private SpeedController aim;
    /**
     * The speed controllers that control the wheels to launch the balls.
     */
    private ShooterMotorController leftController;
    private ShooterMotorController rightController;
    /**
     * The Timer that delays any shooter action during a shot.
     */
    private Timer delay = new Timer();
    //PID stuff
    private double Kp = 0;
    private double Ki = 0;
    private double Kd = 0;
    private double minimumEncoderValue = 0;
    private double maximumEncoderValue = 0;
    //private PIDController leftCannonController;
    //private PIDController rightCannonController;

    /**
     * constructs a new Shooter with the given victors.
     * @param aimMotor The victor controlling the motor used to aim the shooter
     * @param left The left ball shooting controller
     * @param right The right ball shooting controller
     */
    public Shooter(SpeedController aimMotor, SpeedController left, SpeedController right) {
        aim = aimMotor;
        leftController = new ShooterMotorController(left);
        rightController = new ShooterMotorController(right);
        //leftCannonController = new PIDController(Kp, Ki, Kd, Sensors.leftShooterEncoder, leftController);
        //leftCannonController.setInputRange(minimumEncoderValue, maximumEncoderValue);
        //rightCannonController = new PIDController(Kp, Ki, Kd, Sensors.rightShooterEncoder, rightController);
        //rightCannonController.setInputRange(minimumEncoderValue, maximumEncoderValue);
    }

    /** 
     * Moves the shooter to the requested position. Currently doesn't do anything.
     * @param position What the encoder should be reading, possibly?
     */
    private void setAimPosition(double position) {
        //TODO: put something here that can aim the shooter
        
    }

    public void shootAtSpeed(double speed) {
        //leftCannonController.setSetpoint(speed);
        //rightCannonController.setSetpoint(speed);
        //Sensors.leftShooterEncoder.start();
        //Sensors.rightShooterEncoder.start();
        //leftCannonController.enable();
        //rightCannonController.enable();
    }
    
    /**
     * Stop the shooter. Shouldn't be called.
     */
    public void stopShooter(){
        /*leftCannonController.disable();
        rightCannonController.disable();
        leftCannonController.setSetpoint(0);
        rightCannonController.setSetpoint(0);*/
//        Hardware.leftShooter.set(0);
//        Hardware.rightShooter.set(0);
    }

    /** 
     * Turns the shooter into shot mode for 1 second
     * In shot mode, the cannon motor is turned on and the aiming motor is off.
     * @param anglechange the direction the shooter should move and how fast.
     *  @deprecated 
     */
    private void shoot() {
        //cannon.set(1);
        delay.reset();
        delay.start();
    }

    /**
     * 
     */
    public void controlShooting() {
        if (delay.get() >= 1000000) {
            stopShooter();
            aim.set(Hardware.ballJoystick.getY() / (-Math.sqrt(2.0000)));
            if (Hardware.ballJoystick.getTrigger()) {
                shootAtSpeed(1);
                 delay.reset();
            delay.start();
            }
        }
    }

    /**
     * Inner wrapper class to allow PID to control speed instead of distance.
     */
    private class ShooterMotorController implements PIDOutput {

        private SpeedController controller;

        /**
         * Constructor.
         * @param c The SpeedController this wrapper object will control.
         */
        public ShooterMotorController(SpeedController c) {
            controller = c;
        }

        /**
         * Give the PIDController a way to set the motor speed.
         * @param output The given output, which is actually a change.
         */
        public void pidWrite(double output) {
            controller.pidWrite(controller.get() + output);
        }
    }

    /**
     * Autonomous shooting
     * @deprecated Use shootAtSpeed instead.
     */
    public void autoShoot() {
        shootAtSpeed(1);
    }

    /**
     * @deprecated Use stopShooter instead.
     */
    public void autoStop() {
        stopShooter();
    }
}
