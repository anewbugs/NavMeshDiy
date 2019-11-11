package navtest.mesh;

import navtest.config.PointConfig;

public class Point {
    public String line;
    public float x ;
    public float y ;//高度坐标
    public float z ;
    // 传入参数可能是浮点数，需修改
    public Point(float x, float y, float z) {
        this.x =  (x * PointConfig.precision);
        this.y =  (y * PointConfig.precision);
        this.z =  (z * PointConfig.precision);
    }

    public static Point vector(Point A, Point B){
        return new Point(B.x - A.x, B.y - A.y, B.z - A.z);
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }

    //获取点之间的距离
    public float getDistance(Point point){
      return (float) Math.sqrt(Math.pow(this.x - point.x , 2) + Math.pow(this.z - this.z ,2));
    }
}
