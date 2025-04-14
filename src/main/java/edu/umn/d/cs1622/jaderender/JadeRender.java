package edu.umn.d.cs1622.jaderender;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class JadeRender extends Application {
    private static int canvasCenterX;
    private static int canvasCenterY;
    private static JadeVector2D canvasOrigin;
    private static PixelWriter writer;
    @Override
    public void start(Stage stage) throws IOException {
        Canvas canvas = new Canvas(500, 500);
        canvas.setOnMouseClicked(e -> {System.out.println("x: " + e.getX() + "| y: " + e.getY());});
        writer = canvas.getGraphicsContext2D().getPixelWriter();


        canvasCenterX = (int) (canvas.getWidth() / 2);
        canvasCenterY = (int) (canvas.getWidth() / 2);
        canvasOrigin = new JadeVector2D(canvasCenterX, canvasCenterY);

        boolean doLines = true;
        if(doLines) {

            JadeLine blue = new JadeLine(new JadeVector2D(0.0f, 0.0f), new JadeVector2D(500.0f, 500.0f));
            JadeLine red = new JadeLine(new JadeVector2D(0.0f, 500.0f), new JadeVector2D(500.0f, 0.0f));
            JadeLine green = new JadeLine(new JadeVector2D(0.0f, canvasCenterY), new JadeVector2D((float) canvas.getWidth(), canvasCenterY));
            JadeLine purple = new JadeLine(new JadeVector2D(canvasCenterX, 0.0f), new JadeVector2D(canvasCenterX, (float) canvas.getHeight()));
            blue.drawLine(Color.BLUE);
            red.drawLine(Color.RED);
            green.drawLine(Color.GREEN);
            purple.drawLine(Color.PURPLE);

            JadeLine teal = new JadeLine(new JadeVector2D(0.0f, 0.0f), new JadeVector2D(50.0f, 500.0f));
            JadeLine orange = new JadeLine(new JadeVector2D(0.0f, 0.0f), new JadeVector2D(500.0f, 20.0f));
            JadeLine darkGreen = new JadeLine(new JadeVector2D(0.0f, 500.0f), new JadeVector2D(500.0f, 250.0f));
            JadeLine hotpink = new JadeLine(new JadeVector2D(0.0f, 500.0f), new JadeVector2D(100.0f, 0.0f));
            teal.drawLine(Color.TEAL);
            orange.drawLine(Color.ORANGE);
            darkGreen.drawLine(Color.DARKGREEN);
            hotpink.drawLine(Color.HOTPINK);

//            JadeLine.drawLine(new JadeVector2D(0.0f, 0.0f), new JadeVector2D(500.0f, 500.0f), Color.BLUE);
//
//            JadeLine.drawLine(new JadeVector2D(0.0f, 500.0f), new JadeVector2D(500.0f, 0.0f), Color.RED);
//
//            JadeLine.drawLine(new JadeVector2D(0.0f, canvasCenterY), new JadeVector2D((float) canvas.getWidth(), canvasCenterY), Color.GREEN);
//
//            JadeLine.drawLine(new JadeVector2D(canvasCenterX, 0.0f), new JadeVector2D(canvasCenterX, (float) canvas.getHeight()), Color.PURPLE);
//
//            System.out.println("teal");
//            JadeLine.drawLine(new JadeVector2D(0.0f, 0.0f), new JadeVector2D(50.0f, 500.0f), Color.TEAL);
//            System.out.println("orange");
//            JadeLine.drawLine(new JadeVector2D(0.0f, 0.0f), new JadeVector2D(500.0f, 2.0f), Color.ORANGE);
//            System.out.println("dark green");
//            JadeLine.drawLine(new JadeVector2D(0.0f, 500.0f), new JadeVector2D(500.0f, 250.0f), Color.DARKGREEN);
//            System.out.println("hotpink");
//            JadeLine.drawLine(new JadeVector2D(0.0f, 500.0f), new JadeVector2D(100.0f, 0.0f), Color.HOTPINK);
        }

        //JadeLine.drawLine(new JadeVector2D(canvasCenterX, (float) canvas.getHeight()), new JadeVector2D(canvasCenterX, 0.0f), Color.PURPLE );



        Group group = new Group(canvas);
        Scene scene = new Scene(group);
        stage.setTitle("JadeRender");
        stage.setScene(scene);
        //stage.setResizable(false);
        stage.show();
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
}