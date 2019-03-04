package cl.grupo.maldonado.app.basket.repositories;


import cl.grupo.maldonado.app.basket.core.game.Match;
import cl.grupo.maldonado.app.basket.core.game.MatchStat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchStatRepository extends JpaRepository<MatchStat, Integer> {

    List<MatchStat> findByMatch(Match match);



}
