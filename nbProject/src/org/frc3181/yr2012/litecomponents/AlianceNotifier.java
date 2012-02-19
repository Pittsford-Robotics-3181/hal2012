package org.frc3181.yr2012.litecomponents;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Solenoid;

/**
 *
 * @author Liam Middlebrook
 */
public class AlianceNotifier {
Solenoid redSpike = new Solenoid(/*module*/1,/*port number*/1);
Solenoid blueSpike = new Solenoid(/*module*/1,/*port number*/2);
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
}//*/
private void redOff()
    {
    redSpike.set(false);
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
