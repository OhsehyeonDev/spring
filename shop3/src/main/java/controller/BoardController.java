package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import exception.LogInException;
import exception.ShopException;
import logic.Board;
import logic.CKEditor;
import logic.ShopService;

@Controller
@RequestMapping("board")
public class BoardController {
	@Autowired
	private ShopService service;
	
	@RequestMapping("list")
	public ModelAndView list(Integer pageNum) {
		ModelAndView mav = new ModelAndView();
		if(pageNum == null || pageNum.toString().equals(""))
			 pageNum = 1;
		int limit = 10; //���������� ��µǴ� �Խù� ����
		int listcount = service.boardcount(); //��ϵ� ��ü �Խù� ����
		//���������� ��µ� �Խù� ����
		List<Board> boardlist = service.boardlist(pageNum, limit);
		//��ü ������ ��
		int maxpage = (int)((double)listcount/limit + 0.95);
		//ȭ�鿡 ��µ� ���� ������
		int startpage = (int)((pageNum / 10.0 + 0.9) - 1 ) * 10 + 1;
		//ȭ�鿡 ��µ� �� ������
		int endpage = startpage + 9;
		if(endpage > maxpage) endpage = maxpage;
		//��µǴ� ����
		int boardno = listcount - (pageNum - 1) * limit;
		mav.addObject("pageNum",pageNum);
		mav.addObject("maxpage",maxpage);
		mav.addObject("startpage",startpage);
		mav.addObject("endpage",endpage);
		mav.addObject("listcount",listcount);
		mav.addObject("boardlist",boardlist);
		mav.addObject("boardno",boardno);
		return mav;
	}
	@GetMapping("*")
	public ModelAndView getBoard
	         (Integer num,HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		Board board = new Board();
		if(num != null) {
			board = service.getBoard(num,request);
		}
		mav.addObject("board",board);
		return mav;
	}
	@PostMapping("write")
	public ModelAndView write(@Valid Board board,BindingResult br, 
    		    HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		if(br.hasErrors()) {	return mav;		}
		try {
			service.boardWrite(board,request);
			mav.setViewName("redirect:list.shop");
		} catch (Exception e) {
			e.printStackTrace();
			throw new ShopException
			      ("�Խù� ��Ͽ� ���� �߽��ϴ�.","write.shop");
		}
		return mav;
	}
	
	@PostMapping("reply")
	public ModelAndView reply (@Valid Board board,BindingResult br,
			HttpServletRequest request ){
		ModelAndView mav = new ModelAndView();
		if(br.hasErrors()) {
			Board dbBoard = 
					   service.getBoard(board.getNum(), request);
			Map<String, Object> map = br.getModel();
			Board b = (Board)map.get("board");
			b.setSubject(dbBoard.getSubject());
			return mav;
		}
		try {
			service.boardReply(board);
			mav.setViewName("redirect:list.shop");
		} catch (Exception e) {
			e.printStackTrace();
			throw new ShopException
			      ("��� ��Ͽ� ���� �߽��ϴ�.","reply.shop");
		}
		return mav;
	}	
	@RequestMapping("imgupload")
	public String imgupload	(MultipartFile upload,
		String CKEditorFuncNum,HttpServletRequest request,
		Model model) {
	    System.out.println(CKEditorFuncNum);
		String path = request.getSession().
			getServletContext().getRealPath("/") + "board/imgfile/";
		File f = new File(path);
		if(!f.exists()) f.mkdirs();
		if (!upload.isEmpty()) {
		   File file = new File(path, upload.getOriginalFilename());
		   try {
			    upload.transferTo(file);
		   } catch (Exception e) {  
		      e.printStackTrace();
		   }
		 }
	  String fileName = 
			  "/shop3/board/imgfile/"+upload.getOriginalFilename();
	  model.addAttribute("fileName", fileName);
	  model.addAttribute("CKEditorFuncNum", CKEditorFuncNum);
	  return "ckeditor";
	}
	@PostMapping("update")
	public ModelAndView update (@Valid Board board,BindingResult br,
			HttpServletRequest request ){
		ModelAndView mav = new ModelAndView();
		Board dbBoard = 
				   service.getBoard(board.getNum(), request);
		if(br.hasErrors()) {
			return mav;
		}
		if(!board.getPass().equals(dbBoard.getPass())) {
			throw new ShopException("��й�ȣ�� Ʋ���ϴ�.", "update.shop?num="
																+board.getNum());
		}
		try {
			service.boardUpdate(board, request);
			mav.setViewName("redirect:list.shop");
		} catch (Exception e) {
			e.printStackTrace();
			throw new ShopException
			      ("��� ��Ͽ� ���� �߽��ϴ�.","update.shop?num="+board.getNum());
		}
		return mav;
	}	
	@PostMapping("delete")
	public ModelAndView delete(Board board, BindingResult br,
			                                   HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		Board dbboard = service.getBoard(board.getNum(), request);
		if(board.getPass() == null || board.getPass().trim().equals("")
						|| !board.getPass().equals(dbboard.getPass())) {
			br.reject("error.login.password");
			return mav;
		}
		if(board.getPass().equals(dbboard.getPass())) {
			try {
				service.boardDelete(board);
				mav.addObject("msg","��������");
				mav.addObject("url","list.shop");
				mav.setViewName("alert");
				return mav;				
			}catch (Exception e) {
				e.printStackTrace();
				throw new LogInException("����", "delete.shop?num="+board.getNum());
			}
		}
		return mav;
	}
}















