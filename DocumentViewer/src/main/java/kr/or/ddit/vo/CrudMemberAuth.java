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
public class CrudMemberAuth {
	private int userNo;
	private String auth;
}
