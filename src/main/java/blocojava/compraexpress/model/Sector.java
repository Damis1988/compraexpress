package blocojava.compraexpress.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Entity

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

    @OneToMany(mappedBy = "sector", targetEntity = Product.class, cascade = CascadeType.PERSIST)
    private List<Product> products;

    @Override
    public String toString() {
        return "Sector{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
