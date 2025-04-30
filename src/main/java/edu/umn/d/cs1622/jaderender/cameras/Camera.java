package edu.umn.d.cs1622.jaderender.cameras;

import edu.umn.d.cs1622.jaderender.vectors.JadeVector3D;

public abstract class Camera {
    protected JadeVector3D eyePos;
    protected JadeVector3D gazeDir;
    protected JadeVector3D viewup;

    protected JadeVector3D w;
    protected JadeVector3D u;
    protected JadeVector3D v;
    public abstract JadeVector3D projection(JadeVector3D vectorToProject);

    public void updatePos(JadeVector3D eyeDir){
        eyePos = eyePos.vectorAdd(eyeDir);
    }


    public void updateGaze(JadeVector3D newGazeDir){
        gazeDir = gazeDir.vectorAdd(newGazeDir);
        System.out.println(gazeDir);
    }
}
