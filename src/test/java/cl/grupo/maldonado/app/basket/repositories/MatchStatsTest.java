package cl.grupo.maldonado.app.basket.repositories;

import cl.grupo.maldonado.app.basket.core.game.MatchStat;
import cl.grupo.maldonado.app.basket.core.game.TypeStat;
import cl.grupo.maldonado.app.basket.core.game.TypeTeam;

import java.util.ArrayList;
import java.util.List;

public class MatchStatsTest {


    public static void main(String[] args) {
        List<MatchStat> stats = new ArrayList<>();

        MatchStat s = new MatchStat();
        s.setTypeTeam( TypeTeam.LOCAL );
        s.setType( TypeStat.MPT );
        s.setValue( 2.0 );
        stats.add( s );

        for (int i = 0; i < 5; i++) {
            MatchStat matchStat = new MatchStat();
            matchStat.setTypeTeam( TypeTeam.LOCAL );
            matchStat.setType( TypeStat.PTS );
            matchStat.setValue( 2.0 );
            stats.add( matchStat );
        }

        for (int i = 0; i < 5; i++) {
            MatchStat matchStat = new MatchStat();
            matchStat.setTypeTeam( TypeTeam.VISITOR );
            matchStat.setType( TypeStat.PTS );
            matchStat.setValue( 2.0 );
            stats.add( matchStat );
        }

        Double a = stats.stream().filter( matchStat1 -> matchStat1.getTypeTeam().equals(TypeTeam.LOCAL) )
                .filter( matchStat -> matchStat.getType().equals(TypeStat.PTS) )
                .mapToDouble( value ->  value.getValue() )
                .sum();

        System.out.println(a);
    }
}
