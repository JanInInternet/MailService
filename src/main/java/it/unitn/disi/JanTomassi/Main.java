package it.unitn.disi.JanTomassi;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Main extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){

        // Center TextArea
        TextArea contenuto = new TextArea();

        // Top HBox
        TextField nArticolo = new TextField();
        TextField titolo = new TextField();
        TextField sommario = new TextField();
        TextField nomeArticolo = new TextField();
        TextField cit = new TextField();
        TextField nomeCit = new TextField();
        HBox hb = new HBox();
        BorderPane bp = new BorderPane();

        // Left Vbox
        DatePicker dataCalendario = new DatePicker();
        TextField mittente = new TextField();
        TextField pass = new TextField();
        TextField soggetto = new TextField();
        DestinationVBox destinatari = new DestinationVBox();
        VBox vb = new VBox();
        ScrollPane sp = new ScrollPane();

        // Bottom Area
        Button sendMail = new Button("Send");
        sendMail.setOnMouseClicked(mouseEvent -> sendMail(sendMail, hb, vb, contenuto));

        //assegnamento a HBox
        nArticolo.setPromptText("Numero");
        titolo.setPromptText("Titolo");
        sommario.setPromptText("Sommario");
        nomeArticolo.setPromptText("Nome del articolo");
        cit.setPromptText("cit");
        nomeCit.setPromptText("nome citato");
        hb.getChildren().addAll(nArticolo, titolo, sommario, nomeArticolo, cit, nomeCit);

        //assegnamento a VBox
        mittente.setPromptText("Mittente");
        pass.setPromptText("Password");
        soggetto.setPromptText("Soggetto");
        vb.getChildren().addAll(dataCalendario, mittente, pass, soggetto, destinatari);
        sp.setPrefWidth(190);
        sp.setContent(vb);

        //assegnamento a BorderPane
        bp.setTop(hb);
        bp.setLeft(sp);
        bp.setBottom(sendMail);
        bp.setCenter(contenuto);

        //stilizzazione BorderPane
        BorderPane.setMargin(hb, new Insets(0, 0, 3, 0));
        BorderPane.setMargin(sp, new Insets(0, 1.5, 0, 3));
        BorderPane.setAlignment(sendMail, Pos.BOTTOM_RIGHT);
        BorderPane.setMargin(sendMail, new Insets(3, 3, 0, 0));
        BorderPane.setMargin(contenuto, new Insets(0, 3, 0, 1.5));

        //setup Scene
        Scene scene = new Scene(bp, 1000, 600);
        scene.getStylesheets().add("style.css");
        //setup Stage
        primaryStage.setTitle("Mail Send");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void sendMail(Button bu, HBox headermsg, VBox mailconfig, TextArea msg) {
        // pass jt mvlvgxpwzjlnbt

        // Destination string build
        DestinationVBox dvb = (DestinationVBox) mailconfig.getChildren().get(4);
        StringBuilder destinatary = new StringBuilder();
        for (int i = 0; i < dvb.getChildren().size() - 1; i++) {
            destinatary.append(((TextField) dvb.getChildren().get(i)).getText());
            destinatary.append(", ");
        }
        destinatary.append(((TextField) dvb.getChildren().get(dvb.getChildren().size() - 1)).getText());

        // smtp prprity setup
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");

        // Create a session with Gmail
        Session session = Session.getInstance(prop,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(((TextField) mailconfig.getChildren().get(1)).getText(),
                                ((TextField) mailconfig.getChildren().get(2)).getText());
                    }
                });

        // try sending the email
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(((TextField) mailconfig.getChildren().get(1)).getText()));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(destinatary.toString())
            );
            message.setSubject(((TextField) mailconfig.getChildren().get(3)).getText());
            htmlMsgBuilder htmlMB = new htmlMsgBuilder(((DatePicker) mailconfig.getChildren().get(0)).getValue().toString(),
                    ((TextField) headermsg.getChildren().get(0)).getText(),
                    ((TextField) headermsg.getChildren().get(1)).getText(),
                    ((TextField) headermsg.getChildren().get(2)).getText(),
                    ((TextField) headermsg.getChildren().get(3)).getText(),
                    ((TextField) headermsg.getChildren().get(4)).getText(),
                    ((TextField) headermsg.getChildren().get(5)).getText(),
                    msg.getText());
            message.setContent(htmlMB.toString(), "text/html; charset=utf-8");

            Transport.send(message);

            bu.setText("Inviata, Mi apro alla chiusura...");
            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            Platform.exit();
                        }
                    },
                    5000
            );

        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
}
