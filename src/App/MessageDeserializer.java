package App;

import Model.*;
import com.google.gson.*;

import java.lang.reflect.Type;

public class MessageDeserializer implements JsonDeserializer<Message> {
    @Override
    public Message deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        String message = jsonObject.get("message").getAsString();
        String objectType = jsonObject.get("objectType").getAsString();
        Object payload = null;
        switch (objectType){
            case "Question":
                payload = context.deserialize(jsonObject.get("payload"), Question.class);
                break;
            case "Quiz":
                payload = context.deserialize(jsonObject.get("payload"), Quiz.class);
                break;
            case "Answer":
                payload = context.deserialize(jsonObject.get("payload"), Answer.class);
                break;
            case "JoinQuizRequest":
                payload = context.deserialize(jsonObject.get("payload"), JoinQuizRequest.class);
                break;
            case "Integer":
                payload = context.deserialize(jsonObject.get("payload"), Integer.class);
                break;
            case "String":
                payload = context.deserialize(jsonObject.get("payload"), String.class);
                break;
            case "CreateQuizRequest":
                payload = context.deserialize(jsonObject.get("payload"), CreateQuizRequest.class);
                break;

        }

        return new Message(message, objectType, payload);

    }
}
