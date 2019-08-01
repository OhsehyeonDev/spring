package controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import logic.ShopService;
import logic.User;
import util.LoginValidator;

public class LoginController {
	private ShopService shopService;
	private LoginValidator loginValidator;
	
	public void setShopService(ShopService shopService) {
		this.shopService = shopService;
	}

	public void setLoginValidator(LoginValidator loginValidator) {
		this.loginValidator = loginValidator;
	}
	
	@GetMapping
	public String loginForm(Model model) { // Model model : view 로 전달할 데이터를 갖고 있는 객체
		model.addAttribute(new User());
		return "login";
	}
	
//	@RequestMapping(method = RequestMethod.GET)
//	@GetMapping // 4.0 버전 이후에서만 가능
//	public ModelAndView loginForm() {
//		ModelAndView mav = new ModelAndView();
//		mav.addObject(new User());
//		return mav;
//	}
	
//	@ModelAttribute
//	public User getUser() {
//		return new User();
//	}
	
//	@RequestMapping(method = RequestMethod.POST)
	@PostMapping // 4.0 버전 이후에서만 가능
	public ModelAndView login(User user, BindingResult bindResult, HttpSession session) {
		// user : user 객체의 프로퍼티와 파라미터 정보를 비교하여 자동으로 값이 저장됨. (스프링 자체적으로)
		ModelAndView mav = new ModelAndView(); // view 설정이 안 되어 있는 경우 URL이 userEntry.shop 이므로 userEntry 값이 기본 값임.
		loginValidator.validate(user, bindResult); // 유효성 검증
		if (bindResult.hasErrors()) {
			mav.getModel().putAll(bindResult.getModel());
			return mav;
		}
		try {
			// dbuser : 입력된 id값에 해당하는 db 사용자 정보 저장 객체
			// user : 입력된 id값과 비밀번호 값이 저장된 객체
			User dbuser = shopService.getUser(user.getUserId());
			if(user.getPassword().equals(dbuser.getPassword())) {
				session.setAttribute("loginUser", dbuser); // 로그인 정보 등록
			} else {
				bindResult.reject("error.login.password");
				mav.getModel().putAll(bindResult.getModel());
				return mav;
			}
		} catch(EmptyResultDataAccessException e) {
			// 조회 대상이 없을 때 발생되는 예외. Spring JDBC에서만 발생되는 예외.
			bindResult.reject("error.login.id"); // messages.txt 에 있는 error.login.id=아이디를 확인하세요.
			mav.getModel().putAll(bindResult.getModel());
			return mav;
		}
		mav.setViewName("loginSuccess");
		return mav;
	}
}
