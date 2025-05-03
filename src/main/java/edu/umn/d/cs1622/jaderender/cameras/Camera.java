package edu.umn.d.cs1622.jaderender.cameras;

import edu.umn.d.cs1622.jaderender.vectors.JadeVector3D;

/**
 * Basic Abstract Camera Superclass
 * @author Jade Lukken
 */
public abstract class Camera {
    protected JadeVector3D eyePos;
    protected JadeVector3D gazeDir;
    protected JadeVector3D viewup;

    protected JadeVector3D w;
    protected JadeVector3D u;
    protected JadeVector3D v;

    /**
     * Projects 3D point to be ready to be projected into screenspace.
     * @param vectorToProject
     * @return 3D to be projected to 2D screenspace
     */
    public abstract JadeVector3D projection(JadeVector3D vectorToProject);

    /**
     * Controls eye position.
     * @param eyeDir
     */
    public void updatePos(JadeVector3D eyeDir){
        eyePos = eyePos.vectorAdd(eyeDir);
    }


    /**
     * Controls gaze direction
     * @param newGazeDir
     */
    public void updateGaze(JadeVector3D newGazeDir){
        gazeDir = gazeDir.vectorAdd(newGazeDir);
        System.out.println(gazeDir);
    }
}
