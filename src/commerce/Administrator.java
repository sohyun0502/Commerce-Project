package commerce;

import java.util.List;
import java.util.Scanner;

public class Administrator {

    private String password;
    private Scanner sc = new Scanner(System.in);
    private List<Category> categories;

    public Administrator(List<Category> categories) {
        this.categories = categories;
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
        while(true) {
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
                    showUpdateProduct();
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
    }

    // Product 추가 기능
    public void addProduct() {
        System.out.println("\n어느 카테고리에 상품을 추가하시겠습니까?");
        for (int i = 0; i < categories.size(); i++) {
            System.out.println((i + 1) + ". " + categories.get(i).getCategoryName());
        }
        int choice = sc.nextInt();

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

    // Product 수정 기능 (화면 선택)
    public void showUpdateProduct() {
        sc.nextLine();
        System.out.print("\n수정할 상품명을 입력해주세요: ");
        String productName = sc.nextLine();
        Product product = null;

        for (int i = 0; i < categories.size(); i++) {
            for(int j = 0; j < categories.get(i).getProducts().size(); j++) {
                if (productName.equals(categories.get(i).getProducts().get(j).getProductName())) {
                    product = categories.get(i).getProducts().get(j);
                    System.out.println("현재 상품 정보: " + String.format("%-12s | %,10d원 | %-15s | 재고: %3d개",
                            product.getProductName(), product.getProductPrice(), product.getProductExplanation(), product.getProductStock()));
                }
            }
        }

        if(product == null) {
            System.out.println("일치하는 상품이 없습니다.");
            return;
        }

        System.out.println("\n수정할 항목을 선택해 주세요: ");
        System.out.println("1. 가격");
        System.out.println("2. 설명");
        System.out.println("3. 재고수량");
        int choice = sc.nextInt();

        switch (choice) {
            case 1:
            case 2:
            case 3:
                updateProduct(choice, product);
                break;
            default:
                System.out.println("잘못된 입력입니다.");
        }
    }

    public void updateProduct(int choice, Product product) {
        if (choice == 1) {
            int beforePrice = product.getProductPrice();
            System.out.print("현재 가격: " + String.format("%,10d원", product.getProductPrice()));
            System.out.print("새로운 가격을 입력해주세요: ");
            int productPrice = sc.nextInt();
            product.setProductPrice(productPrice);
            int afterPrice = product.getProductPrice();
            System.out.println("\n"+ product.getProductName() + "의 가격이 " + String.format("%,10d원", beforePrice) +
                    " -> " + String.format("%,10d원", afterPrice) + "으로 수정되었습니다.\n");
        } else if (choice == 2) {
            String beforeExplanation = product.getProductExplanation();
            System.out.println("현재 설명: " + String.format("%-15s", product.getProductExplanation()));
            System.out.print("새로운 설명을 입력해주세요: ");
            String productExplanation = sc.nextLine();
            product.setProductExplanation(productExplanation);
            String afterExplanation = product.getProductExplanation();
            System.out.println("\n"+ product.getProductName() + "의 설명이 " + String.format("%-15s", beforeExplanation) +
                    " -> " + String.format("%-15s", afterExplanation) + "으로 수정되었습니다.\n");
        } else if (choice == 3) {
            int beforeStock = product.getProductStock();
            System.out.print("현재 재고수량: " + String.format("%3d개", product.getProductStock()));
            System.out.print("새로운 재고수량을 입력해주세요: ");
            int productStock = sc.nextInt();
            product.setProductStock(productStock);
            int afterStock = product.getProductStock();
            System.out.println("\n"+ product.getProductName() + "의 재고수량이 " + String.format("%3d개", beforeStock) +
                    " -> " + String.format("%3d개", afterStock) + "으로 수정되었습니다.\n");
        }
    }



    // Product 삭제 기능
    public void deleteProduct() {
        sc.nextLine();
        System.out.print("\n삭제할 상품명을 입력해주세요: ");
        String productName = sc.nextLine();
        Product product = null;

        for (int i = 0; i < categories.size(); i++) {
            for(int j = 0; j < categories.get(i).getProducts().size(); j++) {
                if (productName.equals(categories.get(i).getProducts().get(j).getProductName())) {
                    product = categories.get(i).getProducts().get(j);
                    System.out.println("상품 정보: " + String.format("%-12s | %,10d원 | %-15s | 재고: %3d개",
                            product.getProductName(), product.getProductPrice(), product.getProductExplanation(), product.getProductStock()));
                }
            }
        }

        if(product == null) {
            System.out.println("일치하는 상품이 없습니다.");
            return;
        }

        System.out.println("\n위 상품을 삭제하시겠습니까?");
        System.out.println("1. 확인   2. 취소");
        int choice = sc.nextInt();

        if (choice == 1) {
            for (int i = 0; i < categories.size(); i++) {
                categories.get(i).getProducts().remove(product);
            }
            System.out.println("상품이 성공적으로 삭제되었습니다!\n");
        }  else if (choice == 2) {
            return;
        } else {
            System.out.println("잘못된 입력입니다.");
            return;
        }
    }

    // 전체 Product 출력
    public void showAllProduct() {

    }
}
