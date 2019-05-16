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
 * This class provides junit tests for CharacterOccuranceCountService
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CharacterOccurrenceCountServiceTest {

    @Autowired
    private CharacterOccuranceCountService characterOccuranceCountService;

    private static Map<String, String> getCharacterOccurrenceServiceRequest() {
        Map<String, String> data = new HashMap<>();
        data.put("testText", "Hello World!");
        return data;
    }

    private static Map<Character, Integer> getExpectedCharacterOccurrenceMap() {
        Map<Character, Integer> data = new HashMap<>();
        data.put('h', 1);
        data.put('e', 1);
        data.put('l', 3);
        data.put('o', 2);
        data.put('w', 1);
        data.put('r', 1);
        data.put('d', 1);
        data.put('!', 1);
        data.put(' ', 1);
        return data;
    }

    @Test
    public void countCharacterOccurrencesInString() {
        Map<String, String> request = getCharacterOccurrenceServiceRequest();
        validateSuccessfulResult(characterOccuranceCountService.countCharacterOccurances(request), request);
    }

    @Test
    public void countCharacterOccurrencesWithEmptyString() {
        Map<String, Map<Character, Integer>> result = characterOccuranceCountService.countCharacterOccurances(null);
        assert (result.size() == 0);
    }

    private void validateSuccessfulResult(Map<String, Map<Character, Integer>> result, Map<String, String> request) {
        Map<Character, Integer> characterOccurrenceMap = getExpectedCharacterOccurrenceMap();
        assert (result != null);
        assert (result.size() == request.size());
        assert (result.get("testText") != null);
        assert (result.get("testText").size() == characterOccurrenceMap.size());
        for (Map.Entry<Character, Integer> expectedEntry : characterOccurrenceMap.entrySet()) {
            assert (result.get("testText").get(expectedEntry.getKey()) == expectedEntry.getValue());
        }
    }

}
