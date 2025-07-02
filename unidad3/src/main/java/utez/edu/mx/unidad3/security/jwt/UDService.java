package utez.edu.mx.unidad3.security.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import utez.edu.mx.unidad3.modules.user.User;
import utez.edu.mx.unidad3.modules.user.UserRepository;

import java.util.Collections;

@Service
public class UDService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Llamar al repositorio
        User found = userRepository.findByUsername(username).orElse(null);
        if (found == null) throw new UsernameNotFoundException("Usuario no encontrado");;

        //Genera el permiso que tiene el usuario
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + found.getRol().getName());

        //Retornamos el user de sesion para el spring dentro de userDetails
        return new org.springframework.security.core.userdetails.User(
                found.getUsername(),
                found.getPassword(),
                Collections.singleton(authority)
        );
    }
}
