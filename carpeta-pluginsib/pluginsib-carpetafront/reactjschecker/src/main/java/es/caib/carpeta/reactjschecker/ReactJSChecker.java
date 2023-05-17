
package es.caib.carpeta.reactjschecker;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReactJSChecker {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("version")
    @Expose
    private String version;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("main")
    @Expose
    private String main;
    @SerializedName("scripts")
    @Expose
    private Scripts scripts;
    @SerializedName("keywords")
    @Expose
    private List<Object> keywords;
    @SerializedName("author")
    @Expose
    private String author;
    @SerializedName("license")
    @Expose
    private String license;
    @SerializedName("dependencies")
    @Expose
    private Dependencies dependencies;
    @SerializedName("devDependencies")
    @Expose
    private DevDependencies devDependencies;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public Scripts getScripts() {
        return scripts;
    }

    public void setScripts(Scripts scripts) {
        this.scripts = scripts;
    }

    public List<Object> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<Object> keywords) {
        this.keywords = keywords;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public Dependencies getDependencies() {
        return dependencies;
    }

    public void setDependencies(Dependencies dependencies) {
        this.dependencies = dependencies;
    }

    public DevDependencies getDevDependencies() {
        return devDependencies;
    }

    public void setDevDependencies(DevDependencies devDependencies) {
        this.devDependencies = devDependencies;
    }

}
