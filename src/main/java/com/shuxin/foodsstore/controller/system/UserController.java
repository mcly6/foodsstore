package com.shuxin.foodsstore.controller.system;

import com.shuxin.foodsstore.commons.configuration.BaseConfig;
import com.shuxin.foodsstore.commons.enums.ResultEnum;
import com.shuxin.foodsstore.commons.enums.StatusEnum;
import com.shuxin.foodsstore.commons.enums.UserTypeEnum;
import com.shuxin.foodsstore.commons.utils.*;
import com.shuxin.foodsstore.entity.system.User;
import com.shuxin.foodsstore.service.system.UserService;
import com.shuxin.foodsstore.vo.ResultVO;
import com.shuxin.foodsstore.vo.TableVO;
import com.shuxin.foodsstore.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.usertype.UserType;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.xml.transform.Result;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/toUserList")
    public ModelAndView toUserList() {

        return new ModelAndView("system/user_list");

    }


    /**
     * @Title: getUserList 根据用户类型查询用户列表
     * @Param [userType]
     * @Return com.shuxin.foodsstore.vo.PageVO
     */
    @RequestMapping("/list")
    @ResponseBody
    public TableVO getUserList(@RequestParam(name = "userType", defaultValue = "") Integer userType,
                               @RequestParam(name = "page") Integer page,
                               @RequestParam(name = "limit") Integer limit) {
        PageRequest pageRequest = new PageRequest(page - 1, limit);

        Page<User> userPage = null;
        if (userType != null) {
            userPage = userService.findAllByType(userType, pageRequest);

        } else {
            userPage = userService.findAll(pageRequest);
        }
        return TableVOResultUtils.success(userPage);
    }

    /**
     * @Title: loginStatus 验证用户是否登录
     * @Param [request]
     * @Return com.shuxin.foodsstore.vo.ResultVO
     */
    @RequestMapping("/loginStatus")
    @ResponseBody
    public ResultVO loginStatus(HttpServletRequest request) {

        // User loginUser = (User) request.getSession().getAttribute(BaseConfig.LOGIN_USER);

        User loginUser = ServletUtil.getCurrentUser();

        UserVO userVO = new UserVO();

        if (loginUser != null) {
            BeanUtils.copyProperties(loginUser, userVO);
            return ResultVOUtils.success(userVO);
        }

        return ResultVOUtils.error(400, ResultEnum.USER_NOT_EXIST.getMessage());
    }


    /**
     * @Title: getUserByUserName 判断用户名是否已注册
     * @Param [username]
     * @Return com.shuxin.foodsstore.vo.ResultVO
     */
    @RequestMapping("/getUserByName")
    @ResponseBody
    public ResultVO getUserByUserName(@RequestParam("username") String username) {

        boolean flag = userService.findByUserName(username);

        if (!flag) {
            return ResultVOUtils.success();
        } else {
            return ResultVOUtils.error(400, ResultEnum.USRENAME_ALREADY_EXIST.getMessage());
        }

    }

    /**
     * @Title: getHosNumInfo 根据住院号查询住院信息
     * @Param [hosNum]
     * @Return com.shuxin.foodsstore.vo.ResultVO
     */
    @RequestMapping("/getHosNumInfo")
    @ResponseBody
    public ResultVO getHosNumInfo(@RequestParam("hosNum") String hosNum) {



        return ResultVOUtils.success();
    }



    /**
     * @Title: bindingHosNum 绑定住院号
     * @Param [hosNum]
     * @Return com.shuxin.foodsstore.vo.ResultVO
     */
    @RequestMapping("/bindingHosNum")
    @ResponseBody
    public ResultVO bindingHosNum(@RequestParam("hosNum") String hosNum) {

        //User user = UserUtils.getUser();
        User user = ServletUtil.getCurrentUser();

        if (user == null) {

            return ResultVOUtils.error(400, ResultEnum.USER_LOGIN_FAIL.getMessage());
        }

        user.setHosNum(hosNum);

        User savaUser = userService.sava(user);
        if (savaUser == null) {
            return ResultVOUtils.error(400, ResultEnum.USER_HOSNUM_FAIL.getMessage());

        }

        return ResultVOUtils.success();
    }

    //crud
    @RequestMapping("/toAdd")
    public ModelAndView toAdd() {

        ModelAndView view = new ModelAndView("system/user_add");

        // List<Integer> typeList = Arrays.stream(UserTypeEnum.values()).map(e -> e.getCode()).collect(Collectors.toList());

        UserTypeEnum[] typeEnums = UserTypeEnum.values();
        view.addObject("typeList", typeEnums);


        return view;

    }

    @RequestMapping("add")
    @ResponseBody
    public ResultVO add(@Valid UserVO userVO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResultVOUtils.error(400, ResultEnum.USER_REGISTER_ERROR.getMessage());
        }
        User user = new User();
        if (userVO.getId() == null) { //新建
            user.setPassword(MD5Util.getMD5("123456"));//默认密码123456
        } else {
            user = userService.findOne(userVO.getId());
        }

        BeanUtils.copyProperties(userVO, user);

        User userResult = userService.save(user);


        if (userResult != null) {
            return ResultVOUtils.success();
        } else {
            return ResultVOUtils.error(400, ResultEnum.USER_REGISTER_ERROR.getMessage());
        }

    }

    @RequestMapping("/toEdit/{id}")
    public ModelAndView toEdit(@PathVariable(value = "id") Integer id) {

        ModelAndView view = new ModelAndView("system/user_edit");
        User user = userService.findOne(id);
        UserVO userVO = new UserVO();

        BeanUtils.copyProperties(user, userVO);
        UserTypeEnum[] typeEnums = UserTypeEnum.values();

        view.addObject("typeList", typeEnums);
        view.addObject("user", userVO);

        return view;

    }
    /**
     * @Title: disable 账号禁用
     * @Param [id]
     * @Return org.springframework.web.servlet.ModelAndView
     */
    @RequestMapping("/disable")
    @ResponseBody
    public ResultVO disable(@RequestParam(value = "id") Integer id) {

        User user = userService.findOne(id);

        if (user.getStatus() == StatusEnum.STOP.getCode()) {
            user.setStatus(StatusEnum.START.getCode());
        } else {
            user.setStatus(StatusEnum.STOP.getCode());
        }
        User result = userService.sava(user);

        if (result == null) {
            return ResultVOUtils.error(400, ResultEnum.ACCOUNT_STATUS_EDIT_FAIL.getMessage());
        } else {
            return ResultVOUtils.success();
        }

    }




      
      

}
