/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package custom;

import javafx.scene.control.TextField;

/**
 *
 * @author Loly
 */
public class NumberField extends TextField {

    @Override
    public void replaceText(int start, int end, String text) {
        if(text.matches("[0-9]") || text.trim().equals("")){
            super.replaceText(start, end, text); 
        }
    }
    
    
}
