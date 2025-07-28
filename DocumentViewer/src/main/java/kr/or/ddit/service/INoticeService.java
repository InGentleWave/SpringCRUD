/*
    This file is part of the iText (R) project.
    Copyright (c) 1998-2023 Apryse Group NV
    Authors: Apryse Software.

    For more information, please contact iText Software at this address:
    sales@itextpdf.com
 */
package kr.or.ddit.service;

import java.util.List;

import kr.or.ddit.vo.CrudMember;
import kr.or.ddit.vo.NoticeMemberVO;

public interface INoticeService {

	public List<NoticeMemberVO> selectMemberList();

	public List<CrudMember> selectCrudMemberList(List<String> authList);

}
