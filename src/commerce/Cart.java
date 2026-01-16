package commerce;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cart {
    // 상품명, 수량, 가격정보
    private String productName;
    private int productPrice;
    private String productExplanation;
    private int productAmount;

    private final Scanner sc = new Scanner(System.in);
    private List<Product> cartList = new ArrayList<>();

    public List<Product> getCartList() {
        return cartList;
    }

    public void setCartList(List<Product> cartList) {
        this.cartList = cartList;
    }

    // 재고 확인
    // 상품을 장바구니에 담을 때 재고를 확인하고, 재고가 부족할 경우 경고 메시지를 출력
    public boolean checkStock(Product product) {
        if (product.getProductStock() > 0) {
            return true;
        } else {
            System.out.println("재고가 부족한 상품입니다.");
            return false;
        }
    }

    public void reduceStock() {
        for (Product product : cartList) {
            int beforeStock = product.getProductStock();
            int afterStock = product.getProductStock()-1;
            product.setProductStock(afterStock);
            System.out.println(product.getProductName() + " 재고가 " +
                    beforeStock + "개 -> " + afterStock + "개로 업데이트 되었습니다.");
        }
        System.out.println();
        cartList.clear();
    }

    // 장바구니 출력 및 금액 계산
    // 사용자가 주문을 시도하기 전에, 장바구니에 담긴 모든 상품과 총 금액을 출력
    public void showCart() {
        int totalPrice = 0;
        System.out.println("아래와 같이 주문 하시겠습니까?\n");
        System.out.println("[ 장바구니 내역 ]");
        for (Product product : cartList) {
            totalPrice += product.getProductPrice();
            System.out.println(product.toString2());
        }
        System.out.println("[ 총 주문 금액 ]");
        System.out.println(String.format("%,10d원", totalPrice) + "\n");

        System.out.println("1. 주문 확정      2. 메인으로 돌아가기");
        int choice = sc.nextInt();
        System.out.println();

        if (choice == 1) {
            System.out.println("주문이 완료되었습니다! 총 금액: " + String.format("%,10d원", totalPrice));
            reduceStock();
        } else if (choice == 2) {
            return;
        } else {
            System.out.println("잘못된 입력입니다.");
            return;
        }
    }

    public void confirmOrder() {

    }

    // 장바구니 담기 기능
    // 상품을 선택하면 장바구니에 추가할 지 물어보고, 입력값에 따라 "추가", "취소" 처리
    // 장바구니에 담은 목록을 출력
    public void addToCart(Product product) {
        boolean check = checkStock(product);

        product.toString();
        System.out.println("위 상품을 장바구니에 추가하시겠습니까?");
        System.out.println("1. 확인        2. 취소");
        int choice = sc.nextInt();

        if (choice == 2) {
            return;
        }

        if (choice < 1 || choice > 2) {
            System.out.println("잘못된 입력입니다.");
            return;
        }

        if (check && choice == 1) {
            cartList.add(product);
            System.out.println(product.getProductName() + "가 장바구니에 추가되었습니다.\n");
        }
    }
}
