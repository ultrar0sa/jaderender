package edu.umn.d.cs1622.jaderender.vectors;

public class JadeVector2D {
    private float[] vector;

    public JadeVector2D(){
        vector = new float[2];
        vector[0] = 0.0f;
        vector[1] = 0.0f;
    }

    public JadeVector2D(float x, float y){
        vector = new float[2];
        vector[0] = x;
        vector[1] = y;
    }


    public JadeVector2D vectorAdd(JadeVector2D toAdd) {
        return new JadeVector2D(vector[0] + toAdd.get(0), vector[1] + toAdd.get(1));
    }

    public JadeVector2D vectorSub(JadeVector2D toSub) {
        return new JadeVector2D(vector[0] - toSub.get(0), vector[1] - toSub.get(1));
    }

    public float dotProduct(JadeVector2D toMultiply) {
        float runningTotal = 0.0f;
        for(int i = 0; i < 2; i++){
            runningTotal += vector[i] * toMultiply.get(i);
        }
        return runningTotal;
    }

    public JadeVector2D scalarMultiplication(float scalar) {
        return new JadeVector2D(vector[0]*scalar, vector[1]*scalar);
    }

    public JadeVector2D scalarDivision(float scalar) {
        return new JadeVector2D(vector[0]/scalar, vector[1]/scalar);
    }

    public void set(int index, float value){
        vector[index] = value;
    }

    public float get(int index){
        return vector[index];
    }

    @Override
    public String toString() {
        return vector[0] + " | " + vector[1];
    }
}
