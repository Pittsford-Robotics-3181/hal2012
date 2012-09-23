package org.frc3181.yr2012.litecomponents;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.SpeedController;
import org.frc3181.yr2012.Hardware;
import org.frc3181.yr2012.Utils;

/**
 * The mechanism that tips the bridge.
 * TODO: Explain why this works!
 * TODO: Test, test, test, so we can determine appropriate values for the constants.
 * @author Ben
 */
public class Tipper {

    private SpeedController tipperMotor; //see var name
    //PID stuff
    private static final double Kp = 0; //if Kp = 1, there is no ramping, just exact movement to the correct speed.
    private static final double Ki = 0; //integral gain of motor
    private static final double Kd = 0; // differential gain of motor
    private static final double MIN_ENCODER_VALUE = 0; //see var name
    private static final double MID_ENCODER_VALUE = 0; //see var name
    private static final double MAX_ENCODER_VALUE = 0; //see var name
    private PIDController tipperController; //reads integral calculations from pid source

    /**
     * Tipper constructor.
     * @param tipMotor The motor that raises and lowers the tipper.
     */
    public Tipper(SpeedController tipMotor) {
        tipperMotor = tipMotor; //sets the local tipperMotor pointer to equal the tipMotor parameter
        tipperController = new PIDController(Kp, Ki, Kd, Sensors.tipperSensor, tipperMotor); //initializes the pidController
        tipperController.setInputRange(MIN_ENCODER_VALUE, MAX_ENCODER_VALUE); // sets the pidcontroller's encoder values to the ones declared previously
        Sensors.tipperSensor.start(); //starts the tippersensor
        tipperController.enable(); // enables the PIDController
    }
    /**
     * Checks if the tipper is in the middle
     * @return whether or not the tipper is in the middle
     */
    public boolean isMiddle() {
        return (Utils.checkForSmall(MID_ENCODER_VALUE - Sensors.tipperSensor.get(), .3) == 0);
    }
    /**
     * 
     * @return the tipperMotor pointer
     */
    public SpeedController getTipperMotor() {
        return tipperMotor;
    }
    /**
     * 
     * @return the location of the Tipper
     */
    public double getTipperPosition() {
        return tipperController.getSetpoint();
    }

    /**
     * Move the tipper to a certain position.
     * @param position The value we want the encoder to be at.
     */
    public void moveTipperTo(double position) {
        //TODO: Determine which is going *downward*, setpoint is less than position, or more than?
        SmartDashboard.putDouble("Tipper PID Setpoint", tipperController.getSetpoint());
        SmartDashboard.putDouble("Tipper Desired Destination", position);
        Hardware.DSOut.say(6, "Limit switch: " + Sensors.lowLimit.get());
        if (tipperController.getSetpoint() < position && !(Sensors.lowLimit.get())) {
            return;
        }

    }

    //TODO: Decide which of MAX_ENCODER_VALUE and MIN_ENCODER_VALUE is up and which is down.
    /**
     * Move tipper all the way up.
     */
    public void moveTipperUp(double value) {
        moveTipperTo(getTipperPosition() - value);
        tipperMotor.set(-value / 10);
    }

    /**
     * Move tipper all the way down.
     */
    public void moveTipperDown(double value) {
       
                moveTipperTo(getTipperPosition() + value);
                tipperMotor.set(value / 10);
          
    }
}