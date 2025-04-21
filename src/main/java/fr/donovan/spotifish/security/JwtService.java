package fr.donovan.spotifish.security;

import fr.donovan.spotifish.entity.User;
import fr.donovan.spotifish.service.ConnectedUserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtService {

    private final String secretKey;

    private final ConnectedUserService connectedUserService;

    public JwtService(ConnectedUserService connectedUserService) {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
            SecretKey sk = keyGen.generateKey();
//            secretKey = Base64.getEncoder().encodeToString(sk.getEncoded());
            this.secretKey = "azsedrtyuiqsdfgyuDTFYGTYUJIdrFTGHJCVGBHNJCGVJHBKNLcgvhjb5445645";
            this.connectedUserService = connectedUserService;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public String generateToken(UserDetails userDetails) {
        // If ya want to add more claims to this token
        // There you add to the "claims" Map
        assert userDetails instanceof User;
        User currentUser = (User) userDetails;
        Map<String, Object> claims = new HashMap<>();
        claims.put("isModerator", currentUser.isModerator());
        claims.put("isArtist", currentUser.isArtist());
        claims.put("slug", currentUser.getSlug());
        claims.put("name", currentUser.getName());
        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(currentUser.getUsername())
                .issuer("spotifish")
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 1 * 1)) // Update the time limit 1 hour here
                .and() // add more infos to the token ?
                .signWith(getKey())
                .compact();
    }

    public String generateRefreshToken(UserDetails currentUser) {
        return Jwts.builder()
                .claims()
                .subject(currentUser.getUsername())
                .issuer("spotifish")
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 30)) // Update the time limit 30 day here
                .and() // add more infos to the token ?
                .signWith(getKey())
                .compact();
    }

    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private SecretKey getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

}