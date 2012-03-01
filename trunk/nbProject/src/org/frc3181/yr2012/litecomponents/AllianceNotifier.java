package org.frc3181.yr2012.litecomponents;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Solenoid;

/**
 *
 * @author Liam Middlebrook
 */
public class AllianceNotifier {
Solenoid redSpike = new Solenoid(/*module*/1,/*port number*/1);
Solenoid blueSpike = new Solenoid(/*module*/1,/*port number*/2);
Solenoid whiteSpike = new Solenoid(/*module*/1,/*port number*/3);
double discoCount = 0.0;

private void redOn()
    {
    redSpike.set(true);
}
private void blueOn()
    {
    blueSpike.set(true);
}
private void blueOff()
    {
    blueSpike.set(false);
}
public void whiteOn()
    {
    whiteSpike.set(true);
}
public void whiteOff()
    {
    whiteSpike.set(false);
}//*/
private void redOff()
    {
    redSpike.set(false);
}
public void discoMode()
    {
    if(discoCount <1.0)
    {
        redOn();
        blueOff();
    }else{
        redOff();
        blueOn();
    }
    discoCount +=.1;
    if(discoCount >= 2.0)
    {
        discoCount = 0.0;
    }
}
public void setAlliance(DriverStation.Alliance alliance){
    if(alliance == alliance.kBlue)
    {
      redOff();
      blueOn();
    }
    if(alliance == alliance.kRed)
    {
      redOn();
      blueOff();
    }

}

}
