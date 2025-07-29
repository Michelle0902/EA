package app;

import app.domain.*;
import app.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

@SpringBootApplication
@EnableJpaRepositories("app/repositories")
@EntityScan("app/domain")
public class OrderApplication implements CommandLineRunner {
	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CDRepository cdRepository;

	@Autowired
	private AddressRepository addressRepository;

	public static void main(String[] args) {
		SpringApplication.run(OrderApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Setup test data
		Book product = new Book();
		product.setName("Hibernate 3");
		product.setDescription("Good book on Hibernate");
		product.setPrice(35.50);
		product.setIsbn("978-1-23456-789-0");

		OrderLine ol1 = new OrderLine(2, product);

		CD product2 = new CD();
		product2.setName("The best of Queen");
		product2.setDescription("Album from 1995");
		product2.setPrice(12.98);
		product2.setArtist("Queen");

		OrderLine ol2 = new OrderLine(4, product2);

		DVD product3 = new DVD();
		product3.setName("Rocky3");
		product3.setDescription("Boxing movie");
		product3.setPrice(8.98);
		product3.setGenre("drama");

		OrderLine ol3 = new OrderLine(4, product3);

		Order o1 = new Order("234743", "12/10/06", "closed");
		o1.addOrderLine(ol1);
		o1.addOrderLine(ol2);
		o1.addOrderLine(ol3);

		Order o2 = new Order("234744", "12/10/07", "open");
		o2.addOrderLine(ol1);
		o2.addOrderLine(ol2);
		o2.addOrderLine(ol3);

		Customer c1 = new Customer("Frank", "Brown");
		c1.addOrder(o1);
		o1.setCustomer(c1);

		Address addr = new Address("Mainstreet 1", "Amsterdam", "43221", "USA");
		c1.setAddress(addr);

		Customer c2 = new Customer("Fr", "Br");
		c2.addOrder(o2);
		o2.setCustomer(c2);

		Address addr2 = new Address("Mainstreet 2", "Amsterdam", "43223", "Netherlands");
		c2.setAddress(addr2);

		orderRepository.save(o1);
		orderRepository.save(o2);

		// Named Queries
		System.out.println("\nNamed query - Customers from USA:");
		customerRepository.findAllFromUSA().forEach(System.out::println);

		System.out.println("\nNamed query - CDs from Queen:");
		cdRepository.findByArtist("Queen").forEach(System.out::println);

		// JPQL @Query
		System.out.println("\nJPQL - Ordernumbers of closed orders:");
		orderRepository.findClosedOrderNumbers().forEach(System.out::println);

		System.out.println("\nJPQL - Customers in Amsterdam:");
		customerRepository.findCustomerNamesFromAmsterdam().forEach(System.out::println);

		System.out.println("\nJPQL - Ordernumbers from customers in city:");
		orderRepository.findOrderNumbersByCustomerCity("Amsterdam").forEach(System.out::println);

		System.out.println("\nJPQL - CDs from Queen with price > 10:");
		cdRepository.findCDsByArtistAndPriceGreaterThan("Queen", 10.0).forEach(System.out::println);

		// Native Queries
		System.out.println("\nNative - Addresses in Amsterdam:");
		addressRepository.findAddressesInAmsterdam().forEach(System.out::println);

		System.out.println("\nNative - CDs from U2:");
		cdRepository.findCDsByU2().forEach(System.out::println);

		// Specifications
		System.out.println("\nSpecification - Closed orders:");
		orderRepository.findAll(OrderSpecifications.statusIsClosed()).forEach(o -> System.out.println(o.getOrdernr()));

		System.out.println("\nSpecification - Customers in Amsterdam:");
		customerRepository.findAll(CustomerSpecifications.livesInAmsterdam()).forEach(System.out::println);

		System.out.println("\nSpecification - CDs by Queen with price > 10:");
		cdRepository.findAll(CDSpecifications.byArtistAndPriceGreaterThan("Queen", 10.0)).forEach(System.out::println);

		printOrder(o1);
	}

	public static void printOrder(Order order) {
		System.out.println("Order with orderNumber: " + order.getOrdernr());
		System.out.println("Order date: " + order.getDate());
		System.out.println("Order status: " + order.getStatus());
		Customer cust = order.getCustomer();
		System.out.println("Customer: " + cust.getFirstname() + " " + cust.getLastname() + " " + cust.getAddress());
		for (OrderLine orderline : order.getOrderlines()) {
			System.out.println("Order line: quantity= " + orderline.getQuantity());
			Product product = orderline.getProduct();
			System.out.println("Product: " + product.getName() + " " + product.getDescription() + " " + product.getPrice());
		}
	}
}