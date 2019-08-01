package controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import logic.Item;
import logic.ShopService;
// url : index.shop ��û�� ȣ��Ǵ� Controller Ŭ����
// 		IndexController.itemList() �޼��� ȣ��
public class IndexController {
	private ShopService shopService;
	public void setShopService(ShopService shopService) {
		this.shopService = shopService;
	}
	@RequestMapping // ��û�� ȣ��Ǵ� �޼���
	public ModelAndView itemList() {
		List<Item> itemList = shopService.getItemList();
		ModelAndView mav = new ModelAndView("index");
		mav.addObject("itemList",itemList);
		return mav;
	}

}
