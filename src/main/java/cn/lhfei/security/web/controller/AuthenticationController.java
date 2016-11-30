package cn.lhfei.security.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Response.Status;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.lhfei.security.web.model.AbstractAccount;
import cn.lhfei.security.web.model.BasicAccountInfo;
import cn.lhfei.security.web.model.JsonResponse;
import cn.lhfei.security.web.model.UserModel;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/authc")
public class AuthenticationController {

	private static final String RETURN_URL_PARAM_NAME = "url";
	private static final String KEY_ACCOUNT = "accountInfo";
	private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);

	@RequestMapping("/forward/{view}")
	public ModelAndView forward(@PathVariable("view") String viewName,
			@RequestParam(value = "message", required = false) String message) {
        ModelAndView view = new ModelAndView(viewName);
        log.info("Forward to {}", viewName);
        view.addObject("message", message);

        return view;
    }
	
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String index(HttpServletRequest request) {
		String url = "";

		log.info("Forward to /login. ");
		try {

			String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
			url = WebUtils.getAndClearSavedRequest(request).getRequestUrl();
			url = basePath + url;

			log.info("FORWARD_REQUEST_URI_ATTRIBUTE = {}", url);

			return "redirect: login?" + RETURN_URL_PARAM_NAME + "=" + url;

		} catch (Exception e) {
			log.warn("Forward to [/login] failed. {}", e.getMessage(), e);
			
			return "redirect: login";
		}
	}

	@RequestMapping(value = "/login")
	public ModelAndView login(HttpServletRequest request) {
		ModelAndView view = new ModelAndView("login");
		String redirectUrl = request.getParameter(RETURN_URL_PARAM_NAME);

		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
		try {
			redirectUrl = WebUtils.getAndClearSavedRequest(request).getRequestUrl();
			redirectUrl = basePath + redirectUrl;
			
		} catch (Exception e) {
			log.warn("Forward to [/login] failed. {}", e.getMessage(), e);
		}
		
		view.addObject(RETURN_URL_PARAM_NAME, redirectUrl);

		log.info(redirectUrl);

		return view;
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(@ModelAttribute("userForm") UserModel user, HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session) {
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
		String url = user.getUrl();
		try {
			SecurityUtils.getSubject().login(token);
			log.info("{}", user.toString());
			
			if (null != url && user.getUrl().length() > 0) {
				return "redirect:" + url;
			} else
				return "main";

		} catch (AuthenticationException e) {
			log.warn("error.invalidLogin. {}", "The username or password was not correct.", e.getMessage(), e);

			return "redirect:index/" + user.getUrl();
		}

	}
	
	@RequestMapping("/user")
	public @ResponseBody JsonResponse<? extends AbstractAccount> getUser() {
		Session session = SecurityUtils.getSubject().getSession();
		
		BasicAccountInfo accountInfo = (BasicAccountInfo) session.getAttribute(KEY_ACCOUNT);
		
		return new JsonResponse<BasicAccountInfo>(Status.OK, "", accountInfo);
	}

    @RequestMapping("/logout")
    public String logout() {
        SecurityUtils.getSubject().logout();
        return "redirect:index";
    }
    
}
