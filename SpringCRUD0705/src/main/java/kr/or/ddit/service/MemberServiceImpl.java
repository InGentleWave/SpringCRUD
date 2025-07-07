package kr.or.ddit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.IMemberMapper;
import kr.or.ddit.vo.MemberVO;

@Service
public class MemberServiceImpl implements IMemberService {

	@Autowired
	private IMemberMapper mapper;
	
	@Override
	public int memberInsert(MemberVO memberVO) {
		return mapper.memberInsert(memberVO);
	}

	@Override
	public MemberVO memberDetail(String memId) {
		return mapper.memberDetail(memId);
	}

	@Override
	public int memberUpdate(MemberVO memberVO) {
		return mapper.memberUpdate(memberVO);
	}

}
