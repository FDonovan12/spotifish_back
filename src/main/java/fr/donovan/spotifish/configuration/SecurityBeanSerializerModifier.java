package fr.donovan.spotifish.configuration;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.BeanSerializer;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import fr.donovan.spotifish.entity.LikeableItem;
import fr.donovan.spotifish.entity.interfaces.EntityInterface;
import fr.donovan.spotifish.security.CanEditService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SecurityBeanSerializerModifier extends BeanSerializerModifier {

    private final CanEditService canEditService;

    @Override
    public JsonSerializer<?> modifySerializer(
            SerializationConfig config, BeanDescription beanDesc, JsonSerializer<?> serializer) {
        System.out.println("SecurityBeanSerializerModifier.modifySerializer");
        if (EntityInterface.class.isAssignableFrom(beanDesc.getBeanClass())) {
            System.out.println(beanDesc.getBeanClass());
            if (serializer instanceof BeanSerializer beanSerializer) {
                return new CustomEntitySerializer(beanSerializer, canEditService);
            }
//            return new CustomEntitySerializer((JsonSerializer<Object>) serializer, canEditService);
        }

        return serializer;
    }
}