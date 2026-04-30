package com.flores.escondida.config;

import com.flores.escondida.entity.Roles;
import com.flores.escondida.entity.Usuario;
import com.flores.escondida.repository.RolesRepository;
import com.flores.escondida.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final RolesRepository rolesRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${APP_USER}")
    private String defaultUser;

    @Value("${APP_PASSWORD}")
    private String defaultPassword;


    @Override
    public void run(String... args) throws Exception {
        // Crear roles base si no existen
        if (rolesRepository.findByNombre("ROLE_ADMIN").isEmpty()) {
            rolesRepository.save(new Roles(null, "ROLE_ADMIN"));
        }
        if (rolesRepository.findByNombre("ROLE_USER").isEmpty()) {
            rolesRepository.save(new Roles(null, "ROLE_USER"));
        }

        // Crear usuario administrador por defecto desde .env si la tabla está vacía
        if (usuarioRepository.findByUsername(defaultUser).isEmpty()) {
            Roles adminRole = rolesRepository.findByNombre("ROLE_ADMIN").get();

            Usuario admin = Usuario.builder()
                    .username(defaultUser)
                    .password(passwordEncoder.encode(defaultPassword))
                    .roles(List.of(adminRole))
                    .build();

            usuarioRepository.save(admin);
            System.out.println("Usuario inicial creado: " + defaultUser);
        }
    }
}
