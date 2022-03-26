package com.lkh.example.demo.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lkh.example.demo.service.MemberService;
import com.lkh.example.demo.util.Ut;
import com.lkh.example.demo.vo.Member;



@Controller
public class UsrMemberController {
	@Autowired
	private MemberService memberService;
	
	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public Object doJoin(String loginId,String loginPw,String name,String nickname,String cellphoneNo,String email) {
		if(Ut.empty(loginId)) {
			return "loginId를 입력해주세요.";
		}
		if(Ut.empty(loginPw)) {
			return "loginPw를 입력해주세요.";
		}
		if(Ut.empty(name)) {
			return "name를 입력해주세요.";
		}
		if(Ut.empty(nickname)) {
			return "nickname를 입력해주세요.";
		}
		if(Ut.empty(cellphoneNo)) {
			return "cellphoneNo를 입력해주세요.";
		}
		if(Ut.empty(email)) {
			return "email를 입력해주세요.";
		}
		
		int id=memberService.join(loginId,loginPw,name,nickname,cellphoneNo,email);
		
		if(id==-1) {
			return "해당 로그인아이디는 이미 사용중입니다.";
		}
		if(id==-2) {
			return "해당 이름과 이메일은 이미 사용중입니다.";
		}
		
		Member member=memberService.getMemberById(id);
		
		return member;
	}
	
}

