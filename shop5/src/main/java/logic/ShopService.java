package logic;

import java.io.File;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
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

	private static byte[] randomKey;
	// 초기화 백터 : iv
	private final static byte[] iv = new byte[] { (byte) 0x8E, 0x12, 0x39, (byte) 0x9C, 0x07, 0x72, 0x6F, (byte) 0x5A,
			(byte) 0x8E, 0x12, 0x39, (byte) 0x9C, 0x07, 0x72, 0x6F, (byte) 0x5A };
	static Cipher cipher;
	static { // 초기화 부분
		try {
			cipher = Cipher.getInstance("AES/CBC/PKCS5Padding"); // AES 알고리즘, CBC 모드, PKCS5Padding 패딩방식
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static AlgorithmParameterSpec paramSpec = new IvParameterSpec(iv);

	public List<Item> getItemList() {
		return itemDao.list();
	}

	public Item getItemById(Integer id) {
		return itemDao.selectOne(id);
	}

	public void itemCreate(Item item, HttpServletRequest request) {
		if (item.getPicture() != null && !item.getPicture().isEmpty()) {
			uploadFileCreate(item.getPicture(), request, "item/img/");
			item.setPictureUrl(item.getPicture().getOriginalFilename());
		}
		itemDao.insert(item);
	}

	private void uploadFileCreate(MultipartFile picture, HttpServletRequest request, String path) {
		// 업로드된 실제 파일의 이름
		String orgFile = picture.getOriginalFilename();
		String uploadPath = request.getServletContext().getRealPath("/") + path;

		File fpath = new File(uploadPath);
		if (!fpath.exists())
			fpath.mkdirs();

		try {
			// 업로드 파일 생성하기
			picture.transferTo(new File(uploadPath + orgFile));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void itemUpdate(Item item, HttpServletRequest request) {
		if (item.getPicture() != null && !item.getPicture().isEmpty()) {
			uploadFileCreate(item.getPicture(), request, "item/img/");
			item.setPictureUrl(item.getPicture().getOriginalFilename());
		}
		itemDao.update(item);
	}

	public void itemDelete(Integer id) {
		itemDao.delete(id);
	}

	public void userCreate(User user) {
		String password = messageDigest(user.getPassword());
		user.setPassword(password);
		user.setEmail(encrypt(user.getEmail(), user.getPassword()));
		userDao.insert(user);
	}

	public String messageDigest(String password) { // 암호화 부분
		byte[] plain = password.getBytes();
		byte[] hash = null;
		String result = "";
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
			hash = md.digest(plain);
			for (byte b : hash) {
				result += String.format("%02X", b);
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return result;
	}

	public User userSelect(String userId) { // user 전체 부분
		User user = userDao.selectOne(userId);
		if(user != null) {
			user.setEmail(decrypt(user.getEmail(), user.getPassword()));
		}
		return user;
	}

	public Sale checkEnd(User loginUser, Cart cart) {
		Sale sale = new Sale();
		sale.setSaleId(saleDao.getMaxSaleId());
		sale.setUser(loginUser);
		sale.setUpdatetime(new Date());
		List<ItemSet> itemList = cart.getItemSetList();
		int i = 0;
		for (ItemSet is : itemList) {
			int saleItemId = ++i;
			SaleItem saleItem = new SaleItem(sale.getSaleId(), saleItemId, is);
			sale.getItemList().add(saleItem);
		}
		saleDao.insert(sale);
		List<SaleItem> saleItemList = sale.getItemList();
		for (SaleItem si : saleItemList) {
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
		user.setEmail(encrypt(user.getEmail(), user.getPassword()));
		userDao.update(user);
	}

	public void userDelete(User user) {
		userDao.delete(user);
	}

	public List<User> userList() { // 회원 목록
		List<User> list = userDao.list();
		for (User u : list) {
			u.setEmail(decrypt(u.getEmail(), u.getPassword()));
		}
		return list;
	}

	public List<User> userList(String[] idchks) { // 이메일 폼 형식
		List<User> list = userDao.list(idchks);
		for (User u : list) {
			u.setEmail(decrypt(u.getEmail(), u.getPassword()));
		}
		return list;
	}

	private String decrypt(String cipherMsg, String password) {
		byte[] plainMsg = new byte[1024];
		try {
			Key genkey = new SecretKeySpec(password.substring(0, 16).getBytes(), "AES");
			cipher.init(Cipher.DECRYPT_MODE, genkey, paramSpec);
			plainMsg = cipher.doFinal(hexToByte(cipherMsg.trim()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new String(plainMsg).trim();
	}

	private static byte[] hexToByte(String str) {
		if (str == null) {
			return null;
		}
		if (str.length() < 2)
			return null;
		int len = str.length() / 2;
		byte[] buf = new byte[len];
		for (int i = 0; i < len; i++) {
			buf[i] = (byte) Integer.parseInt(str.substring(i * 2, i * 2 + 2), 16);
		}

		return buf;
	}
	public static String encrypt(String plain, String password) {
		byte[] cipherMsg = new byte[1024];
		try {
			Key genKey = new SecretKeySpec(password.substring(0, 16).getBytes(), "AES");
			cipher.init(Cipher.ENCRYPT_MODE, genKey, paramSpec);
			cipherMsg = cipher.doFinal(plain.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return byteToHex(cipherMsg);
	}

	private static String byteToHex(byte[] cipherMsg) {
		if (cipherMsg == null)
			return null;
		String str = "";
		for (byte b : cipherMsg) {
			str += String.format("%02X", b);
		}
		return str;
	}

	public int boardcount(String searchtype, String searchcontent) {
		return boardDao.count(searchtype, searchcontent);
	}

	public List<Board> boardlist(int pageNum, int limit, String searchtype, String searchcontent) {
		return boardDao.list(pageNum, limit, searchtype, searchcontent);
	}

	public Board getBoard(Integer num, HttpServletRequest request) {
		if (request.getRequestURI().contains("detail")) {
			boardDao.readcntadd(num);
		}
		return boardDao.selectone(num);
	}

	public void boardWrite(Board board, HttpServletRequest request) {
		if (board.getFile1() != null && !board.getFile1().isEmpty()) {
			uploadFileCreate(board.getFile1(), request, "board/file/");
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

	public void boardUpdate(Board board, HttpServletRequest request) {
		if (board.getFile1() != null && !board.getFile1().isEmpty()) {
			uploadFileCreate(board.getFile1(), request, "board/file/");
			board.setFileurl(board.getFile1().getOriginalFilename());
		}
		boardDao.update(board);
	}

	public void boardDelete(Board board) {
		boardDao.delete(board);

	}

	public Map<String, Object> graph() {
		Map<String, Object> map = new HashMap<String, Object>();
		for (Map<String, Object> m : boardDao.graph()) {
			map.put((String) m.get("key1"), m.get("value1"));
		}
		return map;
	}
}
