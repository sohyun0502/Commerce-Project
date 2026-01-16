package commerce;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // 1. 전자제품
        List<Product> electronicsProducts = new ArrayList<>();
        electronicsProducts.add(new Product("Galaxy S25", 1200000, "최신 안드로이드 스마트폰", 50));
        electronicsProducts.add(new Product("iPhone 16", 1350000, "Apple의 최신 스마트폰", 30));
        electronicsProducts.add(new Product("MacBook Pro", 2400000, "M3 칩셋이 탑재된 노트북", 20));
        electronicsProducts.add(new Product("AirPods Pro", 350000, "노이즈 캔슬링 무선 이어폰", 70));

        Category electronics = new Category("전자제품", electronicsProducts);

        // 2. 의류
        List<Product> clothingProducts = new ArrayList<>();
        clothingProducts.add(new Product("의류1", 200000, "유행 의류1", 30));
        clothingProducts.add(new Product("의류2", 300000, "유행 의류2", 20));
        clothingProducts.add(new Product("의류3", 400000, "유행 의류3", 40));

        Category clothing = new Category("의류", clothingProducts);

        // 3. 식품
        List<Product> foodProducts = new ArrayList<>();
        clothingProducts.add(new Product("식품1", 20000, "맛있는 식품1", 50));
        clothingProducts.add(new Product("식품2", 30000, "맛있는 식품2", 60));
        clothingProducts.add(new Product("식품3", 40000, "맛있는 식품3", 70));

        Category food = new Category("식품", foodProducts);

        List<Category> categories = new ArrayList<>();
        categories.add(electronics);
        categories.add(clothing);
        categories.add(food);

        CommerceSystem commerceSystem = new CommerceSystem(categories);
        commerceSystem.start();
    }
}
