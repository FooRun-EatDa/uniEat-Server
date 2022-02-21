package com.foorun.unieat.domain.email.repository;

import com.foorun.unieat.domain.email.jpo.EmailVerificationCodeJpo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailVerificationCodeRepository extends JpaRepository<EmailVerificationCodeJpo, String> {
}
