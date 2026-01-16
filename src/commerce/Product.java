package commerce;

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
}
