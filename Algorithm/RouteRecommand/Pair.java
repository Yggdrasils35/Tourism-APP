public class Pair {
    public int first;
    public int second;
    Pair(int first, int second) {
        this.first = first;
        this.second = second;
    }
    @Override
    public boolean equals(Object obj) {
        Pair p = (Pair) obj;
        return first == p.first && second == p.second;
    }

    @Override
    public int hashCode() {
        return (first + 2 * second) % 998244353;
    }
}