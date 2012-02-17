/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package frc3181.smartdash.widget;

import edu.wpi.first.smartdashboard.gui.StaticWidget;
import edu.wpi.first.smartdashboard.properties.Property;
import edu.wpi.first.smartdashboard.robot.Robot;
import java.awt.Dimension;
import java.io.ObjectInputStream;
import java.net.Socket;
import javax.swing.JLabel;

/**
 *
 * @author Chris Cheng
 */
public class CamTrack2012 extends StaticWidget {
    
    public static final String NAME = "Camera Tracking Facilitator for 2012";
    
    CamTrackThread ctThread = new CamTrackThread();

    @Override
    public void init() {
        ctThread.start();
        JLabel l = new JLabel("Don't delete me, please!\nI manage the Camera Tracker!");
        setPreferredSize(new Dimension(64, 128));
        l.setPreferredSize(new Dimension(64, 128));
        add(l);
    }

    @Override
    public void propertyChanged(Property prprt) {
    }
    
}

class CamTrackThread extends Thread {
    private Socket skt;
    
    public CamTrackThread() {
        super("CamTrackThread");
        try {
            skt = new Socket("localhost", 3181);
        } catch (Exception ex) {}
    }

    @Override
    public void run() {
        ObjectInputStream input;
        try {
            input = new ObjectInputStream(skt.getInputStream());
            while(true) { if(input.available() > 0) { Robot.getTable().putString("CamTrack", (String) input.readObject()); } }
        } catch (Exception ex) {}
     }
        
}