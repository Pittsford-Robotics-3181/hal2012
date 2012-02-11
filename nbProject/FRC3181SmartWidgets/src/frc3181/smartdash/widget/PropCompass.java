/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package frc3181.smartdash.widget;

import edu.wpi.first.smartdashboard.gui.StaticWidget;
import edu.wpi.first.smartdashboard.properties.Property;
import edu.wpi.first.smartdashboard.properties.StringProperty;
import edu.wpi.first.smartdashboard.robot.Robot;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

/**
 *
 * @author Chris Cheng
 */
public class PropCompass extends StaticWidget {
    
    public static final String NAME = "Compass w/ Proportional Arrow";
    
    public final StringProperty propD = new StringProperty(this, "Direction Key", "Direction"), propM = new StringProperty(this, "Magnitude Key", "Magnitude");

    @Override
    public void init() {
        setPreferredSize(new Dimension(128, 128));
        Graphics g = getGraphics();
        g.setColor(Color.red);
        g.drawOval(1, 1, 128, 128); //r = 64
        Graphics ring = g.create();
        boolean moar = true;
        while(moar) {
            try {wait(1/20);} catch (Exception ex) {}
            while(Robot.getTable().getString("State").equals("Teleoperated")) {
                try {wait(1/20);} catch (Exception ex) {}
                g = ring.create();
                double d = Robot.getTable().getDouble(propD.getValue());
                double m = Robot.getTable().getDouble(propM.getValue());
                g.drawLine(getSize().width/2, getSize().height/2, (int) ((getSize().width/2)+m*((getSize().width/2)*(Math.sin(Math.toRadians(d))))), (int) ((getSize().height/2)+m*((getSize().height/2)*(Math.cos(Math.toRadians(d))))));
            }
        }
    }

    @Override
    public void propertyChanged(Property prprt) {
    }
    
    
    
}
