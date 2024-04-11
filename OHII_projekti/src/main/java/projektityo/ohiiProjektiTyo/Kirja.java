package projektityo.ohiiProjektiTyo;

/**
 * Kirja-luokka on olio luokka joka sisältää alustajat get,set ja tostring metodt olioille.
 */
public class Kirja {


    private String kirjailija;
    private String kirjanNimi;
    private int vuosi;


    /**
     * Luo uuden Kirja-olion oletuskonstruktorilla.
     */
    public Kirja(){
    }



    /**
     * Luo uuden Kirja-olion annetuilla tiedoilla.
     *
     * @param kirjailija kirjan kirjoittajan nimi
     * @param kirjanNimi kirjan nimi
     * @param vuosi      kirjan julkaisuvuosi
     */
    public Kirja(String kirjailija, String kirjanNimi, int vuosi){
        this.kirjailija = kirjailija;
        this.kirjanNimi = kirjanNimi;
        this.vuosi = vuosi;
    }


    /**
     * Palauttaa kirjan kirjoittajan nimen.
     *
     * @return kirjan kirjoittajan nimi
     */
    public String getKirjailija() {
        return kirjailija;
    }

    /**
     * Asettaa kirjan kirjoittajan nimen.
     *
     * @param kirjailija kirjan kirjoittajan nimi
     */
    public void setKirjailija(String kirjailija) {
        this.kirjailija = kirjailija;
    }

    /**
     * Palauttaa kirjan nimen.
     *
     * @return kirjan nimi
     */
    public String getKirjanNimi() {
        return kirjanNimi;
    }

    /**
     * Asettaa kirjan nimen.
     *
     * @param kirjanNimi kirjan nimi
     */
    public void setKirjanNimi(String kirjanNimi) {
        this.kirjanNimi = kirjanNimi;
    }

    /**
     * Palauttaa kirjan julkaisuvuoden.
     *
     * @return kirjan julkaisuvuosi
     */
    public int getVuosi() {
        return vuosi;
    }

    /**
     * Asettaa kirjan julkaisuvuoden.
     *
     * @param vuosi kirjan julkaisuvuosi
     */
    public void setVuosi(int vuosi) {
        this.vuosi = vuosi;
    }


    /**
     * Palauttaa merkkijonoesityksen Kirja-oliosta.
     *
     * @return merkkijonoesitys Kirja-oliosta
     */
    public String toString(){
        return "Kirjan nimi: " + getKirjanNimi() + "\nKirjan kirjoittaja: "
                + getKirjailija() + "\nKirjoitus vuosi: " + getVuosi();
    }


    /**
     * Testi pääohjelma.
      */
    public static void main(String[] args) {
        Kirja kirja1 = new Kirja("Jees mies", "hassunhauska", 1999);

        kirja1.setKirjailija("Jees mies 2");

        System.out.println(kirja1.toString());
    }
}
// file:///C:/Users/miskah/OneDrive%20-%20University%20of%20Eastern%20Finland/OHII/Kirja.html