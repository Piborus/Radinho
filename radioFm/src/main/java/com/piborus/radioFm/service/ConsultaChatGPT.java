package com.piborus.radioFm.service;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;

public class ConsultaChatGPT {

    public static String obterInformacao(String texto){
        OpenAiService service = new OpenAiService(System.getenv("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"));

        CompletionRequest requisicao = CompletionRequest.builder()
                .model("text-davinci-003")
                .prompt("me fale sobre o artista: " + texto)
                .maxTokens(1000)
                .temperature(0.7)
                .build();


        var resposta = service.createCompletion(requisicao);
        return resposta.getChoices().get(0).getText();
    }
}