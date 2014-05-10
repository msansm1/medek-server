package bzh.medek.server.conf;

import javax.ejb.Stateful;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.log4j.Logger;

/**
 * Class to manage the server errors message for the client. The bundle must be a
 * file like "messages_en.properties" where en is the language parameter. This
 * is a stateful bean, so the message bundle is persistent inside the session
 * and can be reload (in another language for ex.) any time it is necessary.
 * 
 * @author rbarbot
 * 
 */

@Stateful
public class ClientMessage {
    private static final Logger LOGGER = Logger.getLogger(ClientMessage.class);

    private PropertiesConfiguration bundle;

    /**
     * Set the language to initialize the correct bundle : en, fr, ...
     * 
     * @param lang
     */
    public void setLanguage(String lang) {
        try {
            bundle = new PropertiesConfiguration(ClientMessage.class.getResource("/messages_" + lang + ".properties"));
        } catch (ConfigurationException e) {
            LOGGER.error("While loading messages properties file : ", e);
            try {
                bundle = new PropertiesConfiguration("messages_en.properties");
            } catch (ConfigurationException e1) {
                LOGGER.error("While loading messages_en properties file : ", e1);
            }
        }
    }

    /**
     * Returns the message to send to client from the properties file
     * 
     * @param errorNumber
     * @return
     */
    public String getMessage(int msgNumber) {
        if (bundle == null) {
            setLanguage("en");
        }
        try {
            return bundle.getString("message." + msgNumber);
        } catch (NullPointerException e) {
            LOGGER.error("Message n." + msgNumber + " not found in config." + bundle.getKeys().next(), e);
            return "Message not found.";
        }
    }

    /**
     * Returns the message to send to client from the properties file
     * 
     * @param errorNumber
     * @param param
     * @return
     */
    public String getMessage(int msgNumber, String param) {
        String[] params = new String[] { param };
        return getMessage(msgNumber, params);
    }

    /**
     * Returns the message to send to client from the properties file
     * 
     * @param errorNumber
     * @param params
     * @return
     */
    public String getMessage(int msgNumber, String[] params) {
        if (bundle == null) {
            setLanguage("en");
        }
        try {
            String msg = bundle.getString("message." + msgNumber);
            return String.format(msg, (Object[]) params);
        } catch (NullPointerException e) {
            LOGGER.error("Message n." + msgNumber + " not found in config." + bundle.getKeys().next(), e);
            return "Message not found.";
        }
    }
}
