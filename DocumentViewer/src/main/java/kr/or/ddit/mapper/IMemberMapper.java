/*
    This file is part of the iText (R) project.
    Copyright (c) 1998-2023 Apryse Group NV
    Authors: Apryse Software.

    For more information, please contact iText Software at this address:
    sales@itextpdf.com
 */
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
