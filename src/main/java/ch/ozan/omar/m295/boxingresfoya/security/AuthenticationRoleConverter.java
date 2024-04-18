package ch.ozan.omar.m295.boxingresfoya.security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Converter class for extracting authorities from a JWT token and converting them into Spring Security GrantedAuthority objects.
 */
public class AuthenticationRoleConverter implements Converter<Jwt, AbstractAuthenticationToken> {
    private final JwtGrantedAuthoritiesConverter defaultGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();

    /**
     * Constructs a new AuthenticationRoleConverter and configures defaultGrantedAuthoritiesConverter.
     */
    public AuthenticationRoleConverter() {
        defaultGrantedAuthoritiesConverter.setAuthoritiesClaimName("roles");
        defaultGrantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");
    }

    /**
     * Extracts resource roles from the JWT token.
     * @param jwt The JWT token.
     * @return Collection of GrantedAuthority objects representing resource roles.
     */
    private static Collection<? extends GrantedAuthority> extractResourceRoles(final Jwt jwt) {
        Map<String, Object> resourceAccess = jwt.getClaim("resource_access");
        Collection<String> resourceRoles;
        if (resourceAccess != null) {
            Map<String, Collection<String>> demoapp = (Map<String, Collection<String>>) resourceAccess.get("boxingresfoya");
          if ((resourceRoles = demoapp.get("roles")) != null) {
              return resourceRoles.stream()
                      .map(SimpleGrantedAuthority::new)
                      .collect(Collectors.toSet());
          }
        }
        return Collections.emptySet();
    }

    /**
     * Converts a Jwt token into an AbstractAuthenticationToken containing the authorities extracted from the token.
     * @param source The Jwt token to convert.
     * @return An AbstractAuthenticationToken containing the authorities extracted from the token.
     */
    @Override
    public AbstractAuthenticationToken convert(final Jwt source) {
        Collection<GrantedAuthority> authorities = Stream.concat(defaultGrantedAuthoritiesConverter.convert(source)
                                .stream(),
                        extractResourceRoles(source).stream())
                .collect(Collectors.toSet());
        return new JwtAuthenticationToken(source, authorities);
    }
}