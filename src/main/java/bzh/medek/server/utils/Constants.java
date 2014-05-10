package bzh.medek.server.utils;

/**
 * Class of constants for the project
 * 
 * @author PC_Projets02
 * 
 */
public class Constants {

    public static final String SESSION_USER = "userlogged";
    public static final String SESSION_CLIENT = "clientweb";
    public static final String SESSION_ID = "sessionidlog";

    public static final String PLANIFIE = "PLANIFIE";
    public static final String APLANIFIER = "A PLANIFIER";

    public static final String PHOTO_TYPE = "Photo";
    public static final String CROQUIS_TYPE = "Croquis";

    public static final String KEY_STARTCOORD = "SH.startcoord";

    public static final String LOGGER_PREFIX = " [SESSION=";

    public static final String ARQUILLIAN_TESTING = "arquillian_testing";

    public static final String SEPARATOR = "file.separator";

    public static final String STATUS_PLANNED = "planned";
    public static final String STATUS_RELEASED = "released";
    public static final String STATUS_REFUSED = "refused";
    public static final String STATUS_RESERVED = "reserved";
    public static final String STATUS_FINISHED = "finished";
    public static final String STATUS_VALIDATED = "validated";

    public static final String APPLICATION_SOURCE = "appli";
    public static final String SESSION_ACCOUNTING_MDL = "accounting_mdl";

    public static final String SESSION_ACCOUNTING_USERLOGIN = "user_login";
    public static final String SESSION_ACCOUNTING_OPMODIFSTATE = "operation_modifstate";

    public static final String SESSION_ACCOUNTING_HARDWARE = "accounting_hardware";

    public static final String DB_USER_VALUE = "user";
    public static final String DB_GLOBAL_VALUE = "global";
    public static final String DB_GROUP_VALUE = "group";
    public static final String DB_WEB_VALUE = "web";
    public static final String DB_PDA_VALUE = "pda";

    public static final String REQ_PARAM_OPERATIONID = "operationId";
    public static final String REQ_PARAM_VERSIONNUMBER = "versionNumber";
    public static final String REQ_PARAM_STATUS = "status";
    public static final String REQ_PARAM_USERID = "userId";
    public static final String REQ_PARAM_FILENAME = "filename";
    public static final String REQ_PARAM_WEB = "web";

    // folder in /opt
    public static final String OPT_HOME_DIR = "/opt/sitessurvey";
    // directories inside the folder of /opt
    public static final String[] OPT_LEVEL1_DIRECTORIES = { "conf", "fs", "jasper", "logs", "projects" };
    // sub-directories of level1 directories (name: level1_level2)
    public static final String[] OPT_LEVEL2_DIRECTORIES = { "fs_shexports", "fs_tmp", "jasper_html", "jasper_model",
            "projects_config" };
    // properties files
    public static final String OPT_PROPERTIES_FILES_DIR = "conf";
    public static final String[] OPT_PROPERTIES_FILES = { "accounting.properties", "project.properties", "sitessurvey.properties" };


    private Constants() {
    }

}
