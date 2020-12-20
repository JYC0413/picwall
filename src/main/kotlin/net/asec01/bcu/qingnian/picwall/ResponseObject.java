package net.asec01.bcu.qingnian.picwall;

public class ResponseObject extends BaseObject{
    Integer status = 0;
    String message = "";
    Object obj;

    public ResponseObject(Object obj) {
        this.obj = obj;
    }

    public ResponseObject(Integer status, String message, Object obj) {
        this.status = status;
        this.message = message;
        this.obj = obj;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
