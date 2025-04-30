package edu.umn.d.cs1622.jaderender;

import edu.umn.d.cs1622.jaderender.vectors.JadeVector3D;

import java.util.ArrayList;
import java.util.Arrays;

public class JadeMatrix { //i hate having to write a generalized matrix class. i hate it. but it is necessary
    private float[][] matrix;


    public static JadeVector3D viewplane(JadeVector3D vector3D){
        JadeMatrix viewplaneTransform = new JadeMatrix(new float[][] {
                {(float)JadeRender.getCanvasWidth()/2, 0.0f, 0.0f, ((float)JadeRender.getCanvasWidth())-1.0f/2.0f},
                {0.0f, (float)JadeRender.getCanvasHeight()/2, 0.0f, ((float)JadeRender.getCanvasHeight())-1.0f/2.0f},
                {0.0f, 0.0f, 1.0f, 0.0f},
                {0.0f, 0.0f, 0.0f, 1.0f}});
        return viewplaneTransform.transform(vector3D);
    }

    public static JadeVector3D orthographicProjection(JadeVector3D vector3D, JadeVector3D leftBottomNear, JadeVector3D rightTopFar){
        JadeMatrix orthographicTransform = new JadeMatrix(new float[][] {
                {2.0f/(rightTopFar.get(0)-leftBottomNear.get(0)), 0.0f, 0.0f, -((rightTopFar.get(0) + leftBottomNear.get(0))/(rightTopFar.get(0)-leftBottomNear.get(0)))},
                {0.0f, 2.0f/(rightTopFar.get(1)-leftBottomNear.get(1)), 0.0f, -((rightTopFar.get(1) + leftBottomNear.get(1))/(rightTopFar.get(1)-leftBottomNear.get(1)))},
                {0.0f, 0.0f, 2.0f/(rightTopFar.get(2)-leftBottomNear.get(2)), -((rightTopFar.get(2) + leftBottomNear.get(2))/(rightTopFar.get(2)-leftBottomNear.get(2)))},
                {0.0f, 0.0f, 0.0f, 1.0f}});
        return orthographicTransform.transform(vector3D);
    }

    public static JadeVector3D perspectiveProjection(JadeVector3D vector3D, JadeVector3D leftBottomNear, JadeVector3D rightTopFar){
        JadeMatrix perspectiveTransform = new JadeMatrix(new float[][] {
                {leftBottomNear.get(2), 0.0f, 0.0f, 0.0f},
                {0.0f, leftBottomNear.get(2), 0.0f, 0.0f},
                {0.0f, 0.0f, leftBottomNear.get(2) + rightTopFar.get(2), -leftBottomNear.get(2)*rightTopFar.get(2)},
                {0.0f, 0.0f, 1.0f, 0.0f}});
        //System.out.println("perspective transform: " + perspectiveTransform.transform(vector3D));
       return orthographicProjection(perspectiveTransform.transform(vector3D),leftBottomNear, rightTopFar);


    }


    public JadeMatrix(){
       matrix = new float[4][4];
    }
    public JadeMatrix(float[][] matrix){
        this.matrix = matrix;
    }

    public JadeVector3D transform(JadeVector3D vectorToTransform){
        ArrayList<Float> hVector = new ArrayList<>(Arrays.asList(vectorToTransform.get(0), vectorToTransform.get(1), vectorToTransform.get(2), 1.0f));
        ArrayList<Float> transformedHVector = new ArrayList<>();

        for(int row = 0; row < matrix.length; row++){
            float value = 0.0f;
            for(int col = 0; col < matrix[row].length; col++){
                value += matrix[row][col] * hVector.get(col);
            }
            transformedHVector.add(value);
        }
        return new JadeVector3D(transformedHVector.get(0)/ transformedHVector.get(3), transformedHVector.get(1) / transformedHVector.get(3), transformedHVector.get(2) / transformedHVector.get(3));
    }

    public float[][] getMatrix() {
        return matrix;
    }
}
