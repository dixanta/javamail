/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lfa.autoapp.dao;

import com.lfa.autoapp.entity.Subscriber;
import java.util.List;

/**
 *
 * @author USER
 */
public interface SubscriberDAO {
    List<Subscriber> getAll();
    void insert(Subscriber model);
}
