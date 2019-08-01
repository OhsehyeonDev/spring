package controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import logic.ShopService;
import logic.User;
import util.UserValidator;

public class UserEntryController {
	private ShopService shopService;
	private UserValidator userValidator;

	public void setShopService(ShopService shopService) {
		this.shopService = shopService;
	}

	public void setUserValidator(UserValidator userValidator) {
		this.userValidator = userValidator;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String userEntryForm() {
		return "userEntry"; // View 만 리턴 : /WEB-INF/view/userEntry.jsp 가 뷰임.
	}

	@ModelAttribute
	public User getUser() {
		return new User();
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView userEntry(User user, BindingResult bindResult) {
		// user : user 객체의 프로퍼티와 파라미터 정보를 비교하여 자동으로 값이 저장됨. (스프링 자체적으로)
		ModelAndView mav = new ModelAndView(); // view 설정이 안 되어 있는 경우 URL이 userEntry.shop 이므로 userEntry 값이 기본 값임.
		userValidator.validate(user, bindResult); // 유효성 검증
		if (bindResult.hasErrors()) {
			mav.getModel().putAll(bindResult.getModel());
			return mav;
		}
		try {
			shopService.insertUser(user);
			mav.addObject("user", user);
		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			bindResult.reject("error.duplicate.user");
			mav.getModel().putAll(bindResult.getModel());
			return mav;
		}
		mav.setViewName("userEntrySuccess");
		return mav;
	}
	@InitBinder // 파라미터 값을 형변환하기 위한 메서드
	public void initBinder(WebDataBinder binder) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		// Date.class : 형변환 대상이 되는 자료형 (birthDay)
		// format : 형변환을 위한 형식 지정
		// true/false : 비입력허용/비입력 허용 하지 않음.
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format, false)); // false : Null 값 허용 X
	}
}
