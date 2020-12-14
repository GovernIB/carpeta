package es.caib.carpeta.pluginsib.carpetafront.api;

/**
 * 
 * @author anadal
 *
 */
public class UserData {

    protected String name;
    protected String surname1;
    protected String surname2;
    protected String administrationID;
    protected String authenticationMethod;
    protected String qaa;

    public UserData() {
        super();
    }

    public UserData(String administrationID) {
        super();
        this.administrationID = administrationID;
    }

    
    
    public UserData(String name, String surname1, String surname2, String administrationID, 
            String authenticationMethod, String qaa) {
        super();
        this.name = name;
        this.surname1 = surname1;
        this.surname2 = surname2;
        this.administrationID = administrationID;
        this.authenticationMethod = authenticationMethod;
        this.qaa = qaa;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname1() {
        return surname1;
    }

    public void setSurname1(String surname1) {
        this.surname1 = surname1;
    }

    public String getSurname2() {
        return surname2;
    }

    public void setSurname2(String surname2) {
        this.surname2 = surname2;
    }

    public String getAdministrationID() {
        return administrationID;
    }

    public void setAdministrationID(String administrationID) {
        this.administrationID = administrationID;
    }

    public String getAuthenticationMethod() {
        return authenticationMethod;
    }

    public void setAuthenticationMethod(String authenticationMethod) {
        this.authenticationMethod = authenticationMethod;
    }

    public String getQaa() {
        return qaa;
    }

    public void setQaa(String qaa) {
        this.qaa = qaa;
    }

}
