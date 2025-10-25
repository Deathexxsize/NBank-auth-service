package NBank.auth_service.service;

import NBank.auth_service.dto.passwordDTOs.*;
import NBank.auth_service.model.User;
import NBank.auth_service.model.VerificationToken;
import NBank.auth_service.repository.RefreshRepository;
import NBank.auth_service.repository.UserRepository;
import NBank.auth_service.repository.VerificationTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class PasswordService {
    private final UserRepository userRepo;
    private final VerificationTokenRepository verificationTokenRepo;
    private final JwtService jwtService;
    private final RefreshService refreshService;

    public String forgotPassword(ForgotRequest request) {
        User user = userRepo.getUserByEmail(request.email())
                .orElseThrow(() -> new RuntimeException("The account with this email address was not found."));
        SecureRandom random = new SecureRandom();
        int token = random.nextInt(100000, 999999);

        // code с email отправляется в кафку

        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setUser(user);
        verificationToken.setToken(token);
        verificationToken.setExpiresAt(Instant.now().plus(15, ChronoUnit.MINUTES));
        verificationToken.setCreatedAt(Instant.now());
        verificationToken.setIsUsed(false);
        verificationTokenRepo.save(verificationToken);

        return "Send message to your email";
    }

    public VerifyCodeResponse verifyCode(VerifyCodeRequest request) {
        VerificationToken verificationToken = verificationTokenRepo
                .getVerificationTokenByToken(request.verificationCode())
                .orElseThrow(() -> new RuntimeException("Verification code is not found"));
        if (verificationToken.getIsUsed()){
            throw new RuntimeException("This verification code has already been used");
        }
        if (!verificationToken.getExpiresAt().isAfter(Instant.now())) {
            throw new RuntimeException("The verification code has already expired");
        }
        if (verificationToken.getToken() != request.verificationCode()) {
            throw new RuntimeException("Invalid verification code");
        }
        if (!verificationToken.getUser().getEmail().equals(request.email())) {
            throw new RuntimeException("This verification code does not belong to you");
        }
        verificationTokenRepo.delete(verificationToken);
        return new VerifyCodeResponse(true);
    }

    public ResetResponse resetPassword(ResetRequest request) {
        if (!request.newPassword().equals(request.repeatNewPassword())) {
            throw new RuntimeException("Passwords don't match");
        }
        User user = userRepo.getUserByEmail(request.email())
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setPassword(request.repeatNewPassword());
        userRepo.save(user);
        String accessToken = jwtService.generateAccessToken(user.getId(), user.getEmail());
        String refreshToken = jwtService.generateRefreshToken(user.getEmail());
        refreshService.saveRefreshToken(refreshToken, user);
        return new ResetResponse(accessToken, refreshToken, "Password reset successfully");
    }
}
