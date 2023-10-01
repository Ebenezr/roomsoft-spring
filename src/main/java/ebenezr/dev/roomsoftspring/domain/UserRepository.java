package ebenezr.dev.roomsoftspring.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByName(String name);
}
