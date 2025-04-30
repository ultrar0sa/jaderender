package edu.umn.d.cs1622.jaderender;

import edu.umn.d.cs1622.jaderender.cameras.Camera;
import edu.umn.d.cs1622.jaderender.cameras.OrthographicCamera;
import edu.umn.d.cs1622.jaderender.cameras.PerspectiveCamera;
import edu.umn.d.cs1622.jaderender.lines.JadeLine2D;
import edu.umn.d.cs1622.jaderender.triangles.JadeTriangle2D;
import edu.umn.d.cs1622.jaderender.triangles.JadeTriangle3D;
import edu.umn.d.cs1622.jaderender.vectors.JadeVector2D;
import edu.umn.d.cs1622.jaderender.vectors.JadeVector3D;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.PixelWriter;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class JadeRender extends Application {

    private static int canvasCenterX;
    private static int canvasCenterY;
    private static JadeVector3D leftBottomNear;
    private static JadeVector3D rightTopFar;
    private static int[][] zbuffer;
    private static int canvasWidth = 500;
    private static int canvasHeight = 500;
    private static JadeVector2D canvasOrigin;
    private static PixelWriter writer;
    private static Camera camera;

    public static void calculateFrustrum(float right, float top, float far, float near){
        rightTopFar = new JadeVector3D(right*(canvasWidth/canvasHeight), top*(canvasWidth/canvasHeight), far);
        leftBottomNear = new JadeVector3D(1,1, -far);
    }

    public static JadeVector3D getLeftBottomNear() {
        return leftBottomNear;
    }

    public static JadeVector3D getRightTopFar() {
        return rightTopFar;
    }

    @Override
    public void start(Stage stage) throws IOException {
        Canvas canvas = new Canvas(canvasWidth, canvasHeight);
        zbuffer = new int[canvasHeight][canvasWidth];
        for(int row = 0; row < zbuffer.length; ++row){
            for(int col = 0; col < zbuffer[row].length; ++col){
                zbuffer[row][col] = Integer.MAX_VALUE;
            }
        }

        canvas.setOnMouseClicked(e -> {System.out.println("x: " + e.getX() + "| y: " + e.getY());});
        writer = canvas.getGraphicsContext2D().getPixelWriter();
        camera = new PerspectiveCamera();

        canvasCenterX = (int) (canvas.getWidth() / 2);
        canvasCenterY = (int) (canvas.getHeight()/ 2);
        calculateFrustrum(-1,-1, -1, 1); //lmao

        canvasOrigin = new JadeVector2D(canvasCenterX, canvasCenterY);

        drawStuff();

        Group group = new Group(canvas);
        Scene scene = new Scene(group);

        scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
//            if(key.getCode()== KeyCode.A) {
//               camera.updatePos(new JadeVector3D(-0.1f, 0.0f, 0.0f));
//            } else if(key.getCode()== KeyCode.D){
//                camera.updatePos(new JadeVector3D(0.1f, 0.0f, 0.0f));
//            } else if
            switch(key.getCode()){
                case KeyCode.A:
                    camera.updatePos(new JadeVector3D(-0.1f, 0.0f, 0.0f));
                    break;
                case KeyCode.D:
                    camera.updatePos(new JadeVector3D(0.1f, 0.0f, 0.0f));
                    break;
                case KeyCode.W:
                    camera.updatePos(new JadeVector3D(0.0f, -0.1f, 0.0f));
                    break;
                case KeyCode.S:
                    camera.updatePos(new JadeVector3D(0.0f, 0.1f, 0.0f));
                    break;
                case KeyCode.Q:
                    camera.updatePos(new JadeVector3D(0.0f, 0.0f, 0.1f));
                    break;
                case KeyCode.E:
                    camera.updatePos(new JadeVector3D(0.0f, 0.0f, -0.1f));
                    break;

                case KeyCode.J:
                    camera.updateGaze(new JadeVector3D(-0.05f, 0.0f, 0.0f));
                    break;
                case KeyCode.L:
                    camera.updateGaze(new JadeVector3D(0.05f, 0.0f, 0.0f));
                    break;
                case KeyCode.I:
                    camera.updateGaze(new JadeVector3D(0.0f, 0.05f, 0.0f));
                    break;
                case KeyCode.K:
                    camera.updateGaze(new JadeVector3D(0.0f, -0.05f, 0.0f));
                    break;
                case KeyCode.U:
                    camera.updateGaze(new JadeVector3D(0.0f, 0.0f, 0.1f));
                    break;
                case KeyCode.O:
                    camera.updateGaze(new JadeVector3D(0.0f, 0.0f, -0.1f));
                    break;

            }
            canvas.getGraphicsContext2D().clearRect(0, 0, canvasWidth, canvasHeight);
            drawStuff();
        });

        stage.setTitle("JadeRender");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();


    }

    public static void drawStuff(){
        JadePlane plane = new JadePlane(new JadeVector3D(1f, 1f, 5.0f), new JadeVector3D(0.0f, 0.0f, 5.0f), new JadeRGB(Color.HOTPINK));
        ArrayList<Shape> listOfShapes = new ArrayList<>();
        listOfShapes.add(plane);
       // JadePlane plane2 = new JadePlane(new JadeVector3D(.3f, .3f, 1.0f), new JadeVector3D(.70f, .70f, 1.0f), new JadeRGB(Color.GREEN));

        for(Shape shape : listOfShapes){
            shape.drawShape(camera);
        }

        System.out.println(rightTopFar);
        System.out.println(leftBottomNear);

        //plane2.drawShape(camera);
    }
    public static void main(String[] args) {
        launch();
    }

    public static int getCanvasCenterX() {
        return canvasCenterX;
    }

    public static int getCanvasCenterY() {
        return canvasCenterY;
    }

    public static JadeVector2D getCanvasOrigin(){
        return canvasOrigin;
    }

    public static PixelWriter getWriter() {
        return writer;
    }

    public static int getCanvasHeight() {
        return canvasHeight;
    }

    public static int getCanvasWidth() {
        return canvasWidth;
    }
}