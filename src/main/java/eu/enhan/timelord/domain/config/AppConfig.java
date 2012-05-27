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
package eu.enhan.timelord.domain.config;

import java.util.Set;

import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.google.common.collect.Sets;

import eu.enhan.timelord.domain.util.DateTimeToStringConverter;
import eu.enhan.timelord.domain.util.StringToDateTimeConverter;

/**
 * @author Emmanuel Nhan
 *
 */
@Configuration
@ComponentScan("eu.enhan.timelord.domain")
@EnableTransactionManagement(mode=AdviceMode.ASPECTJ)
public class AppConfig {
    
    @Bean
    public ConversionServiceFactoryBean conversionService(){
	ConversionServiceFactoryBean cf = new ConversionServiceFactoryBean();
	Set<Converter<?, ?>> converters = Sets.newHashSet();
	converters.add(new DateTimeToStringConverter());
	converters.add(new StringToDateTimeConverter());
	cf.setConverters(converters);
	
	return cf;
    }
    

}
