/*
 *   This file is part of Timelord.
 *
 *   Timelord is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU Affero General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   Timelord is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU Affero General Public License for more details.
 *
 *   You should have received a copy of the Affero GNU General Public License
 *   along with Timemord.  If not, see <http://www.gnu.org/licenses/>.
 */
package eu.enhan.timelord.domain.core;

import org.joda.time.DateTime;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.aspects.core.NodeBacked;
import org.springframework.security.core.GrantedAuthority;

/*
 * It implements NodeBacked directly to avoid errors in Eclipse
 */
/**
 * Represents a user for the Timelord application.
 * 
 * @author Emmanuel Nhan
 * 
 */
@NodeEntity
public class TimelordUser{
    
    @GraphId
    private Long id;
    
    private String login;
    private String password;
    private String email;
    private DateTime registrationDate;
    
    private TimelordRoles roles[];

    public TimelordUser(String login, String password, String email) {
	super();
	this.login = login;
	this.password = password;
	this.email = email;
	registrationDate = new DateTime();
	this.roles = new TimelordRoles[1];
	this.roles[0] = TimelordRoles.ROLE_USER;
    }
    
    public TimelordUser() {
    }

    public String getLogin() {
	return login;
    }

    public void setLogin(String login) {
	this.login = login;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((email == null) ? 0 : email.hashCode());
	result = prime * result + ((login == null) ? 0 : login.hashCode());
	result = prime * result + ((password == null) ? 0 : password.hashCode());
	result = prime * result + ((registrationDate == null) ? 0 : registrationDate.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	TimelordUser other = (TimelordUser) obj;
	if (email == null) {
	    if (other.email != null)
		return false;
	} else if (!email.equals(other.email))
	    return false;
	if (login == null) {
	    if (other.login != null)
		return false;
	} else if (!login.equals(other.login))
	    return false;
	if (password == null) {
	    if (other.password != null)
		return false;
	} else if (!password.equals(other.password))
	    return false;
	if (registrationDate == null) {
	    if (other.registrationDate != null)
		return false;
	} else if (!registrationDate.equals(other.registrationDate))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "TimelordUser [id=" + id + ", login=" + login + ", password=" + password + ", email=" + email + ", registrationDate=" + registrationDate + "]";
    }
    
    
    
    public enum TimelordRoles implements GrantedAuthority{
	ROLE_ADMIN, ROLE_USER;

	@Override
	public String getAuthority() {
	    return name();
	}
	
    }

}
