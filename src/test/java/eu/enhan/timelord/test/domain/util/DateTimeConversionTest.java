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
package eu.enhan.timelord.test.domain.util;

import org.joda.time.DateTime;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.enhan.timelord.domain.util.DateTimeToStringConverter;
import eu.enhan.timelord.domain.util.StringToDateTimeConverter;

import static org.junit.Assert.*;

/**
 * @author Emmanuel Nhan
 *
 */
public class DateTimeConversionTest {

    private static final Logger logger = LoggerFactory.getLogger(DateTimeConversionTest.class);
    
    @Test
    public void testConvertion(){
	StringToDateTimeConverter stringToDate = new StringToDateTimeConverter();
	DateTimeToStringConverter dateToString = new DateTimeToStringConverter();
	
	DateTime time = new DateTime(2012, 2, 2, 10, 0);
	String converted = dateToString.convert(time);
	logger.debug("Converted value = {}.", converted);
	DateTime restored = stringToDate.convert(converted);
	
	assertEquals(time, restored);
	
	
	
    }
    
}
