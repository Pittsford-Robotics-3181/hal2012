/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.frc3181.dash;

/**
 * Interface shared between every type of object that can go on the Dash.
 * @author Chris Cheng
 */
public interface DashObj {
    
    public abstract void Insert(DashObj item);
    public abstract void Add();
}
