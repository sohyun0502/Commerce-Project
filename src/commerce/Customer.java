package commerce;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Customer {

    private Scanner sc = new Scanner(System.in);
    private String customerName;
    private String customerEmail;
    private CustomerLevel customerLevel;
    private int totalPrice;
    private List<Customer> customers;

    public Customer() {
        this.customers = Customer.createCustomers();
    }

    public Customer(String customerName, String customerEmail, CustomerLevel customerLevel, int totalPrice) {
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerLevel = customerLevel;
        this.totalPrice = totalPrice;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public CustomerLevel getCustomerLevel() {
        return customerLevel;
    }

    /**
     * 고객 생성
     */
    public static List<Customer> createCustomers() {
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer("홍길동", "hong@gmail.com", CustomerLevel.BRONZE, 300000));
        customers.add(new Customer("김아무", "kimamu@naver.com", CustomerLevel.SILVER, 900000));
        customers.add(new Customer("최장군", "choi@gmail.com", CustomerLevel.GOLD, 1900000));
        return customers;
    }

    /**
     * 고객을 이메일로 찾는 메서드
     * @param email
     * @return
     */
    public Customer findCustomerByEmail(String email) {
        return customers.stream()
                .filter(c -> c.getCustomerEmail().equals(email))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 이메일 입니다."));
    }

    /**
     * 고객 이메일 입력하는 메서드
     * @return
     */
    public Customer chooseCustomer() {
        System.out.println("고객 이메일을 입력해주세요.");
        System.out.print("입력 : ");
        String email = sc.nextLine();

        Customer customer = findCustomerByEmail(email);

        System.out.println("\n해당 유저는 " + customer.getCustomerLevel()
                + " 등급 이므로 "
                + customer.getCustomerLevel().getDiscount()
                + "% 할인이 적용됩니다.");

        return customer;
    }

    public void updateLevel(int price) {
        this.totalPrice += price;
        CustomerLevel newLevel = CustomerLevel.fromTotalPrice(this.totalPrice);

        if (this.customerLevel != newLevel) {
            this.customerLevel = newLevel;
            System.out.println("고객 등급이 " + newLevel + " 등급으로 변경되었습니다.");
        }
    }
}
