package org.frc3181.yr2012;

/**
 * Kills people.
 * @author UNKNOWN
 */
public interface DeathMachine {
    public int killTarget = 1000000000;
    public abstract void killPeople();
    public abstract int numKilled();   
}
