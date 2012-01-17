/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.frc3181.dash;

/**
 *
 * @author Chris Cheng
 */
public class DashCluster implements DashObj {
    
    public DashObj[] objs = new DashObj[0];

    public void Add() {
        
    }

    public void Insert(DashObj item) {
        DashObj[] tempobjs = new DashObj[objs.length + 1];
        System.arraycopy(objs, 0, tempobjs, 0, objs.length);
        objs = new DashObj[tempobjs.length];
        System.arraycopy(tempobjs, 0, objs, 0, tempobjs.length-1);
        
    }
    
}
