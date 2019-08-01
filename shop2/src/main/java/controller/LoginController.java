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
	public String loginForm(Model model) { // Model model : view �� ������ �����͸� ���� �ִ� ��ü
		model.addAttribute(new User());
		return "login";
	}
	
//	@RequestMapping(method = RequestMethod.GET)
//	@GetMapping // 4.0 ���� ���Ŀ����� ����
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
	@PostMapping // 4.0 ���� ���Ŀ����� ����
	public ModelAndView login(User user, BindingResult bindResult, HttpSession session) {
		// user : user ��ü�� ������Ƽ�� �Ķ���� ������ ���Ͽ� �ڵ����� ���� �����. (������ ��ü������)
		ModelAndView mav = new ModelAndView(); // view ������ �� �Ǿ� �ִ� ��� URL�� userEntry.shop �̹Ƿ� userEntry ���� �⺻ ����.
		loginValidator.validate(user, bindResult); // ��ȿ�� ����
		if (bindResult.hasErrors()) {
			mav.getModel().putAll(bindResult.getModel());
			return mav;
		}
		try {
			// dbuser : �Էµ� id���� �ش��ϴ� db ����� ���� ���� ��ü
			// user : �Էµ� id���� ��й�ȣ ���� ����� ��ü
			User dbuser = shopService.getUser(user.getUserId());
			if(user.getPassword().equals(dbuser.getPassword())) {
				session.setAttribute("loginUser", dbuser); // �α��� ���� ���
			} else {
				bindResult.reject("error.login.password");
				mav.getModel().putAll(bindResult.getModel());
				return mav;
			}
		} catch(EmptyResultDataAccessException e) {
			// ��ȸ ����� ���� �� �߻��Ǵ� ����. Spring JDBC������ �߻��Ǵ� ����.
			bindResult.reject("error.login.id"); // messages.txt �� �ִ� error.login.id=���̵� Ȯ���ϼ���.
			mav.getModel().putAll(bindResult.getModel());
			return mav;
		}
		mav.setViewName("loginSuccess");
		return mav;
	}
}
