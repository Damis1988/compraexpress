package blocojava.compraexpress.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "sector")
public class Sector {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "id_menu")
    private Menu menu;

    @OneToMany(mappedBy = "sector", targetEntity = Product.class, fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Product> products;
}
