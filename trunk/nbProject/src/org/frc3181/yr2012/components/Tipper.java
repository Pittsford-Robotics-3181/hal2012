package org.frc3181.yr2012.components;

import edu.wpi.first.wpilibj.Victor;
import org.frc3181.yr2012.Hardware;
import org.frc3181.yr2012.Utils;

/**
 * The mechanism that tips the bridge.
 * @author Robbie
 * @author Ben
 * @deprecated Use litecomponents.Tipper
 */
public class Tipper {

    //Height constants
    //These WILL be changed.
    public static final double TIPPER_BACK= 0;
    public static final double TIPPER_UP = 1;
    public static final double TIPPER_HALF = 2;
    public static final double TIPPER_DOWN = 3;
    private Victor tipperLifter;
    private double targetHeight = TIPPER_BACK;

    /**
     * Tipper constructor.
     * @param tipMotor The motor that raises and lowers the tipper.
     * @param pullMotor The motor that retracts and extends the tipper.
     */
    public Tipper(Victor tipMotor) {
        tipperLifter = tipMotor;
    }

    /**
     * Actually tips the bridge, after ensuring the robot is not driving.
     */
    private void tipBridge() {
        Hardware.driveSystem.setStop(true);
        if(Sensors.tipperAtBottom()) return;
        else
            tipperLifter.set(-1);
            targetHeight = TIPPER_DOWN;
    }

    /**
     * Figure out what buttons are being pushed on joystick and act appropriately.
     */
    private void readTipControls() {
//        if (Hardware.driveJoystick.getRawButton(3)) {
//            tipperLifter.set((TIPPER_UP < Sensors.getTipperDistance()) ? 1 : -1);
//            targetHeight = TIPPER_UP;
//        } else if (Hardware.driveJoystick.getRawButton(6)) {
//            tipperLifter.set((TIPPER_HALF < Sensors.getTipperDistance()) ? 1 : -1);
//            targetHeight = TIPPER_HALF;
//        } else if (Hardware.driveJoystick.getRawButton(2)) {
//            tipperLifter.set((TIPPER_DOWN < Sensors.getTipperDistance()) ? 1 : -1);
//            targetHeight = TIPPER_DOWN;
//        }
//         else if (Hardware.driveJoystick.getRawButton(7)) {
//            tipperLifter.set((TIPPER_BACK < Sensors.getTipperDistance()) ? 1 : -1);
//            targetHeight = TIPPER_BACK;
//        }
    }

    /**
     * Control tipper.
     */
    public void controlTipper() {
            if(!Sensors.getStopMoving())
                tipperLifter.set(.25*Utils.checkForSmall(Sensors.getTipperDistance()-targetHeight,.1));
            if(Utils.checkForSmall(Sensors.getTipperDistance()-TIPPER_HALF,.1)==0){
            switch (Sensors.senseBridge()) {
                case Sensors.AWAY_FROM_BRIDGE:
                    Hardware.driveSystem.setSlow(false);
                    Hardware.driveSystem.setStop(true);
                    readTipControls();
                    break;
                case Sensors.NEAR_BRIDGE:
                    Hardware.driveSystem.setSlow(true);
                    Hardware.driveSystem.setStop(false);
                    break;
                case Sensors.AT_BRIDGE:
                    Hardware.driveSystem.setSlow(false);
                    Hardware.driveSystem.setStop(true);
                    tipBridge();
                    break;
            }
            }
        }
    public void autoHorizontal(){
        tipperLifter.set((TIPPER_HALF < Sensors.getTipperDistance()) ? 1 : -1);
            targetHeight = TIPPER_HALF;
    }
    /**
     * autonomous tipping
     * @param b has the tip been activated
     * @return true if tipping got activated or tipper is full down.
     */
    public boolean autoTip(boolean b){
            tipperLifter.set(.25*(Sensors.getTipperDistance()-targetHeight));
            if(Sensors.getTipperDistance()>=TIPPER_DOWN)
                   return true;
            else if(b)
                   return false;
            else if(Sensors.getTipperDistance()==TIPPER_HALF){
            switch (Sensors.senseBridge()) {
                case Sensors.AWAY_FROM_BRIDGE:
                    Hardware.driveSystem.setSlow(false);
                    Hardware.driveSystem.setStop(true);
                    readTipControls();
                    break;
                case Sensors.NEAR_BRIDGE:
                    Hardware.driveSystem.setSlow(true);
                    Hardware.driveSystem.setStop(false);
                    break;
                case Sensors.AT_BRIDGE:
                    Hardware.driveSystem.setSlow(false);
                    Hardware.driveSystem.setStop(true);
                    tipBridge();
                    break;
            }
            }
            return false;
        }
 }
