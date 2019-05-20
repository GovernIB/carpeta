package es.caib.carpeta.front.config;

import es.caib.carpeta.core.utils.UsuarioClave;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UsuarioAutenticado implements UserDetails {


    private UsuarioClave usuarioClave;
    private Collection<GrantedAuthority> authorities;

    public UsuarioClave getUsuarioClave() {
        return usuarioClave;
    }

    public void setUsuarioClave(UsuarioClave usuarioClave) {
        this.usuarioClave = usuarioClave;
    }

    public void setAuthorities(Collection<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return usuarioClave.getNif();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
