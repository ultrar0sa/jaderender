package edu.umn.d.cs1622.jaderender;

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

public class JadeRender extends Application {
    private static int canvasCenterX;
    private static int canvasCenterY;
    private static int canvasWidth = 500;
    private static int canvasHeight = 500;
    private static JadeVector2D canvasOrigin;
    private static PixelWriter writer;
    private static PerspectiveCamera camera;



    @Override
    public void start(Stage stage) throws IOException {
        Canvas canvas = new Canvas(500, 500);
        canvas.setOnMouseClicked(e -> {System.out.println("x: " + e.getX() + "| y: " + e.getY());});
        writer = canvas.getGraphicsContext2D().getPixelWriter();
        camera = new PerspectiveCamera();

        canvasCenterX = (int) (canvas.getWidth() / 2);
        canvasCenterY = (int) (canvas.getWidth() / 2);
        canvasOrigin = new JadeVector2D(canvasCenterX, canvasCenterY);

        boolean doLines = false;
        if(doLines) {
            JadeLine2D blue = new JadeLine2D(new JadeVector2D(0.0f, 0.0f), new JadeVector2D(500.0f, 500.0f));
            JadeLine2D red = new JadeLine2D(new JadeVector2D(0.0f, 500.0f), new JadeVector2D(500.0f, 0.0f));
            JadeLine2D green = new JadeLine2D(new JadeVector2D(0.0f, canvasCenterY), new JadeVector2D((float) canvas.getWidth(), canvasCenterY));
            JadeLine2D purple = new JadeLine2D(new JadeVector2D(canvasCenterX, 0.0f), new JadeVector2D(canvasCenterX, (float) canvas.getHeight()));
            blue.drawLine(new JadeRGB(Color.BLUE));
            red.drawLine(new JadeRGB(Color.RED));
            green.drawLine(new JadeRGB(Color.GREEN));
            purple.drawLine(new JadeRGB(Color.PURPLE));

            JadeLine2D teal = new JadeLine2D(new JadeVector2D(0.0f, 0.0f), new JadeVector2D(50.0f, 500.0f));
            JadeLine2D orange = new JadeLine2D(new JadeVector2D(0.0f, 0.0f), new JadeVector2D(500.0f, 20.0f));
            JadeLine2D darkGreen = new JadeLine2D(new JadeVector2D(0.0f, 500.0f), new JadeVector2D(500.0f, 250.0f));
            JadeLine2D hotpink = new JadeLine2D(new JadeVector2D(0.0f, 500.0f), new JadeVector2D(100.0f, 0.0f));
            teal.drawLine(new JadeRGB(Color.TEAL));
            orange.drawLine(new JadeRGB(Color.ORANGE));
            darkGreen.drawLine(new JadeRGB(Color.DARKGREEN));
            hotpink.drawLine(new JadeRGB(Color.HOTPINK));
        }

        boolean do2DTriangles = false;
        if(do2DTriangles) {
            JadeTriangle2D pleaseJustFuckingWorkTriangle = new JadeTriangle2D(new JadeVector2D(100.0f, 100.0f), new JadeVector2D(100.0f, 200.0f), new JadeVector2D(200.0f, 200.0f), new JadeRGB(7, 21, 205), new JadeRGB(181, 54, 218), new JadeRGB(74, 201, 37)); //do not ask where i got the colors.
            pleaseJustFuckingWorkTriangle.drawTriangle(new JadeRGB(Color.HOTPINK)); //first time works. happiness.

            JadeTriangle2D thisShouldNotWork = new JadeTriangle2D(new JadeVector2D(100.0f, 100.0f), new JadeVector2D(100.0f, 200.0f), new JadeVector2D(0, 100.0f), new JadeRGB(7, 21, 205), new JadeRGB(181, 54, 218), new JadeRGB(74, 201, 37)); //do not ask where i got the colors.
            thisShouldNotWork.drawTriangle(new JadeRGB(Color.RED)); //it does now! (this was checking what happens when you draw triangles right next to each other. it now has no gaps!)
        }

        JadeTriangle3D pleaseworkagain = new JadeTriangle3D(new JadeVector3D(.75f, .75f, 1.0f), new JadeVector3D(.75f, .25f, 1.0f), new JadeVector3D(.5f, .5f, 1.0f)); // https://www.youtube.com/watch?v=pqE66RltUaQ
        pleaseworkagain.projectToScreenSpace(camera).drawTriangle(new JadeRGB(Color.HOTPINK));
        //JadeLine.drawLine(new JadeVector2D(canvasCenterX, (float) canvas.getHeight()), new JadeVector2D(canvasCenterX, 0.0f), Color.PURPLE );


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
        JadeTriangle3D pleaseworkagain = new JadeTriangle3D(new JadeVector3D(.75f, .75f, 1.0f), new JadeVector3D(.75f, .25f, 1.0f), new JadeVector3D(.5f, .5f, 1.0f)); // https://www.youtube.com/watch?v=pqE66RltUaQ
        pleaseworkagain.projectToScreenSpace(camera).drawTriangle(new JadeRGB(Color.HOTPINK));
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