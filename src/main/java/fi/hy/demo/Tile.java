package fi.hy.demo;

class Tile implements Cloneable {
    boolean merged;
    int value;

    Tile(int val) {
        value = val;
    }

    public Tile(int value, boolean merged) {
        this.merged = merged;
        this.value = value;
    }

    int getValue() {
        return value;
    }

    void setMerged(boolean m) {
        merged = m;
    }

    boolean canMergeWith(Tile other) {
        return !merged && other != null && !other.merged && value == other.getValue();
    }

    int mergeWith(Tile other) {
        if (canMergeWith(other)) {
            value *= 2;
            merged = true;
            return value;
        }
        return -1;
    }

    public Tile Clone() {
        Tile tile = new Tile(this.value, this.merged);
        return tile;
    }
}
