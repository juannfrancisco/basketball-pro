package cl.grupo.maldonado.app.basket.repositories;


import cl.grupo.maldonado.app.basket.core.Coach;
import cl.grupo.maldonado.app.basket.core.championship.Championship;
import cl.grupo.maldonado.app.basket.core.game.Match;
import cl.grupo.maldonado.app.basket.core.game.MatchState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Integer> {


    List<Match> findByChampionship(Championship championship);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE tbl_match " +
            "SET state = :state " +
            "WHERE oid = :oid")
    int updateStateById(@Param("oid") Integer oid,
                        @Param("state") MatchState state);
}
