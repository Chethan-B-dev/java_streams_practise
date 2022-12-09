import repository.CustomerRepository;
import repository.OrderRepository;
import repository.ProductRepository;

public class Main {

    private static final OrderRepository orderRepository = OrderRepository.getInstance();
    private static final CustomerRepository customerRepository = CustomerRepository.getInstance();
    private static final ProductRepository productRepository = ProductRepository.getInstance();

    // ! https://blog.devgenius.io/15-practical-exercises-help-you-master-java-stream-api-3f9c86b1cf82

    public static void main(String[] args) {
        // find all the customers who have not placed an order
        // [gowtham, abhi]
        // System.out.println(customerRepository.findCustomersWhoHaveNotPlacedAnOrder());

        // order with maximum purchase amount
        // order1 -> 300, 400 -> 700
        // order2 -> 300 + 3000 -> 3300

        // System.out.println(orderRepository.getOrderWithMaximumPurchase());

        // products which are not sold
        // [tab]
        // System.out.println(productRepository.getProductsThatHaveNotBeenPurchased());

        // Obtain a list of products belongs to category “Books” with price > 100
        // System.out.println(productRepository.getBooksGreaterThanHundred());

        // Obtain a list of order with products belong to category “Baby”

        // System.out.println(orderRepository.getOrdersWithBabyProduct());

        // Obtain a list of product with category = “Toys” and then apply 10% discount

        // System.out.println(productRepository.getToysProductWithDiscount());

        // Obtain a list of products ordered by customer of tier 2 between 01-Feb-2021 and 01-Apr-2021
        // [tshirt,watch,book1]

        // System.out.println(orderRepository.getProductsWithTier2Date());

        // Get the cheapest products of “Books” category
        // System.out.println(productRepository.getCheapestBook());

        // Get the 3 most recent placed order
        // for this sort by placed date and limit(3)
        // System.out.println(orderRepository.getThreeMostRecentOrders());

        // Get a list of orders which were ordered on 15-Mar-2021,
        // log the order records to the console and then return its product list
        // System.out.println(orderRepository.getOnDate());

        // Calculate total lump sum of all orders (or money earned) placed in Feb 2021
        // try to do this using grouping by
        // System.out.println(orderRepository.getLumpSumInYear(2021, 2));
        // Map<Month, Double>
        /*
        Map<Month, Double> allMonthsLumpSumOfYear = orderRepository.getAllMonthsLumpSumOfYear(2022);
        Map<Month, Double> allMonths = Arrays.stream(Month.values())
                .collect(toMap(Function.identity(), month -> allMonthsLumpSumOfYear.getOrDefault(month, 0.0)));

        allMonths.forEach((month, aDouble) -> System.out.println(month + " - " + aDouble));
        */
        // Calculate order average payment placed on 14-Mar-2021
        // System.out.println(orderRepository.orderAverageOnDate(LocalDate.parse("2021-03-14")));

        //Obtain a collection of statistic figures (i.e. sum, average, max, min, count) for all products of category “Books”
//        System.out.println(productRepository.getStatsOfBabyProducts());
        // Obtain a data map with order id and order’s product count

        // System.out.println(orderRepository.getMapOrderIdAndProductCount());

        // Produce a data map with order records grouped by customer
        // System.out.println(orderRepository.getOrdersGroupedByCustomers());

        // Produce a data map with order record and product total sum
        // System.out.println(orderRepository.getOrderMapWithTotalSum());

        // Obtain a data map with list of product name by category

        // System.out.println(productRepository.getProductNamesGroupedByCategory());

         // Get the most expensive product by category
         // System.out.println(productRepository.getMostExpensiveProductByCategory());
    }
}
