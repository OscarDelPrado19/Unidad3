package utez.edu.mx.unidad3.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.function.Function;

@Service
public class JWTUtils {
    @Value("${secret.key}")
    private String SECRET_KEY;

    //Obtiene el usuario del token
    public String extractUsername(String token){
        return extractClaims(token, Claims::getSubject);
    }

    //Obtiene la fecha de expiración del token
    public Date extractExpiration(String token){
        return extractClaims(token, Claims::getExpiration);
    }

    //Resuelve el cuerpo del token y regresa el cuerpo del token resuelto
    public <T> T extractClaims(String token, Function<Claims, T> claimsResolver){
        final Claims CLAIMS = extractAllClaims(token);
        return claimsResolver.apply(CLAIMS);
    }

    //Firma el token con la palabra secreta y obtiene el cuerpo del token
    public Claims extractAllClaims(String token){
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }
}
