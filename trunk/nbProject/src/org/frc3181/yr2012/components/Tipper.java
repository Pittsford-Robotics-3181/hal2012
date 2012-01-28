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
    public static final double TIPPER_UP = 1;
    public static final double TIPPER_HALF_UP = 2;
    public static final double TIPPER_HALF_DOWN = 3;
    public static final double TIPPER_DOWN=4;
    
    private Victor tipperLifter;
    private double targetHeight = TIPPER_UP;
    private boolean goDown = true;
    private boolean running = false;

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
    private void TipBridge() {
        Hardware.driveSystem.setStop(true);
        running = true;
        tipperLifter.set(-1);
        targetHeight = TIPPER_DOWN;
        goDown = true;
    }

    /**
     * Figure out what buttons are being pushed on joystick and act appropriately.
     */
    private void readTipControls() {
        if (Hardware.driveController.getRawButton(3)) {
            running = true;
            tipperLifter.set((TIPPER_UP < Hardware.sensorSet.findFoot()) ? 1 : -1);
            targetHeight = TIPPER_UP;
            goDown = (TIPPER_UP < Hardware.sensorSet.findFoot());
        } else if (Hardware.driveController.getRawButton(8)) {
            running = true;
            tipperLifter.set((TIPPER_HALF_UP < Hardware.sensorSet.findFoot()) ? 1 : -1);
            targetHeight = TIPPER_HALF_UP;
            goDown = (TIPPER_HALF_UP < Hardware.sensorSet.findFoot());
        } else if (Hardware.driveController.getRawButton(9)) {
            running = true;
            tipperLifter.set((TIPPER_HALF_DOWN < Hardware.sensorSet.findFoot()) ? 1 : -1);
            targetHeight = TIPPER_HALF_DOWN;
            goDown = (TIPPER_HALF_DOWN < Hardware.sensorSet.findFoot());
        } else if (Hardware.driveController.getRawButton(2)) {
            running = true;
            tipperLifter.set((TIPPER_DOWN < Hardware.sensorSet.findFoot()) ? 1 : -1);
            targetHeight = TIPPER_DOWN;
            goDown= (TIPPER_DOWN < Hardware.sensorSet.findFoot());
        }
    }

    /**
     * Control tipper.
     */
    public void controlTipper() {
        if (running) {
            if (((targetHeight <= Hardware.sensorSet.findFoot()) && (goDown)) || ((targetHeight >= Hardware.sensorSet.findFoot()) && (!goDown))) {
                running = false;
                tipperLifter.set(0);
            }
        }else {
            switch (Hardware.sensorSet.findBridge()) {
                case 0:
                    Hardware.driveSystem.setSlow(false);
                    readTipControls();
                    break;
                case 1:
                    Hardware.driveSystem.setSlow(true);
                    break;
                case 2: {
                    Hardware.driveSystem.setStop(true);
                    TipBridge();
                    break;
                }
            }
        }
    }
}
