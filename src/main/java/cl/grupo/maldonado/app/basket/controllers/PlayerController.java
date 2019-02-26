package cl.grupo.maldonado.app.basket.controllers;


import cl.grupo.maldonado.app.basket.core.Player;

import cl.grupo.maldonado.app.basket.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Juan Francisco Maldonado León - juan.maldonado.leon@gmail.com
 * Magno Labs - Santiago de Chile
 * Estadisticas de Deportes - Basketball
 */
@RestController
@RequestMapping("/api/v1/players")
public class PlayerController {

    @Autowired
    private PlayerService service;

    @RequestMapping(method = RequestMethod.GET)
    public List<Player> listAll(){
        return service.listAll();
    }

    @RequestMapping(method = RequestMethod.GET, value="/{oid}")
    public Player findById( @PathVariable("oid") Integer oid ){
        return service.findById(oid);
    }


    @RequestMapping(method = RequestMethod.DELETE, value="/{oid}")
    public void deteleById( @PathVariable("oid") Integer oid ){
        service.deleteById(oid);
    }

}
