/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.frc3181.yr2012;

import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.camera.AxisCameraException;
import edu.wpi.first.wpilibj.image.ColorImage;
import edu.wpi.first.wpilibj.image.NIVisionException;

/**
 *
 * @author Liam
 */
public class Camera {
    
    AxisCamera camera = Hardware.camera;
    ColorImage frame;
    ColorImage getImage() throws AxisCameraException, NIVisionException
    {
    frame = camera.getImage();  
    return frame;
    }
    
}
