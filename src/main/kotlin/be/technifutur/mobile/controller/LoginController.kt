package be.technifutur.mobile.controller

import be.technifutur.mobile.data.BeloteDao
import be.technifutur.mobile.domain.Player
import be.technifutur.mobile.util.NavigationMessenger
import be.technifutur.mobile.util.Page
import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.scene.control.*
import javafx.scene.input.KeyEvent
import javafx.scene.input.MouseEvent


class LoginController : EventHandler<MouseEvent> {

    @FXML
    private lateinit var username: TextField

    @FXML
    private lateinit var password: PasswordField

    @FXML
    private lateinit var valider: Button

    @FXML
    private lateinit var InvalidMatch: Label

    @FXML
    private lateinit var loginBackHL: Hyperlink


    fun initialize() {

        println("RegisterInitialize()")


        //        loginButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
        //            @Override
        //            public void handle(MouseEvent mouseEvent) {
        //                System.out.println("Clicked !");
        //                loginButton.setText("Already clicked");
        //                loginButton.setDisable(true);
        //            }
        //        });
        valider.onMouseClicked = this

        loginBackHL.onMouseClicked = this

        password.onKeyPressed = EventHandler { keyEvent ->
            if (keyEvent.code.code == 10) {
                validationBouton()

            }
        }

        username.onKeyPressed = EventHandler { keyEvent ->
            if (keyEvent.code.code == 10) {
                validationBouton()

            }
        }

    }


    override fun handle(mouseEvent: MouseEvent) {
        if (mouseEvent.source === valider) {
            validationBouton()
        }
        if (mouseEvent.source === loginBackHL) {
            println("LOGIN BACK")

            NavigationMessenger.navigateTo(Page.MAIN)
        }


    }

    private fun validationBouton() {
        val player: Player
        val beloteDao = BeloteDao()
        InvalidMatch.isVisible = false

        if (!beloteDao.identificationIsValid(username.text, password.text)) {
            InvalidMatch.isVisible = true
        } else {
            println("Identification réussie !")
            player = beloteDao.loginWhenMatches(username.text)
            NavigationMessenger.navigateTo(Page.LOGGEDMENU, player)

        }
    }


}
