package es.caib.carpeta.reactjschecker;

import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

/**
 * 
 */
public class Checker {

    public static void main(String[] args) {

        try {

            File root = new File("..");

            File[] projectes = root.listFiles(new FileFilter() {
                
                @Override
                public boolean accept(File pathname) {
                    
                    return pathname.isDirectory();
                }
            });

            System.out.println("# Plugins " + projectes.length);

            for (File file : projectes) {

                File p = new File(file, "package.json");

                if (p.exists()) {
                    System.out.println(checkPackageJson(file.getName(), p));
                } else {
                    File react = new File(file, "react/package.json");
                    if (react.exists()) {
                        System.out.println(checkPackageJson(file.getName(), react));
                    } else {
                        System.out.println(" - " + file.getName() + ":\tskip");
                    }
                }

            }

            System.out.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Final");
    }

    public static final Map<String, String> versions = new HashMap<String, String>();

    public static String checkPackageJson(String pluginName, File p) throws Exception {

        Gson gson = new Gson();
        ReactJSChecker packageBean = gson.fromJson(new FileReader(p), ReactJSChecker.class);

        checkProperty(pluginName, "getI18next", packageBean.getDependencies().getI18next());
        checkProperty(pluginName, "getAxios", packageBean.getDependencies().getAxios());
        checkProperty(pluginName, "getReact", packageBean.getDependencies().getReact());
        checkProperty(pluginName, "getReactDom", packageBean.getDependencies().getReactDom());
        
        checkProperty(pluginName, "getWebpack", packageBean.getDevDependencies().getWebpack());
        
        
        //"webpack": "^5.74.0",

        return " - " + pluginName + ":\tOK";

    }

    public static void checkProperty(String pluginName, String name, String value) throws Exception {

        if (value != null) {

            String valMap = versions.get(name);
            if (valMap == null) {
                versions.put(name, value);
            } else {
                if (!valMap.equals(value)) {
                    throw new Exception("La llibreria " + name + " del plugin " + pluginName + " té versió " + value
                            + " però s'esperava " + valMap);
                }
            }
        }

    }

}
