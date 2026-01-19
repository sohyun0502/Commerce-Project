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

    public static CustomerLevel fromTotalPrice(int totalPrice) {
        if (totalPrice < 500000) {
            return BRONZE;
        } else if (totalPrice < 1000000) {
            return SILVER;
        } else if (totalPrice < 2000000) {
            return GOLD;
        } else {
            return PLATINUM;
        }
    }
}
