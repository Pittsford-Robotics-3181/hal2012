package org.frc3181.yr2012.components;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Victor;
import org.frc3181.yr2012.Hardware;
import edu.wpi.first.wpilibj.Timer;
/**
 * The mechanism that tips the bridge.
 * @author Robbie
 * @author Ben
 */
public class Tipper {
    /*
     * Four fields:
     * Tip: the Victor controlling the Tipper
     * clock: controls time
     * running: is the tipper operating?
     * down: is the tipper down or moving down?
     */
    private Victor Tip;
    private Timer clock=new Timer();
    private boolean running=false;
    private boolean down=false;
    
    /*
     * Constructs a Tipper
     * @param TipMotor The victor used for Tip.
     */
    public Tipper(Victor TipMotor){
    Tip=TipMotor;
    }
    
    /*
     * controls the tipper.
     * if the 3 button is pressed, the tipper changes position.
     */
    private void TipBridge(){
        clock.reset();
        clock.start();
        running=true;
        Hardware.driveSystem.setStop(true);
        Tip.set(down ? 1 : -1);
        
    }
    public void controlTipper(){
        if(running){
            if(clock.get()>=1000000){
                running=false;
                Tip.set(0);
                Hardware.driveSystem.setStop(false);
            }
        }
        else{
            switch(Hardware.sensorSet.findBridge()){
                case 0: Hardware.driveSystem.setSlow(false); break;
                case 1: Hardware.driveSystem.setSlow(true); break;
                case 2: {
                            Hardware.driveSystem.setStop(true); 
                            TipBridge(); 
                            break;
                        }
            }
        }
    }
}
