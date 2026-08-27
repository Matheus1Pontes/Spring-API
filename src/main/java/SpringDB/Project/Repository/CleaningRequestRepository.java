package SpringDB.Project.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import SpringDB.Project.model.CleaningRequest;
import SpringDB.Project.model.enums.RequestStatus;

public interface CleaningRequestRepository extends JpaRepository<CleaningRequest, Integer> {
    List<CleaningRequest> findByStatus(RequestStatus status);
}
