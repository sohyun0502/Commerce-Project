package commerce;

import java.util.ArrayList;
import java.util.List;
import static commerce.Product.*;

public class Category {

    private String categoryName;
    private List<Product> products;

    public Category(String categoryName, List<Product> products) {
        this.categoryName = categoryName;
        this.products = products;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public List<Product> getProducts() {
        return products;
    }

    // 상품 추가 기능
    public void addProduct(Product product) {
        products.add(product);
    }

    // 상품 삭제 기능
    public void removeProduct(Product product) {
        products.remove(product);
    }

    /**
     * 전체 카테고리 생성
     */
    public static List<Category> createCategories() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category("전자제품", createElectronicsProducts()));
        categories.add(new Category("의류", createClothingProducts()));
        categories.add(new Category("식품", createFoodProducts()));
        return categories;
    }
}
