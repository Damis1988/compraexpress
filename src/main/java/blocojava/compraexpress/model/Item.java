package blocojava.compraexpress.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "item")
public class Item extends Product{

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
    @Column
    private String observations; //tell the restaurant any extra information about the order
}