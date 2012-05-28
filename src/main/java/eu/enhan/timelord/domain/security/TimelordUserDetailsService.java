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
package eu.enhan.timelord.domain.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eu.enhan.timelord.domain.core.TimelordUser;
import eu.enhan.timelord.domain.core.UserRepository;

/**
 * @author Emmanuel Nhan
 *
 */
@Service
public class TimelordUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;
    
    
    @Autowired
    public TimelordUserDetailsService(UserRepository userRepository) {
	super();
	this.userRepository = userRepository;
    }

    /* (non-Javadoc)
     * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	final TimelordUser user =findUser(username);
	if (user == null){
	    throw new UsernameNotFoundException("Username not found");
	}
	return new TimelordUserDetails(user);
    }
    
    public TimelordUser findUser(String login){
	TimelordUser user = userRepository.findByPropertyValue("login", login);
	if (user == null){
	    // try by email
	    user = userRepository.findByPropertyValue("email", login);
	}
	return user;
	
    }

    public TimelordUser getUserFromSession(){
	SecurityContext ctx = SecurityContextHolder.getContext();
	Authentication auth = ctx.getAuthentication();
	Object principal = auth.getPrincipal();
	if (principal instanceof TimelordUserDetails){
	    return ((TimelordUserDetails) principal).getUser();
	}
	return null;
    }
    
    

}
