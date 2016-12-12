package cz.muni.fi.pa165.brown;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.text.ParseException;

/**
 *
 * @author michal hagara
 */
@Configuration
@Import(ServiceConfig.class)
@ComponentScan(basePackageClasses = {SampleDataLoadingFacadeImpl.class})
public class BookingManagerSampleDataConfiguration {

    final static Logger log = LoggerFactory.getLogger(BookingManagerSampleDataConfiguration.class);

    @Autowired
    SampleDataLoadingFacade sampleDataLoadingFacade;

    @PostConstruct
    public void dataLoading() throws IOException, ParseException {
        log.debug("dataLoading()");
        sampleDataLoadingFacade.loadData();
    }
}

