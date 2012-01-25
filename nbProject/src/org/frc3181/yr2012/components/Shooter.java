package org.frc3181.yr2012.components;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Victor;
import org.frc3181.yr2012.Hardware;
import edu.wpi.first.wpilibj.Timer;
/**
 * The shooter.
 * @author Robbie
 * @author Ben
 */
public class Shooter {
    /**
     * Three Variables:
     * aim: the Victor that controls the angle
     * cannon: the Victor that controls the wheels to launch the balls
     * delay: the timer that delays any shooter action during a shot.
     */
    private Victor aim;
    private Victor cannon;
    private Timer delay=new Timer();
    
    public Shooter (Victor aimMotor, Victor ballLauncher){
    aim=aimMotor;
    cannon=ballLauncher;
    }
    
    private void aimShot(double anglechange) {
    aim.set(anglechange);
    }
    
    private void Shoot() {
    cannon.set(1);
    delay.reset();
    delay.start();
    }
    
    public void controlShooting(){
        if (delay.get()>=1000000){
            cannon.set(0);
            aimShot(-Hardware.shotController.getY());
            if(Hardware.shotController.getTrigger())
                Shoot();
        }
    }
}
