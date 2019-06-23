package com.zilanghuo.springbootdemo.web;

import com.zilanghuo.springbootdemo.model.User;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author laiwufa
 * @date 2019/6/23 13:42
 */
@RestController
@Scope("singleton")
@RequestMapping(value = "/simple")
public class UserControllerSimple {

    // 创建线程安全的Map
    static Map<Long, User> users = Collections.synchronizedMap(new HashMap<Long, User>());

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<User> getUserList() {
// 处理"/users/"的GET请求， ⽤来获取⽤户列表
// 还可以通过@RequestParam从⻚⾯中传递参数来进⾏查询条件或者翻⻚信息的传递
        List<User> r = new ArrayList<User>(users.values());
        return r;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String postUser(@ModelAttribute User user) {
// 处理"/users/"的POST请求， ⽤来创建User
// 除了@ModelAttribute绑定参数之外， 还可以通过@RequestParam从⻚⾯中传递参数
        users.put(user.getId(), user);
        return "success";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable Long id) {
// 处理"/users/{id}"的GET请求， ⽤来获取url中id值的User信息
// url中的id可通过@PathVariable绑定到函数的参数中
        return users.get(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String putUser(@PathVariable Long id, @ModelAttribute User user) {
// 处理"/users/{id}"的PUT请求， ⽤来更新User信息
        User u = users.get(id);
        u.setName(user.getName());
        u.setAge(user.getAge());
        users.put(id, u);
        return "success";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable Long id) {
// 处理"/users/{id}"的DELETE请求， ⽤来删除User
        users.remove(id);
        return "success";
    }
}
