import java.util.*;

public class RouteRecResult {
    public static int SUCCESS = 0;
    public static int EMPTY_SPOTS_LIST = 1;
    public static int TIME_LIMIT_EXCEEDED = 2;
    public ArrayList<Integer> route;
    public int time;
    public int retCode;
    public RouteRecResult(ArrayList<Integer> route, int time, int retCode) {
        this.route = route;
        this.time = time;
        this.retCode = retCode;
    }
}