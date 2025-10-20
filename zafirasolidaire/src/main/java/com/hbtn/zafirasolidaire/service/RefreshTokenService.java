// package com.hbtn.zafirasolidaire.service;

// import java.time.Instant;
// import java.util.UUID;

// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.stereotype.Service;

// import com.hbtn.zafirasolidaire.model.RefreshToken;
// import com.hbtn.zafirasolidaire.model.User;
// import com.hbtn.zafirasolidaire.repository.RefreshTokenRepository;
// import com.hbtn.zafirasolidaire.repository.UserRepository;

// @Service
// public class RefreshTokenService {


//     private final RefreshTokenRepository refreshTokenRepository;
//     private final UserRepository userRepository;
//     private final JwtService jwtService;

//     public RefreshTokenService(RefreshTokenRepository refreshTokenRepository, UserRepository userRepository, JwtService jwtService) {
//         this.refreshTokenRepository = refreshTokenRepository;
//         this.userRepository = userRepository;
//         this.jwtService = jwtService;
//     }

//     public RefreshToken createRefreshToken(UUID userId) {
//         User user = userRepository.findById(userId)
//                                   .orElseThrow(() -> new IllegalArgumentException("User not found"));
//         RefreshToken refreshToken = new RefreshToken().setUSer(user)
//                                                       .setExpirationDate(Instant.now().plusMillis(refreshTokenDuration))
//                                                       .setToken(jwtService.generateToken(userId, user.getIsAdmin()));


//         return refreshTokenRepository.save(refreshToken);
//     }

//     public boolean isTokenExpired(RefreshToken token) {
//         return token.getExpirationDate().isBefore(Instant.now());
//     }
// }
