package bzh.medek.server.error;

import org.apache.log4j.Logger;

/**
 * Model class for software errors (can be throw to GUI) Ex of error thrown :
 * login error
 * 
 * @author msansm1
 * 
 */
public class MedekError extends Exception {

	private static final long serialVersionUID = -1123914675710006975L;

	private static final Logger LOGGER = Logger.getLogger(MedekError.class);

    private final int errorId;
    private final String[] params;
    private final String reason;

    public MedekError(int errorId) {
        LOGGER.error("throwing new error : " + errorId);
        this.errorId = errorId;
        this.params = null;
        this.reason = null;
    }

    public MedekError(int errorId, String arg) {
        LOGGER.error("throwing new error : " + errorId);
        this.errorId = errorId;
        this.params = new String[] { arg };
        this.reason = null;
    }

    public MedekError(int errorId, String[] args) {
        LOGGER.error("throwing new error : " + errorId);
        this.errorId = errorId;
        this.params = args.clone();
        this.reason = null;
    }

    public MedekError(String reason) {
        this.reason = reason;
        this.params = null;
        this.errorId = 0;
    }

    public int getErrorId() {
        return errorId;
    }

    public String getReason() {
        return reason;
    }

    public String[] getParams() {
        if (params != null) {
            return params.clone();
        }
        return new String[] {};
    }

}
