package be.technifutur.mobile

import be.technifutur.mobile.util.NavigationMessenger
import be.technifutur.mobile.util.Page
import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage

import java.io.IOException

class Main : Application(), NavigationMessenger.Listener {

    private var primaryStage: Stage? = null


    override fun onNavigateTo(page: Page, data: Any?) {

        when (page) {
            Page.MAIN -> goToFirstScene()
            Page.LOGIN -> goToLoginScene()
            Page.REGISTER -> goToRegisterScene()
            Page.LOGGEDMENU -> goToLoggedMenuScene()
            Page.SOLO -> goToSoloScene()
        }
    }

    private fun goToLoggedMenuScene() {
        try {
            val root = FXMLLoader.load<Parent>(Main::class.java.getResource("/views/loggedmenu.fxml"))
            primaryStage!!.scene = Scene(root, 800.0, 800.0)
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    private fun goToLoginScene() {
        try {
            val root = FXMLLoader.load<Parent>(Main::class.java.getResource("/views/login.fxml"))
            primaryStage!!.scene = Scene(root, 800.0, 800.0)
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }


    fun goToFirstScene() {
        try {
            val root = FXMLLoader.load<Parent>(Main::class.java.getResource("/views/main.fxml"))
            primaryStage!!.scene = Scene(root, 800.0, 800.0)
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    fun goToRegisterScene() {
        try {
            val root = FXMLLoader.load<Parent>(Main::class.java.getResource("/views/register.fxml"))
            primaryStage!!.scene = Scene(root, 800.0, 800.0)
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    fun goToSoloScene() {
        try {
            val root = FXMLLoader.load<Parent>(Main::class.java.getResource("/views/solo.fxml"))
            primaryStage!!.scene = Scene(root, 800.0, 800.0)
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    @Throws(Exception::class)
    override fun start(primaryStage: Stage) {

        this.primaryStage = primaryStage

        primaryStage.title = "Belote de la mort qui tue"
        goToFirstScene()
        primaryStage.show()

        NavigationMessenger.register(this)

    }
}

fun main(args: Array<String>) {
    Application.launch(Main::class.java, *args)
}