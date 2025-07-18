/*
    This file is part of the iText (R) project.
    Copyright (c) 1998-2023 Apryse Group NV
    Authors: Apryse Software.

    For more information, please contact iText Software at this address:
    sales@itextpdf.com
 */
package kr.or.ddit.tutorial.chapter05;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfButtonFormField;
import com.itextpdf.forms.fields.PdfFormCreator;
import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.forms.fields.CheckBoxFormFieldBuilder;
import com.itextpdf.forms.fields.properties.CheckBoxType;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfString;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.action.PdfAction;
import com.itextpdf.kernel.pdf.annot.PdfAnnotation;
import com.itextpdf.kernel.pdf.annot.PdfTextAnnotation;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import java.io.File;
import java.io.IOException;

/**
 * Simple adding annotations example.
 */
public class C05E01_AddAnnotationsAndContent {

    public static final String SRC = "src/main/resources/pdf/job_application.pdf";
    public static final String DEST = "results/chapter05/edited_job_application.pdf";

    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new C05E01_AddAnnotationsAndContent().manipulatePdf(SRC, DEST);
    }

    public void manipulatePdf(String src, String dest) throws IOException {

        //Initialize PDF document
        PdfDocument pdfDoc = new PdfDocument(new PdfReader(src), new PdfWriter(dest));

        //Add text annotation
        PdfAnnotation ann = new PdfTextAnnotation(new Rectangle(400, 795, 0, 0))
                .setOpen(true)
                .setTitle(new PdfString("iText"))
                .setContents("Please, fill out the form.");
        pdfDoc.getFirstPage().addAnnotation(ann);

        PdfCanvas canvas = new PdfCanvas(pdfDoc.getFirstPage());
        canvas.beginText().setFontAndSize(PdfFontFactory.createFont(StandardFonts.HELVETICA), 12)
                .moveText(265, 597)
                .showText("I agree to the terms and conditions.")
                .endText();

        //Add form field
        PdfAcroForm form = PdfFormCreator.getAcroForm(pdfDoc, true);
        PdfButtonFormField checkField = new CheckBoxFormFieldBuilder(pdfDoc, "agreement")
                .setWidgetRectangle(new Rectangle(245, 594, 15, 15))
                .setCheckType(CheckBoxType.CHECK).createCheckBox();
        checkField.setValue("Off", true);
        checkField.setRequired(true);
        form.addField(checkField);

        //Update reset button
        form.getField("reset").getFirstFormAnnotation().setAction(PdfAction.createResetForm(new String[]{"name",
                "language", "experience1", "experience2", "experience3", "shift", "info", "agreement"}, 0));

        pdfDoc.close();

    }
}