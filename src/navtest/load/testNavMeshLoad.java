package navtest.load;

import navtest.mesh.NavMeshInfo;

public class testNavMeshLoad {
    public static void main(String[] args) {
       NavMeshLoad navMeshLoad = new NavMeshLoad();
       navMeshLoad.equals(null);
       NavMeshInfo navMeshInfo = navMeshLoad.load("s1.obj");
    }
}
