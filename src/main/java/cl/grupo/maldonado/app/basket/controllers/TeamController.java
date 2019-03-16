package cl.grupo.maldonado.app.basket.controllers;

import cl.grupo.maldonado.app.basket.core.Player;
import cl.grupo.maldonado.app.basket.core.Team;
import cl.grupo.maldonado.app.basket.core.championship.Championship;
import cl.grupo.maldonado.app.basket.core.championship.ChampionshipTeam;
import cl.grupo.maldonado.app.basket.core.game.Match;
import cl.grupo.maldonado.app.basket.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * @author Juan Francisco Maldonado León - juan.maldonado.leon@gmail.com
 * Magno Labs - Santiago de Chile
 * Estadisticas de Deportes - Basketball
 */
@RestController
@RequestMapping("/api/v1/teams")
public class TeamController {

    @Autowired
    private TeamService service;


    @RequestMapping(method = RequestMethod.GET)
    public List<Team> listAllTeams(){
        return service.getAll();
    }


    @RequestMapping(method = RequestMethod.GET, value="/{oid}")
    public Team findById( @PathVariable("oid") Integer oid ){
        return service.getById(oid);
    }

    @RequestMapping(method = RequestMethod.GET, value="/n/{name}")
    public Team findByNameURL( @PathVariable("name") String name ){
        return service.findByName(name);
    }


    @RequestMapping(method = RequestMethod.GET, value="/n/{name}/players")
    public List<Player> findPlayersByNameURL( @PathVariable("name") String name ){
        return service.findPlayersByName(name);
    }

    @RequestMapping(method = RequestMethod.GET, value="/n/{name}/championships")
    public  List<Championship> findChampionshipsByNameURL(@PathVariable("name") String name ){
        return service.findChampionshipsByName(name);
    }

    @RequestMapping(method = RequestMethod.GET, value="/n/{name}/standings")
    public  List<List<ChampionshipTeam>> findStandingsByNameURL(@PathVariable("name") String name ){
        return service.findStandingsByName(name);
    }

    @RequestMapping(method = RequestMethod.GET, value="/n/{name}/matches")
    public List<Match> findMatchesByNameURL(@PathVariable("name") String name ){
        return service.findMatchesByName(name);
    }

    @RequestMapping(method = RequestMethod.GET, value="/n/{name}/last-match")
    public Match findLastMatch(@PathVariable("name") String name ){
        return service.findLastMatch(name);
    }


    @RequestMapping(method = RequestMethod.DELETE, value="/{oid}")
    public void deleteById( @PathVariable("oid") Integer oid ){
         service.deleteById(oid);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void addTeam(@RequestBody  Team team){
        service.save(team);
    }

    @RequestMapping(method = RequestMethod.POST, value="/{oid}")
    public void updateTeam(@RequestBody  Team team){
        service.update(team);
    }




    @RequestMapping(method = RequestMethod.PUT,value="/{oid}/players" )
    public void addPlayerTeam(@PathVariable("oid") Integer oid, @RequestBody Player player){
        Team team = new Team(oid);
        service.addPlayer(team, player);
    }





    @RequestMapping(method = RequestMethod.POST,value="/{oid}/players/{oidPlayer}" )
    public void updatePlayerTeam(@PathVariable("oid") Integer oid,
                                 @PathVariable("oidPlayer") Integer oidPlayer,
                                 @RequestBody Player player){
        Team team = new Team(oid);
        service.updatePlayer(team, player);
    }




    @RequestMapping(method = RequestMethod.DELETE,value="/{oid}/players/{oidPlayer}" )
    public void removePlayerTeam(@PathVariable("oid") Integer oid,@PathVariable("oidPlayer") Integer oidPlayer){
        service.deletePlayer(oidPlayer);
    }
}
