package ebenezr.dev.roomsoftspring.controller;


import ebenezr.dev.roomsoftspring.repository.UserRepository;
import ebenezr.dev.roomsoftspring.model.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/api",produces=MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Slf4j
public class UserRestController
{
    private final UserRepository repository;

    @PostMapping({ "/users" })
    @ResponseStatus(HttpStatus.CREATED)
    public User saveUser(@RequestBody User user) {
        User savedUser = null;
        log.info("saveUser() - start: user = {}",user);
        savedUser = (User)this.repository.save(user);
        log.info("saveUser() - end: savedUser = {}", savedUser.getId());
        return savedUser;
    }

    @GetMapping({ "/users" })
    @ResponseStatus(HttpStatus.OK)
    public Iterable<User> getAllUsers() {
        log.info("getAllUsers() - start");
        Iterable<User> collection = this.repository.findAll();
        log.info("getAllUsers() - end");
        return collection;
    }

    @GetMapping({ "/users/{id}" })
    @ResponseStatus(HttpStatus.OK)
    public User getUserById(@PathVariable Long id) {
        log.info("getUserById() - start: id = {}", id);
        User receivedUser = (User)this.repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity with id = Not found"));
        log.info("getUserById() - end: user = {}", receivedUser.getId());
        return receivedUser;
    }

    @GetMapping(value = { "/users" }, params = { "name" })
    @ResponseStatus(HttpStatus.OK)
    public Iterable<User> findUserByName(@RequestParam(value = "name") String name) {
        log.info("findUserByName() - start: name = {}", name);
        Iterable<User> collection = this.repository.findByName(name);
        log.info("findUserByName() - end: collection = {}", collection);
        return collection;
    }

//    email ,name, phone, role, activeStatus
    @PutMapping({ "/users/{id}" })
    @ResponseStatus(HttpStatus.OK)
    public User updateUser(@PathVariable Long id, @RequestBody User userRequest) {
        log.info("updateUser() - start: id = {}, userRequest = {}", id, userRequest);
        User updatedUser = (User)this.repository.findById(id).map(user -> {
            user.setName(userRequest.getName());
            user.setEmail(userRequest.getEmail());
            user.setPhone(userRequest.getPhone());
            user.setRole(userRequest.getRole());
            user.setActiveStatus(userRequest.getActiveStatus());

            return (User)this.repository.save(user);
        }).orElseThrow(() -> new EntityNotFoundException("User not found with id " + id));
        log.info("updateUser() - end: updatedUser = {}", updatedUser.getId());
        return updatedUser;
    }

    @DeleteMapping({ "/users/{id}" })
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable Long id) {
        log.info("deleteUser() - start: id = {}", id);
        this.repository.deleteById(id);
        log.info("deleteUser() - end: id = {}", id);
    }

    @DeleteMapping({ "/users" })
    @ResponseStatus(HttpStatus.OK)
    public void deleteAllUsers() {
        log.info("deleteAllUsers() - start");
        this.repository.deleteAll();
        log.info("deleteAllUsers() - end");
    }

@GetMapping({ "/users/count" })
    @ResponseStatus(HttpStatus.OK)
    public Long countUsers() {
        log.info("countUsers() - start");
        Long count = this.repository.count();
        log.info("countUsers() - end");
        return count;
    }

}
