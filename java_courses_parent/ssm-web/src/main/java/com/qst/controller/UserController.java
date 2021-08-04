package com.qst.controller;

import com.github.pagehelper.PageInfo;
import com.qst.domain.ResponseResult;
import com.qst.domain.Role;
import com.qst.domain.User;
import com.qst.domain.UserVO;
import com.qst.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/findAllUserByPage")
    public ResponseResult findAllUserByPage(@RequestBody UserVO userVO){
        PageInfo<User> pageInfo = userService.findAllUserByPage(userVO);

        ResponseResult result = new ResponseResult(true,200,"响应成功",pageInfo);
        return result;

    }
    /*
    * 更新用户状态
    * ENABLE能登录，DISABLE不能登录
    * */
    @RequestMapping("/updateUserStatus")
    public ResponseResult updateUserStatus(Integer id,String status){
        //当传过来是能登录就要修改为不能登录状态
        if("enable".equalsIgnoreCase(status)){
            status = "DISABLE";
        }else{
            status = "ENABLE";
        }
        userService.updateUserStatus(id,status);
        ResponseResult result = new ResponseResult(true,200,"用户状态修改成功",status);
        return result;
    }


    /*
    * 用户登录
    * */
    @RequestMapping("/login")
    public ResponseResult login(User user, HttpServletRequest request) throws  Exception{
        User user1 = userService.login(user);
        if(user1 != null){
            //保存用户id及access_token到session中  主要是下次请求时直接就可以从session中判断用户就行
            HttpSession session = request.getSession();
            String access_token = UUID.randomUUID().toString();
            session.setAttribute("access_token",access_token);
            session.setAttribute("user_id",user1.getId());

            //将查询出来的信息响应给前台
            Map<String,Object> map = new HashMap<>();
            map.put("access_token",access_token);
            map.put("user_id",user1.getId());
            //将查询出来的user，也存到map中
            map.put("user",user1);
            return new ResponseResult(true,1,"登录成功",map);
        }else{
            return new ResponseResult(true,400,"用户名或密码错误",null);
        }
    }

    /*
    * 分配角色回显
    * */
    @RequestMapping("/findUserRoleById")
    public ResponseResult findUserRelatioinRoleById(Integer id){
        List<Role> roleList = userService.findUserRelationRoleById(id);

        return new ResponseResult(true,200,"分配角色回显成功",roleList);

    }
    /*
    * 分配角色
    * */

    @RequestMapping("/userContextRole")
    public ResponseResult userContextRole(@RequestBody UserVO userVO){

        userService.userContextRole(userVO);

        return new ResponseResult(true,200,"分配角色成功",null);

    }

    /*
    * 获取用户权限，进行菜单动态展示  用户必须进行登录
    * */

    @RequestMapping("/getUserPermissions")
    public ResponseResult getUserPermissions(HttpServletRequest request) {
        //1.获取请求头中的token
        String header_token = request.getHeader("Authorization");
        //2.获取session中token
        String session_token = (String) request.getSession().getAttribute("access_token");
        //3.判断token是否一致
        if (header_token.equalsIgnoreCase(session_token)) {
            //获取用户id
            Integer user_id = (Integer) request.getSession().getAttribute("user_id");
            System.out.println(user_id);
            //调用service，进行菜单信息查询
            ResponseResult responseResult = userService.getUserPermissions(user_id);
            return responseResult;
        } else {
            ResponseResult result = new ResponseResult(false, 400, "获取菜单信息失败", null);
            return result;
        }
    }
}
