package edu.umn.d.cs1622.jaderender.vectors;

import edu.umn.d.cs1622.jaderender.JadeClipping;
import edu.umn.d.cs1622.jaderender.JadeMatrix;
import edu.umn.d.cs1622.jaderender.JadeRender;
import edu.umn.d.cs1622.jaderender.cameras.Camera;
import edu.umn.d.cs1622.jaderender.cameras.OrthographicCamera;
import edu.umn.d.cs1622.jaderender.cameras.PerspectiveCamera;

public class JadeVector3D {
    protected float[] vector;

    public JadeVector3D(){
        vector = new float[3];
        vector[0] = 0.0f;
        vector[1] = 0.0f;
        vector[2] = 0.0f;
    }

    public JadeVector3D(float x, float y, float z){
        vector = new float[3];
        vector[0] = x;
        vector[1] = y;
        vector[2] = z;
    }

    public JadeVector3D vectorAdd(JadeVector3D toAdd){
        return new JadeVector3D(vector[0] + toAdd.get(0), vector[1] + toAdd.get(1), vector[2] + toAdd.get(2));
    }

    public JadeVector3D vectorSub(JadeVector3D toSub) {
        return new JadeVector3D(vector[0] - toSub.get(0), vector[1] - toSub.get(1), vector[2] - toSub.get(2));
    }

    public float dotProduct(JadeVector3D toMultiply) {
        float runningTotal = 0.0f;
        for(int i = 0; i < 3; i++){
            runningTotal += vector[i] * toMultiply.get(i);
        }
        return runningTotal;
    }

    public JadeVector3D crossProduct(JadeVector3D toMultiply){
        return new JadeVector3D(vector[1]*toMultiply.get(2) - vector[2]*toMultiply.get(1),
                                vector[2]*toMultiply.get(0) - vector[0]*toMultiply.get(2),
                                vector[0]*toMultiply.get(1) - vector[1]*toMultiply.get(0));
    }

    public JadeVector3D scalarMultiplication(float scalar) {
        return new JadeVector3D(vector[0]*scalar, vector[1]*scalar, vector[2]*scalar);
    }

    public JadeVector3D scalarDivision(float scalar) {
        return new JadeVector3D(vector[0]/scalar, vector[1]/scalar, vector[2]/scalar);
    }

    public JadeVector2D projectToScreenspace(Camera camera){
        if(camera instanceof OrthographicCamera){
            JadeVector3D projectedFromCamera = camera.projection(this);
            JadeVector3D transformed = JadeMatrix.viewplane(JadeMatrix.orthographicProjection(projectedFromCamera, new JadeVector3D(1,1,1), new JadeVector3D(-1,-1,-1)));
            //System.out.println("running");
            return new JadeVector2D(transformed.get(0), transformed.get(1));
        } else {
            JadeVector3D leftBottomNear = JadeRender.getLeftBottomNear();
            JadeVector3D rightTopFar = JadeRender.getRightTopFar();
            JadeClipping.checkClipping(this, leftBottomNear, rightTopFar);
            JadeVector3D projectedFromCamera = camera.projection(this);
            System.out.println("projected: " + projectedFromCamera);
            JadeVector3D transformed = JadeMatrix.viewplane(JadeMatrix.perspectiveProjection(projectedFromCamera, leftBottomNear,rightTopFar));
            System.out.println("transformed: " + transformed);
            //System.out.println("running");
            return new JadeVector2D(transformed.get(0), transformed.get(1));
        }
    }


    public float length(){
        return (float) Math.sqrt(Math.pow(vector[0], 2) + Math.pow(vector[1], 2)+ Math.pow(vector[2], 2));
    }

    public void set(int index, float value){
        vector[index] = value;
    }

    public float get(int index){
        return vector[index];
    }

    @Override
    public String toString() {
        return vector[0] + " | " + vector[1] + " | " + vector[2];
    }
}
