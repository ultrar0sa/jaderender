package edu.umn.d.cs1622.jaderender.cameras;

import edu.umn.d.cs1622.jaderender.JadeMatrix;
import edu.umn.d.cs1622.jaderender.vectors.JadeVector3D;

public class PerspectiveCamera extends Camera {
    public PerspectiveCamera(){
        eyePos = new JadeVector3D(0.0f, 0.0f, 0.0f);
        viewup = new JadeVector3D(0.0f, 1.0f, 0.0f);
        gazeDir = new JadeVector3D(0.0f, 0.0f, -1.0f);

        w = gazeDir.scalarDivision(-gazeDir.length());
        u = viewup.crossProduct(w).scalarDivision(viewup.crossProduct(w).length());
        v = w.crossProduct(u);
    }

    public void updatePos(JadeVector3D eyeDir){
        eyePos = eyePos.vectorAdd(eyeDir);
        System.out.println("eyePos: " + eyePos);

    }


    public void updateGaze(JadeVector3D newGazeDir){
        gazeDir = gazeDir.vectorAdd(newGazeDir);
        System.out.println("gazePos: " + gazeDir);
    }

    @Override
    public JadeVector3D projection(JadeVector3D vectorToProject) {
        w = gazeDir.scalarDivision(-gazeDir.length());
        u = viewup.crossProduct(w).scalarDivision(viewup.crossProduct(w).length());
        v = w.crossProduct(u);

        JadeMatrix eyeToOrigin = new JadeMatrix(new float[][]{{1.0f, 0.0f, 0.0f, -eyePos.get(0)},{0.0f, 1.0f, 0.0f, -eyePos.get(1)},{0.0f, 0.0f, 1.0f, -eyePos.get(2)},{0.0f, 0.0f, 0.0f, 1.0f}});
        vectorToProject = eyeToOrigin.transform(vectorToProject);
        //System.out.println(vector3D);
        JadeMatrix xyzAlignment = new JadeMatrix(new float[][]{{u.get(0), u.get(1), u.get(2), 0.0f},{v.get(0), v.get(1), v.get(2), 0.0f},{w.get(0), w.get(1), w.get(2), 0.0f},{0.0f, 0.0f, 0.0f, 1.0f}});
        //System.out.println(xyzAlignment.transform(vector3D));

        return xyzAlignment.transform(vectorToProject);
    }
}
