package sam_bekannter.handlers;

import sam_bekannter.model.DBHandler;
import sam_bekannter.model.Related;
import com.amazon.ask.attributes.AttributesManager;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.*;
import com.amazon.ask.response.ResponseBuilder;
import sam_bekannter.PhrasesAndConstants;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class TestUtil {

    private final static String NAME = "Testuser";

    public static HandlerInput mockHandlerInput(String name,
                                                String slot,
                                                Map<String, Object> sessionAttributes,
                                                Map<String, Object> persistentAttributes,
                                                Map<String, Object> requestAttributes) {
        final AttributesManager attributesManagerMock = Mockito.mock(AttributesManager.class);
        when(attributesManagerMock.getSessionAttributes()).thenReturn(sessionAttributes);
        when(attributesManagerMock.getPersistentAttributes()).thenReturn(persistentAttributes);
        when(attributesManagerMock.getRequestAttributes()).thenReturn(requestAttributes);

        // Mock Slots
        final RequestEnvelope requestEnvelopeMock = RequestEnvelope.builder()
                .withRequest(IntentRequest.builder()
                        .withIntent(Intent.builder()
                                .putSlotsItem(slot, Slot.builder()
                                        .withName(PhrasesAndConstants.NAME_SLOT)
                                        .withValue(name)
                                        .build())
                                .build())
                        .build())
                .build();


        // Mock Handler input attributes
        final HandlerInput input = Mockito.mock(HandlerInput.class);
        when(input.getAttributesManager()).thenReturn(attributesManagerMock);
        when(input.getResponseBuilder()).thenReturn(new ResponseBuilder());
        when(input.getRequestEnvelope()).thenReturn(requestEnvelopeMock);

        return input;
    }

    public static Response standardTestForHandle(RequestHandler handler, final Map<String, Object> sessionAttributes, final Map<String, Object> persistentAttributes) {
        final HandlerInput inputMock = TestUtil.mockHandlerInput(null, PhrasesAndConstants.NAME_SLOT, sessionAttributes, persistentAttributes, null);
        final Optional<Response> res = handler.handle(inputMock);

        assertTrue(res.isPresent());
        final Response response = res.get();

        //assertFalse(response.getShouldEndSession());
        assertNotEquals("Test", response.getReprompt());
        assertNotNull(response.getOutputSpeech());
        return response;
    }

    public static Response withInput(String input, String slot, RequestHandler handler, final Map<String, Object> sessionAttributes, final Map<String, Object> persistentAttributes) {
        final HandlerInput inputMock = TestUtil.mockHandlerInput(input, slot, sessionAttributes, persistentAttributes, null);
        final Optional<Response> res = handler.handle(inputMock);

        assertTrue(res.isPresent());
        final Response response = res.get();

        //assertFalse(response.getShouldEndSession());
        assertNotEquals("Test", response.getReprompt());
        assertNotNull(response.getOutputSpeech());
        return response;
    }

    /*public static Response sessionAttributesTestForHandle(RequestHandler handler, final Map<String, Object> sessionAttributes, final Map<String, Object> persistentAttributes, final Map<String, Object> requestAttributes) {
        final HandlerInput inputMock = TestUtil.mockHandlerInput("Peter", sessionAttributes, persistentAttributes, null);
        final Optional<Response> res = handler.handle(inputMock);

        assertTrue(res.isPresent());
        final Response response = res.get();

        //in the WhatsMyColorIntentHandler
        //assertTrue(response.getShouldEndSession());
        assertNotNull(response.getOutputSpeech());
        return response;
    }
    public static Response persistentAttributesTestForHandle(RequestHandler handler) {
        Related related = new Related(NAME);
        DBHandler dbhandler = new DBHandler();
        String byteArr = dbhandler.serealize(related);
        final Map<String, Object> persistentAttributes = new HashMap<>();
        persistentAttributes.put(NAME, byteArr);
        final HandlerInput inputMock = TestUtil.mockHandlerInput(null, null, persistentAttributes, null);
        final Optional<Response> res = handler.handle(inputMock);

        assertTrue(res.isPresent());
        final Response response = res.get();

        //in the WhatsMyColorIntentHandler
        //assertTrue(response.getShouldEndSession());
        assertNotNull(response.getOutputSpeech());
        return response;
    }

    public static Response noAttributesTestForHandle(RequestHandler handler) {
        final HandlerInput inputMock = TestUtil.mockHandlerInput(null, null, null, null);
        final Optional<Response> res = handler.handle(inputMock);

        assertTrue(res.isPresent());
        final Response response = res.get();

        assertFalse(response.getShouldEndSession());
        assertNotNull(response.getOutputSpeech());
        return response;
    }
    public static Response sessionEndedTestForHandle(RequestHandler handler) {
        final HandlerInput inputMock = TestUtil.mockHandlerInput(null, null, null, null);
        final Optional<Response> res = handler.handle(inputMock);

        assertTrue(res.isPresent());
        final Response response = res.get();
        assertTrue(response.getShouldEndSession());
        return response;
    }*/
}
