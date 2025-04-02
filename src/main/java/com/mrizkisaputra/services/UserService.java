package com.mrizkisaputra.services;

import com.mrizkisaputra.exceptions.ResetPasswordInvalidException;
import com.mrizkisaputra.models.dto.RegisterRequestDto;
import com.mrizkisaputra.models.dto.ResetPasswordRequestDto;
import com.mrizkisaputra.models.entities.ResetPassword;
import com.mrizkisaputra.models.entities.Role;
import com.mrizkisaputra.models.entities.User;
import com.mrizkisaputra.models.entities.UserPassword;
import com.mrizkisaputra.repositories.ResetPasswordRepository;
import com.mrizkisaputra.repositories.UserPasswordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;
    private final ResetPasswordRepository resetPasswordRepository;
    private UserPasswordRepository userPasswordRepository;

    @Autowired
    public UserService(UserRepository userRepository,
                       ResetPasswordRepository resetPasswordRepository,
                       UserPasswordRepository userPasswordRepository) {

        this.userRepository = userRepository;
        this.resetPasswordRepository = resetPasswordRepository;
        this.userPasswordRepository = userPasswordRepository;
    }

    @Transactional
    public void register(RegisterRequestDto requestDto) {
        Role role = new Role();
        role.setId("R001");
        role.setName("staff");
        User user = new User();
        user.setUsername(requestDto.getEmail());
        user.setRole(role);

        userRepository.save(user);

        ResetPassword rp = new ResetPassword();
        rp.setUniqueCode(UUID.randomUUID().toString());
        rp.setUser(user);

        resetPasswordRepository.save(rp);
    }

    @Transactional
    public void verifyEmail(String uniqueCode) throws ResetPasswordInvalidException {
        ResetPassword rp = resetPasswordRepository.findByUniqueCode(uniqueCode)
                .orElseThrow(() -> new ResetPasswordInvalidException("Link reset password sudah terpakai"));

        User user = rp.getUser();
        user.setActive(true);
        userRepository.save(user);
    }

    @Transactional
    public User verifyResetPasswordLink(String uniqueCode) throws ResetPasswordInvalidException {
        ResetPassword rp = resetPasswordRepository.findByUniqueCode(uniqueCode)
                .orElseThrow(() -> new ResetPasswordInvalidException("Invalid UniqueCode "+uniqueCode));

        if (LocalDateTime.now().isAfter(rp.getExpired())) {
            throw new ResetPasswordInvalidException("Unique Code Expired");
        }

        return rp.getUser();
    }

    @Transactional
    public void setPassword(ResetPasswordRequestDto request) {
        resetPasswordRepository.deleteByUser(request.getUser());
        UserPassword  userPassword = userPasswordRepository.findByUser(request.getUser());
        if (userPassword == null) {
            userPassword = new UserPassword();
            userPassword.setUser(request.getUser());
        }

        userPassword.setPassword(passwordEncoder.encode(request.getPassword()));
        userPasswordRepository.save(userPassword);
    }

    public void forgotPassword(String email) {
        Optional<User> user = userRepository.findByUsername(email);
        if (user.isPresent()) {
            ResetPassword resetPassword = new ResetPassword();
            resetPassword.setUniqueCode(UUID.randomUUID().toString());
            resetPassword.setUser(user.get());
            resetPasswordRepository.save(resetPassword);
        }
    }

}
