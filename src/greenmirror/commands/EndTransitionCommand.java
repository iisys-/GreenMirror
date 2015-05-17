package greenmirror.commands;

import greenmirror.Command;
import greenmirror.CommunicationFormat;
import groovy.json.JsonOutput;

import java.util.HashMap;

/**
 * The command that signals that the transition has ended. 
 * This command is sent to the server.
 * 
 * Values sent:
 * none
 */
public class EndTransitionCommand extends Command {

    // -- Instance variables -----------------------------------------------------------------
    

    // -- Constructors -----------------------------------------------------------------------

    
    // -- Queries ----------------------------------------------------------------------------
    
    
    // -- Commands ---------------------------------------------------------------------------

    /**
     * Prepare the <tt>Command</tt>.
     */
    public void prepare() {
        // Nothing to prepare.
    }

    /**
     * Fetch the raw data that will be sent.
     * @param format The format in which the data will be.
     */
    //@ requires format != null;
    public String getFormattedString(CommunicationFormat format) {
        switch (format) {
        default: case JSON:
            return JsonOutput.toJson(new HashMap<String, Double>() {
                {
                    // Nothing to send.
                }
            });
        }
    }
}