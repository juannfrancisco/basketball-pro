package cl.grupo.maldonado.app.basket.repositories;

import cl.grupo.maldonado.app.basket.core.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Integer> {

    List<Team> findByNameURL(String nameURL);


    @Modifying(clearAutomatically = true)
    @Query("UPDATE Team " +
            "SET name = :name, " +
            "bio = :bio," +
            "nameURL = :nameURL," +
            "contact.phone = :phone," +
            "contact.email = :email " +
            "WHERE oid = :oid")
    int updateInfoById(@Param("oid") Integer oid,
                       @Param("bio") String bio,
                       @Param("nameURL") String nameURL,
                       @Param("phone") String phone,
                       @Param("email") String email,
                       @Param("name") String name);
}
