package be.technifutur.mobile.controller

import be.technifutur.mobile.util.NavigationMessenger
import be.technifutur.mobile.util.Page
import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.input.MouseEvent

class MainController : EventHandler<MouseEvent> {

    @FXML
    private lateinit var loginButton: Button

    @FXML
    private lateinit var registerButton: Button


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

        loginButton.onMouseClicked = this
        registerButton.onMouseClicked = this
    }

    override fun handle(mouseEvent: MouseEvent) {

        if (mouseEvent.source === loginButton) {
            println("Login !")
            NavigationMessenger.navigateTo(Page.LOGIN)

        }
        if (mouseEvent.source === registerButton) {
            println("Register !")


            NavigationMessenger.navigateTo(Page.REGISTER)

        } else {
            println("Clic non géré")
        }
    }

}

/*   joueur.inscription(joueur);
            BeloteDao beloteDao = new BeloteDao();
            beloteDao.insert(joueur);

            joueur.identification(joueur);*/

