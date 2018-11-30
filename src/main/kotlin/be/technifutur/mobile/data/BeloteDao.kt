package be.technifutur.mobile.data

import be.technifutur.mobile.domain.Player
import org.mindrot.jbcrypt.BCrypt

import java.sql.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class BeloteDao {

    private val connectionStr = "jdbc:postgresql://localhost:5432/belotev001"
    private val user = "postgres"
    private val passwd = "postgres"

    /*

CREATE TABLE joueur(
    id_joueur SERIAL NOT NULL,
    mail character varying(50) COLLATE pg_catalog."default" NOT NULL,
    username character varying(25) COLLATE pg_catalog."default" NOT NULL,
    password character varying(100) COLLATE pg_catalog."default" NOT NULL,
    Country character varying(25) COLLATE pg_catalog."default",
    date_inscription date,
    hp integer,
    CONSTRAINT joueur_pkey PRIMARY KEY (id_joueur)
)

     */


    fun insert(player: Player) {

        try {
            DriverManager.getConnection(connectionStr, user, passwd).use { connection ->
                connection.createStatement().use { statement ->

                    /*CREATE TABLE player (id_joueur SERIAL not null,
                mail VARCHAR(50) not null,
                username VARCHAR(25) not null,
                Country VARCHAR(25),
                date_inscription Date,
                hp INT, PRIMARY KEY(id_joueur))*/
                    val pwd = BCrypt.hashpw(player.password, BCrypt.gensalt())
                    val query = String.format("INSERT INTO player (mail, identifiant, mdp, pays, date_inscription, hp)" + "VALUES ('%s', '%s', '%s', '%s', '%s', '%d')", player.emailAdress, player.username, pwd,
                            player.Country, player.registerDate.format(DateTimeFormatter.ISO_LOCAL_DATE), player.hp)


                    statement.execute(query, Statement.RETURN_GENERATED_KEYS)
                    val rs = statement.generatedKeys
                    if (rs.next()) {
                        player.id = rs.getInt(1)

                    }
                    rs.close()

                }
            }
        } catch (e: SQLException) {
            System.err.println("Erreur avec la DB: " + e.message)
        }
        // statement.executeUpdate(...) INSERT, UPDATE, DELETE
        // statement.executeQuery(...) SELECT
        // SELECT id, nom FROM jeu


    }

    fun mailNotUsed(mail: String): Boolean {

        var bool = true

        try {
            DriverManager.getConnection(connectionStr, user, passwd).use { connection ->
                connection.createStatement().use { statement ->

                    /*CREATE TABLE joueur (id_joueur SERIAL not null,
                mail VARCHAR(50) not null,
                username VARCHAR(25) not null,
                Country VARCHAR(25),
                date_inscription Date,
                hp INT, PRIMARY KEY(id_joueur))*/
                    val query = String.format("SELECT * FROM joueur WHERE mail = '%s'", mail)


                    statement.execute(query)
                    val rs = statement.executeQuery(query)
                    bool = rs.next()
                    rs.close()


                }
            }
        } catch (e: SQLException) {
            System.err.println("Erreur avec la DB: " + e.message)
        }
        // statement.executeUpdate(...) INSERT, UPDATE, DELETE
        // statement.executeQuery(...) SELECT
        // SELECT id, nom FROM jeu
        return bool


    }

    fun usernameNotUsed(username: String): Boolean {

        var bool = true

        try {
            DriverManager.getConnection(connectionStr, user, passwd).use { connection ->
                connection.createStatement().use { statement ->

                    /*CREATE TABLE joueur (id_joueur SERIAL not null,
                mail VARCHAR(50) not null,
                username VARCHAR(25) not null,
                Country VARCHAR(25),
                date_inscription Date,
                hp INT, PRIMARY KEY(id_joueur))*/
                    val query = String.format("SELECT * FROM joueur WHERE identifiant = '%s'", username)


                    statement.execute(query)
                    val rs = statement.executeQuery(query)
                    bool = rs.next()
                    rs.close()


                }
            }
        } catch (e: SQLException) {
            System.err.println("Erreur avec la DB: " + e.message)
        }
        // statement.executeUpdate(...) INSERT, UPDATE, DELETE
        // statement.executeQuery(...) SELECT
        // SELECT id, nom FROM jeu
        return bool


    }


    fun identificationIsValid(username: String, pwd: String): Boolean {

        try {
            DriverManager.getConnection(connectionStr, user, passwd).use { connection ->
                connection.createStatement().use { statement ->

                    /*CREATE TABLE joueur (id_joueur SERIAL not null,
                mail VARCHAR(50) not null,
                username VARCHAR(25) not null,
                Country VARCHAR(25),
                date_inscription Date,
                hp INT, PRIMARY KEY(id_joueur))*/

                    val query = String.format("SELECT * FROM joueur WHERE identifiant = '%s'", username)


                    statement.execute(query)
                    val rs = statement.executeQuery(query)

                    if (rs.next()) {
                        if (BCrypt.checkpw(pwd, rs.getString("mdp"))) {
                            return true
                        }
                    }


                    rs.close()


                }
            }
        } catch (e: SQLException) {
            System.err.println("Erreur avec la DB: " + e.message)
        }
        // statement.executeUpdate(...) INSERT, UPDATE, DELETE
        // statement.executeQuery(...) SELECT
        // SELECT id, nom FROM jeu

        return false


    }

    fun loginWhenMatches(username: String): Player {

        val joueur = Player()

        try {
            DriverManager.getConnection(connectionStr, user, passwd).use { connection ->
                connection.createStatement().use { statement ->

                    /*CREATE TABLE joueur (id_joueur SERIAL not null,
                mail VARCHAR(50) not null,
                username VARCHAR(25) not null,
                Country VARCHAR(25),
                date_inscription Date,
                hp INT, PRIMARY KEY(id_joueur))*/
                    val query = String.format("SELECT * FROM joueur WHERE identifiant = '%s'", username)


                    statement.execute(query)
                    val rs = statement.executeQuery(query)

                    if (rs.next()) {
                        joueur.id = rs.getInt("id_joueur")
                        joueur.emailAdress = rs.getString("mail")
                        joueur.username = rs.getString("identifiant")
                        joueur.password = rs.getString("mdp")
                        joueur.Country = rs.getString("pays")
                        joueur.registerDate = LocalDate.parse(rs.getString("date_inscription"), DateTimeFormatter.ISO_LOCAL_DATE)
                        joueur.hp = rs.getInt("hp")
                    }


                    rs.close()


                }
            }
        } catch (e: SQLException) {
            System.err.println("Erreur avec la DB: " + e.message)
        }
        // statement.executeUpdate(...) INSERT, UPDATE, DELETE
        // statement.executeQuery(...) SELECT
        // SELECT id, nom FROM jeu


        return joueur


    }


    fun usernameValidator(username: String): Boolean {

        val pattern = "(?=\\S+$).{4,}"

        return if (username.matches(pattern.toRegex())) {
            true
        } else {
            false
        }
    }

    fun mailValidator(mail: String): Boolean {
        val ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$"
        val p = java.util.regex.Pattern.compile(ePattern)
        val m = p.matcher(mail)
        if (!m.matches()) {
            println("Adresse mail incorrecte.")
        }

        return m.matches()
    }

    fun passwordVerificator(mdp: String): Boolean {
        val pattern = "(?=\\S+$).{8,}"

        if (mdp.matches(pattern.toRegex())) {
            return true
        } else {
            println("Mot de passe incorrect. (Minimum: 8 caractères)")
            return false
        }
    }
}
