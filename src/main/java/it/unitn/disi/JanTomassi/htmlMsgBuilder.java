package it.unitn.disi.JanTomassi;

import java.time.LocalDate;

public class htmlMsgBuilder {
    String header = """
            <!DOCTYPE html>
            <html lang="en">
            <head>
                <meta charset="UTF-8">
                <meta http-equiv="X-UA-Compatible" content="IE=edge">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Comunicazione</title>
                <link rel="stylesheet" href="style.css">
            <style>
                    @import url('https://fonts.googleapis.com/css2?family=Outfit&display=swap');
                    #header h1 {
                        font-weight: 900;
                    }
                    #header h4 {
                        font-weight: 200;
                    }
                    #wrapper *{
                        font-weight: 400;
                    }
                    #wrapper h1{
                        font-weight: 600;
                    }
                    #wrapper h3{
                        font-weight: 600;
                    }
                    #body {
                        margin-right: 10%;
                        margin-left: 10%;
                    }
                    #wrapper {
                        display: table;
                    }
                    .divs {
                        display: table-cell;
                    }
                    #leftdiv {
                        width: 75%;
                    }
                    #rightdiv {
                        width: 25%;
                    }
                    /* For width 400px and larger: */
                    @media only screen and (max-width: 25cm) {
                        #body {
                            margin-right: 0%;
                            margin-left: 0%;
                        }
                    }
                </style>
            </head>
            <div id="body" style="
                    font-family: 'Outfit', sans-serif;">
                <div id="header" style="text-align: center;
                    background: rgb(0, 0, 160);
                    width: 100%;
                    margin-bottom: 50pt;">
            """;

    String body = """
                    <h4 style="color: yellow;"><i>La newsletter ufficiale dell'Associazione Ex Alunni Chris Cappell</i></h4>
                    <h4 style="color: rgb(175, 175, 175);">%td/%tB, NUMERO %s</h4>
                    <h1 style="color: white;">%s</h1>
                    <h4 style="color: rgb(175, 175, 175);">%s</h4>
                </div>
                <div id="wrapper">
                    <div id="leftdiv" class="divs">
                        <h1 style="margin-top: 0;">%s</h1>
                %s
                    </div>
                    <div id="rightdiv" class="divs" style="
                background: rgb(180, 199, 231);
                color: white;
                text-align: center;">
                <h3>%s</h3>
                        <h5>%s</h5>
                    </div>
            """;
    String footer = """
            <div style="content: '';
                display: table;
                clear: both;"></div>
                </div>
                <footer style="background: rgb(0, 0, 160);
                position: relative;
                height: 25px;
                width: 100%;"></footer>
            </div>
            </html>
            """;

    public htmlMsgBuilder(LocalDate Data, String nArticolo, String titolo, String sommario,
                          String nomeArticolo, String cit, String nomeCit, String msg) {
        body = String.format(body, Data, Data, nArticolo, titolo, sommario, nomeArticolo, msg, cit, nomeCit);
        System.out.println(body);
        System.out.println(header + body + footer);
    }

    @Override
    public String toString() {
        return header + body + footer;
    }
}
