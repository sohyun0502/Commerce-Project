package commerce;

import java.util.List;
import java.util.Scanner;

public class Administrator {

    private Scanner sc = new Scanner(System.in);
    private List<Category> categories;
    private CommerceSystem commerceSystem;
    private final String password = "admin123";

    public Administrator(List<Category> categories, CommerceSystem commerceSystem) {
        this.categories = categories;
        this.commerceSystem = commerceSystem;
    }

    /**
     * 관리자 인증 기능
     * 비밀번호 입력 시 3회 실패하면 메인 메뉴로 돌아가기
     */
    public void checkPassword() {
        int failCount = 0;

        while (failCount < 3) {
            System.out.println("\n관리자 비밀번호를 입력해주세요:");
            String pw = sc.nextLine();

            if(pw.equals(password)) {
                showAdminMode();
                return;
            } else {
                failCount++;
                System.out.println("비밀번호가 일치하지 않습니다. (" + failCount + "회 실패)");
            }
        }

        System.out.println("\n비밀번호 입력 3회 실패. 메인 메뉴로 돌아갑니다.");
    }

    /**
     * 관리자 모드 메뉴 출력
     */
    public void showAdminMode() {
        boolean isAdminMode = true;

        while(isAdminMode) {
            System.out.println("\n[ 관리자 모드 ]");
            System.out.println("1. 상품 추가");
            System.out.println("2. 상품 수정");
            System.out.println("3. 상품 삭제");
            System.out.println("4. 전체 상품 현황");
            System.out.println("0. 메인으로 돌아가기");
            int choice = readInt();

            switch (choice) {
                case 0:
                    isAdminMode = false;
                    break;
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

    /**
     * 상품 추가 기능
     */
    public void addProduct() {
        System.out.println("\n어느 카테고리에 상품을 추가하시겠습니까?");
        for (int i = 0; i < categories.size(); i++) {
            System.out.println((i + 1) + ". " + categories.get(i).getCategoryName());
        }
        int choice = readInt();

        if (choice < 1 || choice > categories.size()) {
            System.out.println("존재하지 않는 카테고리입니다.");
            return;
        }

        Category category = categories.get(choice - 1);

        System.out.println("\n[ " + category.getCategoryName() + " 카테고리에 상품 추가 ]");

        System.out.print("상품명을 입력해주세요: ");
        String productName = sc.nextLine();

        System.out.print("가격을 입력해주세요: ");
        int productPrice = readInt();

        System.out.print("상품 설명을 입력해주세요: ");
        String productExplanation = sc.nextLine();

        System.out.print("재고수량을 입력해주세요: ");
        int productStock = readInt();

        System.out.println("\n"+String.format("%-12s | %,10d원 | %-15s | 재고: %3d개", productName, productPrice, productExplanation, productStock));
        System.out.println("위 정보로 상품을 추가하시겠습니까?");
        System.out.println("1. 확인    2. 취소");
        int choice2 = readInt();

        if (choice2 == 1) {
            Product product = new Product(productName, productPrice, productExplanation, productStock);
            category.addProduct(product);
            System.out.println("상품이 성공적으로 추가되었습니다!\n");
        }  else if (choice2 == 2) {
            return;
        } else {
            System.out.println("잘못된 입력입니다.");
            return;
        }
    }

    /**
     * 상품 수정 기능 (선택 화면 출력)
     */
    public void showUpdateProduct() {
        System.out.print("\n수정할 상품명을 입력해주세요: ");
        String productName = sc.nextLine();

        Product targetProduct = null;
        Category targetCategory = null;

        for (Category category : categories) {
            SearchEngine searchEngine = new SearchEngine(category.getProducts());

            // 재귀
            targetProduct = searchEngine.binarySearchRecursive(productName, 0, category.getProducts().size() - 1);

            // 반복문
            // targetProduct = searchEngine.binarySearchIterative(productName);

            if(targetProduct != null) {
                targetCategory = category;

                System.out.println("현재 상품 정보: " + String.format("%-12s | %,10d원 | %-15s | 재고: %3d개",
                        targetProduct.getProductName(), targetProduct.getProductPrice(),
                        targetProduct.getProductExplanation(), targetProduct.getProductStock()));
                break;
            }

            /*for (Product product : category.getProducts()) {
                if (product.getProductName().equalsIgnoreCase(productName.trim())) {
                    targetProduct = product;
                    targetCategory = category;
                    System.out.println("현재 상품 정보: " + String.format("%-12s | %,10d원 | %-15s | 재고: %3d개",
                            targetProduct.getProductName(), targetProduct.getProductPrice(),
                            targetProduct.getProductExplanation(), targetProduct.getProductStock()));
                    break;
                }
            }
            if (targetProduct != null) break;*/
        }

        if (targetProduct == null) {
            System.out.println("해당 상품을 찾을 수 없습니다.");
            return;
        }

        System.out.println("\n수정할 항목을 선택해 주세요: ");
        System.out.println("1. 가격");
        System.out.println("2. 설명");
        System.out.println("3. 재고수량");
        int choice = readInt();

        switch (choice) {
            case 1:
            case 2:
            case 3:
                updateProduct(choice, targetProduct);
                break;
            default:
                System.out.println("잘못된 입력입니다.");
        }
    }

    /**
     * 실제 상품 수정 메서드
     * @param choice
     * @param product
     */
    public void updateProduct(int choice, Product product) {
        if (choice == 1) {
            int beforePrice = product.getProductPrice();
            System.out.println("\n현재 가격: " + String.format("%,10d원", product.getProductPrice()));
            System.out.print("새로운 가격을 입력해주세요: ");
            int newPrice = readInt();
            product.setProductPrice(newPrice);
            int afterPrice = product.getProductPrice();
            System.out.println("\n"+ product.getProductName() + "의 가격이 " + String.format("%,10d원", beforePrice) +
                    " -> " + String.format("%,10d원", afterPrice) + "으로 수정되었습니다.\n");
        } else if (choice == 2) {
            String beforeExplanation = product.getProductExplanation();
            System.out.println("현재 설명: " + String.format("%-15s", product.getProductExplanation()));
            System.out.print("새로운 설명을 입력해주세요: ");
            String newExplanation = sc.nextLine();
            product.setProductExplanation(newExplanation);
            String afterExplanation = product.getProductExplanation();
            System.out.println("\n"+ product.getProductName() + "의 설명이 " + String.format("%-15s", beforeExplanation) +
                    " -> " + String.format("%-15s", afterExplanation) + "으로 수정되었습니다.\n");
        } else if (choice == 3) {
            int beforeStock = product.getProductStock();
            System.out.println("현재 재고수량: " + String.format("%3d개", product.getProductStock()));
            System.out.print("새로운 재고수량을 입력해주세요: ");
            int newStock = readInt();
            product.setProductStock(newStock);
            int afterStock = product.getProductStock();
            System.out.println("\n"+ product.getProductName() + "의 재고수량이 " + String.format("%3d개", beforeStock) +
                    " -> " + String.format("%3d개", afterStock) + "으로 수정되었습니다.\n");
        }
    }

    /**
     * 상품 삭제 기능
     */
    public void deleteProduct() {
        System.out.print("\n삭제할 상품명을 입력해주세요: ");
        String productName = sc.nextLine();

        Product targetProduct = null;
        Category targetCategory = null;

        for (Category category : categories) {
            SearchEngine searchEngine = new SearchEngine(category.getProducts());

            // 재귀
            targetProduct = searchEngine.binarySearchRecursive(productName, 0, category.getProducts().size() - 1);

            // 반복문
            // targetProduct = searchEngine.binarySearchIterative(productName);

            if(targetProduct != null) {
                targetCategory = category;
                break;
            }

            /*for (Product product : category.getProducts()) {
                if (product.getProductName().equals(productName)) {
                    targetProduct = product;
                    targetCategory = category;
                    break;
                }
            }
            if (targetProduct != null) {
                break;
            }*/
        }

        if(targetProduct == null) {
            System.out.println("일치하는 상품이 없습니다.");
            return;
        }

        System.out.println("\n" + targetProduct.toString());
        System.out.println("위 상품을 삭제하시겠습니까?");
        System.out.println("1. 확인    2. 취소");
        int choice = readInt();

        if (choice == 1) {
            // 상품 삭제
            targetCategory.removeProduct(targetProduct);
            // 장바구니에서도 삭제
            Cart cart = commerceSystem.getCart();
            if (cart != null) {
                cart.removeProduct(targetProduct);
            }
            System.out.println("상품이 성공적으로 삭제되었습니다!\n");
        }  else if (choice == 2) {
            return;
        } else {
            System.out.println("잘못된 입력입니다.");
            return;
        }
    }

    /**
     * 전체 상품 목록 출력
     */
    public void showAllProduct() {
        for (Category category : categories) {
            System.out.println("\n[ " + category.getCategoryName() + " ]");

            if (category.getProducts().isEmpty()) {
                System.out.println("등록된 상품이 없습니다.");
                continue;
            }

            for (Product product : category.getProducts()) {
                System.out.println(
                        product.getProductName() + " | " +
                                product.getProductPrice() + "원 | " +
                                product.getProductExplanation() + " | 재고: " +
                                product.getProductStock() + "개"
                );
            }
        }
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
