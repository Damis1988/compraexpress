package blocojava.compraexpress.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "order")
public class Order {

    /*
    *   Refers to the customer's order / finalization of shopping cart.
    * */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Set<Item> itemSet;
    @Column
    private Double total;
    @ManyToOne
    @JoinColumn(name = "id_customer")
    private Customer customer;
}
