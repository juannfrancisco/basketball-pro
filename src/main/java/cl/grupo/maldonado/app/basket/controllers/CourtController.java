package cl.grupo.maldonado.app.basket.controllers;

import cl.grupo.maldonado.app.basket.core.Coach;
import cl.grupo.maldonado.app.basket.core.Court;
import cl.grupo.maldonado.app.basket.services.CoachService;
import cl.grupo.maldonado.app.basket.services.CourtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Juan Francisco Maldonado León - juan.maldonado.leon@gmail.com
 * Magno Labs - Santiago de Chile
 * Estadisticas de Deportes - Basketball
 */
@RestController
@RequestMapping("/api/v1/courts")
public class CourtController {


    @Autowired
    private CourtService service;



    @RequestMapping(method = RequestMethod.GET)
    public List<Court> listAll(){
        return service.listAll();
    }

    @RequestMapping(method = RequestMethod.GET, value="/{oid}")
    public Court findById( @PathVariable("oid") Integer oid ){
        return service.findById(oid);
    }


    @RequestMapping(method = RequestMethod.DELETE, value="/{oid}")
    public void deteleById( @PathVariable("oid") Integer oid ){
        service.deleteById(oid);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void save( @RequestBody Court court){
        service.save(court);
    }
}
