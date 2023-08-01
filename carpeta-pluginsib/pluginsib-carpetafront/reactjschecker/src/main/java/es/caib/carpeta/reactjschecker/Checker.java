package es.caib.carpeta.reactjschecker;

import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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
            
            
            List<File> projectesList = new ArrayList<File>();
            
            projectesList.addAll(Arrays.asList(projectes));
            
            File reactFront= new File("../../../carpeta-front/react");
            projectesList.add(reactFront);


            for (File file : projectesList) {

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

        Dependencies deps = packageBean.getDependencies();
        
        checkProperty(pluginName, "getAxios", deps.getAxios());
        
        
        
        //checkProperty(pluginName, "getCarpetacommonreactlib", deps.getCarpetacommonreactlib());
        
        
        checkProperty(pluginName, "getHttpsBrowserify", deps.getHttpsBrowserify());
        checkProperty(pluginName, "getI18next", deps.getI18next());
        checkProperty(pluginName, "getI18nextBrowserLanguagedetector", deps.getI18nextBrowserLanguagedetector());
        checkProperty(pluginName, "getI18nextHttpBackend", deps.getI18nextHttpBackend());
        checkProperty(pluginName, "getHttpsBrowserify", deps.getHttpsBrowserify());
        checkProperty(pluginName, "getReact", deps.getReact());
        checkProperty(pluginName, "getReactDom", deps.getReactDom());
        checkProperty(pluginName, "getReactI18next", deps.getReactI18next());
        
        
        
        
        DevDependencies devDep = packageBean.getDevDependencies();
        checkProperty(pluginName, "getBabelCore", devDep.getBabelCore());
        checkProperty(pluginName, "getBabelLoader", devDep.getBabelLoader());
        checkProperty(pluginName, "getBabelPluginProposalClassProperties", devDep.getBabelPluginProposalClassProperties());
        checkProperty(pluginName, "getBabelPresetEnv", devDep.getBabelPresetEnv());
        checkProperty(pluginName, "getBabelPresetReact", devDep.getBabelPresetReact());
        
        checkProperty(pluginName, "getCssLoader", devDep.getCssLoader());
        checkProperty(pluginName, "getRimraf", devDep.getRimraf());
        checkProperty(pluginName, "getStyleLoader", devDep.getStyleLoader());
        checkProperty(pluginName, "getTsLoader", devDep.getTsLoader());
        checkProperty(pluginName, "getTsNode", devDep.getTsNode());
        
        
        checkProperty(pluginName, "getTypescript", devDep.getTypescript());
        checkProperty(pluginName, "getTypesJquery", devDep.getTypesJquery());
        checkProperty(pluginName, "getTypesNode", devDep.getTypesNode());
        checkProperty(pluginName, "getTypesReact", devDep.getTypesReact());
        checkProperty(pluginName, "getTypesReactDom", devDep.getTypesReactDom());
        
        checkProperty(pluginName, "getTypesWebpack", devDep.getTypesWebpack());
        checkProperty(pluginName, "getUrlLoader", devDep.getUrlLoader());
        checkProperty(pluginName, "getWebpack", devDep.getWebpack());
        checkProperty(pluginName, "getWebpackCli", devDep.getWebpackCli());
        
        
        checkProperty(pluginName, "getWebpackDevServer", devDep.getWebpackDevServer());
        
        
        
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
