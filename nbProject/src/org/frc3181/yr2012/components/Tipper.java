package org.frc3181.yr2012.components;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Victor;
import org.frc3181.yr2012.Hardware;
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
    private int targetDistance=1;
    private boolean up;
    private boolean running=false;
    
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
        Hardware.driveSystem.setStop(true);
        running=true;
        Tip.set(1);
        targetDistance=4; 
        up=false;
    }
    private void readTipControls(){
        if(Hardware.driveController.getRawButton(3))
        {
            running=true;
            Tip.set((1<Hardware.sensorSet.findFoot()) ?1:-1);
            targetDistance=1; 
            up=(1<Hardware.sensorSet.findFoot());
        }
        else if(Hardware.driveController.getRawButton(8))
        {
            running=true;
            Tip.set((2<Hardware.sensorSet.findFoot()) ?1:-1);
            targetDistance=2; 
            up=(2<Hardware.sensorSet.findFoot());
        }
         else if(Hardware.driveController.getRawButton(9))
        {
            running=true;
            Tip.set((3<Hardware.sensorSet.findFoot()) ?1:-1);
            targetDistance=3; 
            up=(3<Hardware.sensorSet.findFoot());
        }
         else if(Hardware.driveController.getRawButton(2))
        {
            running=true;
            Tip.set((4<Hardware.sensorSet.findFoot()) ?1:-1);
            targetDistance=4; 
            up=(4<Hardware.sensorSet.findFoot());
        }
    }
    public void controlTipper(){
        if(running){
            if(((targetDistance<=Hardware.sensorSet.findFoot())&&(up))||((targetDistance>=Hardware.sensorSet.findFoot())&&(!up))){
                    running=false;
                    Tip.set(0);
                }
        }
        else{
            switch(Hardware.sensorSet.findBridge()){
                case 0: Hardware.driveSystem.setSlow(false); readTipControls(); break;
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
