package app;

import app.domain.*;
import app.repositories.CDRepository;
import app.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import app.repositories.OrderRepository;

import java.util.List;

@SpringBootApplication
@EnableJpaRepositories("app/repositories")
@EntityScan("app/domain")
public class OrderApplication implements CommandLineRunner{
	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CDRepository cdRepository;

	public static void main(String[] args) {
		SpringApplication.run(OrderApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
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

		Order o1 = new Order("234743", "12/10/06", "open");
		o1.addOrderLine(ol1);
		o1.addOrderLine(ol2);

		Customer c1 = new Customer("Frank", "Brown", "Mainstreet 1",
				"New york", "43221");
		c1.addOrder(o1);
		o1.setCustomer(c1);

		Address addr = new Address("Mainstreet 1", "New York", "43221");
		c1.setAddress(addr);

		// Test queries
		List<Customer> allCustomers = customerRepository.findAll();
		System.out.println("\nAll Customers:");
		allCustomers.forEach(System.out::println);

		List<Customer> zipCustomers = customerRepository.findByAddressZip("43221");
		System.out.println("\nCustomers in zip 43221:");
		zipCustomers.forEach(System.out::println);

		List<Customer> rockyBuyers = customerRepository.findByOrderedDvdWithName("The best of Queen");
		System.out.println("\nCustomers who ordered 'The best of Queen':");
		rockyBuyers.forEach(System.out::println);

		List<CD> cheapQueenCDs = cdRepository.findByArtistAndPriceLessThan("Queen", 13.00);
		System.out.println("\nCheap Queen CDs:");
		cheapQueenCDs.forEach(System.out::println);

		orderRepository.save(o1);

		printOrder(o1);
	}

	public static void printOrder(Order order) {
		System.out.println("Order with orderNumber: " + order.getOrdernr());
		System.out.println("Order date: " + order.getDate());
		System.out.println("Order status: " + order.getStatus());
		Customer cust = order.getCustomer();
		System.out.println("Customer: " + cust.getFirstname() + " "
				+ cust.getLastname() + " " + cust.getAddress());
		for (OrderLine orderline : order.getOrderlines()) {
			System.out.println("Order line: quantity= "
					+ orderline.getQuantity());
			Product product = orderline.getProduct();
			System.out.println("Product: " + product.getName() + " "
					+ product.getDescription() + " " + product.getPrice());
		}

	}
}
