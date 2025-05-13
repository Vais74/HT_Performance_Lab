package com.example.newMock2.Controller;

import com.example.newMock2.Model.RequestDTO;
import com.example.newMock2.Model.ResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Random;

@RestController

public class MainController {

    private Logger log = LoggerFactory.getLogger(MainController.class);

    ObjectMapper mapper = new ObjectMapper();

    @PostMapping(
            value = "/info/postBalances",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )

    public Object postBalances(@RequestBody RequestDTO requestDTO) {



        try {
            Random rand = new Random();
            ResponseDTO responseDTO = new ResponseDTO();
            String clientId = requestDTO.getClientId();
            char firstChar = clientId.charAt(0);
            BigDecimal maxLimit;
            String rqUID = requestDTO.getRqUID();


            if (firstChar == '8') {
                maxLimit = new BigDecimal(2000);
                responseDTO.setCurrency("US");
            } else if (firstChar == '9') {
                maxLimit = new BigDecimal(1000);
                responseDTO.setCurrency("EU");
            } else {
                maxLimit = new BigDecimal(10000);
                responseDTO.setCurrency("RUB");
            }



//            ResponseDTO responseDTO1 = new ResponseDTO(
//                    rqUID,
//                    clientId,
//                    requestDTO.getAccount(),
//                    "RUB",
//                    new BigDecimal(777),
//                    maxLimit
//
//            );

            responseDTO.setRqUID(rqUID);
            responseDTO.setClientId(clientId);
            responseDTO.setAccount(requestDTO.getAccount());
            responseDTO.setBalance(new BigDecimal(rand.nextInt(maxLimit.intValue())));
            responseDTO.setMaxLimit(maxLimit);

            log.info("***** RequestDTO *****"+mapper.writerWithDefaultPrettyPrinter().writeValueAsString(requestDTO));
            log.info("***** ResponseDTO *****"+mapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseDTO));

            return responseDTO;

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
