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

public class WhatsMyResidenceIntentHandlerTest {
    private WhatsMyResidenceIntentHandler handler;
    private final static String NAME = "Testuser";
    private final static String RESIDENCE = "Berlin";

    @Before
    public void setup() {
        handler = new WhatsMyResidenceIntentHandler();
    }

    @Test
    public void testCanHandle() {
        final HandlerInput inputMock = Mockito.mock(HandlerInput.class);
        when(inputMock.matches(any())).thenReturn(true);
        assertTrue(handler.canHandle(inputMock));
    }

    @Test
    public void testHandle() {
        final Map<String, Object> sessionAttributes = new HashMap<>();
        final Map<String, Object> persistentAttributes = new HashMap<>();
        sessionAttributes.put(PhrasesAndConstants.NAME_KEY, NAME);
        Related related = new Related(NAME);
        related.setResidence("Berlin");
        DBHandler handler1 = new DBHandler();
        String byteArr =  handler1.serealize(related);
        persistentAttributes.put(NAME, byteArr);
        final Response response = TestUtil.standardTestForHandle(handler, sessionAttributes, persistentAttributes);
        assertTrue(response.getOutputSpeech().toString().contains(PhrasesAndConstants.RESIDENCE_IS));
        assertTrue(response.getOutputSpeech().toString().contains(RESIDENCE));
    }

    @Test
    public void testHandleNullObject() {
        final Map<String, Object> sessionAttributes = new HashMap<>();
        final Map<String, Object> persistentAttributes = new HashMap<>();
        sessionAttributes.put(PhrasesAndConstants.NAME_KEY, NAME);
        Related related = new Related(NAME);
        DBHandler handler1 = new DBHandler();
        String byteArr =  handler1.serealize(related);
        persistentAttributes.put(NAME, byteArr);
        final Response response = TestUtil.standardTestForHandle(handler, sessionAttributes, persistentAttributes);
        assertTrue(response.getOutputSpeech().toString().contains(PhrasesAndConstants.RESIDENCE_UNKNOWN));
    }

    /*@Test(expected = AssertionError.class)
    public void testHandleNullRelated() {
        final Map<String, Object> sessionAttributes = new HashMap<>();
        final Map<String, Object> persistentAttributes = new HashMap<>();
        sessionAttributes.put(PhrasesAndConstants.NAME_KEY, NAME);
        Related related = new Related("Hans");
        related.setResidence("Berlin");
        DBHandler handler1 = new DBHandler();
        String byteArr =  handler1.serealize(related);
        persistentAttributes.put("Hans", byteArr);
        final Response response = TestUtil.standardTestForHandle(handler, sessionAttributes, persistentAttributes);
        assertTrue(response.getOutputSpeech().toString().contains(PhrasesAndConstants.RESIDENCE_UNKNOWN));
    }*/
}
