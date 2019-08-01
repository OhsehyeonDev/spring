package aop;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import exception.LogInException;
import logic.User;

@Component
@Aspect
public class AdminAspect {

 @Around("execution(* controller.Admin*.*(..)) && args(..,session)")
 public Object adminCheck(ProceedingJoinPoint joinPoint, HttpSession session)
		 throws Throwable {
	 User loginUser = (User)session.getAttribute("loginUser");
	 if(loginUser == null) {
		 throw new LogInException
		         ("로그인 후 이용하세요","../user/login.shop");
	 }
	 if(!loginUser.getUserId().equals("admin")) {
		 throw new LogInException
         ("관리자만 가능한 페이지 입니다.",
        		 "../user/mypage.shop?id="+loginUser.getUserId());
	 }
	 Object ret = joinPoint.proceed();
	 return ret;
 }
}
