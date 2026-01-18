package commerce;

import java.util.Scanner;

public class Customer {

    private Scanner sc = new Scanner(System.in);

    /**
     * 고객 등급 선택하는 메서드
     * @return
     */
    public CustomerLevel chooseCustomerLevel() {
        while (true) {
            try {
                System.out.println("고객 등급을 입력해주세요.");

                for (int i=0; i<CustomerLevel.values().length; i++) {
                    System.out.println(i+1 + ". " + CustomerLevel.values()[i] + " : " +
                            CustomerLevel.values()[i].getDiscount() + "% 할인");
                }
                int choice = Integer.parseInt(sc.nextLine());

                if (choice < 1 || choice > CustomerLevel.values().length) {
                    System.out.println("올바른 번호를 선택해주세요.");
                    continue;
                }
                return CustomerLevel.values()[choice-1];

            } catch (NumberFormatException e) {
                System.out.println("숫자만 입력해주세요.");
            }
        }
    }
}
