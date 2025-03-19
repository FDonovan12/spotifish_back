package fr.donovan.spotifish.configuration;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.BeanSerializer;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import fr.donovan.spotifish.entity.IsLiked;
import fr.donovan.spotifish.entity.Permission;
import fr.donovan.spotifish.security.SecurityService;
import fr.donovan.spotifish.service.UserLikeableItemService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SecurityBeanSerializerModifier extends BeanSerializerModifier {

    private final SecurityService securityService;
    private final UserLikeableItemService userLikeableItemService;

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
        if (IsLiked.class.isAssignableFrom(beanDesc.getBeanClass())) {
            if (serializer instanceof BeanSerializer beanSerializer) {
                return new CustomUserLikeableItemSerializer(beanSerializer, securityService, userLikeableItemService);
            }
        }

        return serializer;
    }
}