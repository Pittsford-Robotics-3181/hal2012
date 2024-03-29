package org.frc3181.yr2012;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.frc3181.yr2012.hybrid.*;
import org.frc3181.yr2012.litecomponents.Sensors;

import org.frc3181.yr2012.hybrid.KinectGestures;

/**
 * The main class for our 2012 robot, HAL.
 * @author Chris Cheng
 * @author Ben Lowenstein
 * @author Eric Lee
 * @author Liam Middlebrook
 * @author Robbie Markwick
 */
public class HAL extends IterativeRobot implements DeathMachine {

    public static final int HYBRID_NOTHING = 0;
    public static final int HYBRID_TIP = 1;
    public static final int HYBRID_SHOOT = 2;
    public static final int HYBRID_KINECT = 4;
    Timer tT = new Timer();
    String state = "";
    int autono = HYBRID_SHOOT;
    boolean gameOver = false;
    DriverStation driveStation = DriverStation.getInstance();

    /**
     * This function is run when the robot starts up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        KinectGestures.initKinect();
        Hardware.lights.setAlliance(driveStation.getAlliance());
    }

    /**
     * This function is called as soon as it enters the disabled state.
     */
    public void disabledInit() {
        state = "Disabled";
        Hardware.DSOut.clearOutput();
        Hardware.DSOut.say(1, "State: " + state);
        tT.stop();
        tT.reset();
    }

    /**
     * This function is called periodically during disabled.
     */
    public void disabledPeriodic() {
        updateDash();
        if (gameOver) {
            Hardware.lights.whiteOn();
            Hardware.lights.discoMode();
        } else {
            Hardware.lights.whiteOn();
            Hardware.lights.setAlliance(driveStation.getAlliance());
        }
    }

    //------------$*$*$*$*$*$*$*$*AUTONOMOUS METHODS*$*$*$*$*$*$*$*$------------//
    /**
     * This function is run when autonomous mode begins.
     */
    public void autonomousInit() {
        tT.start();
        state = "Autonomous";
        Hardware.DSOut.clearOutput();
        Hardware.DSOut.say(1, "State: " + state);
        Hardware.lights.setAlliance(driveStation.getAlliance());
        Hardware.lights.whiteOn();
        updateDash();
        switch (autono) {
            case (HYBRID_NOTHING):
                HybridNothing.init();
                break; //Do nothing
            case (HYBRID_TIP):
                HybridTip.init();
                break;//Tip the bridge
            case (HYBRID_SHOOT):
                HybridShoot.init();
                break;//Shoot
            case (HYBRID_KINECT):
                HybridKinect.init();
                break;//Kinect mode!
        }
    }

    /**
     * This function is called periodically during autonomous.
     */
    public void autonomousPeriodic() {
        Hardware.lights.setAlliance(driveStation.getAlliance());
        updateDash();
        Hardware.lights.whiteOn();

        switch (autono) {
            case (HYBRID_NOTHING):
                HybridNothing.run();
                break; //Do nothing
            case (HYBRID_TIP):
                HybridTip.run();
                break;//Tip the bridge
            case (HYBRID_SHOOT):
                HybridShoot.run();
                break;//Shoot
            case (HYBRID_KINECT):
                HybridKinect.run();
                break;//Kinect mode!
        }
    }

    //------------$*$*$*$*$*$*$*$*TELEOP METHODS*$*$*$*$*$*$*$*$------------//
    /**
     * This function is run when teleop mode begins.
     */
    public void teleopInit() {
        tT.start();
        state = "Teleoperated";
        Hardware.DSOut.clearOutput();
        Hardware.DSOut.say(1, "State: " + state);
        Hardware.lights.setAlliance(driveStation.getAlliance());
        Hardware.lights.whiteOn();
        Hardware.bridgeTip.moveTipperTo(0);
        gameOver = true; // this boolean will not be checked until the robot is disabled again, making it safe it enter discoMode at endGame
    }

    /**
     * This function is called periodically during operator control.
     */
    public void teleopPeriodic() {
        ControlScheme.update();
        Hardware.lights.setAlliance(driveStation.getAlliance());
        Hardware.lights.whiteOn();
        //update dashboard
        updateDash();

        //drive
        Hardware.driveSystem.mecanumDrive();

        //Control Roller (ball collector)
        Hardware.collector.controlRoller();

        //Control Shooter
        if (ControlScheme.isShooting) {
            Hardware.ballLauncher.shootAtSpeed(-.6);
        } else {
            Hardware.ballLauncher.shootAtSpeed(0);
        }

        //Control Tipper
        if (ControlScheme.tipperUp) {
            Hardware.bridgeTip.moveTipperUp(10);
        } else if (ControlScheme.tipperDown) {
            Hardware.bridgeTip.moveTipperDown(10);
        } else {
            Hardware.tip.set(0);
        }

        //Control the stopper peg thing
        Hardware.stopper.controlStopper();

    }

    /**
     * Updates the Dashboard.
     * @author Chris Cheng (2012)
     */
    public void updateDash() {
        SmartDashboard.putString("State", state);
        SmartDashboard.putDouble("Front Left", Math.floor(Hardware.frontLeftMotor.get() * 128));
        SmartDashboard.putDouble("Front Right", Math.floor(Hardware.frontRightMotor.get() * 128));
        SmartDashboard.putDouble("Rear Left", Math.floor(Hardware.rearLeftMotor.get() * 128));
        SmartDashboard.putDouble("Rear Right", Math.floor(Hardware.rearRightMotor.get() * 128));
        double t = tT.get();
        SmartDashboard.putString("Time", (int) Math.floor(t / 60) + ":" + ((t % 60) < 10 ? "0" : "") + (int) Math.floor(t % 60));

        SmartDashboard.putInt("Encoder", Sensors.tipperSensor.get());
        SmartDashboard.putBoolean("Lower Limit Switch", Sensors.lowLimit.get());
        try {
            autono = SmartDashboard.getInt("Autonomous");
        } catch (Exception ex) {
        }
    }

    /**
     * How we dispose of those who bother us (e.g., beat us in a match).
     * Also, how we live up to our robot's name.
     */
    public void killPeople() {
        //become HAL9000
        Hardware.DSOut.say(1, "I can't let you do that, Dave.");
        Hardware.DSOut.say(2, "I can't let you do that, Dave.");
        Hardware.DSOut.say(3, "I can't let you do that, Dave.");
        Hardware.DSOut.say(4, "I can't let you do that, Dave.");
        Hardware.DSOut.say(5, "I can't let you do that, Dave.");
        Hardware.DSOut.say(6, "I can't let you do that, Dave.");
    }

    /**
     * Method implemented because of interface DeathMachine.
     * @return How many people we've killed.
     */
    public int numKilled() {
        //it's over 9000!!!
        return 9001;
    }
}