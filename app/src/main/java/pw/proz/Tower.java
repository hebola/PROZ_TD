package pw.proz;

public class Tower {
    private float range;
    static int num_of_towers = 0;

    Tower() {
        num_of_towers++;
    }

    public static int getNum_of_towers() {
        return num_of_towers;
    }

    public float getRange() {
        return range;
    }

    public void setRange(float range) {
        this.range = range;
    }

}
