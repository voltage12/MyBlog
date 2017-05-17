package cc.xiejy.controller.admin;

import cc.xiejy.entity.User;
import cc.xiejy.service.UserService;
import cc.xiejy.util.CryptographyUtil;
import cc.xiejy.util.ResponseUtil;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 用户信息管理Controller
 * Created by xie on 2017/4/10 0010.
 */
@Controller
@RequestMapping("/admin/user")
public class UserAdminController {

    @Resource
    private UserService userService;

    /**
     * 跳转到密码修改页面
     *
     * @return
     */
    @RequestMapping("/passwdManage")
    public ModelAndView passwdManage() {
        ModelAndView mav = new ModelAndView();

        mav.addObject("pageTitle", "后台管理-密码修改");
        mav.addObject("includePage", "passwdManage.jsp");
        mav.setViewName("admin/index");
        return mav;
    }

    /**
     * 跳转到个人信息修改页面
     *
     * @return
     */
    @RequestMapping("/infoManage")
    public ModelAndView infoManage() {
        ModelAndView mav = new ModelAndView();

        mav.addObject("pageTitle", "后台管理-个人信息修改");
        mav.addObject("includePage", "infoManage.jsp");
        mav.setViewName("admin/index");
        return mav;
    }

    /**
     * 修改用户密码
     *
     * @throws Exception
     */
    @RequestMapping("/changePw")
    public void changePassword(@RequestParam(value = "oldPw") String oldPw, @RequestParam(value = "newPw") String newPw,
                               HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject result = new JSONObject();
        User loginUser = (User) request.getSession().getAttribute("currentUser");

        if (loginUser != null) {
            //计算出密码的MD5值进行对比，如果一致就更新密码
            if (loginUser.getPassword().equals(CryptographyUtil.md5(oldPw))) {
                result.put("success", true);
                loginUser.setPassword(CryptographyUtil.md5(newPw));
                userService.update(loginUser);
            } else {
                result.put("success", false);
                result.put("errorInfo", "密码错误");
            }
        }
        ResponseUtil.write(response, result);
    }

    /**
     * 修改用户个人信息
     *
     * @throws Exception
     */
    @RequestMapping("/changeInfo")
    public void changeInfo(User user, @RequestParam(value = "image", required = false) MultipartFile image,
                           HttpServletResponse response, HttpServletRequest request) throws Exception {
        //如果用户上传了新的头像，就将图片存储在userimages目录下
        if (image != null && !image.isEmpty()) {
            String filePath = request.getServletContext().getRealPath("/");
            //为了避免文件名重复在文件名之前加上上传时间
            String dateStr = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            image.transferTo(new File(filePath + "static/userimages/" + dateStr + image.getOriginalFilename()));
            user.setImageLocal(dateStr + image.getOriginalFilename());
        }
        int resultNum;
        JSONObject result = new JSONObject();

        user.setId(1);
        //调用userService对用户信息进行更新
        resultNum = userService.update(user);
        if (resultNum != 0) {
            result.put("success", true);
            //对系统缓存中的用户信息进行更新
            User newUser = userService.getByUserName("xiejy");
            request.getSession().getServletContext().setAttribute("user", newUser);
        } else {
            result.put("success", false);
        }
        ResponseUtil.write(response, result);
    }
}
