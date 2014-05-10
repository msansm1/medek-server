package bzh.medek.server.json;

public class JsonSimpleResponse extends JsonResponse {

    private String ok;

    public JsonSimpleResponse() {
    }

    public JsonSimpleResponse(boolean val) {
        super();
        this.ok = "" + val;
    }

    public void setOk(String ok) {
        this.ok = ok;
    }

    public String getOk() {
        return ok;
    }

    @Override
    public String toString() {
        return "JsonSimpleResponse [ok=" + ok + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((ok == null) ? 0 : ok.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        JsonSimpleResponse other = (JsonSimpleResponse) obj;
        if (ok == null) {
            if (other.ok != null) {
                return false;
            }
        } else if (!ok.equals(other.ok)) {
            return false;
        }
        return true;
    }

}
