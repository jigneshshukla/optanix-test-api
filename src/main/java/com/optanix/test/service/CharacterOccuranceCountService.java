package com.optanix.test.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author jignesh
 * This class provide service methods to count total number of character occurrences in entire text in case-insensitive way.
 */
@Service
public class CharacterOccuranceCountService {

    /**
     * This method counts total number of character occurrences in entire text in case-insensitive way and returns
     * a Map of key/value pair where key = Paragraph name and value = map of character to number of occurrences map.
     *
     * @return Map<String   ,   Map   <   Character   ,   Integer>>
     */
    public Map<String, Map<Character, Integer>> countCharacterOccurances(Map<String, String> data) {
        Map<String, Map<Character, Integer>> result = new HashMap<>();
        if (data == null || data.size() == 0) {
            return result;
        }
        data.entrySet().stream().forEach(entry -> {
            List<Character> distinctCharacters = getDistinctCharacters(entry.getValue());
            result.put(entry.getKey(), getCharacterOccurrences(distinctCharacters, entry.getValue()));
        });
        return result;
    }

    /**
     * This method calculates total number of occurrences for every distinct letter in given text in case-insensitive way
     *
     * @param characters
     * @param text
     * @return Map<Character   ,   Integer>
     */
    private Map<Character, Integer> getCharacterOccurrences(List<Character> characters, String text) {
        Map<Character, Integer> characterOccurrenceMap = new HashMap<>();
        characters.stream().forEach(character ->
                characterOccurrenceMap.put(character, StringUtils.countOccurrencesOf(text.trim().toLowerCase(), character.toString())));
        return characterOccurrenceMap;
    }

    /**
     * This method returns List of distinct letters in given text in case-insensitive way
     *
     * @param text
     * @return List<Character>
     */
    private List<Character> getDistinctCharacters(String text) {
        return text.trim().toLowerCase().chars().distinct().mapToObj(e -> (char) e).collect(Collectors.toList());
    }
}
