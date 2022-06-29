package com.gnm.adrunner.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.core.io.InputStreamResource;

import com.gnm.adrunner.config.GlobalConstant;

// 웹 CPA 참여 스크립트 빌더
public class scriptBuilder {

    public static InputStreamResource build(String adsKey, String mediaKey) throws FileNotFoundException{
        
        File file = new File("adr_script.js");

        try(FileWriter fw = new FileWriter(file, true);
        
        BufferedWriter bw = new BufferedWriter(fw);
        
        PrintWriter out = new PrintWriter(bw)){

            out.println("function adr_init(){");
            out.println("fetch(' " +GlobalConstant.CLICK_SERVER_HOST+ "/click/web?ads_key=" +adsKey+ "&media_key=" +mediaKey+"')");
            out.println(".then(response=>response.text().then(function(text){");
            out.println("if(response.status == 200){");
            out.println("document.cookie = 'click_key='+text");
            out.println("}");
            out.println("}))");
            out.println("}");

            out.println("function adr_callback(){");
            out.println("var userAgent   = navigator.userAgent.toLowerCase()");
            out.println("var referrer    = document.referrer");
            out.println("var myRegExp = new RegExp('^' + 'click_key=', 'i');");
            out.println("if(myRegExp.test(document.cookie)){");
            out.println("var clickKey = myRegExp.exec(document.cookie).input.split('=')[1]");
            out.println("fetch('" +GlobalConstant.POSTBACK_SERVER_HOST+ "/postback/web/cpa?click_key=' +clickKey+'&user_agent=' +encodeURIComponent(userAgent)+ '&referrer=' + referrer)");
            out.println(".then(success =>{");
            out.println("}).catch(error => {");
            out.println("console.log(error)");
            out.println("return []");
            out.println("})");
            out.println("}");
            out.println("}");

        } catch (IOException e) {

        }

        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        file.delete();

        return resource;
    }
}
