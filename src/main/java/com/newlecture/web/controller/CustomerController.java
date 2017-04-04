package com.newlecture.web.controller;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.newlecture.web.dao.NoticeDao;
import com.newlecture.web.dao.NoticeFileDao;
import com.newlecture.web.data.entity.Notice;
import com.newlecture.web.data.entity.NoticeFile;
import com.newlecture.web.data.entity.NoticeView;


@Controller
@RequestMapping("/customer/*")
public class CustomerController {
	
	@Autowired
	private ServletContext context;
	
	@Autowired
	private NoticeDao noticeDao;
	
	@Autowired
	private NoticeFileDao noticeFileDao;	
	
	@RequestMapping("notice")
	public String notice(
			@RequestParam(value="p", defaultValue="1")String page,
			@RequestParam(value="f", defaultValue="TITLE")String field, 
			@RequestParam(value="q", defaultValue="")String query, Model model){	
		//return String.format("p:%s, f:%s, q:%s\n", page,field,query);	
		
		List<NoticeView> list =noticeDao.getList();
		model.addAttribute("list",list);
		
		return "customer.notice";
	}

	@RequestMapping("notice-detail")
	public String noticeDetail(
			@RequestParam(value="c", defaultValue="1")String code,Model model){

		NoticeView n= noticeDao.get(code);
		NoticeView prev= noticeDao.getPrev(code);
		NoticeView next= noticeDao.getNext(code);

		List<NoticeFile> list = noticeFileDao.getList(n.getCode());
		
		//response.sendRedirect(""); //현재하는 작업을 취소하고 새로운 서블릿을 요청한다.
		model.addAttribute("n", n);
		model.addAttribute("prev", prev);
		model.addAttribute("next", next);
		model.addAttribute("list", list);
		
		return "customer.notice-detail";
	}
	
	@RequestMapping(value="notice-reg", method= RequestMethod.GET)
	public String noticeReg(){
		
		return "/WEB-INF/views/customer/notice-reg.jsp";
	}
	
	@RequestMapping(value="notice-reg", method= RequestMethod.POST, produces="text/txt; charset=UTF-8")
	@ResponseBody
	public String noticeReg(
			Notice notice,
			@RequestParam(value="files") List<MultipartFile> files) throws IOException{
		
		String path = context.getRealPath("/resource/upload");
		
		File d = new File(path);
		if(!d.exists())
			d.mkdir();
		
		byte[] buf= new byte[1024];
		
		for(MultipartFile file :files){
			if(!file.isEmpty())
				{
				String fileName = file.getOriginalFilename();
				InputStream fis=file.getInputStream();
				OutputStream fos=new FileOutputStream(path+File.separator+fileName);
				
				int len = 0;
			
				while((len=fis.read(buf))>0)
					fos.write(buf, 0, len);
				
				fis.close();
				fos.close();
		}
		}


		notice.setWriter("newlec");
		noticeDao.add(notice);
		
		for(MultipartFile file : files){
			String fileName = file.getOriginalFilename();
			NoticeFile f = new NoticeFile();
			f.setNoticeCode(noticeDao.lastCode());
			f.setSrc(fileName);
			noticeFileDao.add(f);
		}
		return "redirect:notice";
		
	}
	
	@RequestMapping("notice-del")
	public String noticeDao(@RequestParam("c") String code){
	
		/*	File f= new File("");
		f.delete();*/
		
		noticeDao.delete(code);
		
		//파일 정보를 얻기
		
		
		return "redirect:notice";
	}
	
	@RequestMapping(value="notice-edit", method=RequestMethod.GET)
	public String noticeEdit(@RequestParam("c") String code, Model model){

		model.addAttribute("n",noticeDao.get(code));
		System.out.println("-------");
		
		return "/WEB-INF/views/customer/notice-edit.jsp";
	}
	@RequestMapping(value="notice-edit", method=RequestMethod.POST)
	public String noticeEdit(String title, String content, String code){

		Notice notice= noticeDao.get(code);
		notice.setTitle(title);
		notice.setContent(content);
		
		noticeDao.update(notice);
		
		System.out.println("!!!!!!!!!!!!!");

		return "redirect:notice-detail?c="+notice.getCode();
	}
	
	
	
}
