package navtest.mesh;

import navtest.config.PointConfig;

public class Point {
    public int x ;
    public int y ;
    public int z ;
    // 传入参数可能是浮点数，需修改
    public Point(float x, float y, float z) {
        this.x = (int) (x * PointConfig.precision);
        this.y = (int) (y * PointConfig.precision);
        this.z = (int) (z * PointConfig.precision);
    }
}
