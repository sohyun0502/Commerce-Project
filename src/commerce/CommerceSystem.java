package commerce;

import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public class CommerceSystem {

    private Scanner sc = new Scanner(System.in);
    private List<Category> categories;
    private Cart cart = new Cart();
    private Administrator admin;

    public CommerceSystem() {
        this.categories = Category.createCategories();
        this.admin = new Administrator(categories, this);
    }

    public Cart getCart() {
        return cart;
    }

    /**
     * 메인 화면 출력하는 메서드
     */
    public void showCategory() {
        System.out.println("\n[ 실시간 커머스 플랫폼 메인 ]");
        for (int i = 0; i < categories.size(); i++) {
            System.out.println((i + 1) + ". " + categories.get(i).getCategoryName());
        }
        System.out.println("0. 종료        | 프로그램 종료");
        System.out.println("6. 관리자 모드");

        if (!cart.getCartItems().isEmpty()) {
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
        boolean isRunning = true;

        while (isRunning) {
            showCategory();
            int choice = readInt();

            switch (choice) {
                case 0:
                    System.out.println("커머스 플랫폼을 종료합니다.");
                    isRunning = false;
                    break;
                case 1:
                case 2:
                case 3:
                    chooseProduct(categories.get(choice - 1));
                    break;
                case 4:
                case 5:
                    if (cart.getCartItems().isEmpty()) {
                        System.out.println("장바구니가 비어 있습니다.");
                    }
                    if (choice == 4) {
                        cart.showCart();
                    } else {
                        System.out.println("진행중인 주문을 취소합니다.\n");
                        cart.clear();
                    }
                    break;
                case 6:
                    admin.checkPassword();
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
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\n[ " + category.getCategoryName() + " 카테고리 ]");
            System.out.println("1. 전체 상품 보기");
            System.out.println("2. 가격대별 필터링 (100만원 이하)");
            System.out.println("3. 가격대별 필터링 (100만원 초과)");
            System.out.println("0. 뒤로가기");

            int choice = readInt();

            switch (choice) {
                case 1:
                    showFilteredProducts(category, p -> true, "전체 상품 목록");
                    break;
                case 2:
                    showFilteredProducts(category, p -> p.getProductPrice() <= 1_000_000, "100만원 이하 상품 목록");
                    break;
                case 3:
                    showFilteredProducts(category, p -> p.getProductPrice() > 1_000_000, "100만원 초과 상품 목록");
                    break;
                case 0:
                    isRunning = false;
                    break;
                default:
                    System.out.println("잘못된 입력입니다.");
            }
        }
    }

    /**
     * 필터된 상품 출력 + 선택 메서드
     * @param category
     * @param filter
     * @param title
     */
    private void showFilteredProducts(Category category, Predicate<Product> filter, String title) {
        List<Product> filteredProducts = category.getProducts().stream()
                                        .filter(filter)
                                        .toList();

        if (filteredProducts.isEmpty()) {
            System.out.println("\n상품이 없습니다.");
            return;
        }

        System.out.println("\n[ " + title + " ]");

        IntStream.range(0, filteredProducts.size())
                .forEach(i -> System.out.println((i + 1) + ". " + filteredProducts.get(i)));

        System.out.println("0. 뒤로가기");

        int choice = readInt();

        if (choice == 0) return;

        if (choice < 1 || choice > filteredProducts.size()) {
            System.out.println("잘못된 입력입니다.");
            return;
        }

        Product selectedProduct = filteredProducts.get(choice - 1);
        cart.addToCart(selectedProduct);
    }

    /**
     * 공통 숫자 입력 메서드 (예외처리)
     * @return
     */
    private int readInt() {
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
