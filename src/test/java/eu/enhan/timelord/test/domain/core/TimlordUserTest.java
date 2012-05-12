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
package eu.enhan.timelord.test.domain.core;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import eu.enhan.timelord.domain.config.AppConfig;
import eu.enhan.timelord.domain.core.TimelordUser;

import static org.junit.Assert.*;

/**
 * @author Emmanuel Nhan
 *
 */
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=AppConfig.class)
@ActiveProfiles("dev")
public class TimlordUserTest {

    @Autowired
    Neo4jOperations template;
    
    @Test
    public void testCreateUser(){
	
	TimelordUser user = new TimelordUser("me", "password", "email@mail.com");
	template.save(user);
	TimelordUser user2 = template.findOne(user.getId(), TimelordUser.class);
	assertEquals(user, user2);
	
	
    }
    
}
