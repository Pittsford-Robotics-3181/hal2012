package org.frc3181.yr2012.litecomponents;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.AnalogModule;
import edu.wpi.first.wpilibj.AnalogTrigger;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.SensorBase;
import edu.wpi.first.wpilibj.Ultrasonic;
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
        timer.reset();
        timer.start();
    }
    public static Ultrasonic BridgeFinder = new Ultrasonic(5, 6);
    public static DigitalInput ballSensor = new DigitalInput(4);
    /**
     * The limit switch to stop the tipper from moving past the lower bound.
     */
    public static DigitalInput lowLimit = new DigitalInput(3);
    //public static AnalogChannel battery = new AnalogChannel(DriverStation.kBatteryChannel);

    /**
     * Get how far the tipper has moved since the robot was started.
     * This will probably only be used to put data on the SmartDashboard.
     * @return The distance as read from the encoder.
     */
    public static double getTipperDistance() {
        return tipperSensor.getDistance();
    }
    // private static DigitalInput ShotEncoder= new DigitalInput(7);
    // private static boolean last=false;
    private static double revs = 0;
    private static double lastRevs = 0;
    private static double time = 0;
    private static double lastTime = 0;
    private static Encoder shotEncoder = new Encoder(7, 8);
    public static Timer timer = new Timer();

    public static double avgRevsPerMin() {
        lastTime = time;
        time = timer.get();
        lastRevs = revs;
        lastTime = revs = shotEncoder.getDistance();
        return (revs - lastRevs) / ((time - lastTime) / 60);
    }
    //Possible shooter encoder. It's only used in ShooterWithEncoder.
    public static Encoder shooterEncoder = new Encoder(2, 1, 2, 2); //aslot, achannel, bslot, bchannel
}
