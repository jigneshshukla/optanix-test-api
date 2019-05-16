package com.optanix.test.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jignesh
 * This class provides junit tests for WordCountService
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class WordCountServiceTest {

    @Autowired
    private WordCountService wordCountService;

    private static Map<String, String> getWordCountServiceRequest() {
        Map<String, String> data = new HashMap<>();
        data.put("testText", "Hello World! How are you all doing? This is WordCountService's unit testing.");
        return data;
    }

    private static Map<String, Integer> getExpectedSentenceWordCountMap() {
        Map<String, Integer> data = new HashMap<>();
        data.put("Hello World!", 2);
        data.put("How are you all doing?", 5);
        data.put("This is WordCountService's unit testing.", 5);
        return data;
    }

    @Test
    public void countWordsPerStatementInParagraph() {
        Map<String, String> request = getWordCountServiceRequest();
        validateSuccessfulResult(wordCountService.countWordsPerSentence(request), request);
    }

    @Test
    public void countWordsPerStatementWithEmptyParagraph() {
        Map<String, Map<String, Integer>> result = wordCountService.countWordsPerSentence(null);
        assert (result.size() == 0);
    }

    private void validateSuccessfulResult(Map<String, Map<String, Integer>> result, Map<String, String> request) {
        Map<String, Integer> statementWordCountMap = getExpectedSentenceWordCountMap();
        assert (result != null);
        assert (result.size() == request.size());
        assert (result.get("testText") != null);
        assert (result.get("testText").size() == statementWordCountMap.size());
        for (Map.Entry<String, Integer> expectedEntry : statementWordCountMap.entrySet()) {
            assert (result.get("testText").get(expectedEntry.getKey()) == expectedEntry.getValue());
        }
    }

}
