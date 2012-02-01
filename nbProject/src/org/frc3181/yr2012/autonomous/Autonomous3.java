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
    private static boolean b=true;
    private static Timer t=new Timer();
    private static double d=t.get();
      public static void run(){
        if(b){
            if(d+2<=t.get()){
                    Hardware.ballLauncher.autoShoot();
            }
            else{
                Hardware.ballLauncher.autoStop();
                b=false;
            }
        }
        else
            Hardware.driveSystem.autodrive(0, 0, 1);//spin in circles to waste time
    }
}
