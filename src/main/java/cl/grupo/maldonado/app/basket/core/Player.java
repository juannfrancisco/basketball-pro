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

import javax.persistence.*;

/**
 * @author Juan Francisco Maldonado León - juan.maldonado.leon@gmail.com
 * Magno Labs - Santiago de Chile
 * Estadisticas de Deportes - Basketball
 */
@Entity
public class Player extends Person{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer oid;
	private String oidCurrentTeam;

	@ManyToOne
	@JoinColumn
	private Team currentTeam;
	private int number;
	private Position position;

	@Transient
	private SkillsPlayer skills;
	
	/**
	 * 
	 */
	public Player(){
		this.number = 0;
		this.position = null;
	}
	
	/**
	 * @param number
	 * @param position
	 */
	public Player(int number, Position position) {
		super();
		this.number = number;
		this.position = position;
	}


	public Player(Integer oid) {
		this.setOid(oid);
	}

	/**
	 * @return the number
	 */
	public int getNumber() {
		return number;
	}
	/**
	 * @param number the number to set
	 */
	public void setNumber(int number) {
		this.number = number;
	}
	/**
	 * @return the position
	 */
	public Position getPosition() {
		return position;
	}
	/**
	 * @param position the position to set
	 */
	public void setPosition(Position position) {
		this.position = position;
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
	 * @return the oidCurrentTeam
	 */
	public String getOidCurrentTeam() {
		return oidCurrentTeam;
	}

	/**
	 * @param oidCurrentTeam the oidCurrentTeam to set
	 */
	public void setOidCurrentTeam(String oidCurrentTeam) {
		this.oidCurrentTeam = oidCurrentTeam;
	}

	/**
	 * @return the skills
	 */
	public SkillsPlayer getSkills() {
		return skills;
	}

	/**
	 * @param skills the skills to set
	 */
	public void setSkills(SkillsPlayer skills) {
		this.skills = skills;
	}

	@Override
	public boolean equals(Object object) {
		if( object instanceof Player )
			return ((Player)object).getOid().equals( this.getOid() );
		return super.equals(object);
	}

	/**
	 * @return the currentTeam
	 */
	public Team getCurrentTeam() {
		return currentTeam;
	}

	/**
	 * @param currentTeam the currentTeam to set
	 */
	public void setCurrentTeam(Team currentTeam) {
		this.currentTeam = currentTeam;
	}

}
