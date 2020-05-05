package cl.grupo.maldonado.app.basket.repositories;


import cl.grupo.maldonado.app.basket.core.Player;
import cl.grupo.maldonado.app.basket.core.Team;
import cl.grupo.maldonado.app.basket.core.game.Match;
import cl.grupo.maldonado.app.basket.core.game.MatchStat;
import cl.grupo.maldonado.app.basket.core.game.TypeStat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MatchStatRepository extends JpaRepository<MatchStat, Integer> {

    List<MatchStat> findByMatch(Match match);


    List<MatchStat> findByPlayer(Player player);

    @Query("SELECT type, sum(value) FROM tbl_match_stat where player_oid=:oid group by type")
    List<MatchStat> getStatsByPlayer(Integer oid);

    //SELECT count(*) FROM db_basketball.tbl_match_stat where team_oid=1 and type=6 group by match_oid;


    //SELECT SUM(sel.countStatPerGame) / count( sel.countStatPerGame ) FROM (SELECT count(*) as countStatPerGame FROM tbl_match_stat WHERE team_oid=:teamOid and type=:type GROUP BY match ) as sel;

    //@Query("SELECT count(*) " +
    //        "FROM tbl_match_stat " +
    //        "WHERE teamOid=:teamOid and " +
    //        "type=:type " +
    //        "group by match")
    @Query(
            value="SELECT SUM(sel.countStatPerGame) / count( sel.countStatPerGame ) " +
                    "FROM (SELECT COUNT(*) AS countStatPerGame " +
                    "FROM tbl_match_stat " +
                    "WHERE  team_oid = :teamOid AND type = :type GROUP BY match_oid ) as sel",
            nativeQuery = true)
    Integer statisticsPerGame(@Param("teamOid") Integer teamOid,
                              @Param("type") TypeStat type);
}
