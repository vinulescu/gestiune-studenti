package ro.facultate.gestiune_studenti.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import ro.facultate.gestiune_studenti.model.Utilizator;
import ro.facultate.gestiune_studenti.repository.UtilizatorRepository;
import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UtilizatorRepository utilizatorRepository;

    public CustomUserDetailsService(UtilizatorRepository utilizatorRepository) {
        this.utilizatorRepository = utilizatorRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utilizator utilizator = utilizatorRepository.findByNumeUtilizator(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utilizatorul nu există!"));

       return new org.springframework.security.core.userdetails.User(
        utilizator.getNumeUtilizator(),
        utilizator.getParolaCriptata(),
        Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + utilizator.getRol().name().toUpperCase()))
);
    }
}