package org.frc3181.yr2012.components;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.Encoder;

/**
 * Our sensors. Note that the sensor slots/channels must be changed!
 * @author Robbie
 * @author Ben
 */
public class Sensors {
    //front bridge detector

    private static Ultrasonic frontSensor = new Ultrasonic(1, 2);
    //back bridge detector
    private static Ultrasonic backSensor = new Ultrasonic(3, 4);
    //foot sensor
    private static Encoder tipperSensor = new Encoder(5, 6);
    //constants
    public static final int AWAY_FROM_BRIDGE = 0;
    public static final int NEAR_BRIDGE = 1;
    public static final int AT_BRIDGE = 2;

    /**
     * Gets the angle/distance the tipper is positioned at. This method should
     * probably be changed to return an angle, but it needs testing/calibration.
     * @return The distance the tipper has moved from original position.
     */
    public static double getTipperDistance() {
        return tipperSensor.getDistance();
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