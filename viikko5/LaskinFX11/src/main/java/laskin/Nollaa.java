/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 *
 * @author okkokuisma
 */
public class Nollaa extends Komento {

    public Nollaa(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        super(tuloskentta, syotekentta, nollaa, undo, sovellus);
    }

    @Override
    public void suorita() {
        try {
            this.edellinenArvo = Integer.parseInt(tuloskentta.getText());
        } catch (Exception e) {
        }
        sovellus.nollaa();
        
        syotekentta.setText("");
        tuloskentta.setText("" + 0);
        
        nollaa.disableProperty().set(true);
        undo.disableProperty().set(false);
    }
    
    public void setEdellinenArvo(int edellinenArvo) {
        this.edellinenArvo = edellinenArvo;
    }
    
    @Override
    public void peru() {
        tuloskentta.setText("" + edellinenArvo);
        undo.disableProperty().set(true);
    }
}
