package blocojava.compraexpress.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "menu")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "menu", targetEntity = Sector.class,cascade =CascadeType.PERSIST)
    private List<Sector> sectors;

    @OneToMany(mappedBy = "menu", targetEntity = Product.class, cascade =CascadeType.PERSIST)
    private List<Product> products;

    @OneToOne(mappedBy = "menu")
    private Restaurant restaurant;
}
