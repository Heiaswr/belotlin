package be.technifutur.mobile.domain

import be.technifutur.mobile.data.BeloteDao

import java.time.LocalDate
import java.util.ArrayList
import java.util.Scanner

class Player {

    var registerDate = LocalDate.now()
    internal var beloteDao = BeloteDao()
    var id: Int = 0
    var emailAdress: String? = null
    var username: String? = null
    var password: String? = null
    var Country: String? = null
    var hp: Int = 0
    var cards: MutableList<Card> = mutableListOf()


    constructor() {

    }

    constructor(identifiant: String) {
        this.username = identifiant
        cards = ArrayList()
    }

    constructor(adresseMail: String, identifiant: String, mdp: String, pays: String) {

        this.emailAdress = adresseMail
        this.username = identifiant
        this.password = mdp
        this.Country = pays
        this.hp = 100
        cards = ArrayList()
    }

    override fun toString(): String {
        return "Player{" +
                "id=" + id +
                ", emailAdress='" + emailAdress + '\''.toString() +
                ", username='" + username + '\''.toString() +
                ", Country='" + Country + '\''.toString() +
                ", registerDate=" + registerDate +
                ", hp=" + hp +
                '}'.toString()
    }


    fun inscription(player: Player): Player {

        val sc = Scanner(System.`in`)


        do {
            print("Mail: ")
            player.emailAdress = sc.nextLine()
        } while (!mailIsValid(player.emailAdress))

        do {
            print("Identifiant: ")
            player.username = sc.nextLine()
        } while (!identifiantIsValid(player.username))

        do {
            print("Mot de passe: ")
            player.password = sc.nextLine()
        } while (!mdpIsValid(player.password!!))

        print("Pays: ")
        player.Country = sc.nextLine()


        return player


    }

    fun identification(player: Player): Boolean {

        val reponse: Boolean
        val sc = Scanner(System.`in`)
        print("Login: ")
        val login = sc.nextLine()
        print("Pwd: ")
        val pwd = sc.nextLine()

        if (!beloteDao.identificationIsValid(login, pwd)) {
            reponse = false
            println("Identifiant et/ou mot de passe incorrect")
        } else {
            reponse = true
        }

        return reponse


    }


    private fun mailIsValid(mail: String?): Boolean {


        val ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$"
        val p = java.util.regex.Pattern.compile(ePattern)
        val m = p.matcher(mail!!)
        if (!m.matches()) {
            println("Adresse mail incorrecte.")
        } else if (beloteDao.mailNotUsed(mail)) {
            println("Adresse mail déjà utilisée.")
            return false
        }
        return m.matches()

    }

    private fun mdpIsValid(mdp: String): Boolean {

        val pattern = "(?=\\S+$).{8,}"

        if (mdp.matches(pattern.toRegex())) {
            return true
        } else {
            println("Mot de passe incorrect. (Minimum: 8 caractères)")
            return false
        }
    }

    private fun identifiantIsValid(identifiant: String?): Boolean {

        val pattern = "(?=\\S+$).{4,}"

        if (beloteDao.usernameNotUsed(identifiant!!)) {
            println("Identifiant déjà utilisée.")
            return false
        } else if (identifiant.matches(pattern.toRegex())) {
            return true
        } else {
            println("Identifiant incorrect. (Minimum: 4 caractères)")
            return false
        }
    }

    fun addCard(card: Card){
        cards.add(card)
    }



}
