package cl.grupo.maldonado.app.basket.controllers;

import cl.grupo.maldonado.app.basket.core.administration.User;
import cl.grupo.maldonado.app.basket.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author Juan Francisco Maldonado León - juan.maldonado.leon@gmail.com
 * Magno Labs - Santiago de Chile
 * Estadisticas de Deportes - Basketball
 */
@RestController
@RequestMapping("/api/v1/users")
public class UserController {


    @Autowired
    private UserService service;



    @RequestMapping(method = RequestMethod.GET)
    public List<User> listAll(){
        return service.listAll();
    }

    @RequestMapping(method = RequestMethod.GET, value="/{oid}")
    public User findById( @PathVariable("oid") Integer oid ){
        return service.findById(oid);
    }


    @RequestMapping(method = RequestMethod.DELETE, value="/{oid}")
    public void deteleById( @PathVariable("oid") Integer oid ){
        service.deleteById(oid);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void save( @RequestBody User user){
        service.save(user);
    }
}
