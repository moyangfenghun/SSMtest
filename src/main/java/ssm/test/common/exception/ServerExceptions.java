package ssm.test.common.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import ssm.test.utils.LoggersUtils;

@Component
public class ServerExceptions implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception e) {
		// TODO Auto-generated method stub
		LoggersUtils.LOGGER.info(request.getServletPath());
		ModelAndView mv=new ModelAndView();
		e.printStackTrace();
		if (e instanceof UnauthorizedException) {
            mv = new ModelAndView("error/403");
            return mv;
        }
//		mv.addObject("status", 500);
//		mv.addObject("message", "服务器错误，请稍后重试或者联系管理员");
		
		mv.setViewName("error/other");
		return mv;
	}

}
