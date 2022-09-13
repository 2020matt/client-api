package za.co.api.processor.client.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "client")
@Data
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "firstname", nullable = false)
    private String firstName;

    @Column(name = "lastname", nullable = false)
    private String lastName;

    @Column(name = "mobilenumber", nullable = false, unique = true)
    private String mobileNumber;

    @Column(name = "idnumber", nullable = false, unique = true, length = 13)
    private Long idNumber;

    @Column(name = "address", nullable = false)
    private String address;

}
