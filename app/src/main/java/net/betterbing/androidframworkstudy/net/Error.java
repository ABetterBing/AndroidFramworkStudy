package net.betterbing.androidframworkstudy.net;

public class Error {
    private int code;

    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Error{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
