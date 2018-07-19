package com.ssm.controller;

import com.ssm.bean.mysql.LoginResult;
import com.ssm.bean.mysql.User;
import com.ssm.service.mysql.ModuleService;
import com.ssm.service.mysql.PermissionService;
import com.ssm.service.mysql.RoleService;
import com.ssm.service.mysql.UserService;
import com.ssm.util.IpUtils;
import com.ssm.util.PasswordEncoder;
import com.ssm.util.ToWebToken;
import com.ssm.util.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@CrossOrigin
@RequestMapping(value = "/user", name = "用户登录模块")
public class LoginContriller {
    @Autowired
    private UserService userSerivice;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private ModuleService moduleService;
    @Autowired
    Token token;

    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping("/login")
    public LoginResult login(String username, String password, HttpServletRequest request) throws UnsupportedEncodingException {
        PasswordEncoder passwordEncoder = new PasswordEncoder("hmb", "MD5");
        password = passwordEncoder.encode(password, 5);
        System.out.println(password);
        User user = userSerivice.selectUserByNameAndPassWord(username, password);
        LoginResult loginResult = new LoginResult();
        if (user == null) {
            int n = userSerivice.updateLoginTimeByUsername(username);
            loginResult.setCode(104);
            loginResult.setMsg("用户不存在");
            return loginResult;
        }
        if (user.getIslockout().equals("是")) {
            loginResult.setMsg("登录失败，您的用户名已被锁定，请联系管理员。QQ：1076718373");
            loginResult.setCode(106);
            return loginResult;
        }
        int m = userSerivice.udateUserIPAndTimeByUsername(username, new Date(), IpUtils.getRemoteHost(request));
        Integer userId = user.getId();
        loginResult.setUserId(userId);
        loginResult.setUserName(user.getLoginname());
        loginResult.setMsg("登录成功");
        loginResult.setCode(100);
        List<Integer> rolesId = roleService.selectRoleIdByUserId(user.getId());
        String roles = roleService.selectRoleByUserId(userId);
        loginResult.setRoles(rolesId);
        loginResult.setModules(moduleService.selectModuleIdByRolesId(userId));
        List<String> permissions = permissionService.selectPermissionByUserId(userId);
        ToWebToken toWebToken = new ToWebToken(userId, roles, permissions, user.getLoginname());

        String token = this.token.createToken(toWebToken, 10 * 60 * 60 * 1000);
        loginResult.setToken(token);
        redisTemplate.opsForValue().set(user.getLoginname(), token, 4, TimeUnit.HOURS);
        ToWebToken object = this.token.getObject(ToWebToken.class, token);
        return loginResult;
    }
}
