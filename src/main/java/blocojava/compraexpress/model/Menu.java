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

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_sector")
    private List<String> sectors;
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_produto")
    private List<Product> products;
}
