package sample;



import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import navtest.load.NavMeshLoad;
import navtest.mesh.NavMeshInfo;
import navtest.mesh.Point;
import navtest.mesh.Triangle;
import navtest.navigation.Navigator;
import navtest.navigation.Route;

import java.util.ArrayList;

public class Main extends Application {
    public static final int size = 10;
    public static final int move = 50;
    public static final int s = 1;
    public static void main(String[] args) {
        launch(args);
    }



    @Override
        public void start(Stage primaryStage) {
            primaryStage.setTitle("Drawing Operations Test");
            Group root = new Group();
            Canvas canvas = new Canvas(1000, 1000);

            GraphicsContext gc = canvas.getGraphicsContext2D();

            drawShapes(gc);
            root.getChildren().add(canvas);
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        }

        private void drawShapes(GraphicsContext gc) {
            NavMeshLoad navMeshLoad = new NavMeshLoad();
            NavMeshInfo navMeshInfo = navMeshLoad.load("s3-2.obj");
            int n =0;
            for (ArrayList<Triangle> value : navMeshInfo.pointIndexes.values()) {
                n = value.size() > n ? value.size() : n;
            }

            System.out.println(n);

            Route route = Route.routeFactory(navMeshInfo,new Point(  -33.67773f, 0f, -26.30138f),new Point(36.24456f, 0, -14.75443f));
            Navigator navigator = new Navigator();
            navigator.start(route);
            gc.setStroke(Color.BLUE);
            gc.setLineWidth(0.5);
            gc.fillOval((0 *s +move) *size,(0*s +move) *size,10,10);
//            for (Triangle triangle : navMeshInfo.allTriangle) {
//                DrawTiangle(triangle,gc);
//            }
            gc.setStroke(Color.RED);
            for (Triangle triangle : route.viaTriangle) {
                DrawTiangle(triangle,gc);
            }

        }

//        private void DrawTiangle(Triangle triangle,GraphicsContext gc){
//            gc.strokeLine((triangle.a.x *s +move) *size,(triangle.a.z *s +move) *size,(triangle.b.x *s +move) *size,(triangle.b.z *s +move) *size);
//            gc.strokeLine((triangle.a.x *s +move) *size,(triangle.a.z *s +move) *size,(triangle.c.x *s +move) *size,(triangle.c.z *s +move) *size);
//            gc.strokeLine((triangle.c.x *s +move) *size,(triangle.c.z *s +move) *size,(triangle.b.x *s +move) *size,(triangle.b.z *s +move) *size);
//        }

        private void DrawTiangle(Triangle triangle,GraphicsContext gc){
            gc.strokeLine((triangle.a.z *s +move) *size,(triangle.a.x *s +move) *size,(triangle.b.z *s +move) *size,(triangle.b.x *s  +move) *size);
            gc.strokeLine((triangle.a.z *s +move) *size,(triangle.a.x *s +move) *size,(triangle.c.z *s +move) *size,(triangle.c.x *s +move) *size);
            gc.strokeLine((triangle.c.z *s +move) *size,(triangle.c.x *s +move) *size,(triangle.b.z *s +move)*size ,(triangle.b.x *s +move)*size);
        }

//        private void DrawTiangle(Triangle triangle,GraphicsContext gc){
//            gc.strokeLine((triangle.a.x *s +move) *size,(-triangle.a.z *s +move) *size,(triangle.b.x *s +move) *size,(-triangle.b.z *s +move) *size);
//            gc.strokeLine((triangle.a.x *s +move) *size,(-triangle.a.z *s +move) *size,(triangle.c.x *s +move) *size,(-triangle.c.z *s +move) *size);
//            gc.strokeLine((triangle.c.x *s +move) *size,(-triangle.c.z *s +move) *size,(triangle.b.x *s +move) *size,(-triangle.b.z *s +move) *size);
//        }

    }

