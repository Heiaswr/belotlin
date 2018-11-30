package be.technifutur.mobile.domain

import javafx.scene.image.Image

import java.util.ArrayList
import java.util.Random

class Game {

    var cards: MutableList<Card>
    var players: MutableList<Player> = mutableListOf()

    internal var rdm = Random()

    init {
        this.cards = generateAndShuffleCards()
    }

    fun generateAndShuffleCards(): ArrayList<Card> {

        val listCard = ArrayList<Card>()
        val spades = ArrayList<Card>()
        val hearts = ArrayList<Card>()
        val clubs = ArrayList<Card>()
        val diamond = ArrayList<Card>()

        spades.add(Card(Value.ACE, Color.SPADES, Image("/image/AsPique.jpg")))
        spades.add(Card(Value.TEN, Color.SPADES, Image("/image/DixPique.jpg")))
        spades.add(Card(Value.KING, Color.SPADES, Image("/image/RoiPique.jpg")))
        spades.add(Card(Value.QUEEN, Color.SPADES, Image("/image/DamePique.jpg")))
        spades.add(Card(Value.JACK, Color.SPADES, Image("/image/ValetPique.jpg")))
        spades.add(Card(Value.NINE, Color.SPADES, Image("/image/NeufPique.jpg")))
        spades.add(Card(Value.EIGHT, Color.SPADES, Image("/image/HuitPique.jpg")))
        spades.add(Card(Value.SEVEN, Color.SPADES, Image("/image/SeptPique.jpg")))

        hearts.add(Card(Value.ACE, Color.HEARTS, Image("/image/AsCoeur.jpg")))
        hearts.add(Card(Value.TEN, Color.HEARTS, Image("/image/DixCoeur.jpg")))
        hearts.add(Card(Value.KING, Color.HEARTS, Image("/image/RoiCoeur.jpg")))
        hearts.add(Card(Value.QUEEN, Color.HEARTS, Image("/image/DameCoeur.jpg")))
        hearts.add(Card(Value.JACK, Color.HEARTS, Image("/image/ValetCoeur.jpg")))
        hearts.add(Card(Value.NINE, Color.HEARTS, Image("/image/NeufCoeur.jpg")))
        hearts.add(Card(Value.EIGHT, Color.HEARTS, Image("/image/HuitCoeur.jpg")))
        hearts.add(Card(Value.SEVEN, Color.HEARTS, Image("/image/SeptCoeur.jpg")))

        clubs.add(Card(Value.ACE, Color.CLUBS, Image("/image/AsTrefle.jpg")))
        clubs.add(Card(Value.TEN, Color.CLUBS, Image("/image/DixTrefle.jpg")))
        clubs.add(Card(Value.KING, Color.CLUBS, Image("/image/RoiTrefle.jpg")))
        clubs.add(Card(Value.QUEEN, Color.CLUBS, Image("/image/DameTrefle.jpg")))
        clubs.add(Card(Value.JACK, Color.CLUBS, Image("/image/ValetTrefle.jpg")))
        clubs.add(Card(Value.NINE, Color.CLUBS, Image("/image/NeufTrefle.jpg")))
        clubs.add(Card(Value.EIGHT, Color.CLUBS, Image("/image/HuitTrefle.jpg")))
        clubs.add(Card(Value.SEVEN, Color.CLUBS, Image("/image/SeptTrefle.jpg")))

        diamond.add(Card(Value.ACE, Color.DIAMOND, Image("/image/AsCarreau.jpg")))
        diamond.add(Card(Value.TEN, Color.DIAMOND, Image("/image/DixCarreau.jpg")))
        diamond.add(Card(Value.KING, Color.DIAMOND, Image("/image/RoiCarreau.jpg")))
        diamond.add(Card(Value.QUEEN, Color.DIAMOND, Image("/image/DameCarreau.jpg")))
        diamond.add(Card(Value.JACK, Color.DIAMOND, Image("/image/ValetCarreau.jpg")))
        diamond.add(Card(Value.NINE, Color.DIAMOND, Image("/image/NeufCarreau.jpg")))
        diamond.add(Card(Value.EIGHT, Color.DIAMOND, Image("/image/HuitCarreau.jpg")))
        diamond.add(Card(Value.SEVEN, Color.DIAMOND, Image("/image/SeptCarreau.jpg")))

        var a: Int
        var a1: Int
        var j1 = 0
        var a2: Int
        var j2 = 0
        var a3: Int
        var j3 = 0
        var a4: Int
        var j4 = 0
        while (spades.size != 0 || diamond.size != 0 || hearts.size != 0 || clubs.size != 0) {
            a = rdm.nextInt(4)
            if (a == 0 && spades.size != 0) {

                a1 = rdm.nextInt(8 - j1)
                listCard.add(spades[a1])
                spades.removeAt(a1)
                j1++

            } else if (a == 1 && hearts.size != 0) {
                a2 = rdm.nextInt(8 - j2)
                listCard.add(hearts[a2])
                hearts.removeAt(a2)
                j2++

            } else if (a == 2 && diamond.size != 0) {

                a3 = rdm.nextInt(8 - j3)
                listCard.add(diamond[a3])
                diamond.removeAt(a3)
                j3++
            } else if (a == 3 && clubs.size != 0) {
                a4 = rdm.nextInt(8 - j4)
                listCard.add(clubs[a4])
                clubs.removeAt(a4)
                j4++
            }
        }

        return listCard
    }

    fun addPlayer(player: Player) {
        this.players.add(player)
    }


    fun firstDistribution(index: Int){

        players.get(index%4)

    }
}
