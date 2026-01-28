package commerce;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PerformanceTest {
    private static final int PRODUCT_COUNT = 10_000;
    private static final int SEARCH_COUNT = 1_000;

    public void compareSearchPerformance() {

        // 1. 대용량 상품 데이터 생성
        List<Product> products = generateProducts();

        // 2. SearchEngine 생성 (이진탐색용)
        SearchEngine searchEngine = new SearchEngine(products);

        // 3. 랜덤 검색어 준비
        List<String> searchKeywords = generateRandomKeywords();

        // 4. 완전탐색 시간 측정
        long linearTime = measureLinearSearch(products, searchKeywords);

        // 5. 이진탐색 시간 측정
        long binaryTime = measureBinarySearch(searchEngine, searchKeywords);

        // 6. 결과 출력
        System.out.println("==== 성능 비교 결과 ====");
        System.out.println("완전탐색: " + linearTime + " ns");
        System.out.println("이진탐색: " + binaryTime + " ns");
        System.out.println("성능 향상: " + (linearTime / binaryTime) + " 배");
    }

    // =========================
    // 완전탐색 성능 측정
    // =========================
    private long measureLinearSearch(List<Product> products, List<String> keywords) {

        long startTime = System.nanoTime();

        for (String keyword : keywords) {
            for (Product product : products) {
                if (product.getProductName().equals(keyword)) {
                    break;
                }
            }
        }

        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    // =========================
    // 이진탐색 성능 측정
    // =========================
    private long measureBinarySearch(SearchEngine searchEngine, List<String> keywords) {

        long startTime = System.nanoTime();

        for (String keyword : keywords) {
            searchEngine.binarySearchIterative(keyword);
        }

        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    // =========================
    // 상품 데이터 생성
    // =========================
    private List<Product> generateProducts() {
        List<Product> products = new ArrayList<>();

        for (int i = 1; i <= PRODUCT_COUNT; i++) {
            String name = String.format("Product_%04d", i);
            products.add(new Product(name, i*1000, i+"번째 상품", 10));
        }

        return products;
    }

    // =========================
    // 랜덤 검색어 생성
    // =========================
    private List<String> generateRandomKeywords() {
        List<String> keywords = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < SEARCH_COUNT; i++) {
            int number = random.nextInt(PRODUCT_COUNT) + 1;
            keywords.add(String.format("Product_%04d", number));
        }

        return keywords;
    }
}
