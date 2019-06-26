package org.cdut.tzg.result;

/**
 * @author anlan
 * @date 2019/6/25 10:08
 */
public class CodeMsg {
    private int code;
    private String msg;

    public static CodeMsg SUCCESS = new CodeMsg(200, "success");
    public static CodeMsg FAILED = new CodeMsg(500500, "异常");

    //求购信息异常
    public static  CodeMsg PUBLISHGOODFAILED = new CodeMsg(600601,"发布求购信息错误");
    public static  CodeMsg DELETEGOODFAILED = new CodeMsg(600602,"删除求购信息错误");

    //通用异常
    //首页异常5002xx
    public static CodeMsg USER_UNDEFIND = new CodeMsg(500201,"未找到该用户");
    public static CodeMsg NO_ORDERS = new CodeMsg(500202,"最近无订单");

    //商品异常 5003xx
    public static CodeMsg STOCKOUT = new CodeMsg(500301,"库存不足");

    //后台数据获取异常5004xx
    public static CodeMsg NO_ORDER = new CodeMsg(500400,"订单不存在");
    public static CodeMsg NO_FROZENUSER = new CodeMsg(500401,"暂无冻结用户");
    public static CodeMsg REPETITIVE_OPERATION = new CodeMsg(500402,"重复操作");
    public static CodeMsg NO_ADMINISTRATOR = new CodeMsg(500403,"暂无管理员");

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
