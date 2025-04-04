package fr.donovan.spotifish.configuration;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import fr.donovan.spotifish.entity.Permission;
import fr.donovan.spotifish.entity.User;
import fr.donovan.spotifish.entity.interfaces.PermissionEntityInterface;
import fr.donovan.spotifish.security.SecurityService;

import java.io.IOException;

public class CustomPermissionSerializer extends StdSerializer<Permission> {

    @Override
    public void serialize(
            Permission permission,
            JsonGenerator jsonGenerator,
            SerializerProvider serializerProvider) throws IOException {

        permission = permission.getEntity().computePermission(securityService.getCurrentUser());
        defaultSerializer.serialize(permission, jsonGenerator, serializerProvider);

    }

    private final SecurityService securityService;

    private final JsonSerializer<Object> defaultSerializer;

    protected CustomPermissionSerializer(JsonSerializer<Object> defaultSerializer, SecurityService securityService) {
        super(Permission.class);
        this.securityService = securityService;
        this.defaultSerializer = defaultSerializer;
    }
}
