package SpringDB.Project.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import SpringDB.Project.model.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {
    List<Address> findByCustomer_Id(Integer customerId);
}
