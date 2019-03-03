package cl.grupo.maldonado.app.basket.repositories;

import cl.grupo.maldonado.app.basket.core.Gender;
import cl.grupo.maldonado.app.basket.core.Player;
import cl.grupo.maldonado.app.basket.core.Position;
import cl.grupo.maldonado.app.basket.core.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Integer> {

    List<Player> findByCurrentTeam(Team team);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Player " +
            "SET name = :name, " +
            "lastName = :lastName," +
            "height = :height," +
            "weight = :weight," +
            "number = :number," +
            "position = :position," +
            "birthdate = :birthdate," +
            "gender = :gender " +
            "WHERE oid = :oid")
    int updateInfoById(@Param("oid") Integer oid,
                       @Param("number") Integer number,
                       @Param("position") Position position,
                       @Param("name") String name,
                       @Param("lastName") String lastName,
                       @Param("height") double height,
                       @Param("weight") double weight,
                       @Param("gender") Gender gender,
                       @Param("birthdate") Date birthdate);
}
