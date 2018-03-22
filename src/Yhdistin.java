
import java.util.ArrayList;
import java.util.Collections;
import kuva.Fotari;
import kuva.Kuva;

public class Yhdistin {

    private String tapa;
    ArrayList<Kuva> sijainti;
    Kuva uusi;

    public Yhdistin(String tapa) {
        this.tapa = tapa;
        this.sijainti = new ArrayList<Kuva>();

    }

    public ArrayList<Kuva> lataaKuvat(ArrayList<String> lista) {
        for (String string : lista) {
            sijainti.add(Fotari.lataa(string));

        }
        return sijainti;
    }

    public Kuva yhdistaKuvat(ArrayList<Kuva> kuva) {
        int leveys = 0;
        int korkeus = 0;
        
        if (tapa.equals("vaalein")) {
            korkeus = kuva.get(0).getKorkeus();
            leveys = kuva.get(0).getLeveys();
            this.uusi = new Kuva(leveys, korkeus);
            int kertaa = 0;
            for (Kuva kuva1 : kuva) {
                int x = 0;

                while (x < leveys) {
                    int y = 0;
                    while (y < korkeus) {
                        int punainen = kuva1.punainen(x, y);
                        int vihrea = kuva1.vihrea(x, y);
                        int sininen = kuva1.sininen(x, y);

                        this.uusi.asetaVari(x, y, Math.max(punainen, this.uusi.punainen(x, y)), Math.max(vihrea, this.uusi.vihrea(x, y)), Math.max(sininen, this.uusi.sininen(x, y)));
                        y++;

                    }

                    x++;

                }
                kertaa++;

            }

        }

        if (tapa.equals("tummin")) {

            korkeus = kuva.get(0).getKorkeus();
            leveys = kuva.get(0).getLeveys();
            this.uusi = new Kuva(leveys, korkeus);
            int x1 = 0;
            while (x1 < leveys) {
                int y1 = 0;
                while (y1 < korkeus) {

                    this.uusi.asetaVari(x1, y1, 255, 255, 255);
                    y1++;

                }

                x1++;

            }

            for (Kuva kuva1 : kuva) {
                int x = 0;

                while (x < leveys) {
                    int y = 0;
                    while (y < korkeus) {
                        int punainen = kuva1.punainen(x, y);
                        int vihrea = kuva1.vihrea(x, y);
                        int sininen = kuva1.sininen(x, y);

                        this.uusi.asetaVari(x, y, Math.min(punainen, this.uusi.punainen(x, y)), Math.min(vihrea, this.uusi.vihrea(x, y)), Math.min(sininen, this.uusi.sininen(x, y)));
                        y++;

                    }

                    x++;

                }
                

            }

        }
        
        
        
        if (tapa.equals("mediaani")) {
            korkeus = kuva.get(0).getKorkeus();
            leveys = kuva.get(0).getLeveys();
            this.uusi = new Kuva(leveys, korkeus);
            int kertaa = 1;
            int punainen = 0;
            int sininen = 0;
            int vihrea = 0;
            
            int x = 0;

            while (x < leveys) {
                int y = 0;
                while (y < korkeus) {
                    ArrayList <Integer> punainenA = new ArrayList <>();
                    ArrayList <Integer> sininenA  = new ArrayList <>();
                    ArrayList <Integer> vihreaA = new ArrayList <>();
                    
                    for (Kuva kuva1 : kuva) {
                        punainenA.add(kuva1.punainen(x, y));
                        sininenA.add(kuva1.sininen(x, y));
                        vihreaA.add(kuva1.vihrea(x, y));
                        
                    }
                    Collections.sort(punainenA);
                    Collections.sort(sininenA);
                    Collections.sort(vihreaA);
                    
                    if(punainenA.size() % 2 == 0){
                        punainen = (punainenA.get(punainenA.size()/2) + punainenA.get(punainenA.size()/2 -1))/2;
                    } else {
                        punainen = punainenA.get((punainenA.size()-1)/2);
                    }
                    if(sininenA.size() % 2 == 0){
                        sininen = (sininenA.get(sininenA.size()/2) + sininenA.get(sininenA.size()/2 -1))/2;
                    } else {
                        sininen = sininenA.get((sininenA.size()-1)/2);
                    }
                    if(vihreaA.size() % 2 == 0){
                        vihrea = (vihreaA.get(vihreaA.size()/2) + vihreaA.get(vihreaA.size()/2 -1))/2;
                    } else {
                        vihrea = vihreaA.get((vihreaA.size()-1)/2);
                    }

                    this.uusi.asetaVari(x, y, punainen, vihrea, sininen);
                    y++;

                }

                x++;

            }
            kertaa++;

            

        }
        
        
        return uusi;
    }

}
