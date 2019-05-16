package com.optanix.test.web.controller;

import com.optanix.test.model.ApiError;
import com.optanix.test.model.ApplicationException;
import com.optanix.test.service.CharacterOccuranceCountService;
import com.optanix.test.service.WordCountService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author jignesh
 * This is controller class which provides Restful endpoint for word_count_per_sentence and total_letter_count services
 */
@RestController
public class ApiController {

    @Autowired
    private WordCountService wordCountService;

    @Autowired
    private CharacterOccuranceCountService characterOccuranceCountService;

    @PostMapping(value = "/word_count_per_sentence", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ApiOperation(value = "Calculate words count per sentences in a paragraph.")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "code: INVALID_REQUEST_DATA, reason: Input request should have at-least one key/value pair representing key=paragraph identifier and value=actual paragraph text to be processed.", response = ApiError.class),
            @ApiResponse(code = 500, message = "code: GENERIC_EXCEPTION, reason: Failed to process request due to an error", response = ApiError.class)
    })
    public ResponseEntity<Map<String, Map<String, Integer>>> calculateWordsCountPerSentence(@RequestBody Map<String, String> request) {

        if (request == null || request.size() == 0) {
            throw new ApplicationException(400, "INVALID_REQUEST_DATA", "Input request should have at-least one key/value pair representing key=paragraph identifier and value=actual paragraph text to be processed");
        }
        return ResponseEntity.ok(wordCountService.countWordsPerSentence(request));
    }

    @PostMapping(value = "/total_letter_count", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ApiOperation(value = "Count total number of occurrences for each case insensitive letter in entire text")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "code: INVALID_REQUEST_DATA, reason: Input request should have at-least one key/value pair representing key=sentence identifier and value=actual text to be processed.", response = ApiError.class),
            @ApiResponse(code = 500, message = "code: GENERIC_EXCEPTION, reason: Failed to process request due to an error", response = ApiError.class)
    })
    public ResponseEntity<Map<String, Map<Character, Integer>>> calculateTotalLetterOccurance(@RequestBody Map<String, String> request) {

        if (request == null || request.size() == 0) {
            throw new ApplicationException(400, "INVALID_REQUEST_DATA", "Input request should have at-least one key/value pair representing key=sentence identifier and value=actual text to be processed");
        }
        return ResponseEntity.ok(characterOccuranceCountService.countCharacterOccurances(request));
    }

}
