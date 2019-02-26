package cl.grupo.maldonado.app.basket.repositories;


import cl.grupo.maldonado.app.basket.core.administration.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
