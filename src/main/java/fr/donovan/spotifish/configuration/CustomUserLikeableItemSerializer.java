package fr.donovan.spotifish.configuration;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import fr.donovan.spotifish.entity.IsLiked;
import fr.donovan.spotifish.entity.LikeableItem;
import fr.donovan.spotifish.entity.User;
import fr.donovan.spotifish.entity.embed.UserLikeableItemId;
import fr.donovan.spotifish.security.SecurityService;
import fr.donovan.spotifish.service.UserLikeableItemService;

import java.io.IOException;

public class CustomUserLikeableItemSerializer extends StdSerializer<IsLiked> {

    private final SecurityService securityService;

    private final UserLikeableItemService userLikeableItemService;

    private final JsonSerializer<Object> defaultSerializer;

    protected CustomUserLikeableItemSerializer(
            JsonSerializer<Object> defaultSerializer,
            SecurityService securityService,
            UserLikeableItemService userLikeableItemService) {
        super(IsLiked.class);
        this.securityService = securityService;
        this.userLikeableItemService = userLikeableItemService;
        this.defaultSerializer = defaultSerializer;
    }

    @Override
    public void serialize(
            IsLiked isLiked,
            JsonGenerator jsonGenerator,
            SerializerProvider serializerProvider) throws IOException {

        LikeableItem likeableItem = isLiked.getLikeableItem();
        User user = securityService.getCurrentUser();
        if (user == null) {
            isLiked = new IsLiked(false, null);
            defaultSerializer.serialize(isLiked, jsonGenerator, serializerProvider);
            return;
        }
        UserLikeableItemId userLikeableItemId = new UserLikeableItemId(user, likeableItem);
        boolean bool = userLikeableItemService.isExist(userLikeableItemId);
        isLiked = new IsLiked(bool, null);
        defaultSerializer.serialize(isLiked, jsonGenerator, serializerProvider);
    }
}
