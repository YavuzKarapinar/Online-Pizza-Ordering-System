package me.jazzy.opos.repository;

import me.jazzy.opos.model.ResetPassword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ResetPasswordRepository extends JpaRepository<ResetPassword, Long> {

    @Query("select r from ResetPassword r where r.user.email = :email and r.sixDigitCode = :code")
    ResetPassword findResetPasswordByEmailAndCode(@Param("email") String email, @Param("code") int code);
}