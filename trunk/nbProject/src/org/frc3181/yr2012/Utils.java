package org.frc3181.yr2012;

/**
 * Contains useful functions that don't fit anywhere else.
 * @author Ben Lowenstein (2011)
 */
public class Utils {
    /**
     * Converts a boolean to 0 or 1.
     * @param bool The boolean to convert
     * @return 0 if bool=false, 1 if bool=true
     */
    public static int toInt(boolean bool) {
        return bool ? 1 : 0;
    }

    /**
     * Converts value to a 3-digit binary number.
     * @param value The value to convert
     * @return The binary representation of the value (e.g. 011)
     */
    public static String toBinary(int value) {
        String returnString = "";
        returnString += value >> 2;
        returnString += value >> 1 & 1;
        returnString += value & 1;
        return returnString;
    }

    /**
     * Checks if a value is small enough to be considered zero.
     * @param value The value to check
     * @return 0, if abs(value) is less than .05; value, otherwise
     */
    public static double checkForSmall(double value) {
        if(Math.abs(value) < .05)
            return 0;
        return value;
    }

    /**
     * Checks if a value is small enough to be considered zero, with a specified
     * tolerance.
     * @param value The value to check
     * @param tolerance The largest absolute value that can be considered 0.
     * @return 0, if abs(value) is less than tolerance; value, otherwise
     */
    public static double checkForSmall(double value, double tolerance) {
        if(Math.abs(value) < tolerance)
            return 0;
        return value;
    }
}
