package kr.or.ddit.service;

import java.util.List;

import kr.or.ddit.vo.NoticeMemberVO;

public interface INoticeService {

	public List<NoticeMemberVO> selectMemberList();

}
