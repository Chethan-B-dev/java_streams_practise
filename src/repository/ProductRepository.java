package repository;


import entity.Product;

import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static utility.Bootstrap.getMockProducts;

public final class ProductRepository extends CrudRepository<Product> {

    private static ProductRepository instance = null;
    private final OrderRepository orderRepository = OrderRepository.getInstance();

    private ProductRepository() {
        super(getMockProducts());
    }

    public static ProductRepository getInstance()
    {
        if (instance == null){
            instance = new ProductRepository();
        }
        return instance;
    }

    public List<Product> getProductsThatHaveNotBeenPurchased() {
        return this.findAll()
                .stream()
                .filter(product -> !orderRepository.hasProductBeenPurchased(product))
                .collect(Collectors.toList());
    }

    public List<Product> getBooksGreaterThanHundred() {
        return this.findAll()
                .stream()
                .filter(product -> product.getCategory().equals("Books"))
                .filter(product -> product.getPrice() > 100)
                .collect(Collectors.toList());
    }

    public List<Product> getToysProductWithDiscount() {
        return this.findAll()
                .stream()
                .filter(product -> product.getCategory().equals("Toys"))
                .map(product -> product.withPrice(product.getPrice() - product.getPrice() / 10))
                .collect(Collectors.toList());
    }

    public Product getCheapestBook() {
        return this.findAll()
                .stream()
                .filter(product -> product.getCategory().equals("Books"))
                .min(Comparator.comparing(Product::getPrice))
                .orElse(null);
    }

    public DoubleSummaryStatistics getStatsOfBabyProducts() {
        return this.findAll()
                .stream()
                .filter(product -> product.getCategory().equals("Baby"))
                .mapToDouble(Product::getPrice)
                .summaryStatistics();
    }

    public Map<String, List<String>> getProductNamesGroupedByCategory() {
        return this.findAll()
                .stream()
                .collect(groupingBy(
                        Product::getCategory,
                        Collectors.mapping(Product::getName, toList())
                ));
    }

    public Map<String, Product> getMostExpensiveProductByCategory() {
        return this.findAll()
                .stream()
                .collect(groupingBy(
                        Product::getCategory,
                        collectingAndThen(Collectors.maxBy(Comparator.comparing(Product::getPrice)), Optional::get)
                ));
    }

}
