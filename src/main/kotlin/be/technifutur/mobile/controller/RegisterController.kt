package be.technifutur.mobile.controller

import be.technifutur.mobile.data.BeloteDao
import be.technifutur.mobile.domain.Player
import be.technifutur.mobile.util.NavigationMessenger
import be.technifutur.mobile.util.Page
import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.scene.control.*
import javafx.scene.input.MouseEvent

class RegisterController : EventHandler<MouseEvent> {
    @FXML
    private lateinit var loginPassword: PasswordField
    @FXML
    private lateinit var loginUsername: TextField
    @FXML
    private lateinit var loginMail: TextField
    @FXML
    private lateinit var loginCountry: TextField
    @FXML
    private lateinit var valider: Button

    @FXML
    private lateinit var loginInvalidUsername: Label

    @FXML
    private lateinit var loginUsedUsername: Label

    @FXML
    private lateinit var loginInvalidMail: Label

    @FXML
    private lateinit var loginUsedMail: Label

    @FXML
    private lateinit var loginInvalidPassword: Label

    @FXML
    private lateinit var registerBackHL: Hyperlink


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

        registerBackHL.onMouseClicked = this


    }


    override fun handle(mouseEvent: MouseEvent) {
        val beloteDao = BeloteDao()
        val player: Player
        var isValid = true
        loginUsedUsername.isVisible = false
        loginInvalidUsername.isVisible = false
        loginUsedMail.isVisible = false
        loginInvalidMail.isVisible = false
        loginInvalidPassword.isVisible = false

        if (mouseEvent.source === registerBackHL) {
            println("REGISTER BACK")

            NavigationMessenger.navigateTo(Page.MAIN)

        }


        if (beloteDao.usernameNotUsed(loginUsername.text)) {
            println("Username used !")
            loginUsedUsername.isVisible = true
            isValid = false

        } else if (!beloteDao.usernameValidator(loginUsername.text)) {
            println("Invalid username")
            loginInvalidUsername.isVisible = true
            isValid = false
        }
        if (beloteDao.mailNotUsed(loginMail.text)) {
            println("Mail used !")
            loginUsedMail.isVisible = true
            isValid = false
        } else if (!beloteDao.mailValidator(loginMail.text)) {
            println("Invalid mail")
            loginInvalidMail.isVisible = true
            isValid = false
        }

        if (!beloteDao.passwordVerificator(loginPassword.text)) {
            println("Invalid password")
            loginInvalidPassword.isVisible = true
            isValid = false
        }


        if (isValid) {

            if (mouseEvent.source === valider) {
                player = Player(loginMail.text, loginUsername.text, loginPassword.text, loginCountry.text)
                beloteDao.insert(player)
                println("Valider !")
                NavigationMessenger.navigateTo(Page.LOGGEDMENU, player)
            }
        }


    }

}
