package com.spring.freemarker;  

import java.util.ArrayList;  
import java.util.List;  
  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
  
import org.springframework.stereotype.Controller;  
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.servlet.ModelAndView;  
  
import com.spring.vo.User;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;  
  

@Controller  
@RequestMapping("/home")  
public class FreeMarkerController {  
  
    @RequestMapping("/index")  
    public ModelAndView Add(HttpServletRequest request, HttpServletResponse response) {  
  
        User user = new User();  
        user.setUsername("zhangsan");  
        user.setPassword("1234");  
        List<User> users = new ArrayList<User>();  
        users.add(user);  
        
		/* taobao official
		 */
		// 官网的URL
		String url = "http://gw.api.taobao.com/router/rest";
		// 成为开发者，创建应用后系统自动生成
		//String appkey = "23326840";   //leosu
		//String secret = "be169f170482fc4fe3374895e30d3244";  //leosu
		String appkey = "23331183";
		String secret = "ed45c69efd06e5b91aa8708ba8595a3a";
		//短信模板的内容  
		String json="{\"code\":\"1234\",\"product\":\"leosu-wangjunhui, 电子商务验证\"}"; 
		TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		req.setExtend("123456");
		req.setSmsType("normal");
		req.setSmsFreeSignName("注册验证");//短信签名，传入的短信签名必须是在阿里大鱼“管理中心-短信签名管理”中的可用签名。如“阿里大鱼
		req.setSmsParamString(json);
		req.setRecNum("13524095436");
		//req.setSmsTemplateCode("SMS_6075223");  //leosu  管理中心-短信模板管理”中的可用模板。示例：SMS_585014
		req.setSmsTemplateCode("SMS_6480466");  //管理中心-短信模板管理”中的可用模板。示例：SMS_585014
		
		try {
			AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
			System.out.println("leo in try");
			System.out.println(rsp.getBody());
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("leo in cache");
			System.out.println(e.toString());
		}
		/**/
		
        return new ModelAndView("index", "users", users);  
    }  
  
}  