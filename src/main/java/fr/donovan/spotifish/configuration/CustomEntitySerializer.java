package fr.donovan.spotifish.configuration;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.ObjectIdWriter;
import com.fasterxml.jackson.databind.ser.std.BeanSerializerBase;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import fr.donovan.spotifish.entity.interfaces.EntityInterface;
import fr.donovan.spotifish.security.CanEditService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

public class CustomEntitySerializer extends StdSerializer<EntityInterface> {

    private final CanEditService canEditService;

    private final JsonSerializer<Object> defaultSerializer;

    protected CustomEntitySerializer(JsonSerializer<Object> defaultSerializer, CanEditService canEditService) {
        super(EntityInterface.class);
        this.canEditService = canEditService;
        this.defaultSerializer = defaultSerializer;
        System.out.println("CustomEntitySerializer.CustomEntitySerializer");
    }

    @Override
    public void serialize(
            EntityInterface entityInterface,
            JsonGenerator jsonGenerator,
            SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
//        defaultSerializer.serialize(entityInterface, jsonGenerator, serializerProvider);
//        serializerProvider.defaultSerializeValue(entityInterface, jsonGenerator);
//        System.out.println("serializerProvider.findValueSerializer(entityInterface.getClass()) = " + serializerProvider.findValueSerializer(entityInterface.getClass()).properties());
//        for (Iterator<PropertyWriter> it = serializerProvider.findValueSerializer(entityInterface.getClass()).properties(); it.hasNext(); ) {
//            BeanPropertyWriter writer = (BeanPropertyWriter) it.next();
//            System.out.println("writer = " + writer);
//            try {
//                writer.serializeAsField(entityInterface, jsonGenerator, serializerProvider);
//                System.out.println("reussi");
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//        }
        jsonGenerator.writeBooleanField("canEdit", canEditService.canEdit(entityInterface));
        jsonGenerator.writeEndObject();
    }
}
