package blocojava.compraexpress.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String street;
    @Column
    private String number;
    @Column
    private String complement;
    @Column
    private String zip;
    @Column
    private String neighborhood;
    @Column
    private String city;
    @Column
    private String state;
    @Column
    private String country;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "id_customer")
    private Customer customer;


    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "id_restaurant")
    private Restaurant restaurant;
}
