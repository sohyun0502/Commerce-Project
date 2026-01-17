package commerce;

import java.util.Scanner;

public class Customer {
    private String customerName;
    private String customerEmail;
    private String customerLevel;
    private Scanner sc = new Scanner(System.in);

    public CustomerLevel chooseCustomerLevel() {
        System.out.println("고객 등급을 입력해주세요.");
        for (int i=0; i<CustomerLevel.values().length; i++) {
            System.out.println(i+1 + ". " + CustomerLevel.values()[i] + " : " +
                    CustomerLevel.values()[i].getDiscount() + "% 할인");
        }
        int choice = sc.nextInt();

        if (choice != 1 && choice != 2 && choice != 3 && choice != 4) {
            System.out.println("잘못된 입력입니다.");
            return null;
        }
        return CustomerLevel.values()[choice-1];
    }
}
