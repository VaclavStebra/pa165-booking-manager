package cz.muni.fi.pa165.brown.mvc.binders;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Component;

/**
 * Date binder
 *
 * @author Dominik Labuda
 */
@Component
public class DateBinder extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        try {
            this.setValue(new SimpleDateFormat("dd.MM.yyyy HH:mm").parse(text));
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public String getAsText() {
        return new SimpleDateFormat("dd.MM.yyyy HH:mm").format(this.getValue());
    }
}
