import rest.RestService;
import threads.PdfBookAnalyzer;

public class Main {
    public static void main(String[] args) {
        PdfBookAnalyzer analyzer = new PdfBookAnalyzer();
        String filePath = "src/main/resources/Computer Systems A Programmers Perspective (3rd).pdf";
        String searchString = "segment";
        int repetitionRate = analyzer.findRepetitionRate(filePath, searchString, 4);
        System.out.println("Repetition rate: " + repetitionRate);

        RestService restService = new RestService();
        String resultCode = restService.getResultCode();
        System.out.println("Result code: " + resultCode);
    }
}