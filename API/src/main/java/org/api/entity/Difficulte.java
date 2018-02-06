/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.api.entity;

/**
 *
 * @author bertrand
 */
enum Difficulte {
    FACILE(1),
    MOYEN(2),
    DIFFICILE(3),
    TRES_DIFFICILE(4);
    
    private int value;
    
    private Difficulte(int value) {
        this.value = value;
    }
}
