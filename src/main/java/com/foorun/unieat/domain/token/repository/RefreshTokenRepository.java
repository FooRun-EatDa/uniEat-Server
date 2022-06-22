package com.foorun.unieat.domain.token.repository;

import com.foorun.unieat.domain.token.jpo.RefreshTokenJpo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshTokenJpo, String> {
}
