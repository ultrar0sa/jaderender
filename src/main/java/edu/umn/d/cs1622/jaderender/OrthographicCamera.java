package edu.umn.d.cs1622.jaderender;


import edu.umn.d.cs1622.jaderender.vectors.JadeVector3D;

public class OrthographicCamera {
    private JadeVector3D eyePos;
    private JadeVector3D gazeDir;
    private JadeVector3D viewup;

    private JadeVector3D w;
    private JadeVector3D u;
    private JadeVector3D v;

    public OrthographicCamera(){
        eyePos = new JadeVector3D(0.0f, 0.0f, 0.0f);
        viewup = new JadeVector3D(0.0f, 1.0f, 0.0f);
        gazeDir = new JadeVector3D(0.0f, 0.0f, -1.0f);

        w = gazeDir.scalarDivision(-gazeDir.length());
        u = viewup.crossProduct(w).scalarDivision(viewup.crossProduct(w).length());
        v = w.crossProduct(u);
    }

    public JadeVector3D orthographicProjection(JadeVector3D vector3D){
        w = gazeDir.scalarDivision(-gazeDir.length());
        u = viewup.crossProduct(w).scalarDivision(viewup.crossProduct(w).length());
        v = w.crossProduct(u);

        JadeMatrix eyeToOrigin = new JadeMatrix(new float[][]{{1.0f, 0.0f, 0.0f, -eyePos.get(0)},{0.0f, 1.0f, 0.0f, -eyePos.get(1)},{0.0f, 0.0f, 1.0f, -eyePos.get(2)},{0.0f, 0.0f, 0.0f, 1.0f}});
        vector3D = eyeToOrigin.transform(vector3D);
        JadeMatrix xyzAlignment = new JadeMatrix(new float[][]{{u.get(0), u.get(1), u.get(2), 0.0f},{v.get(0), v.get(1), v.get(2), 0.0f},{w.get(0), w.get(1), w.get(2), 0.0f},{0.0f, 0.0f, 0.0f, 1.0f}});
        return xyzAlignment.transform(vector3D);
    }

    public void updatePos(JadeVector3D eyeDir){
        eyePos = eyePos.vectorAdd(eyeDir);
    }


    public void updateGaze(JadeVector3D newGazeDir){
        gazeDir = gazeDir.vectorAdd(newGazeDir);
        System.out.println(gazeDir);
    }

}

