package com.zilanghuo.canal.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.state.PDExtendedGraphicsState;
import org.apache.pdfbox.util.Matrix;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by laiwufa on 2022-11-21
 */
public class PDFMain {

    public static void main(String[] args) {

        try {
            createPdf();
            watermark(new File("/Users/admin/Desktop/20221121/wuhaiq.pdf"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private static void createPdf() throws Exception{
        //Loading an existing document
        PDDocument document = new PDDocument();
        PDPage my_page = new PDPage();
        document.addPage(my_page);

        //Retrieving the pages of the document
        PDPage page = document.getPage(0);
        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        PDFont font = PDType0Font.load(document, new FileInputStream("/Users/admin/Desktop/20221121/微软雅黑.TTF"), true);

        contentStream.setFont(font,12F);
        //Begin the Content stream
        contentStream.beginText();
        //Setting the font to the Content stream
        //contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
        //Setting the position for the line
        contentStream.newLineAtOffset(25, 500);
        String text = "吴海清吴海清吴海清吴海清吴海清--------吴海清吴海清吴海清吴海清吴海清--------吴海清吴海清吴海清吴海清吴海清--------吴海清吴海清吴海清吴海清吴海清--------吴海清吴海清吴海清吴海清吴海清--------";
        //Adding text in the form of string
        contentStream.showText(text);
        //Ending the content stream
        contentStream.endText();
        System.out.println("吴海清吴海清吴海清。。。。。。。。。。。。。。。。。。");
        //Closing the content stream
        contentStream.close();
        //Saving the document
        document.save(new File("/Users/admin/Desktop/20221121/wuhaiq.pdf"));
        //Closing the document
        document.close();


    }

    private static void watermark(File file) throws Exception {

        //创建新pdf文件

        File tmpPDF = new File("/Users/admin/Desktop/20221121/10_" + file.getName());

        //打开pdf文件

        PDDocument doc = PDDocument.load(file);

        doc.setAllSecurityToBeRemoved(true);

        //遍历pdf所有页

        for (PDPage page : doc.getPages()) {

            PDPageContentStream cs = new PDPageContentStream(doc, page, PDPageContentStream.AppendMode.APPEND, true, true);

            String ts = "这是吴海清的水印";

            //引入字体文件 解决中文汉字乱码问题

            PDFont font = PDType0Font.load(doc, new FileInputStream("/Users/admin/Desktop/20221121/微软雅黑.TTF"), true);

            float fontSize = 30;

            PDResources resources = page.getResources();

            PDExtendedGraphicsState r0 = new PDExtendedGraphicsState();

            // 水印透明度

            r0.setNonStrokingAlphaConstant(0.2f);

            r0.setAlphaSourceFlag(true);

            cs.setGraphicsStateParameters(r0);

            //水印颜色

            cs.setNonStrokingColor(200, 0, 0);

            cs.beginText();

            cs.setFont(font, fontSize);

            //根据水印文字大小长度计算横向坐标需要渲染几次水印

            float h = ts.length() * fontSize;

            for (int i = 0; i <= 10; i++) {

                // 获取旋转实例

                cs.setTextMatrix(Matrix.getRotateInstance(-150, i * 100, 0));

                cs.showText(ts);

                for (int j = 0; j < 20; j++) {

                    cs.setTextMatrix(Matrix.getRotateInstance(-150, i * 100, j * h));

                    cs.showText(ts);

                }

            }

            cs.endText();

            cs.restoreGraphicsState();

            cs.close();

        }

        doc.save(tmpPDF);

    }

}
