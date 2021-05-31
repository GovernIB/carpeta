package es.caib.carpeta.front.pluginlogin;

/**
 * 
 * @author anadal
 *
 */
public class LoginInfo {

    public static final int NIVELL_AUTENTICACIO_BAIX = 1;
    public static final int NIVELL_AUTENTICACIO_MITJA = 2;
    public static final int NIVELL_AUTENTICACIO_ALT = 3;

    private String username;
    private String name;
    private String surname1;
    private String surname2;
    private String administrationID;
    private String authenticationMethod;
    private int qaa;
    private String identityProvider;
    private boolean business;
    private LoginInfoRepresentative representative;

    /**
     * 
     */
    public LoginInfo() {
        super();
    }

    /**
     * 
     * @param username
     * @param name
     * @param surname1
     * @param surname2
     * @param administrationID
     * @param authenticationMethod
     * @param qaa
     * @param identityProvider
     * @param business
     * @param representative
     */
    public LoginInfo(String username, String name, String surname1, String surname2, String administrationID,
            String authenticationMethod, int qaa, String identityProvider, boolean business,
            LoginInfoRepresentative representative) {
        super();
        this.username = username;
        this.name = name;
        this.surname1 = surname1;
        this.surname2 = surname2;
        this.administrationID = administrationID;
        this.authenticationMethod = authenticationMethod;
        this.qaa = qaa;
        this.identityProvider = identityProvider;
        this.business = business;
        this.representative = representative;
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

    public int getQaa() {
        return qaa;
    }

    public void setQaa(int qaa) {
        this.qaa = qaa;
    }

    public String getIdentityProvider() {
        return identityProvider;
    }

    public void setIdentityProvider(String identityProvider) {
        this.identityProvider = identityProvider;
    }

    public boolean isBusiness() {
        return business;
    }

    public void setBusiness(boolean business) {
        this.business = business;
    }

    public LoginInfoRepresentative getRepresentative() {
        return representative;
    }

    public void setRepresentative(LoginInfoRepresentative representative) {
        this.representative = representative;
    }

}
