package bzh.medek.server.json.ping;

import bzh.medek.server.json.JsonResponse;

public class JsonPingResp extends JsonResponse {

    private String buildVersion;
    private String appVersion;

    public JsonPingResp() {
    }

    public JsonPingResp(String buildVersion, String appVersion) {
        super();
        this.buildVersion = buildVersion;
        this.appVersion = appVersion;
    }

    public String getBuildVersion() {
        return buildVersion;
    }

    public void setBuildVersion(String buildVersion) {
        this.buildVersion = buildVersion;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

}
