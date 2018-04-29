/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lfa.autoapp.loader;

import com.lfa.autoapp.dao.SubscriberDAO;
import com.lfa.autoapp.entity.Subscriber;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author USER
 */
public class SubscriberLoader {
    
    private SubscriberDAO subDAO;

    public SubscriberLoader(SubscriberDAO subDAO) {
        this.subDAO = subDAO;
    }
    
    public void load(String fileName){
        try{
        BufferedReader reader=new BufferedReader(new FileReader(fileName));
            String line="";
            while((line=reader.readLine())!=null){
                String[] tokens=line.split(",");
                if(tokens.length>=2){
                    subDAO.insert(new Subscriber(tokens[0],
                            tokens[1]));
                }
            }
        }catch(IOException ioe){
            System.out.println(ioe.getMessage());
        }
    }
}
