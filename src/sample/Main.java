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
import navtest.mesh.Triangle;

public class Main extends Application {
public static final int size = 10;
    public static final int move = 50;
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
            NavMeshInfo navMeshInfo = navMeshLoad.load("s3.obj");
            gc.setFill(Color.GREEN);
            gc.setStroke(Color.BLUE);
            gc.setLineWidth(1);
            for (Triangle triangle : navMeshInfo.allTriangle) {
                DrawTiangle(triangle,gc);
            }
            gc.strokeLine(0,0,0,10);

        }

//        private void DrawTiangle(Triangle triangle,GraphicsContext gc){
//            gc.strokeLine((triangle.a.x / 10000 +move) *size,(triangle.a.z / 10000 +move) *size,(triangle.b.x/ 10000  +move) *size,(triangle.b.z/ 10000  +move) *size);
//            gc.strokeLine((triangle.a.x/ 10000 +move) *size,(triangle.a.z /10000 +move) *size,(triangle.c.x /10000 +move) *size,(triangle.c.z/10000 +move) *size);
//            gc.strokeLine((triangle.c.x/10000+move) *size,(triangle.c.z /10000 +move)*size,(triangle.b.x/10000 +move)*size,(triangle.b.z /10000 +move)*size);
//        }

        private void DrawTiangle(Triangle triangle,GraphicsContext gc){
            gc.strokeLine((triangle.a.z / 10000 +move) *size,(triangle.a.x / 10000 +move) *size,(triangle.b.z/ 10000  +move) *size,(triangle.b.x/ 10000  +move) *size);
            gc.strokeLine((triangle.a.z /10000 +move) *size,(triangle.a.x/ 10000 +move) *size,(triangle.c.z/10000 +move) *size,(triangle.c.x /10000 +move) *size);
            gc.strokeLine((triangle.c.z /10000 +move)*size,(triangle.c.x/10000 +move) *size,(triangle.b.z /10000 +move)*size ,(triangle.b.x/10000 +move)*size);
        }

//        private void DrawTiangle(Triangle triangle,GraphicsContext gc){
//            gc.strokeLine((triangle.a.x / 10000 +move) *size,(-triangle.a.z / 10000 +move) *size,(triangle.b.x/ 10000  +move) *size,(-triangle.b.z/ 10000  +move) *size);
//            gc.strokeLine((triangle.a.x/ 10000 +move) *size,(-triangle.a.z /10000 +move) *size,(triangle.c.x /10000 +move) *size,(-triangle.c.z/10000 +move) *size);
//            gc.strokeLine((triangle.c.x/10000+move) *size,(-triangle.c.z /10000 +move)*size,(triangle.b.x/10000 +move)*size,(-triangle.b.z /10000 +move)*size);
//        }

    }

