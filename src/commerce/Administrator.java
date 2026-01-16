package commerce;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Administrator {

    private String password;
    private Scanner sc = new Scanner(System.in);
    private List<Category> categories;

    public Administrator(List<Category> categories) {
        this.categories = categories;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // 관리자 인증 기능
    public void checkPassword() {
        System.out.println("\n관리자 비밀번호를 입력해주세요:");
        String pw = sc.nextLine();
        if(pw.equals(password)) {
            showAdminMode();
        }
    }

    // 관리자 모드 목록 출력
    public void showAdminMode() {
        System.out.println("\n[ 관리자 모드 ]");
        System.out.println("1. 상품 추가");
        System.out.println("2. 상품 수정");
        System.out.println("3. 상품 삭제");
        System.out.println("4. 전체 상품 현황");
        System.out.println("0. 메인으로 돌아가기");
        int choice = sc.nextInt();

        switch (choice) {
            case 0:
                return;
            case 1:
                addProduct();
                break;
            case 2:
                updateProduct();
                break;
            case 3:
                deleteProduct();
                break;
            case 4:
                showAllProduct();
                break;
            default:
                System.out.println("잘못된 입력입니다.");
        }
    }

    // Product 추가 기능
    public void addProduct() {
        System.out.println("\n어느 카테고리에 상품을 추가하시겠습니까?");
        for (int i = 0; i < categories.size(); i++) {
            System.out.println((i + 1) + ". " + categories.get(i).getCategoryName());
        }
        int choice = sc.nextInt();

        List<Product> newProduct = new ArrayList<>();
        sc.nextLine();
        System.out.println("\n[ " + categories.get(choice-1).getCategoryName() + " 카테고리에 상품 추가 ]");
        System.out.print("상품명을 입력해주세요: ");
        String productName = sc.nextLine();
        System.out.print("가격을 입력해주세요: ");
        int productPrice = Integer.parseInt(sc.nextLine());
        System.out.print("상품 설명을 입력해주세요: ");
        String productExplanation = sc.nextLine();
        System.out.print("재고수량을 입력해주세요: ");
        int productStock = Integer.parseInt(sc.nextLine());

        System.out.println("\n"+String.format("%-12s | %,10d원 | %-15s | 재고: %3d개", productName, productPrice, productExplanation, productStock));
        System.out.println("위 정보로 상품을 추가하시겠습니까?");
        System.out.println("1. 확인   2. 취소");
        int choice2 = sc.nextInt();

        if (choice2 == 1) {
            Product product = new Product(productName, productPrice, productExplanation, productStock);
            categories.get(choice - 1).getProducts().add(product);

            System.out.println("상품이 성공적으로 추가되었습니다!\n");
        }  else if (choice2 == 2) {
            return;
        } else {
            System.out.println("잘못된 입력입니다.");
            return;
        }
    }

    // Product 수정 기능
    public void updateProduct() {

    }

    // Product 삭제 기능
    public void deleteProduct() {

    }

    // 전체 Product 출력
    public void showAllProduct() {

    }
}
