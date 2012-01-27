package org.frc3181.yr2012.components;
import edu.wpi.first.wpilibj.Victor;

/**
 * The wheel that moves balls up the robot.
 * @author Robbie
 * @author Ben
 */
public class Wheel {
    private Victor mover;
    
    public Wheel(Victor spin){
        mover=spin;
    }
    
    public void moveBalls (){
        mover.set(1.00);
    }
}
