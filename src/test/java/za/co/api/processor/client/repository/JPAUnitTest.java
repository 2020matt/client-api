package za.co.api.processor.client.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import za.co.api.processor.client.model.Client;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class JPAUnitTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    ClientRepository clientRepository;

    @Test
    public void should_find_client_by_name() {

        Client client = new Client();
        client.setFirstName("Matthews");
        client.setLastName("Ramolibe");
        client.setIdNumber(8910246109088L);
        client.setMobileNumber("0745417429");
        client.setAddress("JHB");
        client.setId(1);
        //entityManager.persist(client);
        Client foundClient = clientRepository.findByFirstName(client.getFirstName()).get();

        assertThat(foundClient).isEqualTo(client);
    }

    @Test
    public void should_find_client_by_id() {

        Client client = new Client();
        client.setFirstName("Matthews");
        client.setLastName("Ramolibe");
        client.setIdNumber(8910246109088L);
        client.setMobileNumber("0745417429");
        client.setAddress("JHB");
        client.setId(1);
        //entityManager.persist(client);
        Client foundClient = clientRepository.findByIdNumber(client.getIdNumber()).get();

        assertThat(foundClient).isEqualTo(client);
    }

    @Test
    public void should_find_client_by_phone_number() {

        Client client = new Client();
        client.setFirstName("Matthews");
        client.setLastName("Ramolibe");
        client.setIdNumber(8910246109088L);
        client.setMobileNumber("0745417429");
        client.setAddress("JHB");
        client.setId(1);
        //entityManager.persist(client);
        Client foundClient = clientRepository.findByMobileNumber(client.getMobileNumber()).get();

        assertThat(foundClient).isEqualTo(client);
    }

}
