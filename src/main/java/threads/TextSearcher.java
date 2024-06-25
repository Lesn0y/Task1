package threads;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TextSearcher {

    private static final Log log = LogFactory.getLog(TextSearcher.class);

    static {
        Logger.getLogger("org.apache.pdfbox").setLevel(Level.OFF);
    }

    public int countRepetitionRate(String text, String searchStr, int nThread) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(nThread);

        List<Callable<Integer>> tasks = createTasks(text, searchStr, nThread);

        long startTime = System.currentTimeMillis();

        List<Future<Integer>> results = executorService.invokeAll(tasks);

        int repetitionRate = 0;
        for (Future<Integer> result : results) {
            repetitionRate += result.get();
        }
        log.info("Duration time with " + nThread + " threads - " + (System.currentTimeMillis() - startTime) + " ms");
        executorService.shutdown();
        return repetitionRate;
    }

    private List<Callable<Integer>> createTasks(String text, String searchStr, int nThread) {
        int partSize = (text.length() + nThread - 1) / nThread;
        List<Callable<Integer>> tasks = new ArrayList<>();

        for (int i = 0; i < nThread; i++) {
            int startIndex = i * partSize;
            int endIndex = Math.min(startIndex + partSize, text.length());
            tasks.add(new SearcherTask(text, searchStr, startIndex, endIndex));
        }

        return tasks;
    }

    class SearcherTask implements Callable<Integer> {

        private final String text;
        private final String searchString;
        private final int startIndex;
        private final int endIndex;

        public SearcherTask(String text, String searchString, int startIndex, int endIndex) {
            this.text = text;
            this.searchString = searchString;
            this.startIndex = startIndex;
            this.endIndex = endIndex;
        }

        @Override
        public Integer call() {
            int count = 0;
            int index = startIndex;
            while ((index = text.indexOf(searchString, index)) != -1 && index < endIndex) {
                count++;
                index += searchString.length();
            }
            return count;
        }
    }

}
