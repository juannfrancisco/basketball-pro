package cl.grupo.maldonado.app.basket.core.championship;

import cl.grupo.maldonado.app.basket.core.Team;

import javax.persistence.*;

/**
 * @author Juan Francisco Maldonado León - juan.maldonado.leon@gmail.com
 * Magno Labs - Santiago de Chile
 * Estadisticas de Deportes - Basketball
 */
@Entity
public class ChampionshipTeam {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer oid;

    @ManyToOne
    @JoinColumn
    private Team team;

    @ManyToOne
    @JoinColumn
    private Championship championship;


    public ChampionshipTeam(){}

    public ChampionshipTeam( Integer oid){
        this.setOid(oid);
    }

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Championship getChampionship() {
        return championship;
    }

    public void setChampionship(Championship championship) {
        this.championship = championship;
    }
}
