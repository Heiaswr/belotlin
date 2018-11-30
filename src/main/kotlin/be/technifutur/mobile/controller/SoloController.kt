package be.technifutur.mobile.controller

import be.technifutur.mobile.domain.Color
import be.technifutur.mobile.domain.Game
import be.technifutur.mobile.domain.Player
import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.input.MouseEvent
import javafx.scene.layout.GridPane
import java.util.*


class SoloController : EventHandler<MouseEvent> {


    @FXML
    private lateinit var playedCardsPlayer1: ImageView
    @FXML
    private lateinit var playedCardsPlayer2: ImageView
    @FXML
    private lateinit var playedCardsPlayer3: ImageView
    @FXML
    private lateinit var playedCardsPlayer4: ImageView
    @FXML
    private lateinit var carteRetourne: ImageView

    private lateinit var playedCards: MutableList<ImageView>

    @FXML
    private lateinit var partir: Label
    @FXML
    private lateinit var oui: Button
    @FXML
    private lateinit var non: Button
    @FXML
    private lateinit var atoutPris: Label
    @FXML
    private lateinit var couleur: ImageView

    @FXML
    private lateinit var grid: GridPane
    @FXML
    private lateinit var grid2: GridPane
    @FXML
    private lateinit var grid3: GridPane
    @FXML
    private lateinit var grid4: GridPane
    @FXML
    private lateinit var gridTable: GridPane

    private lateinit var grids: List<GridPane>

    private var game: Game = Game()

    //endregion

    fun initialize() {

        println("initialize()")

        playedCards = mutableListOf(playedCardsPlayer1, playedCardsPlayer2, playedCardsPlayer3, playedCardsPlayer4)

        grids = Arrays.asList<GridPane>(grid, grid2, grid3, grid4)




        game.addPlayer(Player("Player 1"))
        game.addPlayer(Player("Player 2"))
        game.addPlayer(Player("Player 3"))
        game.addPlayer(Player("Player 4"))

        premiereDistribution()
        dispositionCartes()

        carteRetourne!!.image = game.cards[0].image
        oui!!.onMouseClicked = this
        non!!.onMouseClicked = this
    }

    private fun dispositionCartes() {

        val players = game.players

        for (i in players.indices) {

            val player = players[i]
            val grid = grids!![i]

            if (i == 0) {
                disposition(player, grid, true)
            } else {
                disposition(player, grid, false)
            }
        }
    }


    private fun disposition(player: Player, grid: GridPane, isPlayer: Boolean) {

        for (card in player.cards) {

            if (isPlayer) {
                val imageView = ImageView()
                imageView.image = card.image
                imageView.fitWidth = 60.0
                imageView.fitHeight = 97.5
                imageView.onMouseClicked = this
                grid.add(imageView, player.cards.indexOf(card), 0)

            }

            if (!isPlayer) {
                val imageViewDos = ImageView()
                imageViewDos.image = Image("/image/DosDeCArte.jpg")
                imageViewDos.fitWidth = 60.0
                imageViewDos.fitHeight = 97.5
                grid.add(imageViewDos, player.cards.indexOf(card), 0)
            }
        }
    }

    private fun premiereDistribution() {
        tourDistribution(3)
        tourDistribution(2)
    }

    private fun tourDistribution(nbrDeCartesADonner: Int) {
        for (player in game.players) {
            for (i in 0 until nbrDeCartesADonner) {
                player.cards.add(game.cards.removeAt(0))
            }
        }
    }

    private fun deuxiemeDistribution() {
        for (player in game.players) {

            if (player.cards.size == 6) {
                player.cards.add(game.cards.removeAt(0))
                player.cards.add(game.cards.removeAt(0))
            } else {
                player.cards.add(game.cards.removeAt(0))
                player.cards.add(game.cards.removeAt(0))
                player.cards.add(game.cards.removeAt(0))
            }

        }
    }

    private fun deuxiemeDisposition() {

        for (j in 5..7) {

            val imageView = ImageView()
            imageView.image = game.players[0].cards[j].image
            imageView.fitWidth = 60.0
            imageView.fitHeight = 97.5
            imageView.onMouseClicked = this
            grid!!.add(imageView, j, 0)

            for (i in 1..3) {
                val grid = grids!![i]
                val imageViewDos = ImageView()
                imageViewDos.image = Image("/image/DosDeCArte.jpg")
                imageViewDos.fitWidth = 60.0
                imageViewDos.fitHeight = 97.5
                grid.add(imageViewDos, j, 0)
            }
        }
    }

    override fun handle(mouseEvent: MouseEvent) {

        if (mouseEvent.source === oui || mouseEvent.source === non) {

            when (game.cards[0].color) {
                Color.SPADES -> {
                    couleur!!.image = Image("/image/Pique1.png")
                    couleur.isVisible = true
                    atoutPris!!.isVisible = true
                }
                Color.HEARTS -> {
                    couleur!!.image = Image("/image/Coeur.png")
                    couleur.isVisible = true
                    atoutPris!!.isVisible = true
                }
                Color.CLUBS -> {
                    couleur!!.image = Image("/image/Trefle.png")
                    couleur.isVisible = true
                    atoutPris!!.isVisible = true
                }
                Color.DIAMOND -> {
                    couleur!!.image = Image("/image/Carreau.png")

                    couleur.isVisible = true
                    atoutPris!!.isVisible = true
                }

            }

            if (mouseEvent.source === oui) {
                game.players[0].cards.add(game.cards.removeAt(0))
                carteRetourne!!.isVisible = false
                partir!!.isVisible = false
                gridTable!!.children.remove(oui)
                gridTable.children.remove(non)

            } else {
                val rdm = Random()
                val hasard = rdm.nextInt(2) + 1
                game.players[hasard].cards.add(game.cards.removeAt(0))
                carteRetourne!!.isVisible = false
                partir!!.isVisible = false
                gridTable!!.children.remove(oui)
                gridTable.children.remove(non)

            }
            deuxiemeDistribution()
            deuxiemeDisposition()
        } else {

            for (player in game.players) {
                val indexJoueur = game.players.indexOf(player)
                if (indexJoueur == 0) {
                    val grid = grids!![indexJoueur]
                    for (child in grid.children) {
                        if (mouseEvent.source === child) {
                            val iv = mouseEvent.source as ImageView
                            iv.isVisible = false
                            playedCards!![indexJoueur].image = iv.image
                        }
                    }
                } else {

                    val random = Random()
                    val card = player.cards[random.nextInt(player.cards.size)]
                    val indexCarte = player.cards.indexOf(card)
                    player.cards.remove(card)

                    val grid = grids!![indexJoueur]
                    val iv = grid.children[indexCarte] as ImageView
                    iv.isVisible = false
                    grid.children.remove(iv)
                    playedCards!![indexJoueur].image = card.image
                }
            }
        }
    }
}
