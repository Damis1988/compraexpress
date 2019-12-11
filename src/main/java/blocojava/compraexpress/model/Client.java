package blocojava.compraexpress.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_address")
    private Address address;
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_phone")
    private Set<Phone> phoneSet;
}
