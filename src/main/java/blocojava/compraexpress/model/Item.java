package blocojava.compraexpress.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "item")
public class Item {

/* 'Item' refers to each item included in the shopping cart.
    Multiple identical products will be counted as one single item.
    Each item corresponds to one line in the invoice.
 */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer quantity;
    @Column
    private Double value;

    @ManyToOne
    @JoinColumn(name = "id_product")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "id_order")
    private Order order;

    public Item(Integer quantity, Product product) {
        this.quantity = quantity;
        this.product = product;
        updateValue();
    }

    public void updateValue(){
        this.value = this.product.getPrice() * this.quantity;
    }

    public void setQuantity(Integer quantity){
        this.quantity = quantity;
        updateValue();
    }
}
