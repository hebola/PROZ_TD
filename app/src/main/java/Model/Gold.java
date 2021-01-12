package Model;

public class Gold {
    private int amount;

    public Gold(int amount) {
        this.amount = amount;
    }
    public void addGold(int amount) {
        this.amount += amount;
    }
    public boolean subtractGold(int amount) {
        if (amount > this.amount)
            return false;

        this.amount -= amount;
        return true;
    }
    public int getAmount() {
        return amount;
    }
}
