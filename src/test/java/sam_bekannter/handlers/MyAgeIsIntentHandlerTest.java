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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class MyAgeIsIntentHandlerTest {
    private MyAgeIsIntentHandler handler;
    private final static String NAME = "Testuser";

    @Before
    public void setup() {
        handler = new MyAgeIsIntentHandler();
    }

    @Test
    public void testCanHandle() {
        final HandlerInput inputMock = Mockito.mock(HandlerInput.class);
        when(inputMock.matches(any())).thenReturn(true);
        assertTrue(handler.canHandle(inputMock));
    }

    @Test
    public void testCanNotHandle() {
        final HandlerInput inputMock = Mockito.mock(HandlerInput.class);
        when(inputMock.matches(any())).thenReturn(false);
        assertFalse(handler.canHandle(inputMock));
    }

    @Test
    public void testCanHandleSwitches() {
        final HandlerInput inputMock = Mockito.mock(HandlerInput.class);
        when(inputMock.matches(any())).thenReturn(true);
        assertTrue(handler.canHandle(inputMock));
        when(inputMock.matches(any())).thenReturn(false);
        assertFalse(handler.canHandle(inputMock));
    }

    @Test
    public void testHandle() {
        final Map<String, Object> sessionAttributes = new HashMap<>();
        final Map<String, Object> persistentAttributes = new HashMap<>();
        sessionAttributes.put(PhrasesAndConstants.NAME_KEY, NAME);
        Related related = new Related(NAME);
        DBHandler handler1 = new DBHandler();
        String byteArr =  handler1.serealize(related);
        persistentAttributes.put(NAME, byteArr);
        final Response response = TestUtil.withInput("drei", PhrasesAndConstants.AGE_SLOT, handler, sessionAttributes, persistentAttributes);
        assertTrue(response.getOutputSpeech().toString().contains(PhrasesAndConstants.AGE_SAVED));
    }
}
