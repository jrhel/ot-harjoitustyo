# Työaikakirjanpito

| päivä | aika (h)  | mitä tein  |
| :----:|:---------:| :----------|
| 21.3. | 3         | Loin Maven-projektin, .gitignore-tiedoston, työaikakirjanpito-tiedoston, ja alustavan määrittelydokumentin |
| 30.3  | 4         | Loin repositorion juureen Maven-projektin, rupesin rakentamaan pakkaushierarkian, aloin koodata tilapäisen tekstikäyttöliittymän, ja varmistin että koodin voi suorittaa komennolla "mvn compile exec:java -Dexec.mainClass=[...]".
| 31.3  | 7         | Palautin mieleeni SQL-kielen, H2:n käyttö ja miten sen saa toimimaan Javassa, ja harjoittelin tätä, ja päivitin README-tiedoston.
| 5.4   | 4         | Testasin tietokannan luonti, tietokantatalujen luonti ja poisto, ja niiden päivittäminen h2 & jdbc:n kautta. Pohdin "SpringApplication"in käyttöönottoa, mutten päätynyt sen kannalle. Suunnittelin myös sovelluksen tietokannan.
| 7.4   | 5         | Checkstyle on otettu käyttöön. Koodasin tietokantayhteyden ja tarvittavien tietokantataulujen olemassaolon tarkistus/luonti ennen käyttöliittymän käynnistämistä, ja alustin sovelluslogiikan metodeilla alustaa ja tyhjentää tietokannan. Jostakin syystä en saa viiteavaimia toimimaan. Todennäköisesti en ehdi ratkoa tätä tänään, ennen tämän päivän määräajan.
| 12.4  | 3         | Tietokannan uudelleensuunnittelu: Havahduin tosiasiaan että "Resepti" on itse "Aineksen" erikoistapaus, ja sen tietokantataulu edellyttää siksi vierasavaimen aineksen tietokantatauluun. Loin tietokantataulut. Ratkoin yllä mainutun bugin. 
| 16.4  | 3         | Palautin mieleeni DAO-suunnittelumallin, loin DAO-rajapinnan ja tietokantatauluille omat DAO-oliot, ja oliot niiden parametreille, ja laajennin tätä mukaan sovellusarkkitehtuurin.
| 17.4  | 3         | Jatkoin sovellusarkkitehtuurin laajentamista korjaamalla  luokkien "Recipe", "Ingredient", ja "RecipeIngredient" muotoa ja toimintaa. Loin DAO-olioille omat "getPrimaryKey()" funktiot. Aloitin sovelluslogiikkamukaisen koodaaminen reseptin tallentamiselle tietokantaan.
| 20.4  | 2         | Jatkoin sovelluslogiikkamukaisen koodaaminen reseptin tallentamiselle.
| 21.4  | 5         | Korjasin puutteen, että "jacobo" ei ollut määritelty projektin pom.xml-tiedostossa, ja varmistin että saa testikattavuusraportin luotua komennolla "mvn test jacoco:report". Loin myös ensimmäisen testin. Lisäsin "Komentorivitoiminnot"-osan projektin "README"-tiedostoon. Loin projektista jar-tiedoston ja projektista "github release". Jostakin syystä "java -jar otRecipeDatabase-1.0-SNAPSHOT.jar" tuottaa virheen: "Error: LinkageError occurred while loading main class main.Main java.lang.UnsupportedClassVersionError: main/Main has been compiled by a more recent version of the Java Runtime (class file version 57.0), this version of the Java Runtime only recognizes class file versions up to 55.0". Tätä en ole saanut ratkottua vielä. Siirsin työaikakirjanpidon dokumentaatio-kansioon.
| 28.4  | 7         | Ratkoin bugin reseptin tallentamisessa tietokantaan. (Yllämainittu "LinkageError" häipyi itsestään(??).) Lisäsin tietokantatestejä ja toiminnon tallennettujen reseptien listaamiseksi. Aloitin JavaDoc-dokumentointia ja toteutin mahdollisuuden generoida JavaDoc komennolla mvn javadoc:javadoc. Laajennin myös projektin arkkitehtuurikuvauksen, ja aloitin käyttöohjeen kirjoittamisen. Korjasin useita chechstyle-virheitä, ja loin uuden releasen projektille (viikko 6).
| 1.5   | 2         | JavaDoc-kattavuuden lisäämistä ja koodin kommentointia ja korjaamista.
| 2.5   | 3         | Siirsin (koodasin uudestaan) DAO-luokkien hallinta täysin "Logic"-luokalle, ja toteutin mahdollisuus listata tietokannassa olevat ainekset.
| 3.5   | 4         | Lisättiin projektiin JavaFX-riippuvuus ja muokattiin ohjelman Main-luokkaa ja graafista käyttöliittymää niin että ohjelmasta saadann generoitua jar-tiedosto. Suunnittelin graafisen käyttöliittymän ulkoasun/pääobjektien asettelun, ja koodasin sen rungon.
| 4.5   | 3         | Koodasin mahdolisuuden lisätä reseptin graafisen käyttöliittymän kautta.
| 5.5   | 4         | Korjasin bugin jonka vuoksi reseptikohtaiset ainekset eivät tallentuneet toivotulla tavalla tietokantaan.
| 6.5   | 4         | Lisäsin mahdollisuuden avata reseptin katseltavaksi graafisessa käyttöliittymässä.
| 7.5   | 3         | Muokkasin graafisen käyttöliittymän ulkoasua, ja lisäsin toiminnon listata reseptejä ja lisäsin sovelluksen aloitussivulle listan tellennetuista resepteistä.
| 8.5   | 3         | Lisäsinn toiminnallisuuden reseptien poistamiseen, ja etsimiseen nimen perusteella.
| 9.5   | 5         | Estin käyttäjää lisäämästa kahta reseptiä samalla nimellä, ja sovelluksen tallentamasta kahta ainesta samalla nimellä. Siirsin tietokantakyselyt tietokannan palauttamiselle alkutilaansa, ja tietokannan taulujen olemassaolon varmistaminen, sovelluslogiikasta DAO:ille, ja lisäsin mahdollisuuden palauttaa tietokanta alkutilaansa graafisesta käyttöliittymästä. Koodasin aloitussivun reseptilistan päivittäytymään automaattisesti kun respetejä lisätään tai poistetaan sovelluksesta.
| 10.5  | 7         | Päivitin projektin README, määrittelydokumentin, arkkitehtuurikuvauksen, käyttöohjeen.
| yht   | 84        | 
