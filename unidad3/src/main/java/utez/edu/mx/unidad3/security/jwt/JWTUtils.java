package utez.edu.mx.unidad3.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
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

    //Validar que el token no este expirado
    public Boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    //Consume la funcion de arriba adicional a que pregunta que el usuario coincida con el usuario que lo usa
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String USERNAME = extractUsername(token);
        return (USERNAME.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    //Crea el token a raiz de los datos del usuario y el token
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    //Consume la funcion de crear oara solamente exportar el token
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) //10 horas
                .signWith(io.jsonwebtoken.SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
}
