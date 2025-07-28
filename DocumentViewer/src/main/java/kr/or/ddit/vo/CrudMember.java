/*
    This file is part of the iText (R) project.
    Copyright (c) 1998-2023 Apryse Group NV
    Authors: Apryse Software.

    For more information, please contact iText Software at this address:
    sales@itextpdf.com
 */
package kr.or.ddit.vo;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class CrudMember {
	private int userNo;
	private String userId;
	private String userPw;
	private String userName;
	private Date regDate;
	private Date updDate;
	private List<CrudMemberAuth> authList;
}
