package org.frc3181.yr2012.components;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Victor;
import org.frc3181.yr2012.Hardware;
import edu.wpi.first.wpilibj.Timer;
/**
 * The roller that collects the balls.
 * @author Robbie
 * @author Ben
 */
public class Roller {
    
    /** 
     * Two Fields
     * Collector: controls the motor that collects the balls
     * debouncer: gives the driver time to pull finger off of button.
     */
    private Victor Collector;
    private Timer debouncer=new Timer();
    
     /** 
     * Constructs the Roller using the given motor.
     */
    public Roller(Victor Collect){
        Collector=Collect;
    }
    
    /**
     * rollerController allows the driver to turn the Roller on and off
     */
    public void rollerController(){
       if(Hardware.shotController.getRawButton(2)&&debouncer.get()>=500000){
           debouncer.reset();
           debouncer.start();
           double d;
           if(Collector.get()==1.00)
               d=0;
           else
               d=1;
           Collector.set(d);
       }
    
    }
    
}
