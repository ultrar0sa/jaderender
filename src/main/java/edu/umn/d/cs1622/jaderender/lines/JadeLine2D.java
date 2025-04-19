package edu.umn.d.cs1622.jaderender.lines;

import edu.umn.d.cs1622.jaderender.JadeRGB;
import edu.umn.d.cs1622.jaderender.JadeRender;
import edu.umn.d.cs1622.jaderender.vectors.JadeVector2D;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

public class JadeLine2D {
    private float x0;
    private float y0;
    private float x1;
    private float y1;

    private JadeVector2D point0;
    private JadeVector2D point1;

    public JadeLine2D(JadeVector2D point0, JadeVector2D point1){
        if(point0.get(0) > point1.get(0)){
            this.point0 = point1;
            this.point1 = point0;
        } else {
            this.point0 = point0;
            this.point1 = point1;
        }
        x0 = point0.get(0);
        y0 = point0.get(1);
        x1 = point1.get(0);
        y1 = point1.get(1);
    }

    public void drawLine(JadeRGB jadeColor){//see potential optimization on pg. 163
        Color color = jadeColor.toColor();
        PixelWriter writer = JadeRender.getWriter();
        float slope = (y1 - y0)/(x1 - x0);
        System.out.println("slope: " + slope);
        if(Float.isInfinite(slope)) {
            if (slope == Float.NEGATIVE_INFINITY) {
                for (int y = (int) y0; y > y1; y--) {
                    writer.setColor((int) x0, y, color);
                }
            } else {
                for (int y = (int) y0; y < y1; y++) {
                    writer.setColor((int) x0, y, color);
                }
            }
            return;
        }
        if(slope < -1){
            int x = (int) x0;
            for(int y = (int) y0; y > y1; y--){
                writer.setColor(x, y, color);
                if(implictLine(x + .5f, y -1) < 0){
                    x += 1;
                }
            }
        } else if (slope < 0){
            int y = (int) y0;
            for(int x = (int) x0; x < x1; x++){
                writer.setColor(x, y, color);
                if(implictLine(x + 1, y - .5f) > 0){
                    y -= 1;
                }
            }
        } else if(slope <= 1.0f) {
            int y = (int) y0;
            for (int x = (int) x0; x < x1; x++) {
                writer.setColor(x, y, color);
                if (implictLine(x + 1, y + .5f) < 0) {
                    y += 1;
                }
            }
        } else {  //slope > 1
            int x = (int) x0;
            for(int y = (int) y0; y < y1; y++){
                writer.setColor(x, y, color);
                if(implictLine(x + .5f, y + 1) > 0){
                    x += 1;
                }
            }
        }
    }

    public float implictLine(float x, float y){
        float value = (y0 - y1) * x + (x1 - x0) * y + x0*y1 -x1*y0;
        return value;
    }

    public JadeVector2D getPoint0() {
        return point0;
    }

    public JadeVector2D getPoint1() {
        return point1;
    }
}
