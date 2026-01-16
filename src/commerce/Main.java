package commerce;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Product product1 = new Product("Galaxy S25", 1200000, "최신 안드로이드 스마트폰", 50);
        Product product2 = new Product("iPhone 16", 1350000, "Apple의 최신 스마트폰", 30);
        Product product3 = new Product("MacBook Pro", 2400000, "M3 칩셋이 탑재된 노트북", 20);
        Product product4 = new Product("AirPods Pro", 350000, "노이즈 캔슬링 무선 이어폰", 70);

        CommerceSystem commerceSystem1 = new CommerceSystem(product1);
        CommerceSystem commerceSystem2 = new CommerceSystem(product2);
        CommerceSystem commerceSystem3 = new CommerceSystem(product3);
        CommerceSystem commerceSystem4 = new CommerceSystem(product4);

        commerceSystem1.start();
    }
}
