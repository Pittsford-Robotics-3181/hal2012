package org.frc3181.yr2012.hybrid;

import org.frc3181.yr2012.Hardware;
import edu.wpi.first.wpilibj.Timer;

/**
 * Shoot at a pre-determined basket, then sit there.
 * TODO: Which basket?
 * @author robbiemarkwick
 */
public class HybridShoot {

    private static boolean shooting = false;
    private static Timer timer = new Timer();
    private static int ballsShot = 0;

    public static void init() {
        Hardware.ballLauncher.shootAtSpeed(-1);
        timer.reset();
        timer.start();
    }

    public static void run() {
        if (shooting) {
            if (timer.get() >= .5) {
                Hardware.stopper.stopperControllerHybrid(false);
                shooting = false;
                timer.reset();
                timer.start();
            }
        } else {
            if (ballsShot < 2) {
                if (timer.get() >= 5.01) {
                    Hardware.stopper.stopperControllerHybrid(true);
                    shooting = true;
                    timer.reset();
                    timer.start();
                }
            } else {
                Hardware.driveSystem.mecanumDrive(.25, 180, 0);

            }
        }
    }
}
