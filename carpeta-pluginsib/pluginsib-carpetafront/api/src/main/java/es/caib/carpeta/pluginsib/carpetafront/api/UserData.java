package es.caib.carpeta.pluginsib.carpetafront.api;

/**
 * 
 * @author anadal
 *
 */
public class UserData {

    String name;
    String surname1;
    String surname2;
    String administrationID;

    public UserData() {
        super();
    }

    public UserData(String name, String surname1, String surname2, String administrationID) {
        super();
        this.name = name;
        this.surname1 = surname1;
        this.surname2 = surname2;
        this.administrationID = administrationID;
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

}
