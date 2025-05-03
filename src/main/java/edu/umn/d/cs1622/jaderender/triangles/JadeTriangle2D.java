package edu.umn.d.cs1622.jaderender.triangles;

import edu.umn.d.cs1622.jaderender.*;
import edu.umn.d.cs1622.jaderender.lines.JadeLine2D;
import edu.umn.d.cs1622.jaderender.vectors.JadeVector2D;
import edu.umn.d.cs1622.jaderender.vectors.JadeVector3D;
import javafx.scene.image.PixelWriter;

public class JadeTriangle2D {
    private JadeVector2D vertex0;
    private JadeVector2D vertex1;
    private JadeVector2D vertex2;
    private JadeLine2D line01;
    private JadeLine2D line12;
    private JadeLine2D line20;
    private JadeVector3D vertex0Color;
    private JadeVector3D vertex1Color;
    private JadeVector3D vertex2Color;

    public JadeTriangle2D(JadeVector2D  vertex0, JadeVector2D vertex1, JadeVector2D vertex2, JadeVector3D v0Color, JadeVector3D v1Color, JadeVector3D v2Color){
       this.vertex0 = vertex0;
       this.vertex1 = vertex1;
       this.vertex2 = vertex2;
       System.out.println("vertex0: " + vertex0);
       System.out.println("vertex1: " + vertex1);
       System.out.println("vertex2: " + vertex2);

       this.vertex0Color = v0Color;
       this.vertex1Color = v1Color;
       this.vertex2Color = v2Color;

       line01 = new JadeLine2D(vertex0, vertex1);
       line12 = new JadeLine2D(vertex1, vertex2);
       line20 = new JadeLine2D(vertex2, vertex0);
    }

    /**
     * Draws a triangle in 2D space.
     * @param color
     */
    public void drawTriangle(JadeRGB color){
        PixelWriter writer = JadeRender.getWriter();
        float a;
        float b;
        float c;

        int xmin = (int) Math.floor(Math.min(Math.min(vertex0.get(0), vertex1.get(0)), vertex2.get(0)));
        int xmax = (int) Math.ceil(Math.max(Math.max(vertex0.get(0), vertex1.get(0)), vertex2.get(0)));

        int ymin = (int) Math.floor(Math.min(Math.min(vertex0.get(1), vertex1.get(1)), vertex2.get(1)));
        int ymax = (int) Math.ceil(Math.max(Math.max(vertex0.get(1), vertex1.get(1)), vertex2.get(1)));
        float implictA = line12.implictLine(vertex0.get(0),vertex0.get(1));
        float implictB = line20.implictLine(vertex1.get(0),vertex1.get(1));
        float implictC = line01.implictLine(vertex2.get(0),vertex2.get(1));
        //System.out.println("A: " + implictA + "\nB: " + implictB + "\nC: " + implictC);
        for(int y = ymin; y < ymax; y++){
            for(int x = xmin; x < xmax; x++){
                a = line12.implictLine(x,y)/implictA;
                b = line20.implictLine(x,y)/implictB;
                c = line01.implictLine(x,y)/implictC;
                //System.out.println("A: " + a + "\nB: " + b + "\nC: " + c);

                if(a >= 0 && b >= 0 && c >= 0){

                    if((a > 0 || implictA*-line12.implictLine(-1,-1) > 0 || implictA*line12.implictLine(-1,-1) > 0 )
                            &&  (b > 0 || implictB*-line20.implictLine(-1,-1)>0 || implictB*line20.implictLine(-1,-1)>0)
                            && (c > 0 || implictC*-line01.implictLine(-1,-1)>0 || implictC*line01.implictLine(-1,-1)>0)) {
                        if(color == null) {
                            color = new JadeRGB().clampRGB(vertex0Color.scalarMultiplication(a).vectorAdd(vertex1Color.scalarMultiplication(b).vectorAdd(vertex2Color.scalarMultiplication(c))));
                        }
                        //System.out.println("x: " + x + " | y: " + y);
                        if(!(x > JadeRender.getCanvasHeight() || y > JadeRender.getCanvasHeight() || x < 0.0f || y < 0.0f)){
                            writer.setColor(x, y, color.toColor());
                        }

                    }
                }
            }
        }
        //System.out.println("drawing");
    }


}
