package blocojava.compraexpress.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

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

    @OneToMany(mappedBy = "product", targetEntity = Item.class,fetch = FetchType.EAGER, cascade =CascadeType.PERSIST)
    private Set<Item> items;
}
