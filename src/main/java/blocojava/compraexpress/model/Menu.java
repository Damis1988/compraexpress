package blocojava.compraexpress.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
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

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_restaurant")
    private Restaurant restaurant;

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                '}';
    }
}
