package za.co.api.processor.client.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.api.processor.client.model.Client;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findByIdNumber(Long aLong);
    Optional<Client> findByFirstName(String aString);
    Optional<Client> findByMobileNumber(String aString);

}
