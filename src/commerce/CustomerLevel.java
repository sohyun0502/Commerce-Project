package commerce;

// 고객 등급 enum
public enum CustomerLevel {
    BRONZE(0),
    SILVER(5),
    GOLD(10),
    PLATINUM(15);

    private int discount; // 등급별 할인율

    CustomerLevel(int discount) {
        this.discount = discount;
    }

    public int getDiscount() {
        return discount;
    }
}
