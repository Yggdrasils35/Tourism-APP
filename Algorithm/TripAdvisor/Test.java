import java.util.HashMap;

public class Test {
    public static void main(String[] args) {
        int[][] adjMat = {
            {0, 2, 1, 3},
            {2, 0, 2, 1},
            {1, 2, 0, 1},
            {3, 1, 1, 0}
        };
        ShortestHamiltonPath solver = new ShortestHamiltonPath(4, adjMat);
        HashMap result = solver.solve();
        System.out.println((int) result.get("time"));
        int[] path = (int[]) result.get("path");
        for (int i = 0; i < 4; ++i)
            System.out.printf("%d->", path[i]);
        System.out.printf("%d\n", path[0]);
    }
}