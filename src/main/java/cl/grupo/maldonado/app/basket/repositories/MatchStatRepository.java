package cl.grupo.maldonado.app.basket.repositories;


import cl.grupo.maldonado.app.basket.core.Player;
import cl.grupo.maldonado.app.basket.core.game.Match;
import cl.grupo.maldonado.app.basket.core.game.MatchStat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MatchStatRepository extends JpaRepository<MatchStat, Integer> {

    List<MatchStat> findByMatch(Match match);


    List<MatchStat> findByPlayer(Player player);

    @Query("SELECT type, sum(value) FROM tbl_match_stat where player_oid=:oid group by type")
    List<MatchStat> getStatsByPlayer(Integer oid);



}
