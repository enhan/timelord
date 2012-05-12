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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class TimelordUserTest {

    private static final Logger logger = LoggerFactory.getLogger(TimelordUserTest.class);
    
    @Autowired
    Neo4jOperations template;
    
    @Test
    public void testCreateUser(){
	
	TimelordUser user = new TimelordUser("me", "password", "email@mail.com");
	template.save(user);
	logger.debug("User saved. Sleeping for 1s.");
	try {
	    Thread.sleep(1000);
	    logger.debug("Wake up, try to get it back.");
	    TimelordUser user2 = template.findOne(user.getId(), TimelordUser.class);
		assertEquals(user, user2);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
	
	
	
    }
    
}
