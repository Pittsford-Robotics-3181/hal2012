/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package frc3181.smartdash.widget;

import edu.wpi.first.smartdashboard.gui.DashboardFrame;
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
    
    public final StringProperty propD = new StringProperty(this, "Direction Key", ""), propM = new StringProperty(this, "Magnitude Key", ""), propR = new StringProperty(this, "Rotation Key", "");

    private CompassThread cThread = new CompassThread();

    @Override
    public void init() {
        setPreferredSize(new Dimension(128, 128));
        cThread.start();
    }

    @Override
    public void propertyChanged(Property prprt) {
    }

    @Override
    public void paint(Graphics g) {
        Dimension size = getSize();

        g.setColor(Color.red);
        g.drawOval(1, 1, size.width-1, size.height-1); //r = 64
        double d = 0.0;
        if(!(propD.getValue().isEmpty()))
            d = Robot.getTable().getDouble(propD.getValue());
        double m = 0.0;
        if(!(propM.getValue().isEmpty()))
            m = Robot.getTable().getDouble(propM.getValue()) / 100;
        double r = 0.0;
        if(!(propR.getValue().isEmpty()))
            r = Robot.getTable().getDouble(propR.getValue()) / 100 * 360;
        g.drawLine(getSize().width/2, getSize().height/2, (int) ((getSize().width/2)+m*((getSize().width/2)*(Math.sin(Math.toRadians(d))))), (int) ((getSize().height/2)-m*((getSize().height/2)*(Math.cos(Math.toRadians(d))))));
        g.drawArc(getSize().width/4, getSize().height/4, getSize().width/2, getSize().height/2, 90, (int) (0-r));
    }

    @Override
    public void disconnect() {
        super.disconnect();
    }
    
    class CompassThread extends Thread {
        boolean destroyed = false;
        
        public CompassThread() {
            super("PropCompassThread");
        }
        
        @Override
        public void run() {
            while(true) {
                DashboardFrame.getInstance().getPanel().repaint(getBounds());
            }
        }
        
    }


}
