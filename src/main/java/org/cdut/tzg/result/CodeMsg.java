package org.cdut.tzg.result;

/**
 * @author anlan
 * @date 2019/6/25 10:08
 */
public class CodeMsg {
    private int code;
    private String msg;

    public static CodeMsg SUCCESS = new CodeMsg(200, "success");
    //通用异常
    public static CodeMsg FAILED = new CodeMsg(500100, "异常");
    //首页异常5002xx
    public static CodeMsg USER_UNDEFIND = new CodeMsg(500201,"未找到该用户");
    public static CodeMsg NO_ORDERS = new CodeMsg(500202,"最近无订单");

    //商品异常 5003xx
    public static CodeMsg STOCKOUT = new CodeMsg(500301,"库存不足");

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
