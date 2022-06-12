package com.foorun.unieat.domain.token.jpo;

import lombok.*;
import org.springframework.data.domain.Persistable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "refresh_token")
public class RefreshTokenJpo implements Persistable<String> {
    @Id
    private String token;

    public static RefreshTokenJpo tokenOf(String token) {
        return new RefreshTokenJpo(token);
    }

    @Override
    public String getId() {
        return token;
    }

    @Override
    public boolean isNew() {
        return true;
    }
}
