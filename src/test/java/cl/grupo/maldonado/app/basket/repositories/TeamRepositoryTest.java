package cl.grupo.maldonado.app.basket.repositories;

import cl.grupo.maldonado.app.basket.core.Gender;
import cl.grupo.maldonado.app.basket.core.Team;
import cl.grupo.maldonado.app.basket.core.TeamCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TeamRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TeamRepository repository;

    // write test cases here

    @Test
    public void whenSaveTeam() {
        Team team = new Team();
        team.setBio("");
        team.setCategory(TeamCategory.ADULT);
        team.setGender(Gender.MALE);
        team.setName("Victoria Nacional");
        repository.save(team);
    }

    @Test
    public void whenGetAllTeam() {

        List<Team> teams = repository.findAll();
        System.out.println(teams.size());
    }
}
