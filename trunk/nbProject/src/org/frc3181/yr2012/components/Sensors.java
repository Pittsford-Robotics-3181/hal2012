package org.frc3181.yr2012.components;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.DigitalInput;

/**
 * Our sensors. Note that the sensor slots/channels must be changed!
 * @author Robbie
 * @author Ben
 * @deprecated Use litecomponents.Sensors
 */
public class Sensors {
    public static Encoder testEncoder = new Encoder(13,14);

    //front bridge detector
    private static Ultrasonic frontSensor = new Ultrasonic(1, 2);
    //back bridge detector
    private static Ultrasonic backSensor = new Ultrasonic(3, 4);
    //full up stop
    private static DigitalInput UpSensor =new DigitalInput(7);
    //full down stop
    private static DigitalInput DownSensor=new DigitalInput(8);
    //foot sensor
    private static Encoder tipperSensor = new Encoder(5, 6);
    //constants
    public static final int AWAY_FROM_BRIDGE = 0;
    public static final int NEAR_BRIDGE = 1;
    public static final int AT_BRIDGE = 2;
    //shooter encoder
    public static Encoder shooterEncoder = new Encoder(/*a*/3, /*b*/4);
    {
        shooterEncoder.setPIDSourceParameter(Encoder.PIDSourceParameter.kRate);
    }

    /**
     * Gets the angle/distance the tipper is positioned at. This method should
     * probably be changed to return an angle, but it needs testing/calibration.
     * @return The distance the tipper has moved from original position.
     */
    public static double getTipperDistance() {
        // Note: Arm is 15 inches long.
        return tipperSensor.getDistance();
    }

    public static boolean getStopMoving(){
        return((UpSensor.get()||DownSensor.get()));
    }
    /**
     * Tells the Tipper and DriveSystem how close robot is to bridge.
     * Returns 0 if both sensors detect ground
     * Returns 1 if only front sensor detects bridge
     * Returns 2 if both sensors detect bridge.
     * @return An int representing what the sensors see.
     */
    public static int senseBridge() {
        if (frontSensor.getRangeInches() <= 5) {
            if (backSensor.getRangeInches() <= 5) {
                return AT_BRIDGE;
            } else {
                return NEAR_BRIDGE;
            }
        } else {
            return AWAY_FROM_BRIDGE;
        }
    }
}
