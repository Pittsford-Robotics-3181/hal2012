package org.frc3181.yr2012.litecomponents;

import edu.wpi.first.wpilibj.SpeedController;

/**
 * The conveyor that moves balls up the robot. Its motor should always be on.
 * @author Ben
 * @author Robbie
 */
public class Conveyor {

    private SpeedController mover;

    public Conveyor(SpeedController sc) {
        mover = sc;
        init();
    }

    /**
     * Starts the balls rolling.
     */
    private void init() {
        mover.set(1.00);
    }
}
