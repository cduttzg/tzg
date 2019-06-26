package org.cdut.tzg.result;

/**
 * @author anlan
 * @date 2019/6/25 10:08
 */
public class CodeMsg {
    private int code;
    private String msg;

    //通用异常
    public static CodeMsg SUCCESS = new CodeMsg(200, "success");
    public static CodeMsg FAILED = new CodeMsg(500500, "异常");

    //求购信息异常
    public static  CodeMsg PUBLISHGOODFAILED = new CodeMsg(600601,"发布求购信息错误");
    public static  CodeMsg DELETEGOODFAILED = new CodeMsg(600602,"删除求购信息错误");

    private CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public int getCode() {
        return code;
    }
    public String getMsg() {
        return msg;
    }
}
