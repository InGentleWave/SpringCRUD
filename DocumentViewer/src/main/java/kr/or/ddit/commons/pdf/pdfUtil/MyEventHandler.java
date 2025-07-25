/*
    This file is part of the iText (R) project.
    Copyright (c) 1998-2023 Apryse Group NV
    Authors: Apryse Software.

    For more information, please contact iText Software at this address:
    sales@itextpdf.com
 */
package kr.or.ddit.commons.pdf.pdfUtil;

import java.io.File;

import com.itextpdf.commons.actions.IEvent;
import com.itextpdf.commons.actions.IEventHandler;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceCmyk;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.event.PdfDocumentEvent;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.Property;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.layout.properties.VerticalAlignment;

/**
 * Simple event handler example.
 */
public class MyEventHandler implements IEventHandler {

	public static final String DATA = "src/main/resources/data/ufo.csv";
	public static final String DEST = "results/chapter03/ufo.pdf";

	static PdfFont helvetica = null;
	static PdfFont helveticaBold = null;

	public static void main(String[] args) throws Exception {
		helvetica = PdfFontFactory.createFont(StandardFonts.HELVETICA);
		helveticaBold = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
		File file = new File(DEST);
		file.getParentFile().mkdirs();
		Document document = PdfUtil.createPdf(DEST);
//		document.add
	}

	public void handleEvent(IEvent event) {
		PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
		PdfDocument pdfDoc = docEvent.getDocument();
		PdfPage page = docEvent.getPage();
		int pageNumber = pdfDoc.getPageNumber(page);
		Rectangle pageSize = page.getPageSize();
		PdfCanvas pdfCanvas = new PdfCanvas(page.newContentStreamBefore(), page.getResources(), pdfDoc);

		// Set background
		Color limeColor = new DeviceCmyk(0.208f, 0, 0.584f, 0);
		Color blueColor = new DeviceCmyk(0.445f, 0.0546f, 0, 0.0667f);
		pdfCanvas.saveState().setFillColor(pageNumber % 2 == 1 ? limeColor : blueColor)
				.rectangle(pageSize.getLeft(), pageSize.getBottom(), pageSize.getWidth(), pageSize.getHeight()).fill()
				.restoreState();

		// Add header and footer
		pdfCanvas.beginText().setFontAndSize(helvetica, 9)
				.moveText(pageSize.getWidth() / 2 - 60, pageSize.getTop() - 20).showText("THE TRUTH IS OUT THERE")
				.moveText(60, -pageSize.getTop() + 30).showText(String.valueOf(pageNumber)).endText();

		// Add watermark
		Canvas canvas = new Canvas(pdfCanvas, page.getPageSize());
		canvas.setFontColor(ColorConstants.WHITE);
		canvas.setProperty(Property.FONT_SIZE, UnitValue.createPointValue(60));
		canvas.setProperty(Property.FONT, helveticaBold);
		canvas.showTextAligned(new Paragraph("CONFIDENTIAL"), 298, 421, pdfDoc.getPageNumber(page),
				TextAlignment.CENTER, VerticalAlignment.MIDDLE, 45);

		pdfCanvas.release();
	}

	@Override
	public void onEvent(IEvent event) {
		// TODO Auto-generated method stub
		
	}
}
