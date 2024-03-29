/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package frc3181.smartdash.widget;

import edu.wpi.first.smartdashboard.gui.Widget;
import edu.wpi.first.smartdashboard.properties.Property;
import edu.wpi.first.smartdashboard.types.DataType;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

/**
 *
 * @author Chris Cheng
 */
public class VerticalBar extends Widget {
    
    public static final String NAME = "Vertical Percentage Bar";
    public static final DataType[] TYPES = { DataType.NUMBER };
    
    private double value = 0.0;

    @Override
    public void setValue(Object o) {
        value = ((Number) o).doubleValue();
        
        repaint();
    }

    @Override
    public void init() {
        setPreferredSize(new Dimension(64,200));
    }

    @Override
    public void propertyChanged(Property prprt) {
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Dimension size = getSize();
        
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, size.width, size.height);
        
        int barHeight = (int) (size.height * value / 100.0);
        g.setColor(Color.GREEN);
        g.fillRect(0, size.height - barHeight, size.width, barHeight);
        
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, size.width - 1, size.height - 1);
    }
    
}
