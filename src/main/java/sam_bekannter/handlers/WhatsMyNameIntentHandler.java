package sam_bekannter.handlers;

import sam_bekannter.model.DBHandler;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.response.ResponseBuilder;
import sam_bekannter.PhrasesAndConstants;
import sam_bekannter.model.Related;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;


public class WhatsMyNameIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("WhatIsMyNameIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        DBHandler handler = new DBHandler();
        Related related = handler.getRelatedByName(input);
        ResponseBuilder responseBuilder = input.getResponseBuilder();

        if (related.isNameValid()) {
            String speechText = String.format("%s %s. %s", PhrasesAndConstants.NAME_IS, related.getName(), PhrasesAndConstants.FURTHER_HELP);
            responseBuilder.withSpeech(speechText)
                    .withSimpleCard(PhrasesAndConstants.CARD_TITLE, speechText)
                    .withShouldEndSession(false);
        }
        return responseBuilder.build();
    }
}

