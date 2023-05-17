
package es.caib.carpeta.reactjschecker;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Scripts {

    @SerializedName("start")
    @Expose
    private String start;
    @SerializedName("deploy")
    @Expose
    private String deploy;
    @SerializedName("build")
    @Expose
    private String build;

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getDeploy() {
        return deploy;
    }

    public void setDeploy(String deploy) {
        this.deploy = deploy;
    }

    public String getBuild() {
        return build;
    }

    public void setBuild(String build) {
        this.build = build;
    }

}
