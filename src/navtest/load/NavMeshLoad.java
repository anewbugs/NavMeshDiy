package navtest.load;

import navtest.mesh.NavMeshInfo;
import navtest.mesh.Point;
import navtest.mesh.Triangle;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public  class NavMeshLoad {
    List<Point> allPoint = new ArrayList<Point>();
//    public static List<Index> allIndex = new ArrayList<Index>();


    /// 读取导航网格信息
    public NavMeshInfo load(String path)
    {
        List<String> fileInfo = loadFile(path);

        NavMeshInfo navMeshInfo = readInfo(fileInfo);

        return navMeshInfo;
    }

    public List<String> loadFile(String path)  {

        List list = new ArrayList<String>();

        try {

            File file = new File(path);
            InputStream inputStream = this.getClass().getClassLoader(). getResourceAsStream(path);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String strLine = null;
            while (null != (strLine = bufferedReader.readLine()) ){
                list.add(strLine);
            }

        }catch (IOException e){
            e.printStackTrace();
        }

        return list;
    }

    //获取导航网格
    public NavMeshInfo readInfo(List<String> fileInfo){

        NavMeshInfo navMeshInfo = new NavMeshInfo();


        for (int i = 0; i < fileInfo.size(); i++)
        {
            String str = fileInfo.get(i);

            String[] Split = str.split(" ");

            if (Split[0].equals("v"))
            {
                Point point =new Point(Float.parseFloat(Split[2]), Float.parseFloat(Split[3]), Float.parseFloat(Split[4]));
                point.line = str;
                allPoint.add(point);
            }
            else if (Split[0].equals( "f"))
            {
                int a = Integer.parseInt(Split[1]);
                int b = Integer.parseInt(Split[2]);
                int c = Integer.parseInt(Split[3]);
                //todo
                //allIndex.add(new Index(a,b,c));
                Triangle triangle = new Triangle(allPoint.get(a - 1), allPoint.get(b - 1), allPoint.get(c-1));
                navMeshInfo.allCentroid.add(triangle.centroid);
                navMeshInfo.allTriangle.add(triangle);
                addPointIndexes(navMeshInfo.pointIndexes,allPoint.get(a - 1),triangle);
                addPointIndexes(navMeshInfo.pointIndexes,allPoint.get(b - 1),triangle);
                addPointIndexes(navMeshInfo.pointIndexes,allPoint.get(c - 1),triangle);

                addStrIndexes(navMeshInfo.strIndexes,allPoint.get(a - 1),triangle);
                addStrIndexes(navMeshInfo.strIndexes,allPoint.get(b - 1),triangle);
                addStrIndexes(navMeshInfo.strIndexes,allPoint.get(c - 1),triangle);

            }
        }

        return navMeshInfo;
    }

    //添加顶点索引
    void addPointIndexes(HashMap<Point,ArrayList<Triangle>> navMeshInfo, Point point, Triangle triangle)
    {
        if (navMeshInfo.containsKey(point))
        {
            navMeshInfo.get(point).add(triangle);
            //navMeshInfo[ponit].Add(triangle);
        }
        else
        {
            ArrayList<Triangle> list = new ArrayList<Triangle>();
            list.add(triangle);
            navMeshInfo.put(point,  list);
        }


    }

    //测试
    void addStrIndexes(HashMap<String,ArrayList<Triangle>> navMeshInfo, Point point, Triangle triangle)
    {
        if (navMeshInfo.containsKey(point.line))
        {
            navMeshInfo.get(point.line).add(triangle);
            //navMeshInfo[ponit].Add(triangle);
        }
        else
        {
            ArrayList<Triangle> list = new ArrayList<Triangle>();
            list.add(triangle);
            navMeshInfo.put(point.line,  list);
        }


    }

}
