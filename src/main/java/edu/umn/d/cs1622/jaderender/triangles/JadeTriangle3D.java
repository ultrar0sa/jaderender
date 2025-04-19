package edu.umn.d.cs1622.jaderender.triangles;

import edu.umn.d.cs1622.jaderender.OrthographicCamera;
import edu.umn.d.cs1622.jaderender.vectors.JadeVector2D;
import edu.umn.d.cs1622.jaderender.vectors.JadeVector3D;

public class JadeTriangle3D {
    private JadeVector3D vertex0;
    private JadeVector3D vertex1;
    private JadeVector3D vertex2;


    public JadeTriangle3D(JadeVector3D vertex0, JadeVector3D vertex1, JadeVector3D vertex2) {
        this.vertex0 = vertex0;
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
    }

    public JadeTriangle2D projectToScreenSpace(OrthographicCamera camera){
        JadeVector2D v02D = vertex0.projectToScreenspace(camera);
        JadeVector2D v12D = vertex1.projectToScreenspace(camera);
        JadeVector2D v22D = vertex2.projectToScreenspace(camera);

//        System.out.println(v02D);
//        System.out.println(v12D);
//        System.out.println(v22D);

        return new JadeTriangle2D(v02D, v12D, v22D, null, null, null);

    }

}
