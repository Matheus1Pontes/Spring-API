package SpringDB.Project.Repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import SpringDB.Project.model.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Integer> {
    Optional<AppUser> findByUsername(String username);

    Optional<AppUser> findByEmail(String email);
}
