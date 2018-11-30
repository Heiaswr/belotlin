package be.technifutur.mobile.controller

import be.technifutur.mobile.domain.Player
import be.technifutur.mobile.util.NavigationMessenger
import be.technifutur.mobile.util.Page
import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.input.MouseEvent

class LoggedMenuController : EventHandler<MouseEvent>, NavigationMessenger.Listener {

    @FXML
    private lateinit var usernameLabel: Label

    @FXML
    private lateinit var soloButton: Button
    @FXML
    private lateinit var multiplayerButton: Button
    @FXML
    private lateinit var optionsButton: Button
    @FXML
    private lateinit var loggedLoggoffButton: Button

    private lateinit var player: Player


    fun initialize() {

        println("initialize()")


        //        loginButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
        //            @Override
        //            public void handle(MouseEvent mouseEvent) {
        //                System.out.println("Clicked !");
        //                loginButton.setText("Already clicked");
        //                loginButton.setDisable(true);
        //            }
        //        });


        NavigationMessenger.register(this)
        soloButton.onMouseClicked = this
        multiplayerButton.onMouseClicked = this
        optionsButton.onMouseClicked = this
        loggedLoggoffButton.onMouseClicked = this


    }

    override fun handle(mouseEvent: MouseEvent) {

        if (mouseEvent.source === soloButton) {
            println("Solo !")
            NavigationMessenger.navigateTo(Page.SOLO, player)

        }
        if (mouseEvent.source === multiplayerButton) {
            println("Multi !")


        }
        if (mouseEvent.source === optionsButton) {
            println("Options !")

        }
        if (mouseEvent.source === loggedLoggoffButton) {
            println("LOGG OFF")

            NavigationMessenger.navigateTo(Page.MAIN)
        } else {
            println("Clic non géré")
        }
    }

    override fun onNavigateTo(page: Page, data: Any?) {
        if (page == Page.LOGGEDMENU) {

            player = data as Player
            println(player)
            usernameLabel.text = player.username
        }
    }
}
