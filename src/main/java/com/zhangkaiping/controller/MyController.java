package com.zhangkaiping.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.zhangkaiping.domain.Article;
import com.zhangkaiping.domain.ArticleEnum;
import com.zhangkaiping.domain.ArticleWithBLOBs;
import com.zhangkaiping.domain.Collect;
import com.zhangkaiping.domain.User;
import com.zhangkaiping.service.ArticleService;
import com.zhangkaiping.service.CollectService;
import com.zhangkaiping.util.Result;
import com.zhangkaiping.util.ResultUtil;
import com.zhangkaiping.vo.ArticleVO;

@RequestMapping("my")
@Controller
public class MyController {
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private CollectService collectService;//我的收藏
	
	@RequestMapping(value = {"","/","index"})
	public String index() {
		return "my/index";
	}
	
	
	
	
	
	
	
	@GetMapping("selectByUser")
	public String selectByUser(HttpServletRequest request,Model m,@RequestParam(defaultValue ="1" )Integer page,@RequestParam(defaultValue ="3" )Integer pageSize) {
		
		Article a = new Article();
		HttpSession session = request.getSession();
		if(session==null) {
			return "redirect:/passport/login";//session可能过期
		}
		User user = (User) session.getAttribute("user");
		a.setUserId(user.getId());
		PageInfo<Article> info = articleService.selects(a, page, pageSize);
		m.addAttribute("info",info);
		return "my/article/articles";
	}
	
	
	
	@GetMapping("publish")
	public String publish() {
		return "my/article/publish";
		
	}
	
	@ResponseBody
	@PostMapping("publish")
	public boolean publish(HttpServletRequest request,ArticleWithBLOBs article,MultipartFile file) throws Exception, IOException {
		if(!file.isEmpty()) {
			//文件上传路径
			String path = request.getSession().getServletContext().getRealPath("/resource/pic/");
			
			String oldFilename = file.getOriginalFilename();
			
			String newFilename =UUID.randomUUID()+oldFilename.substring(oldFilename.lastIndexOf("."));
			
			File f = new File(path,newFilename);
			
			try {
				file.transferTo(f);
				article.setPicture(newFilename);
			} catch (Exception e) {
				// TODO: handle exception
			}
	
		}
		//初始化设置
		article.setStatus(0);//待审核
		HttpSession session = request.getSession();
		if(session==null) {
			return false;
		}
		User user = (User) session.getAttribute("user");
		article.setUserId(user.getId());
		article.setHits(0);
		article.setHot(0);
		article.setDeleted(0);
		article.setCreated(new Date());
		article.setUpdated(new Date());
		
		article.setContentType(0);
		
		
		return articleService.insertSelective(article);
		
	}
	
	
	/**
	 * 
	 * @Title: collects 
	 * @Description: 我得收藏
	 * @return
	 * @return: String
	 */
	@GetMapping("collects")
	public String collects(HttpServletRequest request,Model m,@RequestParam(defaultValue ="1" )Integer page,@RequestParam(defaultValue ="3" )Integer pageSize) {
		HttpSession session = request.getSession(false);
		
		User user = (User) session.getAttribute("user");
		
		PageInfo<Collect> info = collectService.selects(page, pageSize, user);
		m.addAttribute("info",info);
		
		return "my/collect/collects";
	}
	
	/**
	 * 
	 * @Title: deleteCollect 
	 * @Description: 删除收藏
	 * @param id
	 * @return
	 * @return: Result<Collect>
	 */
	@ResponseBody
	@PostMapping("deleteCollect")
	public Result<Collect> deleteCollect(Integer id){
		collectService.deleteById(id);
		return ResultUtil.success();
	}
	
	
	/**
	 * 
	 * @Title: detail 
	 * @Description: 查询文章详情
	 * @param model
	 * @param id
	 * @return
	 * @return: String
	 */
	
	@RequestMapping("article")
	public String detail(Model model,Integer id) {
		ArticleWithBLOBs article = articleService.selectByPrimaryKey(id);
		model.addAttribute("article",article);
		return "my/article/article";
	}
	
	
	@GetMapping("publishpic")
	public String publishpic() {
		return "my/article/publishpic";
		
	}
	
	
	/**
	 * 
	 * @Title: publishpic 
	 * @Description: 发布图片
	 * @param request
	 * @param article
	 * @param file
	 * @return
	 * @throws Exception
	 * @throws IOException
	 * @return: boolean
	 */
	
	@ResponseBody
	@PostMapping("publishpic")
	public boolean publishpic(HttpServletRequest request,ArticleWithBLOBs article,MultipartFile[] files,String[] descr) throws Exception, IOException {
		String newFilename=null;
		ArrayList<ArticleVO> list = new ArrayList<ArticleVO>();//用来存放图片的地址和描述
		int i=0;
		for (MultipartFile file : files) {
			ArticleVO vo = new ArticleVO();
			if(!file.isEmpty()) {
				//文件上传路径
				String path = request.getSession().getServletContext().getRealPath("/resource/pic/");
				
				String oldFilename = file.getOriginalFilename();
				
				newFilename =UUID.randomUUID()+oldFilename.substring(oldFilename.lastIndexOf("."));
				
				File f = new File(path,newFilename);
				vo.setUrl(newFilename);
				vo.setDescr(descr[i]);
				i++;
				list.add(vo);
				try {
					file.transferTo(f);
				
				} catch (Exception e) {
					// TODO: handle exception
				}
		
			}

		}
		article.setPicture(newFilename);//标题图片
		Gson gson = new Gson();
		//使gson转json
		article.setContent(gson.toJson(list));
		
		//初始化设置
				article.setStatus(0);//待审核
				article.setUserId(160);
				article.setHits(0);
				article.setHot(0);
				article.setDeleted(0);
				article.setCreated(new Date());
				article.setUpdated(new Date());
				//图片集标识
				article.setContentType(ArticleEnum.IMAGE.getCode());
				
				
				
				
				return articleService.insertSelective(article);
	}
}
