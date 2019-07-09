package com.esempla.familytree.restclient.dto;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.gen.RSAKeyGenerator;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;


public class JWTNimbusEncode {
    public static void main(String[] args) throws Exception{
        // RSA signatures require a public and private RSA key pair, the public key
// must be made known to the JWS recipient in order to verify the signatures
        RSAKey rsaJWK = new RSAKeyGenerator(2048)
                .keyID("123")
                .generate();
        RSAKey rsaPublicJWK = rsaJWK.toPublicJWK();

// Create RSA-signer with the private key
        JWSSigner signer = new RSASSASigner(rsaJWK);

// Prepare JWT with claims set
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat("YYYY-MM-dd");
        LocalDate localDate = LocalDate.now().plusMonths(1);
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject("alice")
                .issuer("https://c2id.com")
//                .expirationTime(new Date(new Date().getTime() + 60 * 1000))
                .expirationTime(new Date(simpleDateFormat.parse(localDate.toString()).getTime()))
                .build();

        SignedJWT signedJWT = new SignedJWT(
                new JWSHeader.Builder(JWSAlgorithm.RS256).keyID(rsaJWK.getKeyID()).build(),
                claimsSet);

// Compute the RSA signature
        signedJWT.sign(signer);

// To serialize to compact form, produces something like
// eyJhbGciOiJSUzI1NiJ9.SW4gUlNBIHdlIHRydXN0IQ.IRMQENi4nJyp4er2L
// mZq3ivwoAjqa1uUkSBKFIX7ATndFF5ivnt-m8uApHO4kfIFOrW7w2Ezmlg3Qd
// maXlS9DhN0nUk_hGI3amEjkKd0BWYCB8vfUbUv0XGjQip78AI4z1PrFRNidm7
// -jPDm5Iq0SZnjKjCNS5Q15fokXZc8u0A
        String s = signedJWT.serialize();
        try (PrintWriter out = new PrintWriter("JWTsignature.yml")) {
            out.println(s);
        }
// On the consumer side, parse the JWS and verify its RSA signature
        signedJWT = SignedJWT.parse(s);

//        JWSVerifier verifier = new RSASSAVerifier(rsaPublicJWK);
//        assertTrue(signedJWT.verify(verifier));

// Retrieve / verify the JWT claims according to the app requirements
//        assertEquals("alice", signedJWT.getJWTClaimsSet().getSubject());
//        assertEquals("https://c2id.com", signedJWT.getJWTClaimsSet().getIssuer());
//        assertTrue(new Date().before(signedJWT.getJWTClaimsSet().getExpirationTime()));
    }

}
