package edu.umn.d.cs1622.jaderender.cameras;


import edu.umn.d.cs1622.jaderender.JadeMatrix;
import edu.umn.d.cs1622.jaderender.vectors.JadeVector3D;

public class OrthographicCamera extends Camera {
    public OrthographicCamera(){
        eyePos = new JadeVector3D(0.0f, 0.0f, 0.0f);
        viewup = new JadeVector3D(0.0f, 1.0f, 0.0f);
        gazeDir = new JadeVector3D(0.0f, 0.0f, -1.0f);

        w = gazeDir.scalarDivision(-gazeDir.length());
        u = viewup.crossProduct(w).scalarDivision(viewup.crossProduct(w).length());
        v = w.crossProduct(u);
    }

    @Override
    public JadeVector3D projection(JadeVector3D vectorToProject) {
        w = gazeDir.scalarDivision(-gazeDir.length());
        u = viewup.crossProduct(w).scalarDivision(viewup.crossProduct(w).length());
        v = w.crossProduct(u);

        JadeMatrix eyeToOrigin = new JadeMatrix(new float[][]{{1.0f, 0.0f, 0.0f, -eyePos.get(0)},{0.0f, 1.0f, 0.0f, -eyePos.get(1)},{0.0f, 0.0f, 1.0f, -eyePos.get(2)},{0.0f, 0.0f, 0.0f, 1.0f}});
        vectorToProject = eyeToOrigin.transform(vectorToProject);
        JadeMatrix xyzAlignment = new JadeMatrix(new float[][]{{u.get(0), u.get(1), u.get(2), 0.0f},{v.get(0), v.get(1), v.get(2), 0.0f},{w.get(0), w.get(1), w.get(2), 0.0f},{0.0f, 0.0f, 0.0f, 1.0f}});
        return xyzAlignment.transform(vectorToProject);
    }
}

