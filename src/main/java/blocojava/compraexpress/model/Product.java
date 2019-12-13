package blocojava.compraexpress.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;
    @Column
    private Double price;

    @ManyToOne
    @JoinColumn(name = "id_menu")
    private Menu menu;

    @ManyToOne
    @JoinColumn(name = "id_sector")
    private Sector sector; //e.g.: drinks, pasta, meat, seafood, desert, etc

    @OneToMany(mappedBy = "product", targetEntity = Item.class, cascade =CascadeType.PERSIST)
    private List<Item> items;
}
