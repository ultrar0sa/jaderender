package edu.umn.d.cs1622.jaderender;

import edu.umn.d.cs1622.jaderender.cameras.Camera;
import edu.umn.d.cs1622.jaderender.cameras.PerspectiveCamera;

public abstract class Shape {
    public abstract void drawShape(Camera camera);
}
