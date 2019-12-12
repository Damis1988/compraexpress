package blocojava.compraexpress.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "menu")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "menu",cascade = CascadeType.PERSIST)
    private List<Product> product;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "id_restaurant")
    Restaurant restaurant;
}
