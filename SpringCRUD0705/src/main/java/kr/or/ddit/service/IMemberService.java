package kr.or.ddit.service;

import kr.or.ddit.vo.MemberVO;

public interface IMemberService {

	public int memberInsert(MemberVO memberVO);

	public MemberVO memberDetail(String memId);

	public int memberUpdate(MemberVO memberVO);

}
