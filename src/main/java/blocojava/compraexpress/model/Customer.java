package blocojava.compraexpress.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column
    private String name;
    @Column
    private String surname;
    @Column
    private String gender;
    @Column
    private String birthday;
    @Column
    private String cpf;
    @Column
    private String email;
    @Column
    private String password;

    @OneToOne(mappedBy = "customer",cascade = CascadeType.PERSIST)
    private Address address;

    @OneToMany(mappedBy = "customer",cascade =CascadeType.PERSIST)
    private List<Order> Order;
}
