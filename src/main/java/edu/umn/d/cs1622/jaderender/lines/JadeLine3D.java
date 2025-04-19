package edu.umn.d.cs1622.jaderender.lines;

import edu.umn.d.cs1622.jaderender.JadeMatrix;
import edu.umn.d.cs1622.jaderender.JadeRGB;
import edu.umn.d.cs1622.jaderender.vectors.JadeVector2D;
import edu.umn.d.cs1622.jaderender.vectors.JadeVector3D;

public class JadeLine3D {
   private float x0;
   private float y0;
   private float z0;
   private float x1;
   private float y1;
   private float z1;

   private JadeVector3D point0;
   private JadeVector3D point1;

   public JadeLine3D(JadeVector3D point0, JadeVector3D point1){
       this.point0 = point0;
       this.point1 = point1;

       x0 = point0.get(0);
       y0 = point0.get(1);
       z0 = point0.get(2);

       x1 = point1.get(0);
       y1 = point1.get(1);
       z1 = point1.get(2);
   }

    public JadeVector3D getPoint0() {
        return point0;
    }

    public JadeVector3D getPoint1() {
        return point1;
    }

    private void projectToScreenSpace(JadeRGB color){
       JadeVector3D p = JadeMatrix.viewplane(JadeMatrix.orthographicProjection(point0, new JadeVector3D(1,1,1), new JadeVector3D(-1,-1,-1)));
       JadeVector3D q = JadeMatrix.viewplane(JadeMatrix.orthographicProjection(point1, new JadeVector3D(1,1,1), new JadeVector3D(-1,-1,-1)));

       JadeLine2D projection2D = new JadeLine2D(new JadeVector2D(p.get(0), p.get(1)), new JadeVector2D(q.get(0), q.get(1)));
       projection2D.drawLine(color);
    }




}
