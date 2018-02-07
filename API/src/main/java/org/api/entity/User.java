/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.api.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
/**
 *
 * @author vali
 */
@Entity
@NamedQuery(name="User.findAll",query="SELECT u FROM User u")
public class User implements Serializable {
    @Id
    private long id;
    
    private String nom;
    
    private String prenom;
    
    private String mail;
    
    private String password;
}
