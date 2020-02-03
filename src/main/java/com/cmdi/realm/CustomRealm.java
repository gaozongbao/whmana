package com.cmdi.realm;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.junit.experimental.theories.Theories;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomRealm extends AuthorizingRealm {

	/* (non-Javadoc)
	 * @see org.apache.shiro.realm.AuthorizingRealm#doGetAuthorizationInfo(org.apache.shiro.subject.PrincipalCollection)
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection pc) {
		// TODO Auto-generated method stub
		//得到doGetAuthenticationInfo返回的SimpleAuthenticationInfo的principal
		String username = (String) pc.getPrimaryPrincipal();
		System.out.println("auth username = " + username);
		//可以从数据库中得到用户的权限，假设所有权限为aaa:bbb,z:x
		ArrayList<String> perms = new ArrayList<String>();
		perms.add("aaa:bbb");
		perms.add("z:x");
		//返回授权信息
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		simpleAuthorizationInfo.addStringPermissions(perms);
		return simpleAuthorizationInfo;
	}

	/* (non-Javadoc)
	 * @see org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken)
	 */
	////没有加md5校验
//	@Override
//	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
//		// TODO Auto-generated method stub
//		System.out.println("进来");
//		String principal = (String) token.getPrincipal();
//		System.out.println("principal = " + principal);
//		//可以自动注入service后查询数据库，用户是否存在，密码，菜单，权限列表等
//		String realPass = "111";
//		//principal可以是一个类
//		return new SimpleAuthenticationInfo(principal, realPass, this.getName());
//		//如果返回null，表示账号找不到
//	}
	
	//加md5校验
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		System.out.println("进来");
		String principal = (String) token.getPrincipal();
		System.out.println("principal = " + principal);
		//可以自动注入service后查询数据库，用户是否存在，密码，菜单，权限列表等
		//默认密码为admin1234!
		String realPass = "61b0a605958bcdb2f3f95574127143ef";
		String salt = "salt";
		//principal可以是一个类
		return new SimpleAuthenticationInfo(principal, realPass, ByteSource.Util.bytes(salt), this.getName());
		//return new SimpleAuthenticationInfo(principal, realPass, this.getName());
		//如果返回null，表示账号找不到
	}
	
	//手动清空缓存，需要在controller中调用
	//对于授权信息，如果调用这个方法，那么所有的授权信息将会清空，重新执行doGetAuthorizationInfo方法
	public void clearCached() {
		PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
		super.clearCache(principals);
	}


}
