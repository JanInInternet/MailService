package it.unitn.disi.JanTomassi;

import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

public class DestinationVBox extends VBox {
    public DestinationVBox() {
        SpecialTextField tf=new SpecialTextField(this);
        this.getChildren().add(tf);
    }

    public void addTextField(){
        this.getChildren().add(new SpecialTextField(this));
    }

    public void removeTextField(SpecialTextField specialTextField) {
        this.getChildren().remove(specialTextField);
    }
}
