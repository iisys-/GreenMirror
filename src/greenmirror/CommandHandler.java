package greenmirror;

import groovy.json.JsonException;
import groovy.json.JsonParserType;
import groovy.json.JsonSlurper;
import groovy.json.internal.LazyValueMap;
import org.eclipse.jdt.annotation.NonNull;

import java.util.LinkedHashMap;
import java.util.Map;


/**
 * A class that handles commands received over the network. Every subclass should have at 
 * least one of the {@link greenmirror.ClientSide ClientSide} and
 * {@link greenmirror.ServerSide ServerSide} annotations to indicate on which
 * part of the application they are used.
 * 
 * @author Karim El Assal
 */
public abstract class CommandHandler {

    // -- Exceptions -------------------------------------------------------------------------

    /**
     * An <code>Exception</code> to indicate that the received data couldn't be
     * parsed correctly.
     * 
     * @author Karim El Assal
     */
    public static class DataParseException extends Exception {

        /**
         * @param msg the message that can be retrieved by using <code>getMessage()</code>
         */
        public DataParseException(String msg) {
            super(msg);
        }
    }

    /**
     * An <code>Exception</code> to indicate that the received data is
     * incomplete.
     * 
     * @author Karim El Assal
     */
    public static class MissingDataException extends Exception {
        
    }

    
    // -- Instance variables -----------------------------------------------------------------

    /** The GreenMirror controller. */
    private GreenMirrorController controller;

    
    // -- Queries ----------------------------------------------------------------------------

    /** @return the GreenMirror controller */
    /* @ pure */public GreenMirrorController getController() {
        return controller;
    }

    /**
     * Returns a one word description of this command handler. For example, an instance of
     * <code>AddNodeCommandHandler</code> would let this method return <code>AddNode</code>.
     * 
     * @return the textual, one word description of the <code>command</code> belonging to this
     *         command handler
     */
    /* @ pure */ @NonNull public String getCommand() {
        final String str = getClass().getSimpleName().replace("CommandHandler", "");
        return str == null ? "" : str;
    }
    

    // -- Setters ----------------------------------------------------------------------------

    /**
     * @param controller the GreenMirror controller to store for later use
     */
    // @ ensures getController() == controller;
    public void setController(@NonNull GreenMirrorController controller) {
        this.controller = controller;
    }

    
    // -- Commands ---------------------------------------------------------------------------

    /**
     * The method that actually handles the received {@link Command}. The
     * <code>Command</code> is passed via <code>data</code> in the specified
     * communication format. The controller should've already been set.
     * 
     * @param format the communication format in which <code>data</code> is formatted
     * @param data   the string representation of the received <code>Command</code>
     * @throws MissingDataException when the data is incomplete
     * @throws DataParseException   when the data can't be parsed correctly
     * @see    CommunicationFormat
     * @see    #setController(GreenMirrorController)
     */
    // @ requires getController() != null;
    public abstract void handle(@NonNull CommunicationFormat format, @NonNull String data)
            throws MissingDataException, DataParseException;

    
    // -- Class usage ------------------------------------------------------------------------

    /**
     * Parse JSON data.
     * 
     * @param data the JSON data
     * @return     a <code>Map</code> containing the parsed data
     * @throws DataParseException   if the JSON string was invalid
     */
    @NonNull public static Map<String, Object> parseJson(@NonNull String data)
            throws DataParseException {

        try {
            final Map<String, Object> res = new LinkedHashMap<String, Object>();
            res.putAll((LazyValueMap) new JsonSlurper().setType(JsonParserType.INDEX_OVERLAY)
                                                       .parseText(data));
            return res;
        } catch (JsonException e) {
            throw new DataParseException("There was an error in the received JSON data: " 
                            + e.getMessage());
        }
    }
}