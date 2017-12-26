package com.tengyun.modules.sys.security;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.tengyun.modules.sys.menu.entity.Menu;
import com.tengyun.modules.sys.role.entity.Role;
import com.tengyun.modules.sys.role.service.RoleService;
import com.tengyun.modules.sys.user.entity.User;
import com.tengyun.modules.sys.user.service.UserService;

public class MyRealm extends AuthorizingRealm {
	
	
	@Autowired
	private UserService<User> userService;
	
	@Autowired 
	private RoleService<Role> roleService;
	
	/*  1、SecurityUtils.subject.hasRole(“admin”) 或 SecurityUtils.subject.isPermitted(“admin”)：自己去调用这个是否有什么角色或者是否有什么权限的时候
	2、@RequiresRoles("admin") ：在方法上加注解的时候；
	3、[@shiro.hasPermission name = "admin"][/@shiro.hasPermission]：在页面上加shiro标签的时候，即进这个页面的时候扫描到有这个标签的时候。1、subject.hasRole(“admin”) 或 subject.isPermitted(“admin”)：自己去调用这个是否有什么角色或者是否有什么权限的时候；
*/
	
	/**
	 *查权限函数 调用之后会在缓存中有某个角色有哪些权限
	 */
 	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
 		//获取登录信息
 		Principal principal = (Principal) getAvailablePrincipal(principals);
		String token = principal.getToken();

		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		
		//登录者的用户Id
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("userId", SecurityUtils.getSubject().getSession().getAttribute("userId"));
		
		//查询该用户的所有角色  将角色名放置SimpleAuthorizationInfo中
		List<Role> roleList = roleService.queryIdentity(param);
		if(roleList != null && roleList.size() > 0) {
			for(int i = 0; i < roleList.size(); i++) {
				authorizationInfo.addRole(roleList.get(i).getEnname());
			}	
		}
		
		//查询该用户的所有可访问的菜单 也就是页面地址 可以说是权限了吧
//		List<Menu> menuList = userService.queryMenu(param);
//		if(menuList != null && menuList.size() > 0) {
//			for(int i = 0; i < menuList.size(); i++) {
//				authorizationInfo.addStringPermission(menuList.get(i).getHref());
//			}
//		}		
				
//		JSONArray dataList = result.getData();
//		for (int i = 0, len = dataList.size(); i < len; i++) {
//			Object[] permissionParts = new Object[] { dataList.getJSONObject(i).get("type"),
//					dataList.getJSONObject(i).get("resourceId") };
//
//			authorizationInfo.addStringPermission(StringUtils.join(permissionParts, ":"));
//		}

		return authorizationInfo;
		
	}
	
	
	
	
	/**
	 * 登录时调用 判断当前登录请求是否可以
	 */

	//登录时调用
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		
		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
	   //调用service查询用户
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("loginName", usernamePasswordToken.getUsername());
		
		User user = userService.findUser(params);
		//返回用户后匹配密码 看是否正确
		if(user != null && user.getPassword().equals(new String(usernamePasswordToken.getPassword()))) {
			//记录当前用户的id
			SecurityUtils.getSubject().getSession().setAttribute("userId", user.getId());
			SecurityUtils.getSubject().getSession().setAttribute("loginName", user.getLoginName());
			SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
					new Principal(usernamePasswordToken.getUsername(), user.getToken().toString()),
					usernamePasswordToken.getCredentials(), getName());
			return authenticationInfo;
		}else {
			return null;//user为空或者密码不匹配返回空 抛异常认证失败 
		}
	}

}
