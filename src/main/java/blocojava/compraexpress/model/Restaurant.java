package blocojava.compraexpress.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "restaurant")
public class Restaurant extends Client{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_restaurant;

    @Column
    private String legalName;
    @Column
    private String tradeName;
    @Column
    private String cnpj;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_menu")
    Menu menu;
}
