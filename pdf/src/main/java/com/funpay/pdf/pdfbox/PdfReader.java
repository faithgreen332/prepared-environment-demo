package com.funpay.pdf.pdfbox;

import com.google.common.base.Strings;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;

/**
 * @author Leeko
 * @date 2022/3/10
 **/
public class PdfReader {

    public static void main(String[] args) {
        String path = "D:\\mxico\\001 HOW TO ENCRYPT MANUAL cp.pdf";
        String text = PdfReader.getTextFromPdf(path);
        System.out.println(text);
    }

    /**
     * 简单的读取 pdf 文件字符，如果需要格式化输出，还要自己解析
     *
     * @param path
     * @return
     */
    private static String getTextFromPdf(String path) {
        String result = Strings.nullToEmpty(null);
        String[] modes = {"r", "rw", "rws", "rwd"};

        try {
            synchronized (PdfReader.class) {
                File file = new File(path);
                RandomAccessFile randomAccessFile = new RandomAccessFile(file, modes[0]);
                PDFParser pdfParser = new PDFParser(randomAccessFile);
                pdfParser.parse();
                PDDocument pdDocument = pdfParser.getPDDocument();
                PDFTextStripper pdfTextStripper = new PDFTextStripper();
                result = pdfTextStripper.getText(pdDocument);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
