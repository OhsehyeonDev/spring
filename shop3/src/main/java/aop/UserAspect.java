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
public class UserAspect {
    @Around
    ("execution(* controller.User*.check*(..)) && args(..,session)")
	public Object userLoginCheck(ProceedingJoinPoint joinPoint,
			   HttpSession session) throws Throwable{
		User loginUser = (User)session.getAttribute("loginUser");
		if(loginUser == null) {
			throw new LogInException
			              ("로그인 후 거래하세요","login.shop");
		}
		Object ret = joinPoint.proceed();
		return ret;
	}
    @Around
    ("execution(* controller.User*.check*(..)) && args(id,session)")
	public Object userIdCheck(ProceedingJoinPoint joinPoint,
			   String id ,HttpSession session) throws Throwable{
		User loginUser = (User)session.getAttribute("loginUser");
		if(loginUser == null) {
			throw new LogInException
			              ("로그인 후 거래하세요","login.shop");
		}
		if(id != null)
		  if(!loginUser.getUserId().equals("admin") &&
		     !loginUser.getUserId().equals(id)	) {
			  throw new LogInException
              ("본인 정보만 조회 가능합니다.","main.shop");
		  }
		Object ret = joinPoint.proceed();
		return ret;
	}

}
