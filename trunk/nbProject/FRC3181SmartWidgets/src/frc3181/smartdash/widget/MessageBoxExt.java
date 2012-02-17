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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Chris Cheng
 */
public class MessageBoxExt extends Widget {
    
    public static final String NAME = "Message Box with History";
    public static final DataType[] TYPES = { DataType.STRING };
    
    private JTextArea ta;
    private String last = "";

    @Override
    public void setValue(Object o) {
        String in = (String) o;
        if(last.equals(in) || last.isEmpty()) { last = ""; return; }
        ta.setRows(ta.getRows()+1);
        ta.append("\n"+in);
        last = in;
        Dimension size = new Dimension();
        size.width = getSize().width - 16;
        size.height = ta.getRows() * 12;
        ta.setPreferredSize(size);
    }

    @Override
    public void init() {
        ta = new JTextArea();
        ta.setPreferredSize(new Dimension(184,12));
        ta.setEditable(false);
        ta.setBackground(Color.white);
        JScrollPane sp = new JScrollPane(ta);
        sp.setPreferredSize(new Dimension(200,128));
        setPreferredSize(new Dimension(200,128));
        this.add(sp);
    }

    @Override
    public void propertyChanged(Property prprt) {
    }
    
}
