/*
    This file is part of the iText (R) project.
    Copyright (c) 1998-2023 Apryse Group NV
    Authors: Apryse Software.

    For more information, please contact iText Software at this address:
    sales@itextpdf.com
 */
package kr.or.ddit.tutorial.chapter05;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfFormCreator;
import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Simple filling out form example.
 */
public class C05E02_FillAndModifyForm {

    public static final String SRC = "src/main/resources/pdf/job_application.pdf";
    public static final String DEST = "results/chapter05/filled_out_job_application.pdf";

    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new C05E02_FillAndModifyForm().manipulatePdf(SRC, DEST);
    }

    public void manipulatePdf(String src, String dest) throws IOException {

        //Initialize PDF document
        PdfDocument pdfDoc = new PdfDocument(new PdfReader(src), new PdfWriter(dest));


        PdfAcroForm form = PdfFormCreator.getAcroForm(pdfDoc, true);
        Map<String, PdfFormField> fields = form.getAllFormFields();

        fields.get("name").setValue("James Bond").getFirstFormAnnotation().setBackgroundColor(ColorConstants.ORANGE);
        fields.get("language").setValue("English");

        fields.get("experience1").setValue("Yes");
        fields.get("experience2").setValue("Yes");
        fields.get("experience3").setValue("Yes");

        List<PdfString> options = new ArrayList<PdfString>();
        options.add(new PdfString("Any"));
        options.add(new PdfString("8.30 am - 12.30 pm"));
        options.add(new PdfString("12.30 pm - 4.30 pm"));
        options.add(new PdfString("4.30 pm - 8.30 pm"));
        options.add(new PdfString("8.30 pm - 12.30 am"));
        options.add(new PdfString("12.30 am - 4.30 am"));
        options.add(new PdfString("4.30 am - 8.30 am"));
        PdfArray arr = new PdfArray(options);
        fields.get("shift").setOptions(arr);
        fields.get("shift").setValue("Any");

        PdfFont courier = PdfFontFactory.createFont(StandardFonts.COURIER);
        fields.get("info").setValue("I was 38 years old when I became an MI6 agent.", courier, 7f);

        pdfDoc.close();

    }
}