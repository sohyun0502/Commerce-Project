package commerce;

import java.util.ArrayList;
import java.util.List;

public class CommerceSystem {

    private List<Product> products = new ArrayList<>();

    public CommerceSystem(Product product) {
        this.products.add(product);
    }

    public List<Product> getProducts() {
        return products;
    }

    public void start() {
        System.out.println("[ 실시간 커머스 플랫폼 - 전자제품 ]");
        for( int i = 0; i < products.size(); i++ ) {
            System.out.println((i+1) + ". " + products.get(i).toString());
        }
        System.out.println(String.format("0. " + "%-10s | %-20s", "종료", "프로그램 종료"));
    }
}
