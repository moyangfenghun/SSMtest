package ssm.test.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/SystemUser")
@Controller
public class SystemUserController {
	
	@RequestMapping("fileupload")
	@ResponseBody
	public String fileupload(HttpServletRequest request,MultipartFile file){
		String requestedSessionId = request.getRequestedSessionId();
		request.getSession();
		System.out.println(request.getServletContext());
		System.out.println(request.getServletContext().getRealPath(""));
		System.out.println(request.getServletContext().getContextPath());
		System.out.println(request.getServletContext().getServerInfo());
		System.out.println(request.getServletPath());
		
		System.out.println(request.getPathInfo());
		System.out.println(request.getPathTranslated());
		System.out.println(request.getContextPath());
		
		System.out.println(request.getScheme());
		System.out.println(request.getServerName());
		System.out.println(request.getServerPort());
		
		return "";
	}
}
