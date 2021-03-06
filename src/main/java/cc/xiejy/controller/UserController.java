package cc.xiejy.controller;

import cc.xiejy.entity.User;
import cc.xiejy.service.UserService;
import cc.xiejy.util.CryptographyUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 用户登入Controller
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/login")
    public String login(User user, HttpServletRequest request) {
        request.setAttribute("user", user);
        User loginUser = userService.getByUserName(user.getUserName());
        if (loginUser != null) {
            if (loginUser.getPassword().equals(CryptographyUtil.md5(user.getPassword()))) {
                request.getSession().setAttribute("currentUser", loginUser);
                return "redirect:/admin/index.html";
            }
        }
        request.setAttribute("errorInfo", "用户名或密码错误");
        return "login";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute("currentUser");
        return "redirect:/";
    }
}
