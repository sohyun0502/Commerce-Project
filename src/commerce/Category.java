package commerce;

import java.util.List;

public class Category {

    private final String categoryName;
    private final List<Product> products;

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
}
