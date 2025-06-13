package com.example.demo.endpoint.rest.controller.health;

import com.example.demo.service.ChatGPTTranslator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HazavaoController {

    ChatGPTTranslator chatGPTTranslator = new ChatGPTTranslator();

    @GetMapping("/hazavao")
    public String hazavao(@RequestParam("teny") String teny) throws Exception {
        return chatGPTTranslator.translate(teny);
    }

}

