import java.util.*;

public class RouteRecommand {
    int timeLimit = 8 * 60;
    ArrayList<SpotInfo> spots = new ArrayList<>();
    ArrayList<Integer> bestRoute;
    int bestTime;
    HashMap<Integer, Boolean> vis;
    ArrayList<Integer> curRoute;
    HashMap<Pair, Integer> timeMap;

    public void addSpot(SpotInfo spot) {
        spots.add(spot);
    }
    public void delSpot(int ID) {
        for (int i = 0; i < spots.size(); ++i)
            if (spots.get(i).ID == ID) {
                spots.remove(i);
                break;
            }
    }
    public void setTimeLimit(int time) {
        timeLimit = time;
    }
    double rad(double deg) {
        return deg * Math.PI / 180;
    }
    int calcTime(int ID1, int ID2) {
        double lat1 = 0.0;
        double lat2 = 0.0;
        double lng1 = 0.0;
        double lng2 = 0.0;
        for (int i = 0; i < spots.size(); ++i) {
            if (spots.get(i).ID == ID1) {
                lat1 = rad(spots.get(i).Latitude);
                lng1 = rad(spots.get(i).Longitude);
            }
            if (spots.get(i).ID == ID2) {
                lat2 = rad(spots.get(i).Latitude);
                lng2 = rad(spots.get(i).Longitude);
            }
        }
        double a = lat1 - lat2;
        double b = lng1 - lng2;
        double R = 6371.0;
        double s = R * 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
            + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(b / 2), 2)));
        return (int) (s / 40 * 60);
    }
    void dfs(int p, int k, int t) {
        vis.put(p, true);
        curRoute.set(k, p);
        if (k == spots.size() - 1) {
            if (t + timeMap.get(new Pair(p, spots.get(0).ID)) < bestTime) {
                bestRoute = new ArrayList<>(curRoute);
                bestTime = t + timeMap.get(new Pair(p, spots.get(0).ID));
            }
        } else {
            for (int i = 0; i < spots.size(); ++i)
                if (!vis.get(spots.get(i).ID))
                    dfs(spots.get(i).ID, k + 1, t + timeMap.get(new Pair(p, spots.get(i).ID)));
        }
        vis.put(p, false);
    }
    public RouteRecResult recRoute() {
        if (spots.size() == 0)
            return new RouteRecResult(
                new ArrayList<Integer>(), 0, RouteRecResult.EMPTY_SPOTS_LIST
                );
        timeMap = new HashMap<>();
        for (int i = 0; i < spots.size(); ++i)
            for (int j = 0; j < spots.size(); ++j) {
                int ID1 = spots.get(i).ID;
                int ID2 = spots.get(j).ID;
                int time = calcTime(ID1, ID2);
                timeMap.put(new Pair(ID1, ID2), time);
            }
        curRoute = new ArrayList<>();
        vis = new HashMap<>();
        for (int i = 0; i < spots.size(); ++i) {
            curRoute.add(0);
            vis.put(spots.get(i).ID, false);
        }
        bestTime = (int) 2e9;
        dfs(spots.get(0).ID, 0, 0);
        for (int i = 0; i < spots.size(); ++i)
            bestTime += spots.get(i).TimeLength;
        if (bestTime > timeLimit)
            return new RouteRecResult(bestRoute, bestTime, RouteRecResult.TIME_LIMIT_EXCEEDED);
        else
            return new RouteRecResult(bestRoute, bestTime, RouteRecResult.SUCCESS);
    }
}