package sam_bekannter.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import sam_bekannter.PhrasesAndConstants;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class LaunchRequestHandlerSickTest {
    private LaunchRequestHandlerSick handler;

    @Before
    public void setup() {
        handler = new LaunchRequestHandlerSick();
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
        final Response response = TestUtil.standardTestForHandle(handler, sessionAttributes, persistentAttributes);
        assertTrue(response.getOutputSpeech().toString().contains(PhrasesAndConstants.WELCOME_SICK + PhrasesAndConstants.EXAMPLE));
    }
}
