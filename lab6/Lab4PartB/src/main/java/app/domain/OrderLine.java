package app.domain;


import jakarta.persistence.*;
import org.hibernate.engine.internal.Cascade;

@Entity
public class OrderLine {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long ol_id;

	private int quantity;

	@OneToOne(cascade = CascadeType.PERSIST )
	private Product product;

	public OrderLine() {
	}

	public OrderLine(int quantity, Product product) {
		this.quantity = quantity;
		this.product = product;
	}


	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
