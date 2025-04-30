package edu.umn.d.cs1622.jaderender.triangles;

import edu.umn.d.cs1622.jaderender.JadeRGB;
import edu.umn.d.cs1622.jaderender.Shape;
import edu.umn.d.cs1622.jaderender.cameras.Camera;
import edu.umn.d.cs1622.jaderender.cameras.OrthographicCamera;
import edu.umn.d.cs1622.jaderender.cameras.PerspectiveCamera;
import edu.umn.d.cs1622.jaderender.vectors.JadeVector2D;
import edu.umn.d.cs1622.jaderender.vectors.JadeVector3D;

public class JadeTriangle3D extends Shape {
    private JadeVector3D vertex0;
    private JadeVector3D vertex1;
    private JadeVector3D vertex2;
    private JadeRGB color;


    public JadeTriangle3D(JadeVector3D vertex0, JadeVector3D vertex1, JadeVector3D vertex2, JadeRGB color) {
        this.vertex0 = vertex0;
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.color = color;
    }




    public JadeTriangle2D projectToScreenSpace(Camera camera){
        JadeVector2D v02D = vertex0.projectToScreenspace(camera);
        JadeVector2D v12D = vertex1.projectToScreenspace(camera);
        JadeVector2D v22D = vertex2.projectToScreenspace(camera);

        return new JadeTriangle2D(v02D, v12D, v22D, null, null, null);

    }


    public void drawShape(Camera camera){
        projectToScreenSpace(camera).drawTriangle(color);
    }

}
