package greenmirror.fxpropertywrappers;

import greenmirror.FxPropertyWrapper;
import javafx.scene.paint.Paint;

/**
 * A wrapper for the <code>Paint</code> type of FX properties.
 * 
 * @author Karim El Assal
 */
public class PaintFxProperty extends FxPropertyWrapper {

    
    // -- Constructors -----------------------------------------------------------------------

    /**
     * @see greenmirror.FxPropertyWrapper#FxPropertyTypeWrapper(String)
     * @param propertyName The name of the property.
     */
    public PaintFxProperty(String propertyName) {
        super(propertyName);
    }

    
    // -- Queries ----------------------------------------------------------------------------

    @Override
    public Class<?> getPropertyType() {
        return Paint.class;
    }

    
    // -- Commands ---------------------------------------------------------------------------

    @Override
    public Paint cast(Object instance) {
        if (instance == null) {
            return null;
        }
        return Paint.valueOf(String.valueOf(instance));
    }

    @Override
    public String castToMapValue(Object instance) {
        if (instance == null) {
            return null;
        }
        return String.valueOf(instance);
    }

}
