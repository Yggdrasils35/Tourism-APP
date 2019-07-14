import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.sun.tools.javac.util.Pair;

class TripAdvisor {
    int timeLimit;
    int recSpotNum;
    HashMap<Integer, Spot> spots;
    HashMap<String, ArrayList<Spot>> cat = new HashMap<>();
    ArrayList<Integer> choice;
    HashMap<Integer, Boolean> chosen = new HashMap<>();
    HashMap<Integer, Boolean> rejected = new HashMap<>();
    HashMap<Integer, Boolean> reachable = new HashMap<>();
    ArrayList<Integer> recSpots = new ArrayList<>();
    HashMap<Pair<Integer, Integer>, Integer> timeTable = new HashMap<>();
    public TripAdvisor(int recSpotNum, int timeLimit, HashMap<Integer, Spot> spots, ArrayList<Integer> choice) {
        this.recSpotNum = recSpotNum;
        this.spots = spots;
        this.choice = choice;
        ArrayList<Integer> idList = new ArrayList<>();
        for (Iterator itr = spots.entrySet().iterator(); itr.hasNext(); ) {
            Map.Entry entry = itr.next();
            Spot spot = entry.getValue();
            if (!cat.containsKey(spot.cat))
                cat.put(spot.cat, new ArrayList<Spot>());
            cat.get(spot.cat).add(spot);
            idList.add(spot.id);
        }
        Comparator<Spot> cmp = new Comparator<>() {
            @Override
            public int compare(Spot lhs, Spot rhs) {
                if (lhs.score > rhs.score)
                    return 1;
                else
                    return -1;
            }
        };
        for (Iterator itr = cat.entrySet().iterator(); itr.hasNext(); ) {
            Map.Entry entry = itr.next();
            Collection.sort(entry.getValue(), cmp);
        }
        for (int i = 0; i < idList.size(); ++i) {
            int u = idList.get(i);
            chosen.put(u, false);
            rejected.put(u, false);
            reachable.put(u, true);
            for (int j = i; j < idList.size(); ++j) {
                int v = idList.get(j);
                int time = calcTime(u, v);
                timeTable.put(new Pair<>(u, v), time);
                timeTable.put(new Pair<>(v, u), time);
            }
        }
        for (int i = 0; i < choice.size(); ++i)
            chosen.put(choice.get(i), true);
        // TODO
    }
    double rad(double deg) {
        return deg * Math.PI / 180.0;
    }
    int calcTime(int id1, int id2) {
        double lat1 = rad(spots.get(id1).lat);
        double lat2 = rad(spots.get(id2).lat);
        double lng1 = rad(spots.get(id1).lng);
        double lng2 = rad(spots.get(id2).lng);
        double a = lat1 - lat2;
        double b = lng1 - lng2;
        double r = 6371.0;
        double s = 2 * r * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
            + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(b / 2), 2)));
        double v = 40.0;
        double c = 1.5;
        return (int) (1.5 * s / v * 60);   
    }
    boolean judgeRoute(ArrayList<Spot> route) {
        // TODO
    }
    public ArrayList<Integer> curChoice() {
        return choice;
    } 
    public ArrayList<Integer> curRecSpots() {
        return recSpots;
    }
    // TODO
}