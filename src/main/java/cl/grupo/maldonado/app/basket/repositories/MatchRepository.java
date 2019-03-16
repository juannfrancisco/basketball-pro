package cl.grupo.maldonado.app.basket.repositories;

import cl.grupo.maldonado.app.basket.core.Team;
import cl.grupo.maldonado.app.basket.core.championship.Championship;
import cl.grupo.maldonado.app.basket.core.game.Match;
import cl.grupo.maldonado.app.basket.core.game.MatchState;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Integer> {


    List<Match> findByChampionship(Championship championship);

    List<Match> findByVisitorOrLocal(Team teamVisitor, Team teamLocal);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE tbl_match " +
            "SET state = :state ," +
            "scoreVisitor= :scoreVisitor ," +
            "scoreLocal= :scoreLocal " +
            "WHERE oid = :oid")
    int updateStateById(@Param("oid") Integer oid,
                        @Param("state") MatchState state,
                        @Param("scoreLocal") int scoreLocal,
                        @Param("scoreVisitor") int scoreVisitor);



    @Query( "SELECT m FROM tbl_match m " +
            "where m.local=:team or " +
            "m.visitor=:team and " +
            "m.state=:state " +
            "order by m.date desc" )
    List<Match> findLastByTeam(@Param("team")Team team,
                               @Param("state")MatchState state );
}
