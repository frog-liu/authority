package com.frog.authority.security.domain;

import com.frog.authority.admin.api.dto.UserDTO;
import com.frog.authority.common.base.enums.StatusEnum;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author frog
 */
@Data
public class UserDetail implements UserDetails {

    private String username;

    private String password;

    private Collection<SimpleGrantedAuthority> authorities;

    private boolean enabled;

    private boolean accountNonLocked;

    public UserDetail(UserDTO user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.enabled = StatusEnum.VALID.equals(user.getStatus());
        this.accountNonLocked = !StatusEnum.LOCKED.equals(user.getStatus());
        if (!CollectionUtils.isEmpty(user.getRoleList())) {
            authorities = new ArrayList<>(user.getRoleList().size());
            user.getRoleList().forEach(roleCode -> authorities.add(new SimpleGrantedAuthority(roleCode)));
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
