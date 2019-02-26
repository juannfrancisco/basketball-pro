package cl.grupo.maldonado.app.basket.repositories;


import cl.grupo.maldonado.app.basket.core.Coach;
import cl.grupo.maldonado.app.basket.core.championship.Championship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChampionshipRepository extends JpaRepository<Championship, Integer> {
}
