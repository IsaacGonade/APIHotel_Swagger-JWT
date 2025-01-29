package com.example.controllers;

import com.example.entities.User;
import com.example.repositories.UserRepository;
import com.example.services.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@Tag(name="Usuarios", description = "Login")
public class UserController {

    private final UserRepository userRepository;

    private final UserService userService;

    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }


    //ruta para obtener el token de usuarioy poder acceder a otras rutas que lo necesiten
    @PostMapping("/usuario")
    public User login(@RequestParam("usuario") String nombreUsuario, @RequestParam("contrasena") String password) {
        // Buscar al usuario por nombre de usuario
        Optional<User> user = userRepository.findByUser(nombreUsuario);

        if (user.isPresent()) {
            User userFound = user.get();
            // Verificar si la contraseña es correcta (aquí debería implementarse la validación de contraseñas seguras)
            if (userFound.getPwd().equals(password)) {
                // Crear el token JWT
                String token = getJWTToken(nombreUsuario);
                // Asignar el token al usuario
                userFound.setPwd(password);
                userFound.setToken(token);

                return userFound;
            }
        }

        // Si no se encuentra el usuario o la contraseña no es correcta
        return null;
    }

    //Utilizamos el método getJWTToken(...) para construir el token,
    // delegando en la clase de utilidad Jwts que incluye información sobre su expiración
    // y un objeto de GrantedAuthority de Spring que, como veremos más adelante,
    // usaremos para autorizar las peticiones a los recursos protegidos.

    private String getJWTToken(String username) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }
}
