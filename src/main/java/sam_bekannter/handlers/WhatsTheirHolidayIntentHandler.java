package sam_bekannter.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.*;
import com.amazon.ask.response.ResponseBuilder;
import sam_bekannter.PhrasesAndConstants;
import sam_bekannter.model.DBHandler;
import sam_bekannter.model.Related;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;


public class WhatsTheirHolidayIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("WhatsTheirHolidayIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        DBHandler dbhandler = new DBHandler();
        Related related = dbhandler.getRelated(input);
        ResponseBuilder responseBuilder = input.getResponseBuilder();
        String speechText;
        if(related == null) {
            speechText = PhrasesAndConstants.NAME_NOT_THERE;
        }
        else if (related.isHolidayValid()) {
            speechText = String.format("%s %s %s. %s", related.getName(), PhrasesAndConstants.HISHOLIDAY, related.getHoliday(), PhrasesAndConstants.MORE_INFORMATION);
        } else {
            speechText = String.format("%s %s %s", related.getName(), PhrasesAndConstants.HIS_HOLIDAY_UNKNOWN, PhrasesAndConstants.MORE_INFORMATION);
        }
        responseBuilder.withSpeech(speechText)
                .withSimpleCard(PhrasesAndConstants.CARD_TITLE, speechText)
                .withShouldEndSession(false);
        return responseBuilder.build();
    }
}
