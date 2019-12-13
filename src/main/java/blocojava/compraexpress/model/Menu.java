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

    @OneToMany(mappedBy = "menu", targetEntity = Sector.class,fetch = FetchType.EAGER,cascade =CascadeType.PERSIST)
    private Set<Sector> sectors;

    @OneToMany(mappedBy = "menu", targetEntity = Product.class,fetch = FetchType.EAGER, cascade =CascadeType.PERSIST)
    private Set<Product> products;

    @OneToOne
    @JoinColumn(name = "id_restaurant")
    private Restaurant restaurant;
}
