package threads;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class PdfBookAnalyzer {

    private static final Log log = LogFactory.getLog(PdfBookAnalyzer.class);

    private final TextSearcher textSearcher = new TextSearcher();

    public int findRepetitionRate(String filePath, String subString, int nThread) {
        try {
            String textFromFile = parsePdfToString(filePath);
            return textSearcher.countRepetitionRate(textFromFile, subString, nThread);
        } catch (InterruptedException | ExecutionException e) {
            log.error(e);
            throw new RuntimeException(e);
        }
    }

    private String parsePdfToString(String pdfFile) {
        log.info("Start extracting text from pdf - " + pdfFile);

        try (PDDocument document = PDDocument.load(new File(pdfFile))) {
            PDFTextStripper pdfStripper = new PDFTextStripper();
            String text = pdfStripper.getText(document);

            log.info("Text successfully extracted from pdf");
            return text;
        } catch (IOException e) {
            log.warn("Unable to extract text from pdf file: " + pdfFile);
            throw new RuntimeException(e);
        }
    }

}
