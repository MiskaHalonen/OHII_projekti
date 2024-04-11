package projektityo.ohiiProjektiTyo;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.*;

/**
 * Tämä luokka edustaa käyttöliittymää kirjahakusovellukselle.
 */

public class UI extends Application {
    private Pane paneeli;
    private TextField hakukentta;
    private TextField kirjailijaTF;
    private TextField kirjanimiTF;
    private TextField kirjoitusVuosiTF;
    private Pane alkuIkkuna;
    private Label logoteksti;
    private Rectangle logo;
    private Button hae;
    private Button muokkaa;

    /**
     * Päämetodi JavaFX-sovelluksen käynnistämiseen.
     * @param args Komentoriviparametrit.
     */

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Ylikirjoitettu metodi JavaFX-sovelluksen käynnistämiseen.
     * @param stage Sovelluksen ensisijainen ikkuna.
     */
    @Override
    public void start(Stage stage) {

        paneeli = new Pane();
        paneeli.setBackground(new Background(new BackgroundFill(Color.web("#333333"),CornerRadii.EMPTY, Insets.EMPTY)));


        alkuNakyma();

        Scene scene = new Scene(paneeli, 700, 500);
        stage.setTitle("Kirja hakukone (muokkaus salasana = 1234)");
        stage.setScene(scene);
        stage.show();

    }

