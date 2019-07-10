import java.util.*;

public class Test {
    public static void main(String[] args) {
        RouteRecommand routeRec = new RouteRecommand();
        SpotInfo[] spots = new SpotInfo[3];
        spots[0] = new SpotInfo(
            0, "Oriental Pearl TV Tower", 121.506377, 31.245105, 60
            );
        spots[1] = new SpotInfo(
            1, "Century Park", 121.558979, 31.222046, 60
            );
        spots[2] = new SpotInfo(
            2, "Shanghai Science and Technology Museum", 121.547823, 31.224356, 60
            );
        routeRec.addSpot(spots[0]);
        routeRec.addSpot(spots[1]);
        routeRec.addSpot(spots[2]);
        RouteRecResult ret = routeRec.recRoute();
        System.out.println(ret.retCode);
        System.out.println(ret.time);
        for (int i = 0; i < ret.route.size(); ++i)
            System.out.print(spots[ret.route.get(i)].Name + "->");
        System.out.println(spots[ret.route.get(0)].Name);    
    }
}