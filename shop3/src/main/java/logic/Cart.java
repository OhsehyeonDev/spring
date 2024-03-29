package logic;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

public class Cart {
	private List<ItemSet> itemSetList = new ArrayList<ItemSet>();
	public List<ItemSet> getItemSetList() {
		return itemSetList;
	}
	public void push(ItemSet itemSet) {
		for(ItemSet is : itemSetList) {
		  if(is.getItem().getId() == itemSet.getItem().getId()) {
			is.setQuantity(is.getQuantity() + itemSet.getQuantity());
			return;
		  }
		}
		itemSetList.add(itemSet);
	}
	public boolean isEmpty() {
		return itemSetList == null || itemSetList.size() == 0;
	}
	public long getTotal() {
		long sum = 0;
		for(ItemSet is : itemSetList) {
			sum += is.getItem().getPrice() * is.getQuantity();
		}
		return sum;
	}
	public void clearAll(HttpSession session) {
		itemSetList.clear();
		session.setAttribute("CART", this);
	}
}
