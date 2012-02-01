/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.frc3181.yr2012.autonomous;

import org.frc3181.yr2012.Hardware;

/**
 * Drive forward and tips bridge.
 * @author robbiemarkwick
 */
public class Autonomous2 {
    /*
     * TODO: NAME THE VARIABLES ACCORDINGLY BECAUSE I CAN'T UNDERSTAND THEM
     * AND DO IT FAST 
     */
    private static boolean b=false;
    private static boolean c=false;
    public static void run(){
        if(b){
        Hardware.bridgeTip.autoHorizontal();
        Hardware.driveSystem.autodrive(2,0,0);
        b=Hardware.bridgeTip.autoTip(false);
        }
        if(b&&!c){
            c=Hardware.bridgeTip.autoTip(true);
        }
        if (c){
            Hardware.driveSystem.autodrive(0.001,180,0);
        }
    }
}
