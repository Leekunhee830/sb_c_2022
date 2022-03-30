package com.lkh.example.demo.controller;



import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lkh.example.demo.service.MemberService;
import com.lkh.example.demo.util.Ut;
import com.lkh.example.demo.vo.Member;
import com.lkh.example.demo.vo.ResultData;



@Controller
public class UsrMemberController {
	@Autowired
	private MemberService memberService;
	
	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public ResultData<Member> doJoin(String loginId,String loginPw,String name,String nickname,String cellphoneNo,String email) {
		if(Ut.empty(loginId)) {
			return ResultData.from("F-1", "loginId를 입력해주세요.");
		}
		if(Ut.empty(loginPw)) {
			return ResultData.from("F-2", "loginPw를 입력해주세요.");
		}
		if(Ut.empty(name)) {
			return ResultData.from("F-3", "name를 입력해주세요.");
		}
		if(Ut.empty(nickname)) {
			return ResultData.from("F-4", "nickname를 입력해주세요.");
		}
		if(Ut.empty(cellphoneNo)) {
			return ResultData.from("F-5", "cellphoneNo를 입력해주세요.");
		}
		if(Ut.empty(email)) {
			return ResultData.from("F-6", "email를 입력해주세요.");
		}
		
		ResultData<Integer> joinRd=memberService.join(loginId,loginPw,name,nickname,cellphoneNo,email);
		
		if(joinRd.isFail()) {
			return (ResultData)joinRd;
		}
		
		Member member=memberService.getMemberById(joinRd.getData1());
		
		return ResultData.newData(joinRd,"member",member);
	}
	
	@RequestMapping("/usr/member/doLogout")
	@ResponseBody
	public ResultData doLogout(HttpSession httpSession) {
		boolean isLogined=false;
		
		if(httpSession.getAttribute("loginedMemberId")==null) {
			isLogined=true;
		}
		
		if(isLogined) {
			return ResultData.from("S-1", "이미 로그아웃 상태입니다.");
		}
		
		httpSession.removeAttribute("loginedMemberId");
		return ResultData.from("S-1","로그아웃 되었습니다.");
		
	}
		
	@RequestMapping("/usr/member/doLogin")
	@ResponseBody
	public ResultData doLogin(HttpSession httpSession, String loginId,String loginPw) {
		boolean isLogined=false;
		
		if(httpSession.getAttribute("loginedMemberId")!=null) {
			isLogined=true;
		}
		
		if(isLogined) {
			return ResultData.from("F-5", "이미 로그인되었습니다.");
		}
		
		if(Ut.empty(loginId)) {
			return ResultData.from("F-1", "loginId를 입력해주세요.");
		}
		if(Ut.empty(loginPw)) {
			return ResultData.from("F-2", "loginPw를 입력해주세요.");
		}
		
		Member member = memberService.getMemberByLoginId(loginId);
		
		if(member==null) {
			return ResultData.from("F-3", "존재하지 않은 로그인아이디입니다.");
		}
		
		if(member.getLoginPw().equals(loginPw)==false) {
			return ResultData.from("F-4", "비밀번호가 일치하지 않습니다.");
		}
		
		httpSession.setAttribute("loginedMemberId", member.getId());
		
		return ResultData.from("S-1", Ut.f("%s님 환영합니다.", member.getNickname()));
		
	}
	
}

