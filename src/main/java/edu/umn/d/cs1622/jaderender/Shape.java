package edu.umn.d.cs1622.jaderender;

import edu.umn.d.cs1622.jaderender.cameras.Camera;
import edu.umn.d.cs1622.jaderender.cameras.PerspectiveCamera;

/**
 * Basic Shape Abstract Superclass
 * @author Jade Lukken
 */
public abstract class Shape {
    /**
     * Draws this shape in the camera's screenspace.
     * @param camera Camera object
     */
    public abstract void drawShape(Camera camera);

}
