package com.optanix.test.service;

import org.springframework.stereotype.Service;

import java.text.BreakIterator;
import java.util.*;

/**
 * @author jignesh
 * This class provide service methods to count words in each sentences of a paragraph
 */
@Service
public class WordCountService {

    /**
     * This method counts words in each sentence of paragraphs and returns
     * a Map of key/value pair where key = Paragraph name and value = map of sentence to number of words map.
     *
     * @return Map<String   ,   Map   <   String   ,   Integer>>
     */
    public Map<String, Map<String, Integer>> countWordsPerSentence(Map<String, String> data) {
        Map<String, Map<String, Integer>> result = new LinkedHashMap<>();
        if (data == null || data.size() == 0) {
            return result;
        }

        for (Map.Entry<String, String> entry : data.entrySet()) {
            result.put(entry.getKey(), getWordCountPerSentenceMap(getSentences(entry.getValue().trim())));
        }
        return result;
    }

    /**
     * This method returns a map consisting of key = text and value = number of words in given text
     *
     * @param sentences
     * @return
     */
    private Map<String, Integer> getWordCountPerSentenceMap(List<String> sentences) {
        Map<String, Integer> wordCountPerSentenceMap = new LinkedHashMap<>();
        sentences.stream().forEach(sentence -> wordCountPerSentenceMap.put(sentence.trim(), sentence.trim().split(" ").length));
        return wordCountPerSentenceMap;
    }

    /**
     * This method gets List of sentences in a paragraph with correct interpretation of periods within numbers and abbreviations
     * and trailing punctuation marks such as Question Mark, Excalamation Mark etc.
     *
     * @param paragraph
     * @return
     */
    private List<String> getSentences(String paragraph) {
        BreakIterator breakIterator = BreakIterator.getSentenceInstance(Locale.US);
        breakIterator.setText(paragraph);
        List<String> sentences = new ArrayList<>();
        int start = breakIterator.first();
        for (int end = breakIterator.next(); end != breakIterator.DONE; start = end, end = breakIterator.next()) {
            sentences.add(paragraph.substring(start, end));
        }
        return sentences;
    }
}
