/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lfa.autoapp.dao.impl;

import com.lfa.autoapp.dao.SubscriberDAO;
import com.lfa.autoapp.entity.Subscriber;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USER
 */
public class SubscriberDAOImpl implements SubscriberDAO{
    private List<Subscriber> subs=new ArrayList<>();
    
    @Override
    public List<Subscriber> getAll() {
       return subs;
    }

    @Override
    public void insert(Subscriber model) {
        subs.add(model);
    }
    
    
}
