package com.flores.escondida.security;

import com.flores.escondida.entity.Roles;
import com.flores.escondida.entity.Usuario;
import com.flores.escondida.repository.RolesRepository;
import com.flores.escondida.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService {

        private final UsuarioRepository usuarioRepository;
        private final RolesRepository rolesRepository;
        private final PasswordEncoder passwordEncoder;
        private final JwtService jwtService;
        private final AuthenticationManager authenticationManager;

        public AuthResponse register(RegisterRequest request) {
                List<Roles> roles = new ArrayList<>();
                if (request.getRoles() != null) {
                        for (String roleName : request.getRoles()) {
                                Roles role = rolesRepository.findByNombre(roleName)
                                                .orElseGet(() -> rolesRepository.save(new Roles(null, roleName)));
                                roles.add(role);
                        }
                }

                var user = Usuario.builder()
                                .username(request.getUsername())
                                .password(passwordEncoder.encode(request.getPassword()))
                                .roles(roles)
                                .build();
                usuarioRepository.save(user);
                var jwtToken = jwtService.generateToken(user);
                return AuthResponse.builder()
                                .token(jwtToken)
                                .build();
        }

        public AuthResponse login(LoginRequest request) {
                authenticationManager.authenticate(
                                new UsernamePasswordAuthenticationToken(
                                                request.getUsername(),
                                                request.getPassword()));
                var user = usuarioRepository.findByUsername(request.getUsername())
                                .orElseThrow();
                var jwtToken = jwtService.generateToken(user);
                List<String> roles = user.getRoles().stream().map(Roles::getNombre).collect(Collectors.toList());
                return AuthResponse.builder()
                                .token(jwtToken)
                                .username(user.getUsername())
                                .roles(roles)
                                .build();
        }
}
