package es.caib.carpeta.front.pluginlogin;

/**
 * 
 * @author anadal
 *
 */
public class LoginInfo {

    private String username;
    private String name;
    private String surname1;
    private String surname2;
    private String administrationID;
    private String authenticationMethod;
    private String qaa;
    private String identityProvider;

    public LoginInfo() {
        super();
    }

    public LoginInfo(String username, String name, String surname1, String surname2, String administrationID,
            String authenticationMethod, String qaa, String identityProvider) {
        super();
        this.username = username;
        this.name = name;
        this.surname1 = surname1;
        this.surname2 = surname2;
        this.administrationID = administrationID;
        this.authenticationMethod = authenticationMethod;
        this.qaa = qaa;
        this.identityProvider = identityProvider;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getIdentityProvider() {
        return identityProvider;
    }

    public void setIdentityProvider(String identityProvider) {
        this.identityProvider = identityProvider;
    }

}
