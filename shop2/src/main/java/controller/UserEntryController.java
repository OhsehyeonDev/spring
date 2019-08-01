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
		return "userEntry"; // View �� ���� : /WEB-INF/view/userEntry.jsp �� ����.
	}

	@ModelAttribute
	public User getUser() {
		return new User();
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView userEntry(User user, BindingResult bindResult) {
		// user : user ��ü�� ������Ƽ�� �Ķ���� ������ ���Ͽ� �ڵ����� ���� �����. (������ ��ü������)
		ModelAndView mav = new ModelAndView(); // view ������ �� �Ǿ� �ִ� ��� URL�� userEntry.shop �̹Ƿ� userEntry ���� �⺻ ����.
		userValidator.validate(user, bindResult); // ��ȿ�� ����
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
	@InitBinder // �Ķ���� ���� ����ȯ�ϱ� ���� �޼���
	public void initBinder(WebDataBinder binder) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		// Date.class : ����ȯ ����� �Ǵ� �ڷ��� (birthDay)
		// format : ����ȯ�� ���� ���� ����
		// true/false : ���Է����/���Է� ��� ���� ����.
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format, false)); // false : Null �� ��� X
	}
}
