package blocojava.compraexpress.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "customer")
public class Customer extends Client{

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
}
