package cl.grupo.maldonado.app.basket.core.game;

import cl.grupo.maldonado.app.basket.core.Player;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity(name = "tbl_match_stat")
public class MatchStat {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer oid;


    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn
    private Match match;


    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn
    private Player player;

    private Integer quarter;

    private TypeStat type;
    private double value;

    private TypeTeam typeTeam;


    /**
     *
     */
    public MatchStat(){

    }

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }


    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Integer getQuarter() {
        return quarter;
    }

    public void setQuarter(Integer quarter) {
        this.quarter = quarter;
    }

    public TypeStat getType() {
        return type;
    }

    public void setType(TypeStat type) {
        this.type = type;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }


    public TypeTeam getTypeTeam() {
        return typeTeam;
    }

    public void setTypeTeam(TypeTeam typeTeam) {
        this.typeTeam = typeTeam;
    }
}
