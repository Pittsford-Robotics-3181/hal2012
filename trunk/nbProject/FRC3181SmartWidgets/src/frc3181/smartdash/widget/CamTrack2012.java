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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.regex.Pattern;

/**
 *
 * @author Chris Cheng
 */
public class CamTrack2012 extends StaticWidget {
    
    public static final String NAME = "Camera Tracking Facilitator for 2012";
    
    CamTrackThread ctThread = new CamTrackThread(this);
    
    CamTrack2012Panel panel;

    @Override
    public void init() {
        ctThread.start();
        panel = new CamTrack2012Panel();
        setPreferredSize(new Dimension(256, 128));
        add(panel);
    }

    @Override
    public void propertyChanged(Property prprt) {
    }
    
}

class CamTrackThread extends Thread {
    private Socket skt;
    private CamTrack2012 wdgt;
    
    public CamTrackThread(CamTrack2012 input) {
        super("CamTrackThread");
        wdgt = input;
        try {
            skt = new Socket("localhost", 3181);
        } catch (Exception ex) {}
    }

    @Override
    public void run() {
        ObjectInputStream input;
        try {
            input = new ObjectInputStream(skt.getInputStream());
            while(true) { if(input.available() > 0) {
                String in = (String) input.readObject();
                String[] alpha = Pattern.compile(";").split(in);
                CamPoint[] beta = new CamPoint[alpha.length];
                double ax = 0, ay = 0;
                for(int i = 0; i < alpha.length; i++) {
                    String[] charlie = Pattern.compile(",").split(alpha[i]);
                    beta[i] = new CamPoint(Integer.parseInt(charlie[1]),Integer.parseInt(charlie[2]));
                }
                ax = CamPoint.averageX(beta); ay = CamPoint.averageY(beta);
                ArrayList<CamPoint> xy = new ArrayList<CamPoint>(1), xY = new ArrayList<CamPoint>(1), Xy = new ArrayList<CamPoint>(1), XY = new ArrayList<CamPoint>(1);
                //Ben wanted a comment: lowercase means less than average, uppercase means more than average.
                for(int i = 0; i < beta.length; i++) {
                    if(beta[i].x >= ax && beta[i].y >= ay) XY.add(beta[i]);
                    if(beta[i].x <= ax && beta[i].y >= ay) xY.add(beta[i]);
                    if(beta[i].x >= ax && beta[i].y <= ay) Xy.add(beta[i]);
                    if(beta[i].x <= ax && beta[i].y <= ay) xy.add(beta[i]);
                }
                
                Double[] dist = new Double[xy.size()];
                for(int i = 0; i < xy.size(); i++) {
                    dist[i] = Math.sqrt(Math.pow(xy.get(i).x - ax, 2) + Math.pow(xy.get(i).y - ay, 2));
                }
                Double[] dists = new Double[xy.size()];
                System.arraycopy(dist, 0, dists, 0, dist.length);
                Arrays.sort(dists, Collections.reverseOrder());
                for(int i = 0; i < dist.length; i++) {
                    if(dists[1].doubleValue() == dist[i].doubleValue()) {
                        Robot.getTable().putInt("blx", xy.get(i).x);
                        Robot.getTable().putInt("bly", xy.get(i).y);
                        wdgt.panel.bl.setText("(" + xy.get(i).x + "," + xy.get(i).y + ")");
                    }
                }
                
                dist = new Double[xY.size()];
                for(int i = 0; i < xY.size(); i++) {
                    dist[i] = Math.sqrt(Math.pow(xY.get(i).x - ax, 2) + Math.pow(xY.get(i).y - ay, 2));
                }
                dists = new Double[xY.size()];
                System.arraycopy(dist, 0, dists, 0, dist.length);
                Arrays.sort(dists, Collections.reverseOrder());
                for(int i = 0; i < dist.length; i++) {
                    if(dists[1].doubleValue() == dist[i].doubleValue()) {
                        Robot.getTable().putInt("tlx", xY.get(i).x);
                        Robot.getTable().putInt("tly", xY.get(i).y);
                        wdgt.panel.tl.setText("(" + xY.get(i).x + "," + xY.get(i).y + ")");
                    }
                }
                
                dist = new Double[Xy.size()];
                for(int i = 0; i < Xy.size(); i++) {
                    dist[i] = Math.sqrt(Math.pow(Xy.get(i).x - ax, 2) + Math.pow(Xy.get(i).y - ay, 2));
                }
                dists = new Double[Xy.size()];
                System.arraycopy(dist, 0, dists, 0, dist.length);
                Arrays.sort(dists, Collections.reverseOrder());
                for(int i = 0; i < dist.length; i++) {
                    if(dists[1].doubleValue() == dist[i].doubleValue()) {
                        Robot.getTable().putInt("brx", Xy.get(i).x);
                        Robot.getTable().putInt("bry", Xy.get(i).y);
                        wdgt.panel.br.setText("(" + Xy.get(i).x + "," + Xy.get(i).y + ")");
                    }
                }
                
                dist = new Double[XY.size()];
                for(int i = 0; i < XY.size(); i++) {
                    dist[i] = Math.sqrt(Math.pow(XY.get(i).x - ax, 2) + Math.pow(XY.get(i).y - ay, 2));
                }
                dists = new Double[XY.size()];
                System.arraycopy(dist, 0, dists, 0, dist.length);
                Arrays.sort(dists, Collections.reverseOrder());
                for(int i = 0; i < dist.length; i++) {
                    if(dists[1].doubleValue() == dist[i].doubleValue()) {
                        Robot.getTable().putInt("trx", XY.get(i).x);
                        Robot.getTable().putInt("try", XY.get(i).y);
                        wdgt.panel.tr.setText("(" + XY.get(i).x + "," + XY.get(i).y + ")");
                    }
                }
                
                Robot.getTable().putDouble("cx", ax);
                Robot.getTable().putDouble("cy", ay);
                wdgt.panel.c.setText("(" + ax + "," + ay + ")");
            } }
        } catch (Exception ex) {}
     }
        
}

class CamPoint {
    public int x;
    public int y;
    
    public CamPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public static double averageX(CamPoint[] pts) {
        double rtn = 0.0;
        for(int i = 0; i < pts.length; i++) {
            rtn += pts[i].x;
        }
        rtn /= pts.length;
        return rtn;
    }
    
    public static double averageY(CamPoint[] pts) {
        double rtn = 0.0;
        for(int i = 0; i < pts.length; i++) {
            rtn += pts[i].y;
        }
        rtn /= pts.length;
        return rtn;
    }
}