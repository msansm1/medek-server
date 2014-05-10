package bzh.medek.server.utils;

import javax.servlet.http.HttpServletRequest;

public class MedekUtils {

    private MedekUtils() {
    }

    /**
     * Get a request parameter value with the name.
     * 
     * @param request
     * @param name
     * @return
     */
    public static String getRequestParam(HttpServletRequest request, String name) {
        if (request.getParameterMap().get(name) != null) {
            return request.getParameterMap().get(name)[0];
        }
        return "";
    }

    /**
     * Remove the timestamp from a file name.
     * 
     * @param file
     * @return
     */
    public static String removeTimestamp(String file) {
        // delete timestamp of filename
        String fileName = file;
        int point = fileName.lastIndexOf('.');
        int underscore = fileName.lastIndexOf('_');

        if (underscore >= 0 && point - underscore >= 14) {
            String ext = fileName.substring(point, fileName.length());
            fileName = fileName.substring(0, underscore);
            fileName = fileName + ext;
        }
        return fileName;
    }

    /**
     * Checks if the session contains a user or if the request the parameter
     * ARQUILLIAN_TESTING
     * 
     * @param request
     * @return 0 - request is correct other - error message ID
     */
    public static int isRequestCorrect(HttpServletRequest request) {
        return isRequestCorrect(request, null);
    }

    /**
     * Checks if the session contains a user or if the request the parameter
     * ARQUILLIAN_TESTING or if a testing param is set
     * 
     * @param request
     * @param testing
     * @return
     */
    public static int isRequestCorrect(HttpServletRequest request, String testing) {
        if (request.getSession().getAttribute(Constants.SESSION_USER) == null
                && request.getParameterMap().get(Constants.ARQUILLIAN_TESTING) == null && testing == null) {
            return 102;
        }
        return 0;
    }

    /**
     * Returns the web param value, for the client or in integration testing
     * 
     * @param request
     * @return
     */
    public static Boolean getWebParam(HttpServletRequest request) {
        if (request.getParameterMap().get(Constants.ARQUILLIAN_TESTING) != null) {
            return Boolean.parseBoolean(MedekUtils.getRequestParam(request, Constants.REQ_PARAM_WEB));
        } else {
            return (Boolean) request.getSession().getAttribute(Constants.SESSION_CLIENT);
        }
    }
}
