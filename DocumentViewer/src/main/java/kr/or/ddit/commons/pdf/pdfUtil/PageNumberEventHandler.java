package kr.or.ddit.commons.pdf.pdfUtil;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.event.AbstractPdfDocumentEvent;
import com.itextpdf.kernel.pdf.event.AbstractPdfDocumentEventHandler;

public class PageNumberEventHandler extends AbstractPdfDocumentEventHandler {
	protected PdfFont font;

	public PageNumberEventHandler(PdfFont font) {
		this.font = font;
	}

	@Override
	protected void onAcceptedEvent(AbstractPdfDocumentEvent event) {
//		PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
//		PdfDocument pdfDoc = docEvent.getDocument();
//		PdfPage page = docEvent.getPage();
//		int pageNumber = pdfDoc.getPageNumber(page);
//		int totalPages = pdfDoc.getNumberOfPages(); // 이 시점에서는 total이 1로만 나올 수 있음
//
//		PdfCanvas canvas = new PdfCanvas(page.newContentStreamAfter(), page.getResources(), pdfDoc);
//		// 하단 중앙에 텍스트 추가
//		int pageWidth = (int) page.getPageSize().getWidth();
//		int stringWidth = 50; // 폰트 및 문자열에 따라 적절히 조절
//		int x = pageWidth / 2 - stringWidth / 2;
//		int y = 20; // 하단 20pt 위치
//		canvas.beginText().setFontAndSize(font, 10).moveText(x, y)
//				.showText(String.format("Page %d / %%TOTAL%%", pageNumber)) // %%TOTAL%%은 후처리로 바꿔줌
//				.endText().release();
	}

	// 현재 페이지 / 전체 페이지 정보 후처리 하는 메서드
	public ByteArrayOutputStream totalPage(ByteArrayOutputStream baos) throws Exception {
		PdfFont font = new PdfFontConfig().regularFont();
		int fontSize = 10;
		// 후처리를 위한 PDF 다시 열기 (메모리에서 처리하려면 baos를 바이트 배열로 변환)
		byte[] pdfBytes = baos.toByteArray();

		// 1) 기존 PDF 읽기, 2) 쓰기용 PdfDocument 생성
		try (ByteArrayOutputStream finalBaos = new ByteArrayOutputStream()) {
			PdfDocument pdfDoc = new PdfDocument(
					new PdfReader(new ByteArrayInputStream(pdfBytes)),
					new PdfWriter(finalBaos));

			int totalPages = pdfDoc.getNumberOfPages();
			//-----------------------------------------------------------------------------------
			// 페이지 왼쪽 위에 반복할 텍스트 추가
			for (int i = 1; i <= totalPages; i++) {
				PdfPage page = pdfDoc.getPage(i);
				PdfCanvas canvas = new PdfCanvas(page.newContentStreamAfter(), page.getResources(), pdfDoc);
				
				float pageWidth = page.getPageSize().getWidth();
				String text = String.format("페이지 왼쪽 위에 반복할 텍스트");
				float approxTextWidth = fontSize * text.length() * 0.5f;
//				float x = (pageWidth - approxTextWidth) / 2;	// 중앙
				// 반복되는 텍스트의 위치. 기준은 문서 왼쪽 아래 꼭짓점
				float x = 20;
				float y = 815;
				
				canvas.beginText().setFontAndSize(font, fontSize).moveText(x, y).showText(text).endText();
			}
			//--------------------------------------------------------------------------------------------
			// 페이지 오른쪽 위에 반복할 텍스트 추가
			for (int i = 1; i <= totalPages; i++) {
				PdfPage page = pdfDoc.getPage(i);
				PdfCanvas canvas = new PdfCanvas(page.newContentStreamAfter(), page.getResources(), pdfDoc);
				
				float pageWidth = page.getPageSize().getWidth();
				String text = String.format("페이지 오른쪽 위에 반복할 텍스트");
				float approxTextWidth = fontSize * text.length() * 0.5f;
//				float x = (pageWidth - approxTextWidth) / 2;	// 중앙
				// 반복되는 텍스트의 위치. 기준은 문서 왼쪽 아래 꼭짓점
				float x = 430;
				float y = 815;
				
				canvas.beginText().setFontAndSize(font, fontSize).moveText(x, y).showText(text).endText();
			}
			//-------------------------------------------------------------------------------------------
			// 페이지 왼쪽 아래 반복할 텍스트 추가
			for (int i = 1; i <= totalPages; i++) {
				PdfPage page = pdfDoc.getPage(i);
				PdfCanvas canvas = new PdfCanvas(page.newContentStreamAfter(), page.getResources(), pdfDoc);
				
				float pageWidth = page.getPageSize().getWidth();
				String text = String.format("페이지 왼쪽 아래 반복할 텍스트");
				float approxTextWidth = fontSize * text.length() * 0.5f;
//				float x = (pageWidth - approxTextWidth) / 2;	// 중앙
				// 반복되는 텍스트의 위치. 기준은 문서 왼쪽 아래 꼭짓점
				float x = 20;
				float y = 20;
				
				canvas.beginText().setFontAndSize(font, fontSize).moveText(x, y).showText(text).endText();
			}
			//------------------------------------------------------------------------------------
			// 페이지 오른쪽 아래 '현재 페이지 / 전체 페이지' 추가
			for (int i = 1; i <= totalPages; i++) {
				PdfPage page = pdfDoc.getPage(i);
				PdfCanvas canvas = new PdfCanvas(page.newContentStreamAfter(), page.getResources(), pdfDoc);

				float pageWidth = page.getPageSize().getWidth();
				String text = String.format("Page %d / %d", i, totalPages);
				float approxTextWidth = fontSize * text.length() * 0.5f;
//				float x = (pageWidth - approxTextWidth) / 2;	// 중앙
				// 반복되는 텍스트의 위치. 기준은 문서 왼쪽 아래 꼭짓점
				float x = 530;
				float y = 20;

				canvas.beginText().setFontAndSize(font, fontSize).moveText(x, y).showText(text).endText();
			}

			pdfDoc.close();

			return finalBaos;
		}
	}
}
