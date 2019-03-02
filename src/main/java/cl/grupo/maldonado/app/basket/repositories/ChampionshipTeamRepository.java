package cl.grupo.maldonado.app.basket.repositories;


import cl.grupo.maldonado.app.basket.core.championship.Championship;
import cl.grupo.maldonado.app.basket.core.championship.ChampionshipTeam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChampionshipTeamRepository extends JpaRepository<ChampionshipTeam, Integer> {

    List<ChampionshipTeam> findByChampionship( Championship championship );
}
