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
import fr.donovan.spotifish.entity.User;
import fr.donovan.spotifish.entity.interfaces.PermissionEntityInterface;
import fr.donovan.spotifish.security.CanEditService;
import fr.donovan.spotifish.security.SecurityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

public class CustomEntitySerializer extends StdSerializer<PermissionEntityInterface> {

    private final SecurityService securityService;

    private final JsonSerializer<Object> defaultSerializer;

    protected CustomEntitySerializer(JsonSerializer<Object> defaultSerializer, SecurityService securityService) {
        super(PermissionEntityInterface.class);
        this.securityService = securityService;
        this.defaultSerializer = defaultSerializer;
        System.out.println("CustomEntitySerializer.CustomEntitySerializer");
    }

    @Override
    public void serialize(
            PermissionEntityInterface permissionEntityInterface,
            JsonGenerator jsonGenerator,
            SerializerProvider serializerProvider) throws IOException {

        jsonGenerator.writeStartObject();
        jsonGenerator.writeFieldName("data");
        defaultSerializer.serialize(permissionEntityInterface, jsonGenerator, serializerProvider);
        User user = (User) securityService.getCurrentUser();
        jsonGenerator.writeFieldName("permission");
        jsonGenerator.writeStartObject();
        jsonGenerator.writeBooleanField("canEdit", permissionEntityInterface.canEdit(user));
        jsonGenerator.writeBooleanField("canDelete", permissionEntityInterface.canDelete(user));
        jsonGenerator.writeEndObject();

        jsonGenerator.writeEndObject();
    }
}
