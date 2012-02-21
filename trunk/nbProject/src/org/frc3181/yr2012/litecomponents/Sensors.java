package org.frc3181.yr2012.litecomponents;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.AnalogModule;
import edu.wpi.first.wpilibj.AnalogTrigger;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SensorBase;
import edu.wpi.first.wpilibj.buttons.AnalogIOButton;

/**
 * Our sensor(s).
 * @author Ben
 */
public class Sensors {
    /**
     * The Encoder to sense the Tipper's angle.
     */
    public static Encoder tipperSensor = new Encoder(1, 2);
    {
        tipperSensor.setPIDSourceParameter(Encoder.PIDSourceParameter.kDistance);
    }
    public static DigitalInput ballSensor = new DigitalInput(4);
    /**
     * The limit switch to stop the tipper from moving past the lower bound.
     */
    public static DigitalInput lowLimit = new DigitalInput(3);

    /**
     * Get how far the tipper has moved since the robot was started.
     * This will probably only be used to put data on the SmartDashboard.
     * @return The distance as read from the encoder.
     */
    public double getTipperDistance(){
        return tipperSensor.getDistance();
    }
}
