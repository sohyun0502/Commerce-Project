package commerce;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CommerceSystem {

    private final List<Category> categories;
    private final Scanner sc = new Scanner(System.in);
    private final Cart cart = new Cart();

    public CommerceSystem() {
        this.categories = Category.createCategories();
    }

    /**
     * 메인 화면 출력하는 메서드
     */
    public void showCategory() {
        System.out.println("[ 실시간 커머스 플랫폼 메인 ]");
        for (int i = 0; i < categories.size(); i++) {
            System.out.println((i + 1) + ". " + categories.get(i).getCategoryName());
        }
        System.out.println("0. 종료        | 프로그램 종료");

        if (!cart.getCartList().isEmpty()) {
            System.out.println();
            System.out.println("[ 주문 관리 ]");
            System.out.println("4. 장바구니 확인   | 장바구니를 확인 후 주문합니다.");
            System.out.println("5. 주문 취소       | 진행중인 주문을 취소합니다.");
        }

    }

    /**
     * 시작 메서드 (카테고리 선택)
     */
    public void start() {
        while (true) {
            showCategory();
            int choice = sc.nextInt();

            switch (choice) {
                case 0:
                    System.out.println("커머스 플랫폼을 종료합니다.");
                    return;
                case 1:
                case 2:
                case 3:
                    chooseProduct(categories.get(choice - 1));
                    break;
                case 4:
                case 5:
                    if (cart.getCartList().isEmpty()) {
                        throw new IllegalStateException("장바구니가 비어 있습니다.");
                    }
                    if (choice == 4) {
                        cart.showCart();
                    } else {
                        System.out.println("진행중인 주문을 취소합니다.\n");
                        cart.setCartList(new ArrayList<>());
                    }
                    break;

                default:
                    System.out.println("잘못된 입력입니다.");
            }
        }
    }

    /**
     * 특정 카테고리의 상품 선택 메서드
     * @param category
     */
    public void chooseProduct(Category category) {
        List<Product> products = category.getProducts();

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
        System.out.println("선택한 상품: " + selectedProduct.toString2() + "\n");
        cart.addToCart(selectedProduct);
    }

}
