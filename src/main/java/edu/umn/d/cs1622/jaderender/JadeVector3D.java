package edu.umn.d.cs1622.jaderender;

public class JadeVector3D {
    private float[] vector;

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

    public void set(int index, float value){
        vector[index] = value;
    }

    public float get(int index){
        return vector[index];
    }
}
