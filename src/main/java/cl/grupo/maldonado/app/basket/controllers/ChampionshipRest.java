/**
 * Copyright (C) 2015-2016  Magno Labs
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package cl.grupo.maldonado.app.basket.controllers;

import cl.grupo.maldonado.app.basket.core.Coach;
import cl.grupo.maldonado.app.basket.core.Team;
import cl.grupo.maldonado.app.basket.core.championship.Championship;
import cl.grupo.maldonado.app.basket.services.ChampionshipService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Juan Francisco Maldonado León - juan.maldonado.leon@gmail.com
 * Magno Labs - Santiago de Chile
 * Estadisticas de Deportes - Basketball
 */
@RestController
@RequestMapping("/api/v1/championships")
public class ChampionshipRest {

	@Autowired
	private ChampionshipService service;


	@RequestMapping(method = RequestMethod.GET)
	public List<Championship> listAll(){
		return service.listAll();
	}

	@RequestMapping(method = RequestMethod.GET, value="/{oid}")
	public Championship findById( @PathVariable("oid") Integer oid ){
		return service.findById(oid);
	}


	@RequestMapping(method = RequestMethod.DELETE, value="/{oid}")
	public void deteleById( @PathVariable("oid") Integer oid ){
		service.deleteById(oid);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public void save( @RequestBody Championship Championship){
		service.save(Championship);
	}


	@RequestMapping(method = RequestMethod.PUT, value="/{oid}/teams")
	public void addTeam( @PathVariable("oid") Integer oid , @RequestBody Team team){
		service.addTeam(oid, team);
	}

}
