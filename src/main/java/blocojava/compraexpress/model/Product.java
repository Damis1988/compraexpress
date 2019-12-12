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
    private String sector; //e.g.: drinks, pasta, meat, seafood, desert, etc
    @Column
    private String name;
    @Column
    private String description; //description, ingredients, moto, etc
    @Column
    private Double price;

    @ManyToMany (fetch=FetchType.LAZY)
    @JoinColumn(name = "id_menu")
    private List<Menu> menu;

    @ManyToMany (fetch=FetchType.LAZY)
    @JoinColumn(name = "id_item")
    private List<Item> item;

}
