package org.frc3181.yr2012.hybrid;

import org.frc3181.yr2012.Hardware;
import org.frc3181.yr2012.litecomponents.Sensors;

/**
 * Drive backward and tips bridge.
 * @author robbiemarkwick
 */
public class HybridTip {

    private static boolean isHorizontal;//is the tipper horizontal (or was horizontal and now tipping down)? False for the first stage
    private static boolean read;//has the sensor picked up the bridge? False for the first two stages.

    /**
     * Sets up the tipping sensor, starts the tipping and driving.
     */
    public static void init() {
        Sensors.tipperSensor.reset();
        Sensors.tipperSensor.start();//reset and start the tipper
        read = false;
        isHorizontal = false;//Sets up the booleans to control the stages
        Hardware.bridgeTip.moveTipperDown(5);//begins moving the tipper into horizonatl
        Hardware.driveSystem.mecanumDrive(.5, 180, 0);//Begins driving backwards
    }
    /**
     * Performs the autonomous run.
     */
    public static void run() {
        if (!read) {//if the sensor has not picked up the bridge:
            if (!isHorizontal) {//if the bridge didn't reach horizontal in previous cycle:
                if (Hardware.bridgeTip.isMiddle()) {
                    Hardware.tip.set(0);//but the tipper is now horizontal, stop moving it
                    isHorizontal = true;// and move on to stage 2
                }
                // otherwise continue lowering
            }
            else if (Sensors.BridgeFinder.getRangeInches() < 6) {//if horizontal, and bridge is now detected:
                    Hardware.driveSystem.mecanumDrive(0, 0, 0);//stop driving
                    read = true; //and move on to stage 3
             }
            //otherwise keep driving.
        } 
        else {//if bridge has been detected:
            Hardware.bridgeTip.moveTipperDown(10);//Begin Tipping Bridge.
        }
    }
}
