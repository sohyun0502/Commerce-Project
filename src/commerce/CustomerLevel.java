package commerce;

public enum CustomerLevel {
    BRONZE(0),
    SILVER(5),
    GOLD(10),
    PLATINUM(15);

    private int discount;

    CustomerLevel(int discount) {
        this.discount = discount;
    }

    public int getDiscount() {
        return discount;
    }
}
