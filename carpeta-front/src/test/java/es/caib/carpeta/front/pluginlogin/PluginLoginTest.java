package es.caib.carpeta.front.pluginlogin;

import java.io.File;
import java.io.FileReader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 
 * @author anadal
 *
 */
public class PluginLoginTest {
    public static void main(String[] args) {

        serialize();

        deserialize();

    }

    protected static void deserialize() {

        FileReader fr;
        try {
            fr = new FileReader(new File("logininfo.json"));

            Gson gson = new GsonBuilder()
                    // .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                    .create();

            LoginInfo logininfo = gson.fromJson(fr, LoginInfo.class);

            System.out.println(logininfo.getName() + " " + logininfo.getSurname1() + " ("
                    + logininfo.getAdministrationID() + ")");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected static void serialize() {
        LoginInfoRepresentative representative = null;

        {
            String name = "Pep";
            String surname1 = "Gonella";
            String surname2 = "Fuster";
            String administrationID = "30000056Y";
            representative = new LoginInfoRepresentative(name, surname1, surname2,
                    administrationID);
        }

        String username = "";
        String name = "MyBusiness";
        String surname1 = null;
        String surname2 = null;

        String administrationID = "87654321Z";

        String authenticationMethod = "None";
        int qaa = LoginInfo.NIVELL_AUTENTICACIO_BAIX;
        String identityProvider = "MOCK";
        boolean business = false;

        LoginInfo li = new LoginInfo(username, name, surname1, surname2, administrationID,
                authenticationMethod, qaa, identityProvider, business, representative);

        Gson gson = new GsonBuilder()
                // .setPrettyPrinting()
                // .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();

        String jsonString = gson.toJson(li);

        System.out.println(jsonString);
    }
}
