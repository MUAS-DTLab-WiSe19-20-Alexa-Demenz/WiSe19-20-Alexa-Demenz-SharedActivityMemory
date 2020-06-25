package sam_bekannter.model;

import com.amazon.ask.attributes.AttributesManager;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Request;
import com.amazon.ask.model.Slot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sam_bekannter.PhrasesAndConstants;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DBHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(DBHandler.class);
    public String serealize(Related related) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        String byteString = "";
        try (
                ObjectOutput out = new ObjectOutputStream(bos)
                ){
            out.writeObject(related);
            out.flush();
            byte[] byteArr = bos.toByteArray();
            byteString = Arrays.toString(byteArr);
        } catch (IOException e) {
            LOGGER.error("IOException", e);
        } finally {
            try {
                bos.close();
            } catch (IOException ex) {
                // ignore close exception
            }
        }
        return byteString;
    }
    public Related desearialize(String byteStr) throws ClassNotFoundException {
        //parse the String back to a byte array
        String[] byteValues = byteStr.substring(1, byteStr.length() -1).split(",");
        byte[] bytes = new byte[byteValues.length];
        for (int i =0; i < bytes.length; i++) {
            bytes[i] = Byte.parseByte(byteValues[i].trim());
        }
        // convert the byte array back to a related object
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        Related r = null;
        try (
                ObjectInput in = new ObjectInputStream(bis)
                ){
            r = (Related) in.readObject();
        } catch (IOException e) {
            LOGGER.error("IOException", e);
        } finally {
            try {
                bis.close();
            } catch (IOException ex) {
                // ignore close exception
            }
        }
        return r;
    }
    public Related getRelated(HandlerInput input) {
        //Initialize the attribute Manager and get the persistent and session attributes
        AttributesManager attributesManager = input.getAttributesManager();
        Map<String, Object> persistentAttributes = attributesManager.getPersistentAttributes();

        //Get the slots from the request
        Request request = input.getRequestEnvelope().getRequest();
        IntentRequest intentRequest = (IntentRequest) request;
        Intent intent = intentRequest.getIntent();
        Map<String, Slot> slots = intent.getSlots();
        Slot relationSlot = slots.get(PhrasesAndConstants.RELATION_SLOT);
        Slot nameSlot = slots.get(PhrasesAndConstants.NAME_SLOT);
        String name = "";
        String relation = "";
        if(nameSlot != null) {
            name = nameSlot.getValue();
        }
        if(relationSlot != null) {
            relation = relationSlot.getValue();
        }
        Map<String, Related> relateds = new HashMap();
        for (Map.Entry<String,Object> entry : persistentAttributes.entrySet()) {
            String key = entry.getKey();
            String byteArr = (String) persistentAttributes.get(key);
            Related related = null;
            try {
                related = desearialize(byteArr);
            } catch (ClassNotFoundException e) {
                LOGGER.error("ClassNotFoundException", e);
            }
            if (related == null) {
                LOGGER.error("Related should not be null");
            }
            relateds.put(key, related);
        }
        if(relation != null) {
            for(Map.Entry<String,Related> entry : relateds.entrySet()) {
                String key = entry.getKey();
                if(relateds.get(key).isRelationValid() && relateds.get(key).getRelation().equalsIgnoreCase(relation)) {
                    name = relateds.get(key).getName();
                }
            }
        }
        return relateds.get(name);
    }
    public Related getRelatedByName(HandlerInput input) {
        AttributesManager attributesManager = input.getAttributesManager();
        Map<String, Object> persistentAttributes = attributesManager.getPersistentAttributes();
        Map<String, Object> sessionAttributes = attributesManager.getSessionAttributes();
        //get the name form the session attributes
        String name = (String) sessionAttributes.get(PhrasesAndConstants.NAME_KEY);
        //Get the bytearray, convert it back to realted object and get the residence
        String byteArr = (String) persistentAttributes.get(name);
        Related related = null;
        if(byteArr != null) {
            try {
                related = desearialize(byteArr);
            } catch (ClassNotFoundException e) {
                LOGGER.error("ClassNotFoundException", e);
            }
            if (related == null) {
                LOGGER.error("Related should not be null");
            }
        }
        return related;
    }

    public void setRelatedAttribute(HandlerInput input) {
        Request request = input.getRequestEnvelope().getRequest();
        IntentRequest intentRequest = (IntentRequest) request;
        Intent intent = intentRequest.getIntent();
        Map<String, Slot> slots = intent.getSlots();
        Slot relationSlot = slots.get(PhrasesAndConstants.RELATION_SLOT);
        Slot nameSlot = slots.get(PhrasesAndConstants.NAME_SLOT);
        Slot holidaySlot = slots.get(PhrasesAndConstants.HOLIDAY_SLOT);
        Slot jobSlot = slots.get(PhrasesAndConstants.JOB_SLOT);
        Slot residenceSlot = slots.get(PhrasesAndConstants.RESIDENCE_SLOT);
        Related related = null;
        if(nameSlot != null) {
            related = new Related(nameSlot.getValue());
        } else {
            related = getRelatedByName(input);
            if(relationSlot != null) {
                related.setRelation(relationSlot.getValue());
            }
            if(holidaySlot != null) {
                related.setHoliday(holidaySlot.getValue());
            }
            if(jobSlot != null) {
                related.setJob(jobSlot.getValue());
            }
            if(residenceSlot != null) {
                related.setResidence(residenceSlot.getValue());
            }
        }
        AttributesManager attributesManager = input.getAttributesManager();
        Map<String, Object> persistentAttributes = attributesManager.getPersistentAttributes();
        String name = related.getName();
        String byteArr = serealize(related);
        persistentAttributes.put(name, byteArr);
        attributesManager.setPersistentAttributes(persistentAttributes);
        attributesManager.savePersistentAttributes();
    }
}
