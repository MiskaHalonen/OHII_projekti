module projektityo.ohiiProjekti {
    requires javafx.controls;
    requires javafx.fxml;


    opens projektityo.ohiiProjektiTyo to javafx.fxml;
    exports projektityo.ohiiProjektiTyo;
}