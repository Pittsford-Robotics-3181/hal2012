package org.frc3181.yr2012.hybrid;

import org.frc3181.yr2012.Hardware;
import org.frc3181.yr2012.litecomponents.Sensors;




/**
 * Drive forward and tips bridge.
 * @author robbiemarkwick
 */
public class Hybrid2 {
    private static boolean Horizontal;
    private static boolean read;
    public static void init(){
       Sensors.tipperSensor.reset();
       Sensors.tipperSensor.start();
       read=false;
       Horizontal=false;
       Hardware.bridgeTip.moveTipperDown(10);
       Hardware.driveSystem.mecanumDrive(.5, 180, 0);
    }
      public static void run(){
          if(!read){
              if(!Horizontal){
                if(Hardware.bridgeTip.isMiddle()){            
                      Hardware.tip.set(0);
                      Horizontal=true;
                }
                else if(Sensors.BridgeFinder.getRangeInches()<6){
                        read=true;
                        Hardware.driveSystem.mecanumDrive(0, 0, 0);
                }
              }
          }
          else{
                    Hardware.bridgeTip.moveTipperDown(10);
          }
      }
}
