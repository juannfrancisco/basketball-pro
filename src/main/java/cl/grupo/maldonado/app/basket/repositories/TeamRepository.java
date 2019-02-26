package cl.grupo.maldonado.app.basket.repositories;

import cl.grupo.maldonado.app.basket.core.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Integer> {

    List<Team> findByNameURL(String nameURL);
}
