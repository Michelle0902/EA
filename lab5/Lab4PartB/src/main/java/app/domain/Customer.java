package app.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long cus_id;
	private String firstname;
	private String lastname;

	@OneToOne(cascade = CascadeType.ALL)
	private Address address;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private Collection<Order> theOrders = new ArrayList<Order>();

	public Customer() {
	}

	public Customer(String firstname, String lastname, String street,
			String city, String zip) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.address = new Address(street, city, zip);
	}


	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public Address getAddress() { return address; }

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public void setAddress(Address address){
		this.address=address;
	}

	public Collection<Order> getTheOrders() {
		return Collections.unmodifiableCollection(theOrders);
	}
	public boolean addOrder(Order order) {
		boolean added = theOrders.add(order);
		if (added) {
			order.setCustomer(this);
		}
		return added;
	}

	public boolean removeOrder(Order order) {
		boolean removed = theOrders.remove(order);
		if (removed) {
			theOrders.remove(order);
		}
		return removed;
	}
}
