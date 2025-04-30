package edu.umn.d.cs1622.jaderender;

import edu.umn.d.cs1622.jaderender.cameras.Camera;
import edu.umn.d.cs1622.jaderender.cameras.PerspectiveCamera;
import edu.umn.d.cs1622.jaderender.triangles.JadeTriangle3D;
import edu.umn.d.cs1622.jaderender.vectors.JadeVector3D;
import javafx.scene.paint.Color;


public class JadePlane extends Shape {
    private JadeTriangle3D triangle0;
    private JadeTriangle3D triangle1;

    private JadeVector3D point0;
    private JadeVector3D point1;
    private JadeRGB color;

    public JadePlane(JadeVector3D point0, JadeVector3D point1, JadeRGB color){
        this.point0 = point0;
        this.point1 = point1;
        this.color = color;

        triangle0 = new JadeTriangle3D(point0, new JadeVector3D(point0.get(0), point1.get(1), point0.get(2)), new JadeVector3D(point1.get(0), point0.get(1), point1.get(2)), color);
        triangle1 = new JadeTriangle3D(point1, new JadeVector3D(point1.get(0), point0.get(1), point1.get(2)),  new JadeVector3D(point0.get(0), point1.get(1), point0.get(2)), color);
    }

    public void drawShape(Camera camera){
        triangle0.drawShape(camera);
        triangle1.drawShape(camera);

    }
}
