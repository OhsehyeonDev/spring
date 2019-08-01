package logic;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import dao.BoardDao;
import dao.ItemDao;
import dao.SaleDao;
import dao.SaleItemDao;
import dao.UserDao;

@Service
public class ShopService {
	@Autowired
	private ItemDao itemDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private SaleDao saleDao;
	@Autowired
	private SaleItemDao saleItemDao;
	@Autowired
	private BoardDao boardDao;
	
	public List<Item> getItemList() {
		return itemDao.list();
	}

	public Item getItemById(Integer id) {
		return itemDao.selectOne(id);
	}

	public void itemCreate(Item item, HttpServletRequest request) {
		if(item.getPicture() != null && !item.getPicture().isEmpty()) {
			uploadFileCreate(item.getPicture(),request,"item/img/");
			item.setPictureUrl(item.getPicture().getOriginalFilename());
		}
		itemDao.insert(item);
	}
	private void uploadFileCreate (MultipartFile picture, 
			           HttpServletRequest request, String path) {
		// 업로드된 실제 파일의 이름
		String orgFile = picture.getOriginalFilename();
		String uploadPath = request.getServletContext().getRealPath("/")
				+ path;
		
		File fpath = new File(uploadPath);
		if(!fpath.exists()) fpath.mkdirs();
		
		try {
			//업로드 파일 생성하기
			picture.transferTo(new File(uploadPath + orgFile));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void itemUpdate(Item item, HttpServletRequest request) {
		if(item.getPicture() != null && !item.getPicture().isEmpty()) {
			uploadFileCreate(item.getPicture(),request,"item/img/");
			item.setPictureUrl(item.getPicture().getOriginalFilename());
		}
		itemDao.update(item);
	}

	public void itemDelete(Integer id) {
		itemDao.delete(id);
	}

	public void userCreate(User user) {
		userDao.insert(user);
	}

	public User userSelect(String userId) {
		
		return userDao.selectOne(userId);
	}

	public Sale checkEnd(User loginUser, Cart cart) {
		Sale sale = new Sale();
		sale.setSaleId(saleDao.getMaxSaleId());
		sale.setUser(loginUser);
		sale.setUpdatetime(new Date());
		List<ItemSet> itemList = cart.getItemSetList();
		int i = 0;
		for(ItemSet is : itemList) {
			int saleItemId = ++i;
			SaleItem saleItem = new SaleItem
					(sale.getSaleId(),saleItemId,is);
			sale.getItemList().add(saleItem);
		}
		saleDao.insert(sale);
		List<SaleItem> saleItemList = sale.getItemList();
		for(SaleItem si : saleItemList) {
			saleItemDao.insert(si);
		}
		return sale;
	}

	public List<Sale> salelist(String id) {
		return saleDao.list(id);
	}

	public List<SaleItem> saleItemList(int saleId) {
		return saleItemDao.list(saleId);
	}

	public void update(User user) {
		userDao.update(user);
	}

	public void userDelete(User user) {
		userDao.delete(user);		
	}

	public List<User> userList() {
		return userDao.list();
	}

	public List<User> userList(String[] idchks) {
		return userDao.list(idchks);
	}

	public int boardcount() {
		return boardDao.count();
	}

	public List<Board> boardlist(int pageNum, int limit) {
		return boardDao.list(pageNum,limit);
	}

	public Board getBoard(Integer num, HttpServletRequest request) {
		if (request.getRequestURI().contains("detail")) {
			boardDao.readcntadd(num);
		}
		return boardDao.selectone(num);
	}

	public void boardWrite(Board board, HttpServletRequest request) {
		if(board.getFile1() != null && !board.getFile1().isEmpty()) {
		  uploadFileCreate(board.getFile1(),
					             request, "board/file/");
		  board.setFileurl(board.getFile1().getOriginalFilename());
		}
		int max = boardDao.maxNum();
		board.setNum(++max);
		board.setRef(max);
		boardDao.insert(board);
	}

	public void boardReply(Board board) {
		boardDao.updateRefStep(board);
		int max = boardDao.maxNum();
		board.setNum(++max);
		board.setReflevel(board.getReflevel() + 1);
		board.setRefstep(board.getRefstep() + 1);
		boardDao.insert(board);
	}

	public void boardUpdate
	   (Board board, HttpServletRequest request) {
		if(board.getFile1() != null && !board.getFile1().isEmpty()) {
			  uploadFileCreate(board.getFile1(),
						             request, "board/file/");
			  board.setFileurl(board.getFile1().getOriginalFilename());
			}
		boardDao.update(board);
	}

	public void boardDelete(Board board) {
		boardDao.delete(board);
		
	}	
}
