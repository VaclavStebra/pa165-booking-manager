package cz.fi.muni.pa165.brown.service.config;

import cz.fi.muni.pa165.brown.facade.UserFacadeImpl;
import cz.fi.muni.pa165.brown.service.BeanMappingService;
import cz.fi.muni.pa165.brown.service.UserServiceImpl;
import cz.muni.fi.pa165.brown.PersistenceApplicationContext;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author vstebra
 */
@Configuration
@Import(PersistenceApplicationContext.class)
@ComponentScan(basePackageClasses = {UserServiceImpl.class, UserFacadeImpl.class, BeanMappingService.class
})
public class ServiceConfiguration {

    @Bean
    public Mapper dozer() {
        DozerBeanMapper dozer = new DozerBeanMapper();
        return dozer;
    }
}
