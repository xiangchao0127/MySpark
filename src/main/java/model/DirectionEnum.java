package model;

public enum DirectionEnum {
    东("1","东"),
    南("2","南"),
    西("3","西"),
    北("4","北");

    private String code;
    private String name;
    DirectionEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DirectionEnum{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
    public static String getNameByCode(String code){
        for(DirectionEnum directionEnum:DirectionEnum.values()){
            if(directionEnum.getCode().equals(code)){
                return directionEnum.getName();
            }else {
                throw new ErrorException(ReturnExceptionEnum.User_Define_Exception);
            }
        }
        return null;
    }
}
