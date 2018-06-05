package com.ssm.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.gson.Gson;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component//注册成为spring Bean
public class Token {

    private static final String SECRET = "secret";
    private Gson gson = new Gson();

    /*
    Token:
        1、创建token头(head),再加入token的身体（内容）
        2、加密（发送到web端）
        3、解密（在后端进行解密，判断用户的身份）
     */


    //生成Token头部
    private Map<String,Object> createHeader(){
        Map<String,Object> map = new HashMap<>();
        map.put("type","jwt");//声明map所加的类型（Token=JWT）
        map.put("alg","HS256");//声明token的加密的方式
        return map;
    }

    //生成加密的Token, obj是你要加密的内容，maxWaitTime是Token的最长存活时间
    public  <T>String createToken(Object obj,long maxWailTime) throws UnsupportedEncodingException {
        JWTCreator.Builder builder = JWT.create();

        builder.withHeader(createHeader());//将头内容加到Token中
        builder.withSubject(gson.toJson(obj));//将要加密的内容加到token的身体中
        if(maxWailTime>0){//判断是否具有存活时间

            long startTime = System.currentTimeMillis();
            long endTime = startTime+maxWailTime;//token的死亡时间
            Date date = new Date(endTime);
            builder.withExpiresAt(date);
            return builder.sign(Algorithm.HMAC256(SECRET));//
        }
        return "gg";
    }

    //对web传来的token进行解密(clazz所要解密成的运行时类对象)
    public <T>T getObject(Class<T> clazz,String token) throws UnsupportedEncodingException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();//获得解密器

        DecodedJWT decodedJWT = verifier.verify(token);

        Date date = decodedJWT.getExpiresAt();//得到解密后所获得token的死亡时间
        if(date!=null && date.after(new Date())){//判断所得到的时间不为null，而且再当前时间之后
            String subject = decodedJWT.getSubject();//得到token的主题内容
            return gson.fromJson(subject,clazz);//将主体内容解密成cladzz的类型对象返回

        }
        return null;
    }

}
