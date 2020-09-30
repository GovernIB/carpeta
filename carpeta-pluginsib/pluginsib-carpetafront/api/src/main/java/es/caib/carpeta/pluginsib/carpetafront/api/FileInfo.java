package es.caib.carpeta.pluginsib.carpetafront.api;

public class FileInfo {

    private String name;
    private String mime;
    private byte[] data;

    public FileInfo() {
    }

    public FileInfo(String name, String mime, byte[] data) {
        this.name = name;
        this.mime = mime;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMime() {
        return mime;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
