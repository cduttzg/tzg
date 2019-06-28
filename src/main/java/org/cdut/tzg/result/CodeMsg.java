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
    public static  CodeMsg DELETEGOODFAILED = new CodeMsg(600602,"删除求购信息错误,求购信息不存在");
    public static  CodeMsg EXITSEEKGOODS = new CodeMsg(600603,"求购信息已经存在，请勿重复发布");
    //通用异常
    //首页异常5002xx
    public static CodeMsg USER_UNDEFIND = new CodeMsg(500201,"未找到该用户");
    public static CodeMsg NO_ORDERS = new CodeMsg(500202,"最近无订单");

    //商品异常 5003xx
    public static CodeMsg STOCKOUT = new CodeMsg(500301,"库存不足");
    public static CodeMsg EMPTY_TYPE_GOODS = new CodeMsg(500302,"当前没有该类型商品");
    public static CodeMsg NO_GOODS = new CodeMsg(500303,"商品不存在");

    //购物异常 5005xx
    public static CodeMsg EMPTY_CART=new CodeMsg(500501,"当前用户购物车为空");

    //后台数据获取异常5004xx
    public static CodeMsg NO_ORDER = new CodeMsg(500400,"订单不存在");
    public static CodeMsg NO_FROZENUSER = new CodeMsg(500401,"暂无冻结用户");
    public static CodeMsg REPETITIVE_OPERATION = new CodeMsg(500402,"重复操作");
    public static CodeMsg NO_ADMINISTRATOR = new CodeMsg(500403,"暂无管理员");

    //个人中心异常 5005xx
    public static CodeMsg NOT_STUDENT = new CodeMsg(500501,"你不是成都理工的学生");
    public static CodeMsg USERNAME_REPEAT = new CodeMsg(500502,"用户名已存在");
    public static CodeMsg PASSWD_ERROE = new CodeMsg(600603,"密码错误");

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
