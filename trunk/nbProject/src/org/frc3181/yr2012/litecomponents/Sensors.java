package org.frc3181.yr2012.litecomponents;

import edu.wpi.first.wpilibj.Encoder;

/**
 * Our sensor(s).
 * @author Ben
 */
public class Sensors {
    public static Encoder tipperSensor = new Encoder(5, 6);
    {
        tipperSensor.setPIDSourceParameter(Encoder.PIDSourceParameter.kDistance);
    }
    
    /**
     * Get how far the tipper has moved since the robot was started.
     * This will probably only be used to put data on the SmartDashboard.
     * @return The distance as read from the encoder.
     */
    public double getTipperDistance(){
        return tipperSensor.getDistance();
    }
}
