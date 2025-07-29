package app.repositories;

import app.domain.Address;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AddressRepository {
    @Query(value = "SELECT * FROM address WHERE city = 'Amsterdam'", nativeQuery = true)
    List<Address> findAddressesInAmsterdam();
}
