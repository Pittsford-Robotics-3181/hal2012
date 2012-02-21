/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package frc3181.smartdash.widget;

import edu.wpi.first.smartdashboard.gui.StaticWidget;
import edu.wpi.first.smartdashboard.properties.Property;
import edu.wpi.first.wpilibj.networking.NetworkTable;

/**
 *
 * @author Chris Cheng
 */
public class FRC3181Widget extends StaticWidget {
    
    public static final String NAME = "3181 Dashboard Widget";
    
    FRC3181Panel panel = new FRC3181Panel();
    int autonoSelect = 0;
    

    @Override
    public void init() {
        NetworkTable.setTeam(3181);
        this.add(panel);
    }

    @Override
    public void propertyChanged(Property prprt) {
    }
    
}