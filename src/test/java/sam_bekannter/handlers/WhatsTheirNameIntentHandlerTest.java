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

public class WhatsTheirNameIntentHandlerTest {
    private WhatsTheirNameIntentHandler handler;
    private final static String NAME = "Testuser";

    @Before
    public void setup() {
        handler = new WhatsTheirNameIntentHandler();
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
        related.setRelation("Bruder");
        DBHandler handler1 = new DBHandler();
        String byteArr =  handler1.serealize(related);
        persistentAttributes.put(NAME, byteArr);
        final Response response = TestUtil.withInput("Bruder", PhrasesAndConstants.RELATION_SLOT, handler, sessionAttributes, persistentAttributes);
        assertTrue(response.getOutputSpeech().toString().contains(NAME));
    }

    @Test
    public void testHandleNoRelatedObject() {
        final Map<String, Object> sessionAttributes = new HashMap<>();
        final Map<String, Object> persistentAttributes = new HashMap<>();
        Related related = new Related(NAME);
        related.setRelation("Bruder");
        DBHandler handler1 = new DBHandler();
        String byteArr =  handler1.serealize(related);
        persistentAttributes.put(NAME, byteArr);
        final Response response = TestUtil.withInput("Schwester", PhrasesAndConstants.RELATION_SLOT, handler, sessionAttributes, persistentAttributes);
        assertTrue(response.getOutputSpeech().toString().contains(PhrasesAndConstants.NAME_NOT_THERE));
    }
}
