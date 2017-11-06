package model;

public class ErrorException extends RuntimeException{
    private String code;
    private String msg;

    public ErrorException(String code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public ErrorException(ReturnExceptionEnum returnExceptionEnum){
        this.code = returnExceptionEnum.getCode();
        this.msg = returnExceptionEnum.getMsg();
    }
    public ErrorException(ReturnExceptionEnum returnExceptionEnum,String msg){
        this.code = returnExceptionEnum.getCode();
        this.msg = returnExceptionEnum.getMsg()+" "+msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
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
        return "ErrorException{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
