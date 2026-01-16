package commerce;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CommerceSystem {

    private final List<Category> categories;

    public CommerceSystem(List<Category> categories) {
        this.categories = categories;
    }

    public void chooseCategory() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("[ 실시간 커머스 플랫폼 메인 ]");
            for (int i = 0; i < categories.size(); i++) {
                System.out.println((i + 1) + ". " + categories.get(i).getCategoryName());
            }
            System.out.println("0. 종료        | 프로그램 종료");

            int choice = sc.nextInt();

            if (choice == 0) {
                System.out.println("커머스 플랫폼을 종료합니다.");
                break;
            }

            if (choice < 1 || choice > categories.size()) {
                System.out.println("잘못된 입력입니다.");
                continue;
            }

            Category selectedCategory = categories.get(choice - 1);
            chooseProduct(selectedCategory);
        }
        sc.close();
    }

    public void chooseProduct(Category category) {
        List<Product> products = category.getProducts();

        Scanner sc = new Scanner(System.in);

        System.out.println("[ " + category.getCategoryName() + " 카테고리 ]");
        for( int i = 0; i < category.getProducts().size(); i++ ) {
            System.out.println((i+1) + ". " + category.getProducts().get(i).toString());
        }
        System.out.println("0. 뒤로가기");

        int choice = sc.nextInt();

        if (choice == 0) {
            return;
        }

        if (choice < 1 || choice > products.size()) {
            System.out.println("잘못된 입력입니다.");
            return;
        }

        Product selectedProduct = products.get(choice - 1);
        printProduct(selectedProduct);
    }

    private void printProduct(Product product) {
        System.out.println("선택한 상품: " + product.toString2() + "\n");
    }

}
