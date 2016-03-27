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
		// ������URL
		String url = "http://gw.api.taobao.com/router/rest";
		// ��Ϊ�����ߣ�����Ӧ�ú�ϵͳ�Զ�����
		//String appkey = "23326840";   //leosu
		//String secret = "be169f170482fc4fe3374895e30d3244";  //leosu
		String appkey = "23331183";
		String secret = "ed45c69efd06e5b91aa8708ba8595a3a";
		//����ģ�������  
		String json="{\"code\":\"1234\",\"product\":\"leosu-wangjunhui, ����������֤\"}"; 
		TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		req.setExtend("123456");
		req.setSmsType("normal");
		req.setSmsFreeSignName("ע����֤");//����ǩ��������Ķ���ǩ���������ڰ�����㡰��������-����ǩ�������еĿ���ǩ�����硰�������
		req.setSmsParamString(json);
		req.setRecNum("13524095436");
		//req.setSmsTemplateCode("SMS_6075223");  //leosu  ��������-����ģ������еĿ���ģ�塣ʾ����SMS_585014
		req.setSmsTemplateCode("SMS_6480466");  //��������-����ģ������еĿ���ģ�塣ʾ����SMS_585014
		
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