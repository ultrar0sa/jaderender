package edu.umn.d.cs1622.jaderender;

import edu.umn.d.cs1622.jaderender.vectors.JadeVector3D;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Unfinished clipping util. Do not use.
 * @author Jade Lukken
 */
public class JadeClipping { //unfinished, to be implemented over summer.

    private static ArrayList<Float> hNear;
    private static ArrayList<Float> hFar;
    private static ArrayList<Float> hBottom;
    private static ArrayList<Float> hTop;
    private static ArrayList<Float> hLeft;
    private static ArrayList<Float> hRight;
    private static ArrayList<ArrayList<Float>> listOfPlanes;
    public static void checkClipping(JadeVector3D point, JadeVector3D leftBottomNear, JadeVector3D rightTopFar){
        hNear = new ArrayList<>(Arrays.asList(0.0f, 0.0f, -1.0f, -leftBottomNear.get(2)));
        hFar = new ArrayList<>(Arrays.asList(0f, 0f, 1f, rightTopFar.get(2)));
        hBottom = new ArrayList<>(Arrays.asList(0f, leftBottomNear.get(2), leftBottomNear.get(1), 0f));
        hTop = new ArrayList<>(Arrays.asList(0f, -leftBottomNear.get(2), rightTopFar.get(1), 0f));
        hLeft = new ArrayList<>(Arrays.asList(leftBottomNear.get(0), leftBottomNear.get(2), 0f, 0f));
        hRight = new ArrayList<>(Arrays.asList(-rightTopFar.get(0), -leftBottomNear.get(2), 0f, 0f));
        listOfPlanes = new ArrayList<>(Arrays.asList(hNear, hFar, hBottom, hTop, hLeft, hRight));
        ArrayList<Float> hPoint = new ArrayList<>(Arrays.asList(point.get(0), point.get(1), point.get(2), 1.0f));

        for(ArrayList<Float> plane : listOfPlanes){
            if(!checkPlane(plane, hPoint)){
                int planeIndex = listOfPlanes.indexOf(plane);
                System.out.println("need clip: " + point + "| index: "  + planeIndex);
            };
        }

    }

    public static boolean checkPlane(ArrayList<Float> planeVector, ArrayList<Float> hPoint){
        float d = 0.0f;
        for(int i = 0; i < hPoint.size(); i++){
            d += hPoint.get(i)*planeVector.get(i);
        }

        if(d >= 0.0f){
            return true;
        } else {
            return false;
        }
    }
}
