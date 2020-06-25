package sam_bekannter.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import sam_bekannter.PhrasesAndConstants;
import sam_bekannter.model.DBHandler;
import sam_bekannter.model.Related;


import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class WhatsTheirAgeIntentHandlerTest {
    private WhatsTheirAgeIntentHandler handler;
    private final static String NAME = "Testuser";
    private final static String AGE = "14";
    private final static String NAME2 = "Nico";
    private final static String AGE2 = "20";

    @Before
    public void setup() {
        handler = new WhatsTheirAgeIntentHandler();
    }

    @Test
    public void testCanHandle() {
        final HandlerInput inputMock = Mockito.mock(HandlerInput.class);
        when(inputMock.matches(any())).thenReturn(true);
        assertTrue(handler.canHandle(inputMock));
    }

    @Test
    public void testHandleEverythingFine() {
        final Map<String, Object> sessionAttributes = new HashMap<>();
        final Map<String, Object> persistentAttributes = new HashMap<>();
        Related related = new Related(NAME);
        Related related2 = new Related(NAME2);
        related.setAge(AGE);
        related2.setAge(AGE2);
        DBHandler handler1 = new DBHandler();
        String byteArr =  handler1.serealize(related);
        persistentAttributes.put(NAME, byteArr);
        byteArr = handler1.serealize(related2);
        persistentAttributes.put(NAME2, byteArr);
        final Response response = TestUtil.withInput(NAME, PhrasesAndConstants.NAME_SLOT, handler, sessionAttributes, persistentAttributes);
        assertTrue(response.getOutputSpeech().toString().contains(PhrasesAndConstants.HISAGE));
    }

    @Test
    public void testHandleNoRelatedObject() {
        final Map<String, Object> sessionAttributes = new HashMap<>();
        final Map<String, Object> persistentAttributes = new HashMap<>();
        Related related = new Related(NAME);
        Related related2 = new Related(NAME2);
        related.setAge(AGE);
        related2.setAge(AGE2);
        DBHandler handler1 = new DBHandler();
        String byteArr =  handler1.serealize(related);
        persistentAttributes.put(NAME, byteArr);
        byteArr = handler1.serealize(related2);
        persistentAttributes.put(NAME2, byteArr);
        final Response response = TestUtil.withInput("Hans", PhrasesAndConstants.NAME_SLOT, handler, sessionAttributes, persistentAttributes);
        assertTrue(response.getOutputSpeech().toString().contains(PhrasesAndConstants.NAME_NOT_THERE));
    }

    @Test
    public void testHandleNoSavedAttribute() {
        final Map<String, Object> sessionAttributes = new HashMap<>();
        final Map<String, Object> persistentAttributes = new HashMap<>();
        Related related = new Related(NAME);
        Related related2 = new Related(NAME2);
        related.setAge(AGE);
        DBHandler handler1 = new DBHandler();
        String byteArr =  handler1.serealize(related);
        persistentAttributes.put(NAME, byteArr);
        byteArr = handler1.serealize(related2);
        persistentAttributes.put(NAME2, byteArr);
        final Response response = TestUtil.withInput(NAME2, PhrasesAndConstants.NAME_SLOT, handler, sessionAttributes, persistentAttributes);
        assertTrue(response.getOutputSpeech().toString().contains(PhrasesAndConstants.HIS_AGE_UNKNOWN));
    }
}