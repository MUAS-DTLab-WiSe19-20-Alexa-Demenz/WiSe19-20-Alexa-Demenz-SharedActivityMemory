package sam_bekannter.model;

import com.amazon.ask.exception.PersistenceException;
import com.amazon.ask.model.*;
import com.amazon.ask.model.interfaces.system.SystemState;
import java.util.Optional;
import java.util.function.Function;

public class MyPartitionKeyGenerators {
    private MyPartitionKeyGenerators() {
    }

    public static Function<RequestEnvelope, String> userId() {
        return r -> (String)Optional.ofNullable(r).map(RequestEnvelope::getContext).map(Context::getSystem).map(SystemState::getUser).map(User::getUserId).orElseThrow(() -> new PersistenceException("Could not retrieve user ID from request envelope to generate persistence ID"));
    }

    public static Function<RequestEnvelope, String> applicationId() {
        return r -> (String)Optional.ofNullable(r).map(RequestEnvelope::getContext).map(Context::getSystem).map(SystemState::getApplication).map(Application::getApplicationId).orElseThrow(() -> new PersistenceException("Could not retrieve Application ID from request envelope to generate persistence ID"));
    }
    public static Function<RequestEnvelope, String> myKey() {
        return r -> "Test";
    }
    public static Function<RequestEnvelope, String> withName(String name) {
        return r -> name;
    }


}
