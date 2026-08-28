package SpringDB.Project.Service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import SpringDB.Project.Repository.AppUserRepository;
import SpringDB.Project.model.AppUser;

@Service
public class AppUserService {
    @Autowired
    private AppUserRepository repository;

    public AppUserService(AppUserRepository repository) {
        this.repository = repository;
    }

    public AppUser createAppUser(AppUser user) {
        if (repository.findByUsername(user.getUsername()).isPresent()
            || 
            repository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Username or email already exists!");
        }

        return repository.save(user);
    }

    public AppUser getUserById(Integer id) {
        return repository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public AppUser getUserByUsername(String username) {
        return repository.findByUsername(username).orElseThrow(NoSuchElementException::new);
    }

    public AppUser updateAppUser(Integer id, AppUser updatedUser) {
        if (repository.existsById(id)) {
            updatedUser.setId(id);
            return repository.save(updatedUser);
        } else {
            throw new NoSuchElementException("User does not exist!");
        }
    } 
}
