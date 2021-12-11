package it.unitn.disi.JanTomassi;

import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

public class SpecialTextField extends TextField {
    public SpecialTextField(DestinationVBox mvb) {
        this.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode()== KeyCode.ENTER) {
                if(this.getText().equals("") && mvb.getChildren().size()>1){
                    mvb.removeTextField(this);
                }
                else {
                    mvb.addTextField();
                }
            }
        });
    }
}
