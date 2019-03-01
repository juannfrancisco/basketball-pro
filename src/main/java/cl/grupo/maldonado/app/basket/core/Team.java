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
package cl.grupo.maldonado.app.basket.core;

import cl.grupo.maldonado.app.basket.core.championship.Championship;
import cl.grupo.maldonado.app.basket.core.common.Contact;

import java.util.List;

import javax.persistence.*;

/**
 * @author Juan Francisco Maldonado León - juan.maldonado.leon@gmail.com
 * Magno Labs - Santiago de Chile
 * Estadisticas de Deportes - Basketball
 */
@Entity
public class Team {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer oid;

	private String nameURL;

	private String name;
	private String bio;
	private Gender gender;

	//@OneToMany(mappedBy = "currentTeam", cascade = CascadeType.ALL)
	@Transient
	private List<Player> players;

	@OneToOne
	@JoinColumn
	private Coach coach;

	private TeamCategory category;

	@Embedded
	private Contact contact;


	@ManyToOne
	@JoinColumn
	private Championship championship;




	/**
	 * 
	 */
	public Team() {
		this.name ="";
		this.players = null;
	}
	
	public Team(Integer oid) {
		this.oid = oid;
	}
	
	
	/**
	 * @param name
	 * @param players
	 */
	public Team(String name, List<Player> players) {
		this.name = name;
		this.players = players;
	}
	/**
	 * @return the oid
	 */
	public Integer getOid() {
		return oid;
	}

	/**
	 * @param oid the oid to set
	 */
	public void setOid(Integer oid) {
		this.oid = oid;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the players
	 */
	public List<Player> getPlayers() {
		return players;
	}
	/**
	 * @param players the players to set
	 */
	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	/**
	 * @return the gender
	 */
	public Gender getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(Gender gender) {
		this.gender = gender;
	}

	/**
	 * @return the bio
	 */
	public String getBio() {
		return bio;
	}

	/**
	 * @param bio the bio to set
	 */
	public void setBio(String bio) {
		this.bio = bio;
	}

	/**
	 * @return the coach
	 */
	public Coach getCoach() {
		return coach;
	}

	/**
	 * @param coach the coach to set
	 */
	public void setCoach(Coach coach) {
		this.coach = coach;
	}

	/**
	 * @return the category
	 */
	public TeamCategory getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(TeamCategory category) {
		this.category = category;
	}


	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public String getNameURL() {
		return nameURL;
	}

	public void setNameURL(String nameURL) {
		this.nameURL = nameURL;
	}


	public Championship getChampionship() {
		return championship;
	}

	public void setChampionship(Championship championship) {
		this.championship = championship;
	}


	@Override
	public boolean equals(Object obj) {
		if( obj instanceof Team ){
			return ((Team)obj).getOid().equals( this.getOid() );
		}
		return super.equals(obj);
	}
	
	
}
