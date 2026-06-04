package org.example._kimicommunitybe.service;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoder;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
@Getter
public class JwtTokenService {

    //데이터 인증 시에 확인 목적으로 사용할 비밀키 생성.(비밀키 .env 에 보관해야 된다)
    private  final String secretString ="09k4+6myWF2gaOjbkaKC1cEGi7kJV2j52RGxn5xAsNA=";
    private final SecretKey secretKey;

    //access toke의 유효 시간.
    private final long accessTokenExpirationTime = 30 *60 * 1000L;

   //비밀키 HMAC-SHA 알고리즘으로 암호화.(비밀키와 + sha 해시 알고리즘을 결합해) 비밀키를 가진 사람만이 풀 수 있음 증명.
    public  JwtTokenService() {
        byte[] keyBytes = Decoders.BASE64.decode(secretString);
        this.secretKey = Keys.hmacShaKeyFor(keyBytes);
    }
    // jwt 토큰 생성 코드.
    public  String createToken(String userId,String role) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + accessTokenExpirationTime);

        return Jwts.builder()
                .subject(userId) //토큰 주인
                .claim("role", role) //토큰 권한
                .issuedAt(now) //토큰 발행 시간
                .expiration(expiryDate) //토큰 종료 시간
                .signWith(secretKey) //서명값
                .compact(); //토큰 생성
    }
    //jwt 토큰 검증 코드



}
