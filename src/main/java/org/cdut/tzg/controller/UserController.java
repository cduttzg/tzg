package org.cdut.tzg.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.cdut.tzg.model.Goods;
import org.cdut.tzg.model.GoodsOrders;
import org.cdut.tzg.model.Orders;
import org.cdut.tzg.model.User;
import org.cdut.tzg.result.CodeMsg;
import org.cdut.tzg.result.Result;
import org.cdut.tzg.service.GoodsOrdersService;
import org.cdut.tzg.service.GoodsService;
import org.cdut.tzg.service.OrderService;
import org.cdut.tzg.service.UserService;
import org.cdut.tzg.utils.CDUTUtils;
import org.cdut.tzg.utils.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.cdut.tzg.result.CodeMsg.NOT_STUDENT;
import static org.cdut.tzg.result.CodeMsg.USERNAME_REPEAT;

/**
 * @author anlan
 * @date 2019/6/25 8:39
 */
@Controller
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private GoodsOrdersService goodsOrdersService;

    @RequestMapping("/findAll")
    @ResponseBody
    public List<User> findAll(){
        List<User> users = userService.findAll();
        return users;
    }

    @RequestMapping("/find")
    @ResponseBody
    public String find(){
        Map<String,String> map = new HashMap<String,String>();
        map.put("1111","1111");
        map.put("2222","2222");
        map.put("3333","3333");
        String test = null;
        ObjectMapper obj = new ObjectMapper();
        try {
             test = obj.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(test);
        return test;
    }

    /**
     * URL： /api/user/register
     * 描述：用户注册
     * 方法：POST
     * 数据：data:{“学号”:XXX,”用户名”:”XXX”,”密码（HASH加密）”:”XXX”,"教务处密码":"xxxxx",”手机号”:”XXX”,”邮箱”:”XXXX”,”地址”:”XXX”}
     * 返回：
     *  {code: 500502, msg: "用户名已存在", data: null}
     *  {code: 500501, msg: "你不是成都理工的学生", data: null}
     *  {code: 200, msg: "success", data: {content: "注册成功", status: 0}}
     */
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ResponseBody
    public Result<Object> register(@RequestBody String data)
    {   Map map = MapUtils.getMap(data);
        String schoolNumber = (String) map.get("学号");
        String schoolPassword = (String) map.get("教务处密码");
        Map mapdata=new HashMap();
        boolean isStudent = CDUTUtils.isStudent(schoolNumber,schoolPassword);
        if(!isStudent)
            return Result.error(NOT_STUDENT);
        User user = new User();
        user.setUsername(String.valueOf(map.get("用户名")));
        user.setSchoolNumber(String.valueOf(map.get("学号")));
        user.setPassword(String.valueOf(map.get("密码")));
        user.setPhoneNumber(String.valueOf(map.get("手机号")));
        user.setAddress(String.valueOf(map.get("地址")));
        user.setEmail(String.valueOf(map.get("邮箱")));
        user.setIsFrozen(0);
        user.setTotalSold(0);
        user.setGrade(0);
        user.setRole(0);
        int count=0;
        try {
            count = userService.register(user);
        }catch (Exception e){
            System.out.println(e);
        }
        if(count==0)
            return Result.error(USERNAME_REPEAT);
        mapdata.put("status",0);
        mapdata.put("content","注册成功");
        return Result.success(mapdata);
    }

    /**
     * 用户登录
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public Result<Object> login(@RequestBody String data){
        Map map=MapUtils.getMap(data);
        User user=userService.findUserByName((String)map.get("用户名"));
        Map mapdata = new LinkedHashMap();
        if(user ==null){
            return Result.error(CodeMsg.USER_UNDEFIND);
        }else{
            if (user.getPassword().equals((String)map.get("密码"))){
                mapdata.put("status",0);
                boolean isFrozen = (user.getIsFrozen()==0?false:true);
                mapdata.put("是否被冻结",isFrozen);
            }else {
                mapdata.put("status",1);
                mapdata.put("是否被冻结",true);
            }
            return Result.success(mapdata);
        }
    }

    /**
     * 用户是否是商家
     */
    @RequestMapping(value = "/home/isSaller",method = RequestMethod.POST)
    @ResponseBody
    public Result<Object> isSaller(@RequestBody String data){
        Map map=MapUtils.getMap(data);
        User user = userService.findUserByName((String) map.get("用户名"));
        Map <String,Object> mapdata = new LinkedHashMap<String,Object>();
        if (user.getMoneyCode() != null){
            mapdata.put("isSaller",true);
            mapdata.put("moneyCode",user.getMoneyCode());
        }else {
            mapdata.put("isSaller",false);
            mapdata.put("moneyCode",null);
        }
        return Result.success(mapdata);
    }

    /**
     * 发布/删除求购
     */
    @RequestMapping(value = "/home/handleSeek",method = RequestMethod.POST)
    @ResponseBody
    public Result<Object> handleSeek(@RequestBody String data){
        Map map=MapUtils.getMap(data);
        Map mapdata = new HashMap();
        Long userId = userService.findIdByUserName((String) map.get("用户名"));
        if((Boolean) map.get("发布")){//发布求购信息
//            Date day=new Date();
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            //int pubStatus = goodsService.publishSeekGood(userId,(Integer) map.get("商品标签"),(String) map.get("商品名称"),(String) map.get("描述"),Float.parseFloat(map.get("单价").toString()),(Integer) map.get("数量"),(String) map.get("商品图片"));
            Goods exitSeekGoods=goodsService.isExitSeekGoods(userId, (Integer) map.get("商品标签"), (String) map.get("商品名称"));
            if (exitSeekGoods==null){ //求购信息不存在的时候
                Goods good=new Goods();
                good.setUserId(userId);
                good.setTag((Integer) map.get("商品标签"));
                good.setTitle((String) map.get("商品名称"));
                good.setContent((String) map.get("描述"));
                good.setPrice(Float.parseFloat(map.get("单价").toString()));
                good.setStock((Integer) map.get("数量"));
                good.setImage((String) map.get("商品图片"));
                int pubStatus = goodsService.publishSeekGood(good);
                if (pubStatus==1){//求购信息发布成功
                    mapdata.put("success",true);
                    return Result.success(mapdata);
                }else {//求购信息发布失败
                    mapdata.put("success",false);
                    return Result.error(CodeMsg.PUBLISHGOODFAILED);
                }
            }else {//求购信息已经存在
                mapdata.put("success",false);
                return Result.error(CodeMsg.EXITSEEKGOODS);
            }

        }else {//删除求购信息
            int delStatus=goodsService.deleteSeekGood(userId, (Integer) map.get("商品标签"), (String) map.get("商品名称"));
            if (delStatus == 1) {//删除求购信息成功
                mapdata.put("success", true);
                return Result.success(mapdata);
            } else {//删除求购信息失败
                mapdata.put("success", false);
                return Result.error(CodeMsg.DELETEGOODFAILED);
            }
        }
    }

    @RequestMapping(value = "/home/SeekInfo",method = RequestMethod.GET)
    @ResponseBody
    public Result<Object> findAllSeekGoods(@RequestParam String username){
        Map map=null;
        Long userId=userService.findIdByUserName(username);
        List<Goods> seekGoods=goodsService.getAllSeekGoodsByUserId(userId);
        String userPhone=userService.findPhoneByUsername(username);
        if (userPhone==null){
            return Result.error(CodeMsg.USER_UNDEFIND);
        }
        List<Map> listdata=new ArrayList<Map>();
        //“商品标签”:”XXX”,”商品名称”:”XXX”,”描述”:”XXX”,”单价”:XXX,”数量”:XXX ,”商品图片”:”XXX”,”联系方式”:”XXX”
        for(Goods good : seekGoods){
            map=new HashMap();
            map.put("商品标签",good.getTag());
            map.put("商品名称",good.getTitle());
            map.put("描述",good.getContent());
            map.put("单价",good.getPrice());
            map.put("数量",good.getStock());
            map.put("商品图片",good.getImage());
            map.put("联系方式",userPhone);
            listdata.add(map);
        }
        return  Result.success(listdata);
    }

    /*
    * 描述：用户添加新商品
    * 方法：POST
    * 数据：formData-->{"data":{”用户名”:”XXX”,“商品标签”:”XXX”,”商品名称”:”XXX”,”描述”:”XXX”,”单价”:XXX,”数量”:XXX },"img":file}  img-->商品图片
    * 返回值：
    * {"code":200,"msg":"success","data":{"success":true,"content":"添加商品完成"}}
    * {"code": 500402, "msg": "重复操作", data: null}
    * {"code": 500201, "msg": "未找到该用户", "data": null}
    * */
    @RequestMapping(value = "/home/addGoods",method = RequestMethod.POST)
    @ResponseBody
    public Result<Map<String,Object>> addGoods(@RequestBody String data){
        Map<String,Object> map = new HashMap<>();
        Map maps = MapUtils.getMap(data);
        String username = (String) maps.get("username");
        User user = userService.findUserByName(username);
        if (user != null){
            Goods goods = new Goods();
            goods.setUserId(user.getId());
            Integer type = (Integer) maps.get("type");
            goods.setType((Integer) maps.get("type"));
            goods.setTitle((String) maps.get("title"));
            goods.setContent((String) maps.get("content"));
            goods.setPrice(Float.valueOf((Integer) maps.get("price")));
            goods.setImage((String) maps.get("image"));
            goods.setStock((Integer) maps.get("stock"));
            goods.setTag(-1);
            int sign = goodsService.addGoods(goods);
            System.out.println(goods);
            if (sign == 1){
                map.put("success",true);
                map.put("content","添加商品完成");
            }else if (sign == 0)
                //再次上架
                return Result.error(CodeMsg.REPETITIVE_OPERATION);
        }else
            return Result.error(CodeMsg.USER_UNDEFIND);

        return Result.success(map);
    }
    /*
    * 描述：用户将商品上架
    * 方法：POST
    * 数据:{"商品ID":""xxxx","商品标签":xxx}
    * 例子：{“商品ID”:1,"商品标签":1}
    * 期望返回值:{"success":"true","content":"商品重新上架完成"}
    * 返回值：
    * {"code":200,"msg":"success","data":{"success":true,"content":"商品上架完成"}}
    * {"code": 500402, "msg": "重复操作", data: null}
    * {"code": 500303, "msg": "商品不存在", data: null}
    * */
    @RequestMapping(value = "/home/onShelves",method = RequestMethod.POST)
    @ResponseBody
    public Result<Map<String,Object>> onShelves(@RequestBody String data){
        Map<String,Object> map = new HashMap<>();
        Map maps = MapUtils.getMap(data);
        Long goodsId = Long.valueOf((Integer) maps.get("goodsId"));
        Goods goods = goodsService.findGoodsById(goodsId);
        if (goods != null){
            int sign = goodsService.updateTypeState(goodsId,0);
            if (sign == 1){
                map.put("success",true);
                map.put("content","商品上架完成");
            }else if (sign == 0)
                return Result.error(CodeMsg.REPETITIVE_OPERATION);
        }else
            Result.error(CodeMsg.NO_GOODS);
        return Result.success(map);
    }

    /*
    * 描述：用户将商品下架
    * 方法：POST
    * 数据：{“商品ID”:”xxxx”,”下架”:true}------>>>{"商品ID":"xxx"}
    * 期望返回格式：{“success”:false/true,”content”:”xxx”}
    * 返回值：
    * {"code":200,"msg":"success","data":{"success":true,"content":"商品下架完成"}}
    * {"code": 500402, "msg": "重复操作", data: null}
    * {"code": 500303, "msg": "商品不存在", data: null}
    * */
    @RequestMapping(value = "/home/offShelves",method = RequestMethod.POST)
    @ResponseBody
    public Result<Map<String,Object>> offShelves(@RequestBody String data){
        Map<String,Object> map = new HashMap<>();
        Map maps = MapUtils.getMap(data);
        Long goodsId = Long.valueOf((Integer) maps.get("goodsId"));
        Goods goods = goodsService.findGoodsById(goodsId);
        if (goods != null){
            int sign = goodsService.updateTypeState(goodsId,-1);
            if (sign == 1){
                map.put("success",true);
                map.put("content","商品下架完成");
            }else if (sign == 0){
                return Result.error(CodeMsg.REPETITIVE_OPERATION);
            }
        }else
            return Result.error(CodeMsg.NO_GOODS);

        return Result.success(map);
    }
    /*
    * 描述：更新用户信息
    * 方法：POST
    * 数据：formData-->{"data":{“用户名”:”XXX”,”手机号码”:”XXX” ,”电子邮箱”:”XXX” ,”收货地址”:”XXX”},"img":[file,]}   img-->收款码、用户头像
    * 期望返回格式：{“success”:false/true,”content”:”xxx”,”beSaller”:false/true}
    * 返回值：
    * {"code":200,"msg":"success","data":{"success":true,"beSaller":false,"content":"信息修改成功"}}
    * {"code": 500402, "msg": "重复操作", data: null}
    * {"code": 500201, "msg": "未找到该用户", "data": null}
    * */
    @RequestMapping(value = "/home/updateMessage",method = RequestMethod.POST)
    @ResponseBody
    public Result<Map<String,Object>> updateUserInformation(@RequestBody String data){
        Map<String,Object> map = new HashMap<>();
        Map maps = MapUtils.getMap(data);
        String username = (String)maps.get("username");
        User user = userService.findUserByName(username);
        if (user != null){
            String phoneNum = (String) maps.get("phoneNum");
            String email = (String) maps.get("email");
            String address = (String) maps.get("address");
            String avatar = (String) maps.get("avatar");
            String moneyCode = (String) maps.get("moneyCode");
            if (moneyCode.equals(""))
                map.put("beSaller",false);
            else
                map.put("beSaller",true);
            int sign = userService.updateUserInformation(username,phoneNum,email,address,avatar,moneyCode);
            if (sign == 1){
                map.put("success",true);
                map.put("content","信息修改成功");
            }else if (sign == 0)
                return Result.error(CodeMsg.REPETITIVE_OPERATION);
        }else
            return Result.error(CodeMsg.USER_UNDEFIND);
        return Result.success(map);
    }

    /*
    * 描述：支付完成
    * 方法：POST
    * 数据：{“买家用户名”:”xxx”,”订单ID”:”xxx”}
    * 期望返回格式：{“success”:false/true,”content”:”xxx”}
    * 返回值：
    * {"code":200,"msg":"success","data":{"success":true,"content":"订单已支付"}}
    * {"code": 500402, "msg": "重复操作", data: null}
    * {"code": 500400, "msg": "订单不存在", data: null}
    * {"code": 500201, "msg": "未找到该用户", "data": null}
    * */
    @RequestMapping(value = "/buyer/paidOrder",method = RequestMethod.POST)
    @ResponseBody
    public Result<Map<String,Object>> setStateToPaid(@RequestBody String data){
        Map<String,Object> map = new HashMap<>();
        Map maps = MapUtils.getMap(data);
        String userName = (String) maps.get("username");
        Long userId = userService.findIdByUserName(userName);
        if (userId != null) {
            //用户存在
            //获取订单id
            Long orderId = Long.valueOf((Integer) maps.get("id"));
            Orders order = orderService.getOrderByIdAndUserId(orderId, userId);
            //订单存在
            if (order != null) {
                int sign = orderService.setStateToPaid(orderId, userId);
                if (sign == 1) {
                    //修改成功
                    map.put("success", true);
                    map.put("content", "订单已支付");
                } else if (sign == 0) {
                    //原本为已支付状态
                    return Result.error(CodeMsg.REPETITIVE_OPERATION);
                }
            } else{
                return Result.error(CodeMsg.NO_ORDER);
            }
        }else {
            //用户不存在
            return Result.error(CodeMsg.USER_UNDEFIND);
        }
        return Result.success(map);
    }

    /*
    * 描述：收货完成
    * 方法：POST
    * 数据：{“买家用户名”:”xxx”,”订单ID”:”xxx”}
    * 期望返回格式：{“success”:false/true,”content”:”xxx”}
    * 返回值：
    * {"code":200,"msg":"success","data":{"success":true,"content":"订单已完成"}}
    * {"code": 500402, "msg": "重复操作", data: null}
    * {"code": 500400, "msg": "订单不存在", data: null}
    * {"code": 500201, "msg": "未找到该用户", "data": null}
    * */
    @RequestMapping(value = "/buyer/completeOrder",method = RequestMethod.POST)
    @ResponseBody
    public Result<Map<String,Object>> setOrderComplete(@RequestBody String data){
        Map<String, Object> map = new HashMap<>();
        Map maps = MapUtils.getMap(data);
        //获取用户id
        String userName = (String) maps.get("username");
        Long userId = userService.findIdByUserName(userName);
        if (userId != null) {
            //用户存在
            //获取订单id
            Long orderId = Long.valueOf((Integer) maps.get("id"));
            Orders order = orderService.getOrderByIdAndUserId(orderId, userId);
            //订单存在
            if (order != null) {
                int sign = orderService.setStateToCompleted(orderId, userId,new Date());
                if (sign == 1) {
                    //修改成功
                    map.put("success", true);
                    map.put("content", "订单已完成");
                } else if (sign == 0) {
                    //原本为完成状态
                    return Result.error(CodeMsg.REPETITIVE_OPERATION);
                }
            } else{
                return Result.error(CodeMsg.NO_ORDER);
            }
        }else {
            //用户不存在
            return Result.error(CodeMsg.USER_UNDEFIND);
        }
        return Result.success(map);
    }


    /**
     *方法：GET
     * 数据：{“用户名”:”XXX” }
     * 期望返回格式：{“学号”:”XXX” ,”用户名”:”XXX” ,”手机号码”:”XXX” ,”电子邮箱”:”XXX” ,”收货地址”:”XXX” ,”用户评分”:5,”头像”:”XXX” ,”总卖出量”:100}
     */
    @RequestMapping(value = "/home/message",method = RequestMethod.GET)
    @ResponseBody
    public Result<Object> getUserMessage(@RequestParam String username){
        User user = userService.findUserByName(username);//根据用户名查找用户信息
        if (user == null){//查找用户失败，则返回"未找到该用户"
            return Result.error(CodeMsg.USER_UNDEFIND);
        }else {//查找成功
            Map<String,Object> datamap = new HashMap();
            datamap.put("学号",user.getSchoolNumber());
            datamap.put("用户名",user.getUsername());
            datamap.put("手机号码",user.getPhoneNumber());
            datamap.put("电子邮箱",user.getEmail());
            datamap.put("收货地址",user.getAddress());
            datamap.put("用户评分",(int)user.getGrade());
            datamap.put("头像",user.getAvatar());
            datamap.put("总卖出量",(int)user.getTotalSold());
            return Result.success(datamap);
        }
    }

    /**
     * 描述：获取当前用户的订单
     * 方法：GET
     * 数据：{“用户名”:”XXX”}
     * 期望返回格式：[{“买家姓名”:”XXX”,“卖家记录”：[{”卖家姓名”:"xxx",”商品名称”:"xxx"},{”卖家姓名”:"xxx",”商品名称”:"xxx"}],”订单时间”:”XXX”,”订单状态”:已付款/已完成/异常,”订单ID”:”xxx”},]
     */
    @RequestMapping(value = "/home/orderInfo",method = RequestMethod.GET)
    @ResponseBody
    public Result<Object> getUserOrderInfo(@RequestParam String username){
        Long userId = userService.findIdByUserName(username);//用户id
        if(userId == null){
            return Result.error(CodeMsg.USER_UNDEFIND);
        }
        List <Map<String,Object>> listdata = new ArrayList<Map<String,Object>>();
        List<Orders> orders = orderService.getOrderByBuyerId(userId);
        List<GoodsOrders> goodsOrders=new ArrayList<GoodsOrders>();//包含goodsorder
        Map<String,Object> map;
        List <Map<String,Object>> listseller;
        Map<String,Object> mapseller;
        //自己是买家时的订单
        for(Orders orders1:orders){
            map=new HashMap<String, Object>();
            map.put("卖家姓名",username);
            map.put("订单时间",orders1.getCreatedTime());
            map.put("订单状态",orders1.getState());
            map.put("订单ID",orders1.getId());
            Long orderid=orders1.getId();
            List<GoodsOrders> goodsOrder = goodsOrdersService.findTheOrdersDetailById(orderid);
            listseller = new ArrayList<Map<String,Object>>();
            for (GoodsOrders goodsOrders1 : goodsOrder){
                mapseller=new HashMap<String, Object>();
                String sellername=userService.getUserNameById(goodsOrders1.getSellerId());
                mapseller.put("卖家姓名",sellername);
                String goodsname=goodsService.getGoodsNameById(goodsOrders1.getGoodsId());
                mapseller.put("商品名称",goodsname);
                listseller.add(mapseller);
            }
            map.put("卖家记录",listseller);
            listdata.add(map);
        }

        //自己是卖家时的订单时
        List<GoodsOrders> goodsOrderss = goodsOrdersService.getGoodsOrdersBySellerId(userId);//获取自己卖的商品订单
        List<Map<String,Object>> listbuyer = new ArrayList<Map<String,Object>>();
        List<Map<String,Object>> listbuyerrec;
        Map<String,Object> buyermap;
        //System.out.println("1:"+goodsOrderss);
        for (GoodsOrders goodsOrders1:goodsOrderss){
            buyermap= new HashMap<String, Object>();
            listbuyerrec=new ArrayList<Map<String,Object>>();
            map=new HashMap<String, Object>();
            Long orderid=goodsOrders1.getOrdersId();
            Orders orders1=orderService.getOrderById(orderid);
            String buyername = userService.getUserNameById(orders1.getBuyerId());
            Long goodsid=goodsOrders1.getGoodsId();
            String goodsname=goodsService.getGoodsNameById(goodsid);
            //System.out.println(goodsname);
            map.put("买家姓名",buyername);
            map.put("订单时间",orders1.getCreatedTime());
            map.put("订单状态",orders1.getState());
            map.put("订单ID",orderid);
            int index = -1;
           // System.out.println(map);
            if (!listbuyer.isEmpty()){//当listbuyer为空的时候，不需要合并卖家记录
                for (Map<String,Object> map1:listbuyer){
                    if(map1.get("订单ID").equals(orderid)){
                        index = listbuyer.indexOf(map1);
                        List<Map<String,Object>> list=(List<Map<String,Object>>) map1.get("卖家记录");
                        Map<String,Object> map2 = new HashMap<String, Object>();
                        map2.put("卖家姓名",username);
                        map2.put("商品名称",goodsname);
                        list.add(map2);
                        listbuyer.get(index).replace("卖家记录",list);
                        break;
                    }
                }
            }
            if(index == -1){
                buyermap.put("卖家姓名",username);
                buyermap.put("商品名称",goodsname);
                listbuyerrec.add(buyermap);
                map.put("卖家记录",listbuyerrec);
                listbuyer.add(map);
            }
        }
        listdata.addAll(listbuyer);
        return Result.success(listdata);
    }

    /**
     *描述：获取当前用户上架商品信息
     * 方法：GET
     * 数据：{“用户名”:”XXX”}
     * 期望返回格式：[{”商品图片”:”XXX”, ”描述”:”XXX”,”单价”:XXX,”数量”:XXX,”商品ID”:”xxx”},]
     */
    @RequestMapping(value = "/home/goodsInfo",method = RequestMethod.GET)
    @ResponseBody
    public Result<Object> goodsInfo(@RequestParam String username){
        User user = userService.findUserByName(username);
        if (user != null) {
            List<Map<String, Object>> list = new ArrayList<>();
            List<Goods> goods = goodsService.getPutGoods(user.getId());
            //System.out.println(goods);
            for (Goods good : goods) {
                Map<String, Object> mapdata = new HashMap<String, Object>();
                mapdata.put("商品图片", good.getImage());
                mapdata.put("描述", good.getContent());
                mapdata.put("单价", good.getPrice());
                mapdata.put("数量", good.getStock());
                mapdata.put("商品ID", good.getId());
                if (good.getType() == -1){
                    mapdata.put("上架",false);
                }else {
                    mapdata.put("上架",true);
                }
                list.add(mapdata);
            }
            return Result.success(list);
        }else
            return Result.error(CodeMsg.USER_UNDEFIND);
    }
}
