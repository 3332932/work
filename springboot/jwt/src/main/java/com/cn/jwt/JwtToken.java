package com.cn.jwt;



import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yshh44
 */
public class JwtToken {
    public String secret ="pWDt0Cx#9l8h8eS9%xQ*zBEHMY6M3BIgA&1PfR0VDgkUFIq$aWmnjOKJAZTcvUD3uILZj1uatw^W1Fz$XAlhcG75dRYWlVcZ%ie";
    /**
     * token类型
     */
    public String algorithm = "alg";
    public String algorithmVal = "JWT";
    /**
     * 加密算法
     */
    public String type = "typ";
    public String typeVal = "HS256";
    /**
     * 有效期
     */
    public Integer timeOutUnit = Calendar.MINUTE;
    public Integer timeOutVal = 30;


    public JwtToken(){

    }
    public JwtToken(String secret){
        this.secret = secret;
    }
    public String createToken(Object obj)  {
        Calendar nowTime = Calendar.getInstance();
        Date iatDate = nowTime.getTime();
        nowTime.add(timeOutUnit, timeOutVal);
        Date expireDate = nowTime.getTime();
        Map<String, Object> map = new HashMap<>(16);
        map.put(algorithm,algorithmVal);
        map.put(type,typeVal);
        JWTCreator.Builder builder = JWT.create().withHeader(map)
                .withExpiresAt(expireDate)
                .withIssuedAt(iatDate);
        Class userClass = obj.getClass();
        Field[] fields = userClass.getDeclaredFields();
        for (Field field : fields){
            boolean annotationPresent = field.isAnnotationPresent(JwtConfig.class);
            if (annotationPresent){
                String name = field.getName();
                Object o = null;
                try {
                    field.setAccessible(true);
                    o = field.get(obj)==null?"":field.get(obj);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                String value = String.valueOf(o);
                builder.withClaim(name,value);
            }
        }


        return builder.sign(Algorithm.HMAC256(secret));
    }
    public Map<String, Claim> verifyToken(String token){
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret)).build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaims();
    }

    public void getInfoByToken(String token, Object obj) {
        Map<String, Claim> stringClaimMap = verifyToken(token);
        Class  userClass = obj.getClass();
        Field[] declaredFields = userClass.getDeclaredFields();
        for (Field field : declaredFields) {
            boolean annotationPresent = field.isAnnotationPresent(JwtConfig.class);
            if (annotationPresent) {
                String name = field.getName();
                Claim claim = stringClaimMap.get(name);
                field.setAccessible(true);
                try {
                    Object value = null;
                    value = getValue(field, claim, value);
                    field.set(obj, value);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Object getValue(Field field, Claim claim, Object value) {
        if (field.getType().getName().equals("java.lang.Long")) {
            value = Long.valueOf(claim.asString());
        } else if (field.getType().getName().equals("java.lang.String")) {
            value = claim.asString();
        } else if (field.getType().getName().equals("java.lang.Double")) {
            value = Double.valueOf(claim.asString());
        } else if (field.getType().getName().equals("java.lang.Integer")) {
            value = Integer.valueOf(claim.asString());
        } else if (field.getType().getName().equals("java.util.Date")) {
            value = claim.asDate();
        } else if (field.getType().getName().equals("java.util.Map")) {
            value = claim.asMap();
        }
        return value;
    }


    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public String getAlgorithmVal() {
        return algorithmVal;
    }

    public void setAlgorithmVal(String algorithmVal) {
        this.algorithmVal = algorithmVal;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeVal() {
        return typeVal;
    }

    public void setTypeVal(String typeVal) {
        this.typeVal = typeVal;
    }

    public Integer getTimeOutUnit() {
        return timeOutUnit;
    }

    public void setTimeOutUnit(Integer timeOutUnit) {
        this.timeOutUnit = timeOutUnit;
    }

    public Integer getTimeOutVal() {
        return timeOutVal;
    }

    public void setTimeOutVal(Integer timeOutVal) {
        this.timeOutVal = timeOutVal;
    }
}
