package SpringDB.Project.Controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import SpringDB.Project.Service.AppUserService;
import SpringDB.Project.model.AppUser;

@RestController
@RequestMapping("/users")
public class AppUserController {

    private final AppUserService userService;

    public AppUserController(AppUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppUser> findById(@PathVariable("id") Integer id) {
        AppUser user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<AppUser> create(@RequestBody AppUser user) {
        AppUser createdUser = userService.createAppUser(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdUser.getId()).toUri();
        return ResponseEntity.created(location).body(createdUser);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<AppUser> findByUsername(@PathVariable("username") String username) {
        AppUser user = userService.getUserByUsername(username);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppUser> update(@PathVariable("id") Integer id, @RequestBody AppUser updatedUser) {
        AppUser user = userService.updateAppUser(id, updatedUser);
        return ResponseEntity.ok(user);
    }

}
