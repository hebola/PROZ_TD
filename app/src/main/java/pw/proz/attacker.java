package pw.proz;

public class attacker {
    private float movement_speed;
    private float hit_points;
    private float armor;
    private float shield;
    private static int num_of_attackers = 0;

    attacker(){
        num_of_attackers++;
    }

    public static int getNum_of_attackers() {
        return num_of_attackers;
    }

    public void move(){

    }
}
