package commerce;

import java.util.*;

public class Cart {

    private Scanner sc = new Scanner(System.in);
    // 장바구니에 같은 상품을 몇번 넣었는지 알기위해 장바구니용 수량 값이 필요함
    // 상품과 수량을 같이 관리하기 위해 Map 사용 (키 = Product, 값 = 수량)
    // HashMap = 순서없음, LinkedHashMap = 넣은 순서 유지
    private Map<Product, Integer> cartItems = new LinkedHashMap<>();

    public Map<Product, Integer> getCartItems() {
        return cartItems;
    }

    /**
     * 장바구니 비우기 기능 (초기화)
     */
    public void clear() {
        cartItems.clear();
    }

    /**
     * 장바구니에서 상품 제거하는 기능 (Product 객체로 삭제)
     */
    public void removeProduct(Product product) {
        cartItems.remove(product);
    }

    /**
     * 장바구니에서 상품 제거하는 기능 (productName 으로 삭제)
     */
    public void removeProductByName(String productName) {
        if (cartItems.isEmpty()) {
            System.out.println("장바구니가 비어 있습니다.");
            return;
        }

        // 1️. 제거할 상품 찾기 (stream.filter 사용)
        Optional<Product> productToRemove = cartItems.keySet()
                        .stream()
                        .filter(p -> p.getProductName().equals(productName))
                        .findFirst();

        // 2️. 결과 처리
        if (productToRemove.isPresent()) {
            cartItems.remove(productToRemove.get());
            System.out.println(productName + " 상품이 장바구니에서 제거되었습니다.");
        } else {
            System.out.println(productName + " 상품이 장바구니에 없습니다.");
        }
    }

    /**
     * 장바구니 담기
     * 상품을 선택하면 장바구니에 추가할 지 물어보고, 입력값에 따라 "추가", "취소" 처리
     * 장바구니에 담은 목록을 출력
     * @param product
     */
    public void addToCart(Product product) {

        product.toString();
        System.out.println("\n위 상품을 장바구니에 추가하시겠습니까?");
        System.out.println("1. 확인        2. 취소");
        int choice = readInt();

        if (choice == 2) {
            return;
        }

        if (choice < 1 || choice > 2) {
            System.out.println("잘못된 입력입니다.");
            return;
        }

        if (choice == 1) {
            System.out.println("\n담을 수량을 입력해 주세요.");
            System.out.print("수량 : ");
            int amount = readInt();
            // 재고가 충분한지 판단
            boolean check = checkStock(product, amount);
            if (check) {
                // getOrDefault 장바구니에 있으면 → 현재 수량, 없으면 → 0
                cartItems.put(product, cartItems.getOrDefault(product, 0) + amount);
                System.out.println(product.getProductName() + "가 " + amount + "개 장바구니에 추가되었습니다.\n");
            }
        }
    }

    /**
     * 재고 확인 - 상품을 장바구니에 담을 때 재고를 확인하고, 재고가 부족할 경우 경고 메시지를 출력
     * @param product
     * @return
     */
    public boolean checkStock(Product product, int amount) {
        if (product.getProductStock() - amount >= 0) {
            return true;
        } else {
            System.out.println("재고가 부족한 상품입니다.");
            return false;
        }
    }

    /**
     * 장바구니 출력 및 금액 계산
     * 사용자가 주문을 시도하기 전에, 장바구니에 담긴 모든 상품과 총 금액을 출력
     */
    public void showCart() {
        int totalPrice = 0;
        System.out.println("아래와 같이 주문 하시겠습니까?\n");
        System.out.println("[ 장바구니 내역 ]");
        for (Product product : cartItems.keySet()) {
            int count = cartItems.get(product);
            totalPrice += product.getProductPrice() * count;
            System.out.println(product.getProductName() + " | " + String.format("%,d원", product.getProductPrice()) + " | 수량: " + count + "개");
        }
        System.out.println("\n[ 총 주문 금액 ]");
        System.out.println(String.format("%,d원", totalPrice) + "\n");

        System.out.println("1. 주문 확정    2. 상품 제거    3. 메인으로 돌아가기");
        int choice = readInt();
        System.out.println();

        if (choice == 1) {
            Customer customer = new Customer();
            Customer loginCustomer;

            try {
                // 고객을 이메일로 결정
                loginCustomer = customer.chooseCustomer();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                return;
            }

            CustomerLevel customerLevel = loginCustomer.getCustomerLevel();
            System.out.println("주문 하시겠습니까?");
            System.out.println("1. 주문 확정  2. 메인으로 돌아가기");

            int confirm = readInt();
            if (confirm != 1) {
                return;
            }

            double discount = customerLevel.getDiscount();
            double lastPrice = (double) totalPrice * (discount/100.0);
            int lastPrice2 = (int) lastPrice;

            System.out.println("\n주문이 완료되었습니다! \n할인 전 금액: " + String.format("%,d원", totalPrice));
            System.out.println(customerLevel + " 등급 할인(" + customerLevel.getDiscount() + "%): " + String.format("-%,d원", lastPrice2));
            System.out.println("최종 결제 금액: " + String.format("%,d원", (totalPrice-lastPrice2)));

            // 재고 수량 변경
            reduceStock();
            // 사용자 등급 변경
            loginCustomer.updateLevel(totalPrice-lastPrice2);
        } else if (choice == 2) {
            System.out.print("제거할 상품명을 입력하세요: ");
            String name = sc.nextLine();
            removeProductByName(name);
        } else if (choice == 3) {
            return;
        } else {
            System.out.println("잘못된 입력입니다.");
            return;
        }
    }

    /**
     * 재고 줄이기 - 상품의 재고를 수량 만큼 줄이고 장바구니 초기화
     */
    public void reduceStock() {
        for (Product product : cartItems.keySet()) {
            int count = cartItems.get(product);
            int beforeStock = product.getProductStock();
            int afterStock = product.getProductStock() - count;
            product.setProductStock(afterStock);
            System.out.println("\n" + product.getProductName() + " 재고가 " +
                    beforeStock + "개 -> " + afterStock + "개로 업데이트 되었습니다.");
        }
        System.out.println();
        clear();
    }

    /**
     * 공통 숫자 입력 메서드 (예외처리)
     * @return
     */
    private int readInt() {
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("숫자만 입력해주세요.");
            return -1;
        }
    }
}
