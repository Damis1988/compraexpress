package blocojava.compraexpress.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String sector; //e.g.: drinks, pasta, meat, seafood, desert, etc
    @Column
    private String name;
    @Column
    private String description; //description, ingredients, moto, etc
    @Column
    private Double price;
}
