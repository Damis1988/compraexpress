package blocojava.compraexpress.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "customer")
public class Customer{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;
    @Column
    private String surname;
    @Column
    private String cpf;
    @Column
    private String email;
    @Column
    private String password;
    @Column
    private String phone;

    @OneToOne
    @JoinColumn(name = "id_address")
    private Address address;

    @OneToMany(mappedBy = "customer", targetEntity = Order.class, cascade =CascadeType.PERSIST)
    private List<Order> order;
}
