package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.AppCtx;
import spring.DuplicateMemberException;
import spring.MemberDeleteService;
import spring.MemberListPrinter;
import spring.MemberNotFoundException;
import spring.MemberRegisterService;
import spring.RegisterRequest;
import spring.passwordChangeService;

public class Main2 {
	private static ApplicationContext ctx = null;

	public static void main(String[] args) throws IOException {
		ctx = new AnnotationConfigApplicationContext(AppCtx.class);
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			System.out.println("명령어를 입력하세요");
			String command = reader.readLine();
			if (command.equalsIgnoreCase("exit")) {
				System.out.println("프로그램을 종료합니다.");
				break;
			}
			// command 문자열의 시작이 "new " 인 경우 true 리턴.
			if (command.startsWith("new ")) {
				processNewCommand(command.split(" "));
				continue;
			} else if (command.startsWith("passchg ")) {
				// passchg 이메일 변경전비밀번호 변경후비밀번호
				// 이메일이 존재하지 않으면 MemberNotFoundException 예외 발생
				// 비밀번호를 변경후 비밀번호로 변경하기
				// 비밀번호 변경완료 메시지 출력
				passwordChangeCommand(command.split(" "));
				continue;
			} else if (command.startsWith("delete ")) { // delete 이메일
				MemberDeleteCommand(command.split(" "));
				continue;
			} else if (command.startsWith("list")) {
				processListCommand();
				continue;
			}
			printHelp();
		}
	}

	private static void MemberDeleteCommand(String[] arg) {
		if (arg.length != 2) {
			printHelp();
			return;
		}
		MemberDeleteService delSvc = ctx.getBean(MemberDeleteService.class);
		try {
			delSvc.delete(arg[1]);
			System.out.println("회원 탈퇴 완료.\n");
		} catch (MemberNotFoundException e) {
			System.out.println("존재하지 않는 이메일 입니다");
			System.out.println(e.getMessage());
		}
	}

	private static void passwordChangeCommand(String[] arg) {
		if (arg.length != 4) {
			printHelp();
			return;
		}
		passwordChangeService passSvc = ctx.getBean(passwordChangeService.class);
		try {
			passSvc.change(arg);
			System.out.println("비밀번호 변경완료.\n");
		} catch (MemberNotFoundException e) {
			System.out.println("존재하지 않는 이메일 입니다");
			System.out.println(e.getMessage());
		} catch (RuntimeException e) {
			System.out.println("비밀번호 오류.");
			System.out.println(e.getMessage());
		}
	}

	private static void processListCommand() {
		MemberListPrinter listprinter = ctx.getBean("listPrinter", MemberListPrinter.class);
		listprinter.printAll();
	}

	private static void processNewCommand(String[] arg) {
		if (arg.length != 5) { // new 이메일 이름 암호 암호확인, arg[0] ~ [4]
			printHelp();
			return;
		}
		MemberRegisterService regSvc = ctx.getBean(MemberRegisterService.class);
		// MemberRegisterService regSvc = ctx.getBean("memberRegisterService",
		// MemberRegisterService.class); 앞에서 했던 방법
		RegisterRequest req = new RegisterRequest();
		req.setEmail(arg[1]);
		req.setName(arg[2]);
		req.setPassword(arg[3]);
		req.setConfirmPassword(arg[4]);
		if (!req.isPasswordEqualToConfirmPassword()) {
			System.out.println("암호와 확인 불일치\n");
			return;
		}
		try {
			regSvc.regist(req);
			System.out.println("등록했습니다.\n");
		} catch (DuplicateMemberException e) {
			System.out.println("이미 존재하는 이메일입니다.\n");
		}
	}

	private static void printHelp() {
		System.out.println("--------------------------------");
		System.out.println("\n잘못된 명령입니다");
		System.out.println("명령어 사용법");
		System.out.println("new 이메일 이름 암호 암호확인");
		System.out.println("--------------------------------");
	}
}