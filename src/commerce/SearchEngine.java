package commerce;

import java.util.Comparator;
import java.util.List;

public class SearchEngine {
    private List<Product> sortedProducts;

    public SearchEngine(List<Product> products) {
        products.sort(Comparator.comparing(Product::getProductName));
        this.sortedProducts = products;
    }

    // 이진탐색 - 재귀 방식
    public Product binarySearchRecursive(String productName, int left, int right) {
        // 탐색 범위가 더 이상 없으면 실패
        if (left > right) {
            return null;
        }

        int mid = (left + right) / 2;
        Product midProduct = sortedProducts.get(mid);

        int compareResult = midProduct.getProductName().compareTo(productName);

        if (compareResult == 0) {
            // 찾음
            return midProduct;
        } else if (compareResult > 0) {
            // 중간값이 더 크면 → 왼쪽 탐색
            return binarySearchRecursive(productName, left, mid - 1);
        } else {
            // 중간값이 더 작으면 → 오른쪽 탐색
            return binarySearchRecursive(productName, mid + 1, right);
        }
    }

    // 이진탐색 - 반복문 방식
    public Product binarySearchIterative(String productName) {
        int left = 0;
        int right = sortedProducts.size() - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            Product midProduct = sortedProducts.get(mid);

            int compareResult = midProduct.getProductName().compareTo(productName);

            if (compareResult == 0) {
                // 찾음
                return midProduct;
            } else if (compareResult > 0) {
                // 왼쪽 범위로 이동
                right = mid - 1;
            } else {
                // 오른쪽 범위로 이동
                left = mid + 1;
            }
        }

        // 끝까지 못 찾음
        return null;
    }
}
