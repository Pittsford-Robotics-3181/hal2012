package org.frc3181.yr2012.components;

import edu.wpi.first.wpilibj.Victor;
import org.frc3181.yr2012.Hardware;

/**
 * The mechanism that tips the bridge.
 * @author Robbie
 * @author Ben
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
        tipperLifter.set(-1);
        targetHeight = TIPPER_DOWN;
    }

    /**
     * Figure out what buttons are being pushed on joystick and act appropriately.
     */
    private void readTipControls() {
        if (Hardware.driveJoystick.getRawButton(3)) {
            tipperLifter.set((TIPPER_UP < Sensors.getTipperDistance()) ? 1 : -1);
            targetHeight = TIPPER_UP;
        } else if (Hardware.driveJoystick.getRawButton(6)) {
            tipperLifter.set((TIPPER_HALF < Sensors.getTipperDistance()) ? 1 : -1);
            targetHeight = TIPPER_HALF;
        } else if (Hardware.driveJoystick.getRawButton(2)) {
            tipperLifter.set((TIPPER_DOWN < Sensors.getTipperDistance()) ? 1 : -1);
            targetHeight = TIPPER_DOWN;
        }
         else if (Hardware.driveJoystick.getRawButton(7)) {
            tipperLifter.set((TIPPER_BACK < Sensors.getTipperDistance()) ? 1 : -1);
            targetHeight = TIPPER_BACK;
        }
    }

    /**
     * Control tipper.
     */
    public void controlTipper() {
            tipperLifter.set(.25*(Sensors.getTipperDistance()-targetHeight));
            if(Sensors.getTipperDistance()==TIPPER_HALF){
            switch (Sensors.senseBridge()) {
                case 0:
                    Hardware.driveSystem.setSlow(false);
                    readTipControls();
                    break;
                case Sensors.NEAR_BRIDGE:
                    Hardware.driveSystem.setSlow(true);
                    break;
                case Sensors.AT_BRIDGE: {
                    Hardware.driveSystem.setStop(true);
                    tipBridge();
                    break;
                }
            }
            }
        }
    public void autoHorizontal(){
        tipperLifter.set((TIPPER_HALF < Sensors.getTipperDistance()) ? 1 : -1);
            targetHeight = TIPPER_HALF;
    }
    public void autoTip(){
            tipperLifter.set(.25*(Sensors.getTipperDistance()-targetHeight));
            if(Sensors.getTipperDistance()==TIPPER_HALF){
            switch (Sensors.senseBridge()) {
                case 0:
                    Hardware.driveSystem.setSlow(false);
                    break;
                case Sensors.NEAR_BRIDGE:
                    Hardware.driveSystem.setSlow(true);
                    break;
                case Sensors.AT_BRIDGE: {
                    Hardware.driveSystem.setStop(true);
                    tipBridge();
                    break;
                }
            }
            }
        } 
 }
