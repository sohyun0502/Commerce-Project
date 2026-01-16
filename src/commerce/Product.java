package commerce;

import java.util.ArrayList;
import java.util.List;

public class Product {
    // 상품명, 가격, 설명, 재고수량
    private String productName;
    private int productPrice;
    private String productExplanation;
    private int productStock;

    public Product(String productName, int productPrice, String productExplanation, int productStock) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productExplanation = productExplanation;
        this.productStock = productStock;
    }

    public String toString() {
        return  String.format("%-12s | %,10d원 | %-20s", productName, productPrice, productExplanation);
    }

    public String toString2() {
        return  String.format("%-12s | %,10d원 | %-15s | 재고: %3d개", productName, productPrice, productExplanation, productStock);
    }

    public String getProductName() {
        return productName;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public int getProductStock() {
        return productStock;
    }

    public void setProductStock(int productStock) {
        this.productStock = productStock;
    }

    /**
     * 카테고리별 상품 생성 - 전자제품
     */
    public static List<Product> createElectronicsProducts() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("Galaxy S24", 1_200_000, "최신 안드로이드 스마트폰", 25));
        products.add(new Product("iPhone 15", 1_350_000, "Apple의 최신 스마트폰", 30));
        products.add(new Product("MacBook Pro", 2_400_000, "M3 칩셋이 탑재된 노트북", 15));
        products.add(new Product("AirPods Pro", 350_000, "노이즈 캔슬링 무선 이어폰", 50));
        return products;
    }

    /**
     * 카테고리별 상품 생성 - 의류
     */
    public static List<Product> createClothingProducts() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("티셔츠", 30_000, "면 티셔츠", 100));
        products.add(new Product("청바지", 80_000, "슬림핏 청바지", 50));
        return products;
    }

    /**
     * 카테고리별 상품 생성 - 식품
     */
    public static List<Product> createFoodProducts() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("사과", 5_000, "국산 사과", 200));
        products.add(new Product("우유", 3_000, "저지방 우유", 150));
        return products;
    }
}
