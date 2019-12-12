package blocojava.compraexpress.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "purchase")
public class Order {

    /*
    *   Refers to the customer's order / finalization of shopping cart.
    * */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private List<Item> itemSet;
    @JoinColumn
    private Double total;

    @OneToMany(fetch=FetchType.LAZY)
    @JoinColumn(name = "id_customer")
    private Customer customer;

 }
