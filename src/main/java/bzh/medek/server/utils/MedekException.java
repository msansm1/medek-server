package bzh.medek.server.utils;

import org.apache.log4j.Logger;

public class MedekException extends Exception {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(MedekException.class);

    private final String code;
    private final String reason;

    public MedekException(Exception e) {
        super();
        LOGGER.warn(e);
        this.code = null;
        this.reason = e.getMessage();
    }

    public MedekException(String error) {
        super();
        LOGGER.warn(error);
        this.code = null;
        this.reason = error;
    }

    public MedekException(String code, String reason) {
        super();
        this.code = code;
        this.reason = reason;
    }

    public String getCode() {
        return code;
    }

    public String getReason() {
        return reason;
    }

}