    /**
     * Asettaa sovelluksen alkuikkunan näkymän.
     */
    private void alkuNakyma(){
        alkuIkkuna = new Pane();
        alkuIkkuna.setPrefSize(700,500);


        hakukentta = new TextField("Kirjannimi / kirjoittaja / julkaisu vuosi");
        hakukentta.setStyle("-fx-background-color: Grey; -fx-background-radius: 15;" +
                " -fx-text-fill: black;" +
                " -fx-border-color: Black; -fx-border-radius: 10;");
        hakukentta.setLayoutY(100);
        hakukentta.setLayoutX(200);
        hakukentta.setPrefSize(250,20);

        hae = new Button("Hae");
        hae.setStyle("-fx-background-color: Grey; -fx-background-radius: 15;" +
                " -fx-text-fill: black;" +
                " -fx-border-color: Black; -fx-border-radius: 10;");
        hae.setLayoutX(460);
        hae.setLayoutY(100);
        hae.setOnMouseClicked(e -> {
            hakuMetodi(hakukentta.getText());
        } );

        logo = new Rectangle(110,50);
        logo.setLayoutX(10);
        logo.setLayoutY(10);
        logo.setFill(Color.TRANSPARENT);
        logo.setStroke(Color.WHITE);

        logoteksti = new Label("Kirjahaku");
        logoteksti.setLayoutX(20);
        logoteksti.setLayoutY(20);
        logoteksti.setFont(new Font(20));
        logoteksti.setTextFill(Color.WHITE);

        muokkaa = new Button("Muokkaa tietoja");
        muokkaa.setStyle("-fx-background-color: Grey; -fx-background-radius: 15;" +
                " -fx-text-fill: black;" +
                " -fx-border-color: Black; -fx-border-radius: 10;");
        muokkaa.setLayoutX(590);
        muokkaa.setLayoutY(10);
        muokkaa.setOnAction(e -> salasanaTarkistus() );

        hakukentta.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                hakuMetodi(hakukentta.getText());
            }
        });


        alkuIkkuna.getChildren().addAll(hakukentta,hae,logo,logoteksti,muokkaa);
        paneeli.getChildren().addAll(alkuIkkuna);


    }

    /**
     * Suorittaa haun annetun hakukyselyn perusteella.
     * @param haettava Hakukysely, jota käytetään kirjatietojen etsimiseen.
     */
    private void hakuMetodi(String haettava) {
        StackPane hakutulokset = new StackPane();
        hakutulokset.setPrefSize(700,500);
        try (BufferedReader reader = new BufferedReader(new FileReader("kirjat.txt"))) {
            String rivi;
            while ((rivi = reader.readLine()) != null) {
                // Tarkista, sisältääkö rivi haetun tiedon
                if (rivi.toLowerCase().contains(haettava.toLowerCase())) {
                    String[] olioOsat = rivi.split(",");
                    Kirja kirja = new Kirja(olioOsat[0], olioOsat[1], Integer.parseInt(olioOsat[2]));

                    Label kirjaLabel = new Label(kirja.toString());
                    kirjaLabel.setTextFill(Color.WHITE);
                    kirjaLabel.setLayoutY(400);
                    kirjaLabel.setLayoutX(210);
                    kirjaLabel.setFont(new Font(14));

                    hakutulokset.getChildren().addAll(kirjaLabel);
                    alkuIkkuna.getChildren().clear();
                    alkuIkkuna.getChildren().addAll(hakutulokset,hakukentta,hae,logo,logoteksti,muokkaa);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Tarkistaa käyttäjän syöttämän salasanan.
     */
    private void salasanaTarkistus(){
        Pane salasanaIkkuna = new Pane();
        salasanaIkkuna.setPrefSize(700,500);

        logoteksti = new Label("Kirjahaku");
        logoteksti.setLayoutX(20);
        logoteksti.setLayoutY(20);
        logoteksti.setFont(new Font(20));
        logoteksti.setTextFill(Color.WHITE);

        logo = new Rectangle(110,50);
        logo.setLayoutX(10);
        logo.setLayoutY(10);
        logo.setFill(Color.TRANSPARENT);
        logo.setStroke(Color.WHITE);

        Label ohje = new Label("Syötä salasana:");
        ohje.setLayoutY(190);
        ohje.setLayoutX(250);
        ohje.setFont(new Font(14));
        ohje.setTextFill(Color.LIGHTGREY);

        TextField salasanaTF = new TextField();
        salasanaTF.setStyle("-fx-background-color: Grey; -fx-background-radius: 15;" +
                " -fx-text-fill: black;" +
                " -fx-border-color: Black; -fx-border-radius: 10;");
        salasanaTF.setLayoutY(220);
        salasanaTF.setLayoutX(250);

        Label vaaraSalasana = new Label("Väärä salasana!");
        vaaraSalasana.setLayoutY(260);
        vaaraSalasana.setLayoutX(250);
        vaaraSalasana.setFont(new Font(18));
        vaaraSalasana.setTextFill(Color.INDIANRED);

        Button ok = new Button("OK");
        ok.setStyle("-fx-background-color: Grey; -fx-background-radius: 15;" +
                " -fx-text-fill: black;" +
                " -fx-border-color: Black; -fx-border-radius: 10;");
        ok.setLayoutX(410);
        ok.setLayoutY(220);

        logo.setOnMouseClicked(e -> {
            paneeli.getChildren().clear();
            alkuNakyma();

        });

        paneeli.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.ESCAPE) {
                paneeli.getChildren().clear();
                alkuNakyma();
            }
        });

        ok.setOnAction(e -> {
            if(salasanaTF.getText().equals("1234")){
                tiedostonMuokkaus();
            }
            else {
                salasanaIkkuna.getChildren().add(vaaraSalasana);
            }
        });


        salasanaTF.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                if(salasanaTF.getText().equals("1234")){
                    tiedostonMuokkaus();
                }
                else{
                    salasanaIkkuna.getChildren().add(vaaraSalasana);

                }
            }
        });

        salasanaIkkuna.getChildren().addAll( logoteksti, logo, salasanaTF, ohje, ok );
        paneeli.getChildren().clear();
        paneeli.getChildren().addAll(salasanaIkkuna);
    }

    /**
     * Käsittelee kirjatietojen muokkausprosessin.
     * kirjoittaa kirjat.txt tiedostoon.
     */
    private void tiedostonMuokkaus() {
        Pane muokkausIkkuna = new Pane();
        muokkausIkkuna.setPrefSize(700,500);

        logoteksti = new Label("Kirjahaku");
        logoteksti.setLayoutX(20);
        logoteksti.setLayoutY(20);
        logoteksti.setFont(new Font(20));
        logoteksti.setTextFill(Color.WHITE);

        logo = new Rectangle(110,50);
        logo.setLayoutX(10);
        logo.setLayoutY(10);
        logo.setFill(Color.TRANSPARENT);
        logo.setStroke(Color.WHITE);

        // lisättävä kirjan lisäys

        Label ohje1 = new Label("Syötä tiedot lisättävälle kirjalle:");
        ohje1.setLayoutY(100);
        ohje1.setLayoutX(50);
        ohje1.setFont(new Font(16));
        ohje1.setTextFill(Color.LIGHTGREY);

        Label kirjanimi = new Label("Kirjan nimi:");
        kirjanimi.setLayoutY(140);
        kirjanimi.setLayoutX(50);
        kirjanimi.setFont(new Font(14));
        kirjanimi.setTextFill(Color.LIGHTGREY);

        kirjanimiTF = new TextField();
        kirjanimiTF.setLayoutY(137);
        kirjanimiTF.setLayoutX(125);
        kirjanimiTF.setStyle("-fx-background-color: Grey; -fx-background-radius: 15;" +
                " -fx-text-fill: black;" +
                " -fx-border-color: Black; -fx-border-radius: 10;");


        Label kirjailija = new Label("Kirjailija: ");
        kirjailija.setLayoutY(180);
        kirjailija.setLayoutX(50);
        kirjailija.setFont(new Font(14));
        kirjailija.setTextFill(Color.LIGHTGREY);

        kirjailijaTF = new TextField();
        kirjailijaTF.setLayoutY(177);
        kirjailijaTF.setLayoutX(125);
        kirjailijaTF.setStyle("-fx-background-color: Grey; -fx-background-radius: 15;" +
                " -fx-text-fill: black;" +
                " -fx-border-color: Black; -fx-border-radius: 10;");


        Label kirjoitusVuosi = new Label("Vuosi: ");
        kirjoitusVuosi.setLayoutY(220);
        kirjoitusVuosi.setLayoutX(50);
        kirjoitusVuosi.setFont(new Font(14));
        kirjoitusVuosi.setTextFill(Color.LIGHTGREY);

        kirjoitusVuosiTF = new TextField();
        kirjoitusVuosiTF.setLayoutY(217);
        kirjoitusVuosiTF.setLayoutX(125);
        kirjoitusVuosiTF.setStyle("-fx-background-color: Grey; -fx-background-radius: 15;" +
                " -fx-text-fill: black;" +
                " -fx-border-color: Black; -fx-border-radius: 10;");


        Label tO = new Label();
        tO.setLayoutX(130);
        tO.setLayoutY(290);
        tO.setFont(new Font(16));
        tO.setTextFill(Color.LIGHTGREY);


        Button tallennaBT = new Button("Tallenna");
        tallennaBT.setLayoutY(257);
        tallennaBT.setLayoutX(210);
        tallennaBT.setOnAction(event -> {
            // Haetaan käyttäjän syöttämät tiedot
            String kirjailijaNimi = kirjailijaTF.getText();
            String kirjanNimi = kirjanimiTF.getText();
            int vuosi = Integer.parseInt(kirjoitusVuosiTF.getText());

            // Luodaan uusi Kirja-olio
            Kirja kirja = new Kirja(kirjailijaNimi, kirjanNimi, vuosi);

            // Tallennetaan kirjan tiedot tiedostoon
            if( kirjailijaNimi!=null && kirjanNimi!=null && kirjoitusVuosiTF.getText()!=null) {
                try (FileWriter writer = new FileWriter("kirjat.txt", true)) {
                    writer.write(kirjailijaNimi + "," + kirjanNimi + "," + vuosi + "\n");
                    writer.flush();
                    tO.setText("Tallennus onnistui!");
                    muokkausIkkuna.getChildren().add(tO);
                } catch (IOException e) {
                    tO.setText("Tallennus epäonnistui!");
                    muokkausIkkuna.getChildren().add(tO);
                    e.printStackTrace();

                }

            }
            else {
                tO.setText("Täytä kaikki kentät!");
                muokkausIkkuna.getChildren().add(tO);
            }

            // Tyhjennetään syöttökentät tallennuksen jälkeen
            kirjailijaTF.clear();
            kirjanimiTF.clear();
            kirjoitusVuosiTF.clear();
        });
        tallennaBT.setStyle("-fx-background-color: Grey; -fx-background-radius: 15;" +
                " -fx-text-fill: black;" +
                " -fx-border-color: Black; -fx-border-radius: 10;");


        logo.setOnMouseClicked(e -> {
            paneeli.getChildren().clear();
            alkuNakyma();

        });

        muokkausIkkuna.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.ESCAPE) {
                paneeli.getChildren().clear();
                alkuNakyma();
            }
        });


        muokkausIkkuna.getChildren().addAll(logoteksti, logo, ohje1, kirjanimi, kirjanimiTF,
                kirjailija, kirjailijaTF, kirjoitusVuosiTF, kirjoitusVuosi, tallennaBT);

        paneeli.getChildren().clear();
        paneeli.getChildren().addAll(muokkausIkkuna);
    }


}


