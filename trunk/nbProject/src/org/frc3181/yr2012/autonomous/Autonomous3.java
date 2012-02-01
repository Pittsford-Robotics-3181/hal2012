/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.frc3181.yr2012.autonomous;

import org.frc3181.yr2012.Hardware;
import edu.wpi.first.wpilibj.Timer;

/**
 * shoot at a pre-determined basket, then waste time by spinning.
 * @author robbiemarkwick
 */
public class Autonomous3 {
    private static boolean shotBalls=true;
    private static Timer timer=new Timer();
    private static double shotStartTime=timer.get();
      public static void run(){
        if(shotBalls){
            if(shotStartTime+2<=timer.get()){
                    Hardware.ballLauncher.shootAtSpeed(1);
            }
            else{
                Hardware.ballLauncher.stopShooter();
                shotBalls=false;
            }
        }
        else
            Hardware.driveSystem.autodrive(0, 0, 1);//spin in circles to waste time
    }
}
