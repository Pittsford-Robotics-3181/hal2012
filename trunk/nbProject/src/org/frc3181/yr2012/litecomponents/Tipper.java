package org.frc3181.yr2012.litecomponents;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.SpeedController;

/**
 * The mechanism that tips the bridge.
 * TODO: Test, test, test, so we can determine appropriate values for the constants.
 * @author Ben
 */
public class Tipper {
    private SpeedController tipperMotor;
    //PID stuff
    private static final double Kp = 0;
    private static final double Ki = 0;
    private static final double Kd = 0;
    private static final double MIN_ENCODER_VALUE = 0;
    private static final double MAX_ENCODER_VALUE = 0;
    private PIDController tipperController;

    /**
     * Tipper constructor.
     * @param tipMotor The motor that raises and lowers the tipper.
     */
    public Tipper(SpeedController tipMotor) {
        tipperMotor = tipMotor;
        tipperController = new PIDController(Kp, Ki, Kd, Sensors.tipperSensor, tipperMotor);
        tipperController.setInputRange(MIN_ENCODER_VALUE, MAX_ENCODER_VALUE);
        Sensors.tipperSensor.start();
        tipperController.enable();
    }
    
    /**
     * Move the tipper to a certain position.
     * @param position The value we want the encoder to be at.
     */    
    public void moveTipperTo(double position){
        tipperController.setSetpoint(position);
    }
    
    //TODO: Decide which of MAX_ENCODER_VALUE and MIN_ENCODER_VALUE is up and which is down.
    /**
     * Move tipper all the way up.
     */
    public void moveTipperUp(){
        moveTipperTo(MAX_ENCODER_VALUE);
    }
    
    /**
     * Move tipper all the way down.
     */
    public void moveTipperDown(){
        moveTipperTo(MIN_ENCODER_VALUE);
    }

 }
