package sam_bekannter.handlers;

import sam_bekannter.PhrasesAndConstants;
import sam_bekannter.model.DBHandler;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.*;
import com.amazon.ask.response.ResponseBuilder;


import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class MyJobIsIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("MyJobIsIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        DBHandler dbhandler = new DBHandler();
        dbhandler.setRelatedAttribute(input);
        ResponseBuilder responseBuilder = input.getResponseBuilder();
        String speechText =
                String.format("%s", PhrasesAndConstants.JOB_SAVED);
        responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, speechText)
                .withSpeech(speechText)
                .withShouldEndSession(false);
        return responseBuilder.build();


    }

}
