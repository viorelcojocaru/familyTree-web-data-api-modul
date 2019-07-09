package com.esempla.familyTree.familyTreeapi.controller.jws.utils;


import com.esempla.familyTree.familyTreeapi.controller.jws.exceptions.JWSException;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.security.KeyStore;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Slf4j
@Component
public class JWSUtil {

    @Value("${server.alias}")
    private String serverAlias;

    @Value("${server.key.store}")
    private Resource serverKeyStore;

    @Value("${client.alias}")
    private String clientAlias;

    @Value("${key.password}")
    private String password;

    //For Server
    public String signServer(JSONObject object) throws Exception {
        RSAPrivateKey privateKey = getServerRSAPrivatKey();
        RSAPublicKey publicKey = getServerRSADefaultPublicKey();
        JWSSigner signer = new RSASSASigner(privateKey);
        JWSObject jwsObject = new JWSObject(
                new JWSHeader.Builder(JWSAlgorithm.RS256).keyID(serverAlias).build(),
                new Payload(object));
        jwsObject.sign(signer);
        String s = jwsObject.serialize();
        jwsObject = JWSObject.parse(s);
        JWSVerifier verifier = new RSASSAVerifier(publicKey);
        if (jwsObject.verify(verifier)) {
            return s;
        }
        throw new JWSException("Could not verificate object signature");
    }


    public JSONObject serializeJWSObjectServer(String json) throws JWSException {
        try {
            RSAPublicKey publicKey = getClientPublicKey();
            JWSObject parse = JWSObject.parse(json);
            JWSVerifier verifier = new RSASSAVerifier(publicKey);
            if (parse.verify(verifier)) {
                return parse.getPayload().toJSONObject();
            } else
                throw new JWSException(
                        String.format("Could not validate certificate signature with alias %s", serverAlias));
        } catch (Exception e) {
            throw new JWSException(
                    String.format("Could not find certificate with alias %s", serverAlias));
        }
    }

    private RSAPublicKey getClientPublicKey() {
        try {
            KeyStore keystore = getServerKeyStore();
            return (RSAPublicKey) keystore.getCertificate(clientAlias).getPublicKey();
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    private RSAPublicKey getServerRSADefaultPublicKey() {
        try {
            KeyStore keystore = getServerKeyStore();
            return (RSAPublicKey) keystore.getCertificate(serverAlias).getPublicKey();
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    private KeyStore getServerKeyStore() {
        try {
            KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
            keystore.load(serverKeyStore.getInputStream(), password.toCharArray());
            return keystore;
        } catch (Exception e) {
            throw new JWSException(e.getMessage());
        }

    }

    private RSAPrivateKey getServerRSAPrivatKey() {
        try {
            KeyStore keystore = getServerKeyStore();
            return (RSAPrivateKey) keystore.getKey(serverAlias, password.toCharArray());
        } catch (Exception e) {
            throw new JWSException(e.getMessage());
        }
    }

}
