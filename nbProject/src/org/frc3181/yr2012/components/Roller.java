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
     * Controls the motor that collects the balls
     */
    private Victor collector;
    
    /**
     * Gives the driver time to pull finger off of button.
     */
    private Timer debouncer=new Timer();
    
     /** 
     * Constructs the Roller using the given motor.
     */
    public Roller(Victor Collect){
        collector = Collect;
    }
    
    /**
     * rollerController allows the driver to turn the Roller on and off
     */
    public void rollerController(){
       if(Hardware.shotController.getRawButton(2) && debouncer.get()>=500000){
           debouncer.reset();
           debouncer.start();
           double d;
           if(collector.get() == 1.00)
               d = 0;
           else
               d = 1;
           collector.set(d);
       }
    
    }
    
}
