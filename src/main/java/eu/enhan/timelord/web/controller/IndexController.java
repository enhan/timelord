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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import eu.enhan.timelord.domain.core.TimelordUser;
import eu.enhan.timelord.domain.security.TimelordUserDetailsService;

/**
 * @author Emmanuel Nhan
 *
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    TimelordUserDetailsService userService;
    
    @RequestMapping(method=RequestMethod.GET)
    public String index(){
	return "index";
    }
    
    
    @ModelAttribute("user") public TimelordUser getUser(){
	return userService.getUserFromSession(); 
    }
}
