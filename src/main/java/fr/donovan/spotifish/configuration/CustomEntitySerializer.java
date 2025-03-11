package fr.donovan.spotifish.configuration;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import fr.donovan.spotifish.entity.User;
import fr.donovan.spotifish.entity.interfaces.PermissionEntityInterface;
import fr.donovan.spotifish.security.SecurityService;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.IOException;

public class CustomEntitySerializer extends StdSerializer<PermissionEntityInterface> {

    private final SecurityService securityService;

    private final JsonSerializer<Object> defaultSerializer;

    protected CustomEntitySerializer(JsonSerializer<Object> defaultSerializer, SecurityService securityService) {
        super(PermissionEntityInterface.class);
        this.securityService = securityService;
        this.defaultSerializer = defaultSerializer;
    }

    @Override
    public void serialize(
            PermissionEntityInterface permissionEntityInterface,
            JsonGenerator jsonGenerator,
            SerializerProvider serializerProvider) throws IOException {

        jsonGenerator.writeStartObject();
        jsonGenerator.writeFieldName("data");
        defaultSerializer.serialize(permissionEntityInterface, jsonGenerator, serializerProvider);
        User user = securityService.getCurrentUser();
        jsonGenerator.writeFieldName("permission");
        jsonGenerator.writeStartObject();
        jsonGenerator.writeBooleanField("canEdit", permissionEntityInterface.canEdit(user));
        jsonGenerator.writeBooleanField("canDelete", permissionEntityInterface.canDelete(user));
        jsonGenerator.writeEndObject();

        jsonGenerator.writeEndObject();
    }
}
