package kr.or.ddit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.CrudMember;
import kr.or.ddit.vo.NoticeMemberVO;

@Mapper
public interface IMemberMapper {

	public List<NoticeMemberVO> selectMemberList();

	public List<CrudMember> selectCrudMemberList(List<String> authList);
	
}
