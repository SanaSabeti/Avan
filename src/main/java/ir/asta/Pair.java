package ir.asta;

public class Pair<K, V> {
    private K firstElement;
    private V secondElement;

    public Pair(K one, V two) {
        this.firstElement = one;
        this.secondElement = two;
    }

    public K getFirstElement() {
        return firstElement;
    }

    public V getSecondElement() {
        return secondElement;
    }
}
