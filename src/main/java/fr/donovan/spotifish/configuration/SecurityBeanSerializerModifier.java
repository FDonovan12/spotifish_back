package fr.donovan.spotifish.configuration;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.BeanSerializer;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import fr.donovan.spotifish.entity.Permission;
import fr.donovan.spotifish.entity.interfaces.PermissionEntityInterface;
import fr.donovan.spotifish.security.SecurityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SecurityBeanSerializerModifier extends BeanSerializerModifier {

    private final SecurityService securityService;

    @Override
    public JsonSerializer<?> modifySerializer(
            SerializationConfig config,
            BeanDescription beanDesc,
            JsonSerializer<?> serializer) {
        if (Permission.class.isAssignableFrom(beanDesc.getBeanClass())) {
            if (serializer instanceof BeanSerializer beanSerializer) {
                return new CustomPermissionSerializer(beanSerializer, securityService);
            }
        }

        return serializer;
    }
}