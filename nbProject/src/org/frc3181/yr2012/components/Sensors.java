package org.frc3181.yr2012.components;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.DigitalInput;

/**
 * Our sensors.
 * @author Robbie
 * @author Ben
 */

/**
 * Sensors needed for Tipper:
 * Detect if full up
 * Detect if full down
 * Detect if front of tipper is over bridge (slow down and prepare to tip)
 * Detect if back of tipper is over to bridge (ready to tip)
 * @author robbiemarkwick
 */
public class Sensors {
   private Ultrasonic front;
   private Ultrasonic back;
   private Ultrasonic foot;
   public DigitalInput in;
   public DigitalInput out;
   
    public Sensors(Ultrasonic S1, Ultrasonic S2, Ultrasonic S3, DigitalInput S4, DigitalInput S5){
        front=S1;
        back=S2;
        foot=S3;
        in=S4;
        out=S5;
    }
    
    public double findFoot(){
        return foot.getRangeInches();
    }
    
    /**
     * Tells the Tipper and DriveSystem how close robot is to bridge.
     * Returns 0 if both sensors detect ground
     * Returns 1 if only front sensor detects bridge
     * Returns 2 if both sensors detect bridge.
     */
    public int findBridge(){
        if(front.getRangeInches()<=5){ 
            if(back.getRangeInches()<=5) 
                return 2;
            else
                return 1;
        }
        else
            return 0;
    }
}
