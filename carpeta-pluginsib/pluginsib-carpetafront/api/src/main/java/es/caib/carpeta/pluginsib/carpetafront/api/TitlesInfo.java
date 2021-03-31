package es.caib.carpeta.pluginsib.carpetafront.api;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author anadal
 *
 */
public class TitlesInfo {

    protected Map<String, String> titlesByLang;

    protected Map<String, String> subtitlesByLang;

    public TitlesInfo() {
        titlesByLang = new HashMap<String, String>();

        subtitlesByLang = new HashMap<String, String>();
    }

    public TitlesInfo(Map<String, String> titlesByLang, Map<String, String> subtitlesByLang) {
        super();
        this.titlesByLang = titlesByLang;
        this.subtitlesByLang = subtitlesByLang;
    }

    public Map<String, String> getTitlesByLang() {
        return titlesByLang;
    }

    public void setTitlesByLang(Map<String, String> titlesByLang) {
        this.titlesByLang = titlesByLang;
    }

    public Map<String, String> getSubtitlesByLang() {
        return subtitlesByLang;
    }

    public void setSubtitlesByLang(Map<String, String> subtitlesByLang) {
        this.subtitlesByLang = subtitlesByLang;
    }

}
