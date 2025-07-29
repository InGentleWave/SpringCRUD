/*
    This file is part of the iText (R) project.
    Copyright (c) 1998-2023 Apryse Group NV
    Authors: Apryse Software.

    For more information, please contact iText Software at this address:
    sales@itextpdf.com
 */
package kr.or.ddit.commons.pdf.pdfUtil;

import com.itextpdf.io.font.FontProgramFactory;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.font.PdfFontFactory.EmbeddingStrategy;

public class PdfFontConfig {
	/* ------------------------------------폰트 등록------------------------------------*/
	// 한글 지원 폰트 
	// 프로젝트 내 경로 지정
	public static final String EXTRALIGHT = "src/main/resources/fonts/MaruBuri-ExtraLight.ttf";
	public static final String LIGHT = "src/main/resources/fonts/MaruBuri-Light.ttf";
	public static final String REGULAR = "src/main/resources/fonts/MaruBuri-Regular.ttf";
	public static final String SEMIBOLD = "src/main/resources/fonts/MaruBuri-SemiBold.ttf";
	public static final String BOLD = "src/main/resources/fonts/MaruBuri-Bold.ttf";

    /* -----------------------------PdfFont 등록 예시---------------------------------- */
	
    // public PdfFont extraLightFont() throws Exception {
	// 		return PdfFontFactory.createFont(FontProgramFactory.createFont(폰트 경로 변수),
	//			인코딩설정(한글 폰트는 필수),임베딩 설정);
	// }
	
	/* 	
	  	임베딩 설정 => FORCE_EMBEDDED,FORCE_NOT_EMBEDDED,PREFER_EMBEDDED,PREFER_NOT_EMBEDDED
	 	임베딩 하면 해당 폰트를 프로그램에 내장하여 사용하기 때문에 해당 폰트를 보유하지 않은 사용자의 컴퓨터에서도
	 	폰트 적용이 정상적으로 이루어진다.
	 	FORCE_EMBEDDED ->무조건 폰트를 PDF에 내장시킴. PDF/A나 국제 표준 문서에서는 이 설정을 권장합니다.
	 					폰트 파일이 반드시 PDF 안에 포함되어 배포 환경에 관계없이 동일한 글꼴이 보장됩니다. 
	 	FORCE_NOT_EMBEDDED -> 절대 폰트를 내장하지 않음.  필수 상황이 아니면 권장하지 않습니다.
	 							폰트 정보는 PDF에 포함되지 않으며, 뷰어 PC에 해당 폰트가 없으면 글자 깨짐·대체 폰트 현상이 발생할 수 있습니다.
	 	PREFER_EMBEDDED -> 폰트를 내장할 수 있으면 내장하고, 불가능하면 내장하지 않습니다. 
	 						일반적으로 안정성을 우선하되 폰트 파일이 없을 때는 경고 없이 내장하지 않을 수도 있습니다.
	 	PREFER_NOT_EMBEDDED -> 가급적 내장하지 않으나 불가능하면 내장합니다. 
	 							파일 용량 감소를 우선시할 때 선택하며, 폰트가 반드시 필요하면 내장합니다.
	*/
	/*------------------------------ 폰트 등록 방법 end---------------------------------- */
    public PdfFont extraLightFont() throws Exception {
    	return PdfFontFactory.createFont(FontProgramFactory.createFont(EXTRALIGHT),
    			PdfEncodings.IDENTITY_H,EmbeddingStrategy.FORCE_EMBEDDED);
    }
    public PdfFont lightFont() throws Exception {
    	return PdfFontFactory.createFont(FontProgramFactory.createFont(LIGHT),
    			PdfEncodings.IDENTITY_H,EmbeddingStrategy.FORCE_EMBEDDED);
    }
    public PdfFont regularFont() throws Exception {
    	return PdfFontFactory.createFont(FontProgramFactory.createFont(REGULAR),
    			PdfEncodings.IDENTITY_H,EmbeddingStrategy.FORCE_EMBEDDED);
    }
    public PdfFont semiBoldFont() throws Exception {
    	return PdfFontFactory.createFont(FontProgramFactory.createFont(SEMIBOLD),
    			PdfEncodings.IDENTITY_H,EmbeddingStrategy.FORCE_EMBEDDED);
    }
    public PdfFont boldFont() throws Exception {
    	return PdfFontFactory.createFont(FontProgramFactory.createFont(BOLD),
    			PdfEncodings.IDENTITY_H,EmbeddingStrategy.FORCE_EMBEDDED);
    }

	/*----------------------------------폰트 등록 끝---------------------------------------*/
}
