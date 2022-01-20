package com.payday.bank.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcryptEncoder {

    public static String  endoce(String pass){
        String encodedString;

        BCryptPasswordEncoder bcryptEncoder=new BCryptPasswordEncoder ();

        encodedString=bcryptEncoder.encode(pass);

        return encodedString;
    }
}
