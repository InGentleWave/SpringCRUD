/*
    This file is part of the iText (R) project.
    Copyright (c) 1998-2023 Apryse Group NV
    Authors: Apryse Software.

    For more information, please contact iText Software at this address:
    sales@itextpdf.com
 */
package kr.or.ddit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.IMemberMapper;
import kr.or.ddit.vo.CrudMember;
import kr.or.ddit.vo.NoticeMemberVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class NoticeServiceImpl implements INoticeService {

	@Autowired
	private IMemberMapper memberMapper;
	
	@Override
	public List<NoticeMemberVO> selectMemberList() {
		return memberMapper.selectMemberList();
	}

	@Override
	public List<CrudMember> selectCrudMemberList(List<String> authList) {
		return memberMapper.selectCrudMemberList(authList);
	}

}
