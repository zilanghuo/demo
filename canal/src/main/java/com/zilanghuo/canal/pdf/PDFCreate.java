package com.zilanghuo.canal.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.File;

/**
 * Created by laiwufa on 2022-11-21
 */
public class PDFCreate {

    public static void main(String[] args) throws Exception{

        //Loading an existing document
        File file = new File("C:/PdfBox_Examples/my_doc.pdf");
        PDDocument document = PDDocument.load(file);
        //Retrieving the pages of the document
        PDPage page = document.getPage(1);
        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        //Begin the Content stream
        contentStream.beginText();
        //Setting the font to the Content stream
        contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
        //Setting the position for the line
        contentStream.newLineAtOffset(25, 500);
        String text = "This is the sample document and we are adding content to it.";
        //Adding text in the form of string
        contentStream.showText(text);
        //Ending the content stream
        contentStream.endText();
        System.out.println("Content added");
        //Closing the content stream
        contentStream.close();
        //Saving the document
        document.save(new File("C:/PdfBox_Examples/new.pdf"));
        //Closing the document
        document.close();



    }
}
