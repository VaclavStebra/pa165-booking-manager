package cz.muni.fi.pa165.brown;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import cz.muni.fi.pa165.brown.dto.RoomDTO;
import cz.muni.fi.pa165.brown.entity.Room;
import cz.muni.fi.pa165.brown.facade.RoomFacadeImpl;
import cz.muni.fi.pa165.brown.service.impl.RoomServiceImpl;

/**
 * Service configuration
 *
 * @author Dominik Labuda
 */
@Configuration
@Import(PersistenceApplicationContext.class)
@ComponentScan(basePackageClasses = {RoomServiceImpl.class, RoomFacadeImpl.class})
public class ServiceConfig {

    @Bean
    public Mapper dozer() {
        DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();
        dozerBeanMapper.addMapping(new DozerConfig());
        return dozerBeanMapper;
    }

    /**
     * Custom configuration
     */
    public class DozerConfig extends BeanMappingBuilder {
        @Override
        protected void configure() {
            mapping(Room.class, RoomDTO.class);
        }
    }
}
