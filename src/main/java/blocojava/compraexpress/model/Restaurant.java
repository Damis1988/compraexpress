package blocojava.compraexpress.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "restaurant")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_restaurant;

    @Column
    private String legalName;
    @Column
    private String tradeName;
    @Column
    private String cnpj;

    @OneToOne(mappedBy = "restaurant",cascade = CascadeType.PERSIST)
    private Address address;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_menu")
    Menu menu;
}
