/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package frc3181.smartdash.widget;

import edu.wpi.first.smartdashboard.gui.Widget;
import edu.wpi.first.smartdashboard.properties.Property;
import edu.wpi.first.smartdashboard.types.DataType;
import java.awt.Dimension;
import javax.swing.JTextArea;

/**
 *
 * @author Chris Cheng
 */
public class MessageBoxExt extends Widget {
    
    public static final String NAME = "Message Box with History";
    public static final DataType[] TYPES = { DataType.STRING };
    
    private JTextArea ta;

    @Override
    public void setValue(Object o) {
        ta.append((String) o);
        
        ta.setSize(getSize());
    }

    @Override
    public void init() {
        ta = new JTextArea();
        ta.setPreferredSize(new Dimension(200,64));
        ta.setEnabled(false);
        setPreferredSize(new Dimension(200,64));
        this.add(ta);
    }

    @Override
    public void propertyChanged(Property prprt) {
    }
    
}
