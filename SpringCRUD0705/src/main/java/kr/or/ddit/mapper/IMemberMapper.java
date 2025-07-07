package kr.or.ddit.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.MemberVO;

@Mapper
public interface IMemberMapper {

	public int memberInsert(MemberVO memberVO);

	public MemberVO memberDetail(String memId);

	public int memberUpdate(MemberVO memberVO);

}
