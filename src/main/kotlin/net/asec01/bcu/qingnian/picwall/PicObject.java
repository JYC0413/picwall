package net.asec01.bcu.qingnian.picwall;

public class PicObject extends BaseObject{
    Integer id;
    String filename;
    String session;
    String useragent;
    String timestamp;
    Boolean checked;

    public PicObject() {
    }

    public PicObject(Integer id, String filename, String timestamp) {
        this.id = id;
        this.filename = filename;
        this.timestamp = timestamp;
    }

    public PicObject(String filename, String session, String useragent) {
        this.filename = filename;
        this.session = session;
        this.useragent = useragent;
    }

    public PicObject(Integer id, String filename, String session, String useragent, String timestamp, Boolean checked) {
        this.id = id;
        this.filename = filename;
        this.session = session;
        this.useragent = useragent;
        this.timestamp = timestamp;
        this.checked = checked;
    }

    public void removeSensitiveInfo(){
        this.session = null;
        this.useragent = null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getUseragent() {
        return useragent;
    }

    public void setUseragent(String useragent) {
        this.useragent = useragent;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }
}
