package org.frc3181.yr2012.litecomponents;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.SpeedController;
import org.frc3181.yr2012.Hardware;
import org.frc3181.yr2012.Utils;

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
    private static final double MID_ENCODER_VALUE = 0;
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
    public boolean isMiddle(){
        return (Utils.checkForSmall(MID_ENCODER_VALUE-Sensors.tipperSensor.get(),.3)==0);
    }
    public SpeedController getTipperMotor(){
    return tipperMotor;
    }
    public double getTipperPosition()
    {
        return tipperController.getSetpoint();
    }
    /**
     * Move the tipper to a certain position.
     * @param position The value we want the encoder to be at.
     */    
    public void moveTipperTo(double position){
        //TODO: Determine which is going *downward*, setpoint is less than position, or more than?
        SmartDashboard.putDouble("Tipper PID Setpoint", tipperController.getSetpoint());
        SmartDashboard.putDouble("Tipper Desired Destination", position);
        Hardware.DSOut.say(6, "Limit switch: " + Sensors.lowLimit.get());
        if(tipperController.getSetpoint() < position && !(Sensors.lowLimit.get())) return;
        
    }
    
    //TODO: Decide which of MAX_ENCODER_VALUE and MIN_ENCODER_VALUE is up and which is down.
    /**
     * Move tipper all the way up.
     */
    public void moveTipperUp(double value){
       
        moveTipperTo(getTipperPosition() - value);
        tipperMotor.set(-value/10);
    }
    
    /**
     * Move tipper all the way down.
     */
    public void moveTipperDown(double value){
        if(Sensors.lowLimit.get()!=true){
        if(getTipperPosition() + value < 130){
        moveTipperTo(getTipperPosition() + value);
        tipperMotor.set(value/10);
        }   
        else{
            Hardware.tip.set(0);
        }
    }
    }

 }