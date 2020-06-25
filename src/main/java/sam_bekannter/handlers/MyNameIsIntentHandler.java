package sam_bekannter.handlers;

import sam_bekannter.PhrasesAndConstants;
import sam_bekannter.model.Related;
import sam_bekannter.model.DBHandler;
import com.amazon.ask.attributes.AttributesManager;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.*;
import com.amazon.ask.response.ResponseBuilder;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class MyNameIsIntentHandler implements RequestHandler {
    @Override
    public boolean canHandle(HandlerInput input) {
           return input.matches(intentName("MyNameIsIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {

        Request request = input.getRequestEnvelope().getRequest();
        IntentRequest intentRequest = (IntentRequest) request;
        Intent intent = intentRequest.getIntent();
        Map<String, Slot> slots = intent.getSlots();

        // Get the name slot from the list of slots.
        Slot nameSlot = slots.get(PhrasesAndConstants.NAME_SLOT);
        ResponseBuilder responseBuilder = input.getResponseBuilder();


         if (nameSlot.getValue() != null ) {
            // Store the user's favorite color in the Session and store in DB then create response.
            input.getAttributesManager().setSessionAttributes(Collections.singletonMap(PhrasesAndConstants.NAME_KEY, nameSlot.getValue()));

            //Check if there already is an related object in the Database and create a new one if not
             // Get persistent attributes
             AttributesManager attributesManager = input.getAttributesManager();
             Map<String, Object> persistentAttributes = attributesManager.getPersistentAttributes();
             if(!persistentAttributes.containsKey(nameSlot.getValue())) {
                 Related related = new Related(nameSlot.getValue());
                 //store persistent
                 //Convert related Object to bytes in order to save it to dynamo db
                 DBHandler dbhandler = new DBHandler();
                 String toSave = dbhandler.serealize(related);
                 persistentAttributes.put(related.getName(), toSave);
                 attributesManager.setPersistentAttributes(persistentAttributes);
                 attributesManager.savePersistentAttributes();
                 String speechText =
                         String.format("%s", PhrasesAndConstants.NEWNAME);
                 responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, speechText)
                         .withSpeech(speechText)
                         .withShouldEndSession(false);
             } else {
                 //User is already known. Tell user that he can update his attributes
                 String speechText =
                         String.format("%s %s %s", "Hallo", nameSlot.getValue(), PhrasesAndConstants.OLDNAME);
                 responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, speechText)
                         .withSpeech(speechText)
                         .withShouldEndSession(false);
             }
        } else {
            // Render an error since we don't know what the users favorite color is.
            responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, PhrasesAndConstants.SAY_NAME_REPROMPT )
                    .withSpeech(PhrasesAndConstants.SAY_NAME_REPROMPT )
                    .withReprompt(PhrasesAndConstants.SAY_NAME_REPROMPT )
                    .withShouldEndSession(false);
         }
        return responseBuilder.build();
    }

}
