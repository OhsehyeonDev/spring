package controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import logic.Item;
import logic.ShopService;

public class DetailController {
	private ShopService shopService;
	public void setShopService(ShopService shopService) {
		this.shopService = shopService;
	}
	// id : 파라미터 id 값이 저장되어 detailItem() 메서드가 호출됨.
	@RequestMapping
	public ModelAndView detailItem(Integer id) {
		Item item = shopService.getItemById(id);
		// WEP-INF/view/detail.jsp 로 뷰를 선택
		ModelAndView mav = new ModelAndView("detail"); // 뷰 이름 설정
		mav.addObject("item",item);
		return mav;
		
	}
}
