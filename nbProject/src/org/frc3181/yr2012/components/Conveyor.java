package org.frc3181.yr2012.components;

import edu.wpi.first.wpilibj.SpeedController;

/**
 * The conveyor that moves balls up the robot.
 * @author Robbie
 * @author Ben
 */
public class Conveyor {

    private SpeedController mover;

    public Conveyor(SpeedController sc) {
        mover = sc;
    }

    public void moveBalls() {
        mover.set(1.00);
    }
}
