/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.frc3181.yr2012;


import edu.wpi.first.wpilibj.AnalogModule;
import edu.wpi.first.wpilibj.Dashboard;
import edu.wpi.first.wpilibj.DigitalModule;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStationEnhancedIO.EnhancedIOException;
import edu.wpi.first.wpilibj.DriverStationEnhancedIO.tAccelChannel;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Solenoid;

/**
 * The main class for our robot
 * @author Chris Cheng
 * @author Ben
 * @author Eric
 * @author Liam
 */
public class HAL extends IterativeRobot {
    /*
     * // Autonomous mode
   
    int autonoMode = 0;

    public static double ana2; // Buttons 1-6
    public static double ana3; // Buttons 7-10
    public static double ana1; // Buttons 12-14
    public static boolean digital2; // Arm control
    public static boolean digital4; // Three-way toggle for lifter
    public static boolean digital6; // Three-way toggle for lifter
    public static boolean digital8; // Minibot deployment
    // Accelerometer values
    public static double AccelX;
    public static double AccelY;
    public static double AccelZ;
*/

    /**
     * This function is run when the robot starts up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        
       
    }

    /**
     * This function is called periodically during disabled. It determines the
     * autonomous mode that we want, based on the Cypress accelerometer.
     */
    public void disabledPeriodic() {
        updateEnhancedIO();

        // Determine which autonomous mode we want and tell the drivers
    }


    //------------$*$*$*$*$*$*$*$*AUTONOMOUS METHODS*$*$*$*$*$*$*$*$------------//

    /**
     * This function is run when autonomous mode begins. It determines which
     * autonomous mode we are using and starts the timer and compressor. It also
     * updating the EnhancedIO inputs.
     */
    public void autonomousInit() {
       
        
    }

    /**
     * This function is called periodically during autonomous. It runs the
     * selected autonomous mode.
     */
    public void autonomousPeriodic() {

        updateDashboard();
        
    }


    //------------$*$*$*$*$*$*$*$*TELEOP METHODS*$*$*$*$*$*$*$*$------------//

    /**
     * This function is run when teleop mode begins. It starts the timer and
     * compressor and updates the EnhancedIO inputs.
     */
    public void teleopInit() {
        

        updateEnhancedIO();
    }

    /**
     * This function is called periodically during operator control.
     */
    public void teleopPeriodic() {
        
        updateDashboard();
        updateEnhancedIO();

        //**************** BEGIN DRIVE CODE ****************//
        
        //**************** END DRIVE CODE ****************//



        //System.out.print("Button Up:" + getBoxButton(17) + " Button Down:" + getBoxButton(16) + "\n");

        // Check if the minibot is "unlocked"
        
        // Control the arm, claw, and lifter
        
        // Check if Minibot is to be deployed or retracted.
        
    }
    

     // <editor-fold defaultstate="collapsed" desc="void HAL.updateDashboard()">
    /**
     * Shows data on driver station dashboard. For an explanation, see link:
     * http://www.chiefdelphi.com/forums/showthread.php?t=90167
     */
    void updateDashboard() {
        Dashboard lowDashData = DriverStation.getInstance().getDashboardPackerLow();
        lowDashData.addCluster();
        {
            lowDashData.addCluster();
            {     //analog modules
                lowDashData.addCluster();
                {
                    for (int i = 1; i <= 8; i++) {
                        lowDashData.addFloat((float) AnalogModule.getInstance(1).getAverageVoltage(i));
                    }
                }
                lowDashData.finalizeCluster();
                lowDashData.addCluster();
                {
                    for (int i = 1; i <= 8; i++) {
                        lowDashData.addFloat((float) AnalogModule.getInstance(2).getAverageVoltage(i));
                    }
                }
                lowDashData.finalizeCluster();
            }
            lowDashData.finalizeCluster();

            lowDashData.addCluster();
            { //digital modules
                lowDashData.addCluster();
                {
                    lowDashData.addCluster();
                    {
                        int module = 4;
                        lowDashData.addByte(DigitalModule.getInstance(module).getRelayForward());
                        lowDashData.addByte(DigitalModule.getInstance(module).getRelayForward());
                        lowDashData.addShort(DigitalModule.getInstance(module).getAllDIO());
                        lowDashData.addShort(DigitalModule.getInstance(module).getDIODirection());
                        lowDashData.addCluster();
                        {
                            for (int i = 1; i <= 10; i++) {
                                lowDashData.addByte((byte) DigitalModule.getInstance(module).getPWM(i));
                            }
                        }
                        lowDashData.finalizeCluster();
                    }
                    lowDashData.finalizeCluster();
                }
                lowDashData.finalizeCluster();

                lowDashData.addCluster();
                {
                    lowDashData.addCluster();
                    {
                        int module = 6;
                        lowDashData.addByte(DigitalModule.getInstance(module).getRelayForward());
                        lowDashData.addByte(DigitalModule.getInstance(module).getRelayReverse());
                        lowDashData.addShort(DigitalModule.getInstance(module).getAllDIO());
                        lowDashData.addShort(DigitalModule.getInstance(module).getDIODirection());
                        lowDashData.addCluster();
                        {
                            for (int i = 1; i <= 10; i++) {
                                lowDashData.addByte((byte) DigitalModule.getInstance(module).getPWM(i));
                            }
                        }
                        lowDashData.finalizeCluster();
                    }
                    lowDashData.finalizeCluster();
                }
                lowDashData.finalizeCluster();

            }
            lowDashData.finalizeCluster();

            lowDashData.addByte(Solenoid.getAllFromDefaultModule());

        }
        lowDashData.finalizeCluster();
        lowDashData.commit();

    }
    // </editor-fold>


    /**
     * Update the analog and digital inputs from the EnhancedIO module.
     */
    public void updateEnhancedIO() {
       /*
        try{
            ana1 = Hardware.dseio.getAnalogIn(1);
            ana3 = Hardware.dseio.getAnalogIn(3);
            ana2 = Hardware.dseio.getAnalogIn(2);
            digital2 = Hardware.dseio.getDigital(2);
            digital4 = Hardware.dseio.getDigital(4);
            digital6 = Hardware.dseio.getDigital(6);
            digital8 = Hardware.dseio.getDigital(8);
            AccelX = Hardware.dseio.getAcceleration(tAccelChannel.kAccelX);
            AccelY = Hardware.dseio.getAcceleration(tAccelChannel.kAccelY);
            AccelZ = Hardware.dseio.getAcceleration(tAccelChannel.kAccelZ);
        } catch (EnhancedIOException ex) {
            System.out.println(ex);
        }
             * */
             
    }

    /**
     * This checks whether a specific button on the button box is being pressed
     * (this can also check the toggle switches). It is unclear why the bounds
     * for ana2 overlap.
     * @param button Which button (or toggle) to check
     * @return Whether the button is being pressed, or the toggle is true.
     */
    public static boolean getBoxButton(int button) {

        return false;
    }
}