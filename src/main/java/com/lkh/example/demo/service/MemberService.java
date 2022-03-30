package com.lkh.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lkh.example.demo.repository.MemberRepository;
import com.lkh.example.demo.util.Ut;
import com.lkh.example.demo.vo.Member;
import com.lkh.example.demo.vo.ResultData;

@Service
public class MemberService {
	@Autowired
	private MemberRepository memberRepository;

	public ResultData<Integer> join(String loginId, String loginPw, String name, String nickname, String cellphoneNo, String email) {
		// 로그인 중복 체크
		Member oldmember = getMemberByLoginId(loginId);

		if (oldmember != null) {
			return ResultData.from("F-7", Ut.f("해당 로그인아이디(%s)는 이미 사용중입니다.",loginId));
		}

		// 이름+이메일 중복 체크
		oldmember = getMemberByNameAndEmail(name,email);

		if (oldmember != null) {
			return ResultData.from("F-8", Ut.f("해당 이름(%s)과 이메일(%s)은 이미 사용중입니다.",name,email));
		}

		memberRepository.join(loginId, loginPw, name, nickname, cellphoneNo, email);
		int id=memberRepository.getLastInsertId();
		return ResultData.from("S-1", "회원가입이 완료되었습니다.","id",id);
	}

	public Member getMemberByNameAndEmail(String name, String email) {
		return memberRepository.getMemberByNameAndEmail(name,email);
	}

	public Member getMemberByLoginId(String loginId) {
		return memberRepository.getMemberByLoginId(loginId);
	}

	public Member getMemberById(int id) {
		return memberRepository.getMemberById(id);
	}


}
