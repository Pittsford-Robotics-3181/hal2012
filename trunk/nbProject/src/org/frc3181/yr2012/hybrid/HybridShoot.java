package org.frc3181.yr2012.hybrid;

import org.frc3181.yr2012.Hardware;
import edu.wpi.first.wpilibj.Timer;

/**
 * Shoot at a pre-determined basket, then sit there.
 * @author robbiemarkwick
 */
public class HybridShoot {

    private static boolean shooting = false;//is the Robot shooting?
    private static Timer timer = new Timer();//Timer to direct the shots
    private static int ballsShot = 0;//how many balls have been shot.

    /**
     * Sets up the tipping sensor, starts the tipping and driving.
     */
    public static void init() {
        Hardware.ballLauncher.shootAtSpeed(-1);//start the shooting wheel, but the robot isn't shooting yet.
        timer.reset();
        timer.start();//reset and start the timer
        ballsShot = 0;//reset ball counter
        shooting = false;//No, the robot isn't shooting yet
    }

    /**
     * Performs the autonomous run.
     */
    public static void run() {
        if (shooting) {//if the Robot is actully shooting:
            if (timer.get() >= .5) {//if it has shot for more than half a second:
                Hardware.stopper.controlStopperHybrid(false);//allow second ball to move up
                shooting = false;//you are no longer shooting
                ballsShot++;//we have now shot another ball
                timer.reset();
                timer.start();//start the timer for the next buildup
            }
            //otherwise continue shooting
        } else {//if not shooting:
            if (ballsShot < 2) {//if we haven't shot both balls:
                if (timer.get() >= 5) {//if we have been building up for at least 5 seconds:
                    shooting = true;//we are now shooting
                    timer.reset();
                    timer.start();//start timing the shot
                }
                //otherwise continue building up
            } else {// if we have shot both balls:
                //TODO: Make sure this isn't dangerous, i.e., we crash into the opposing alliance's bridge or alley.
                Hardware.driveSystem.mecanumDrive(.25, 180, 0);//start creeping towards the bridge.
            }
        }
    }
}
