package cl.grupo.maldonado.app.basket.repositories;


import cl.grupo.maldonado.app.basket.core.Coach;
import cl.grupo.maldonado.app.basket.core.championship.Championship;
import cl.grupo.maldonado.app.basket.core.game.Match;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Integer> {


    List<Match> findByChampionship(Championship championship);
}
