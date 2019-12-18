package blocojava.compraexpress.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "restaurant")
public class Restaurant{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String legalName;
    @Column
    private String tradeName;
    @Column
    private String cnpj;
    @Column
    private String phone;

    @OneToOne
    @JoinColumn(name = "id_address")
    private Address address;

    @OneToOne
    @JoinColumn(name = "id_menu")
    private Menu menu;

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                '}';
    }
}
