package greenmirror.commands;

import greenmirror.Command;
import greenmirror.CommunicationFormat;
import groovy.json.JsonOutput;

import java.util.HashMap;

/**
 * The command to start all visualizations that are in the queue. 
 * This command is sent to the server.
 * 
 * Values sent:
 * delay : double   (in milliseconds) Delays the transition. Defaults to 0.
 */
public class StartTransitionCommand extends Command {

    // -- Instance variables -----------------------------------------------------------------

    //@ private invariant delay >= 0;
    private double delay;
    

    // -- Constructors -----------------------------------------------------------------------

    /**
     * Initialize the <tt>Command</tt>.
     * @param delay Delays the transition by the given milliseconds.
     */
    //@ requires delay >= 0;
    //@ ensures getDelay() == delay;
    public StartTransitionCommand(double delay) {
        this.delay = delay;
    }

    
    // -- Queries ----------------------------------------------------------------------------

    /**
     * @return The delay in milliseconds.
     */
    //@ ensures \result >= 0;
    /*@ pure */ public double getDelay() {
        return delay;
    }
    
    
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
                    put("delay", getDelay());
                }
            });
        }
    }
}