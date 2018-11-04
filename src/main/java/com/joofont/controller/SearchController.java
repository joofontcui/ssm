package com.joofont.controller;

import com.joofont.entity.User;
import com.joofont.service.UserService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author cui jun on 2018/11/4.
 * @version 1.0
 */
@Controller
@RequestMapping("/lucene")
public class SearchController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/add")
    @ResponseBody
    public String addUser() throws Exception {
        List<User> userList = userService.getAllUserList();

        if(CollectionUtils.isNotEmpty(userList)) {
            for(User user : userList) {
                userService.addIndex(user);
            }
        }

        return "写入成功";
    }

    @GetMapping("/search")
    public String search(@RequestParam(value = "q", required = false,defaultValue = "") String q,
                         @RequestParam(value = "page", required = false, defaultValue = "1") String page,
                         Model model,
                         HttpServletRequest request) throws Exception {
        List<User> userList = userService.searchBlog(q);

        Integer toIndex = userList.size() >= Integer.parseInt(page) * 5 ? Integer.parseInt(page) * 5 : userList.size();
        List<User> newList = userList.subList((Integer.parseInt(page) - 1) * 5, toIndex);
        model.addAttribute("userList",newList) ;
        String s = genUpAndDownPageCode(Integer.parseInt(page), userList.size(), q, 5, request.getServletContext().
                getContextPath());
        model.addAttribute("pageHtml",s) ;
        model.addAttribute("q",q) ;
        model.addAttribute("resultTotal",userList.size()) ;
        model.addAttribute("pageTitle","搜索关键字'" + q + "'结果页面") ;
        return "queryResult";
    }

    private String genUpAndDownPageCode(int page,Integer totalNum,String q,Integer pageSize,String projectContext){
        long totalPage=totalNum%pageSize==0?totalNum/pageSize:totalNum/pageSize+1;
        StringBuffer pageCode=new StringBuffer();
        if(totalPage==0){
            return "";
        }else{
            pageCode.append("<nav>");
            pageCode.append("<ul class='pager' >");
            if(page>1){
                pageCode.append("<li><a href='"+projectContext+"/q?page="+(page-1)+"&q="+q+"'>上一页</a></li>");
            }else{
                pageCode.append("<li class='disabled'><a href='#'>上一页</a></li>");
            }
            if(page<totalPage){
                pageCode.append("<li><a href='"+projectContext+"/q?page="+(page+1)+"&q="+q+"'>下一页</a></li>");
            }else{
                pageCode.append("<li class='disabled'><a href='#'>下一页</a></li>");
            }
            pageCode.append("</ul>");
            pageCode.append("</nav>");
        }
        return pageCode.toString();
    }

}
