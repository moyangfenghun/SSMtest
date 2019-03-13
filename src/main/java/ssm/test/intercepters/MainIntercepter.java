package ssm.test.intercepters;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


public class MainIntercepter implements HandlerInterceptor  {
	
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO 视图渲染后
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO 处理请求之后
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// TODO 请求处理前
//		.info(request.getServletPath()+ "主拦截器pre..");
		
//		UserBean userinfo=(UserBean) request.getSession().getAttribute(LogindateServer.USER_Session);
//		if(userinfo==null||StringUtils.isEmpty(userinfo.getName())){
//
//			//没有session,判断cookie
//			if (logindateServer.logingVerifyCookies(response, request)) {
//				System.out.println("cooki登录..");
//				response.sendRedirect("/index");
//				return false;
//			}else{
//				response.sendRedirect("/login/loginRegister");
//				return false;
//			}
//		}
		
		return true;
	}

}
