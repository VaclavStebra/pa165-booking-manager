package cz.muni.fi.pa165.brown;

import java.io.IOException;
import java.text.ParseException;

/**
 * Provides sample data.
 *
 * @author michal hagara
 */
public interface SampleDataLoadingFacade {

    /**
     * Loads data to the database.
     *
     * @throws IOException
     * @throws java.text.ParseException
     */
    void loadData() throws IOException, ParseException;

}
