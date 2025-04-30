package edu.umn.d.cs1622.jaderender;

import edu.umn.d.cs1622.jaderender.vectors.JadeVector3D;
import javafx.scene.paint.Color;

public class JadeRGB extends JadeVector3D {
    public JadeRGB(){
        vector[0] = 255;
        vector[1] = 135;
        vector[2] = 255;
        //pink as a default
    }

    public JadeRGB(int r, int g, int b){
        vector[0] = r;
        vector[1] = g;
        vector[2] = b;

        clampRGB();
    }

    public JadeRGB(Color color){
        vector[0] = (float) color.getRed() * 255;
        vector[1] = (float) color.getGreen() * 255;
        vector[2] = (float) color.getBlue() * 255;

        clampRGB(); //better to be safe than sorry
    }

    public void clampRGB(){
        vector[0] = Math.clamp(vector[0], 0, 255);
        vector[1] = Math.clamp(vector[1], 0, 255);
        vector[2] = Math.clamp(vector[2], 0, 255);
    }

    public JadeRGB clampRGB(JadeVector3D vector3D){
        vector3D.set(0, Math.clamp(vector3D.get(0), 0, 255));
        vector3D.set(1, Math.clamp(vector3D.get(1), 0, 255));
        vector3D.set(2, Math.clamp(vector3D.get(2), 0, 255));
        return new JadeRGB((int) vector3D.get(0), (int) vector3D.get(1), (int) vector3D.get(2));
    }

    public Color toColor(){
        return Color.rgb((int) vector[0], (int) vector[1], (int) vector[2]);
    }
}
