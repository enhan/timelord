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
package eu.enhan.timelord.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.common.collect.Lists;

import eu.enhan.timelord.domain.core.TimelordUser;

/**
 * @author Emmanuel Nhan
 *
 */
@Controller
@RequestMapping("/user")
public class TimelordUserController {

    private static final Logger logger = LoggerFactory.getLogger(TimelordUserController.class);
    
    private Neo4jTemplate temp;
    
    
    
    
    @Autowired
    public TimelordUserController(Neo4jTemplate temp) {
	super();
	this.temp = temp;
    }




    
    @Transactional
    @RequestMapping(method=RequestMethod.POST)
    public String create(@RequestParam String registrationUsername, @RequestParam String registrationEmail, @RequestParam String registrationPassword){
	TimelordUser user = new TimelordUser(registrationUsername, registrationPassword, registrationEmail);
	temp.save(user);
	return "redirect:/";
    }
    
    @Transactional
    @RequestMapping(method=RequestMethod.GET)
    public String list(Model model){
	Iterable<TimelordUser> u =temp.findAll(TimelordUser.class);
	List<TimelordUser> users = Lists.newArrayList();
	
	for (TimelordUser timelordUser : u) {
	    logger.debug("Found user : {}", timelordUser);
	    users.add(timelordUser);
	}
	
	model.addAttribute("users", u );
	return "user/list";
    }
    
    
}
