/*
    This file is part of the iText (R) project.
    Copyright (c) 1998-2023 Apryse Group NV
    Authors: Apryse Software.

    For more information, please contact iText Software at this address:
    sales@itextpdf.com
 */
package kr.or.ddit.tutorial.chapter04;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * Simple filling out form example.
 */
public class C04E03_CreateAndFill {

    public static final String DEST = "results/chapter04/create_and_fill.pdf";

    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new C04E03_CreateAndFill().createPdf(DEST);
    }

    public void createPdf(String dest) throws IOException {

        //Initialize PDF document
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));

        // Initialize document
        Document doc = new Document(pdf);

        PdfAcroForm form = C04E02_JobApplication.addAcroForm(doc);
        Map<String, PdfFormField> fields = form.getAllFormFields();
        fields.get("name").setValue("James Bond");
        fields.get("language").setValue("English");
        fields.get("experience1").setValue("Off");
        fields.get("experience2").setValue("Yes");
        fields.get("experience3").setValue("Yes");
        fields.get("shift").setValue("Any");
        fields.get("info").setValue("I was 38 years old when I became an MI6 agent.");

        doc.close();

    }
}