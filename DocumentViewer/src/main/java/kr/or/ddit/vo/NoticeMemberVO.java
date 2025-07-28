/*
    This file is part of the iText (R) project.
    Copyright (c) 1998-2023 Apryse Group NV
    Authors: Apryse Software.

    For more information, please contact iText Software at this address:
    sales@itextpdf.com
 */
package kr.or.ddit.vo;

import lombok.Data;

@Data
public class NoticeMemberVO {
	 private int memNo;
	 private String memId;
	 private String memPw;
	 private String memName;
	 private String memGender;
	 private String memEmail;
	 private String memPhone;
	 private String memPostcode;
	 private String memAddress1;
	 private String memAddress2;
	 private String memAgree;
	 private String memProfileimg;
	 private String memRegdate;
}
