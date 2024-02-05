package com.example.pki_mobile.utility;

import com.example.pki_mobile.R;

import java.util.ArrayList;
import java.util.List;

public class Promotion {
    private static int staticID = 1;
    public static final List<Promotion> promotions = new ArrayList<>();

    private int id = staticID++;
    private String name;
    private String description;
    private int image;

    static {
        promotions.add(new Promotion(
                "Popust 30% na sve kolače. Samo dok traju praznici",
                "Ove novogodišnje sezone, doživite ukus praznične radosti uz naše neodoljive kolače!\n" +
                        "Uživajte u čarima slatkog ukusa sa popustom od 30% na sve naše ukusne kolače tokom celog prazničnog perioda.\n" +
                        "Prepustite se slatkom uživanju!",
                R.drawable.promotion1));
        promotions.add(new Promotion(
                "Jagodinka: 2 + 1",
                "Zaronite u svet sočnih jagoda sa našom neodoljivom promocijom 'Jagodinka: 2 + 1'!\n" +
                        "Kupite dve ukusne torte od jagode iz našeg vrhunskog asortimana 'Jagodinka' i obradujte svoje nepce trećom tortom koja vam stiže POTPUNO BESPLATNO!",
                R.drawable.promotion2));
        promotions.add(new Promotion(
                "Za vernost: 30%",
                "Dragi naši slatki kupci, želimo vas nagraditi za vašu vernost\n" +
                        "Kako biste osvojili fantastičan popust od 30%, dovoljno je da nam pokažete tri prethodna fiskalna računa uz sledeću kupovinu!" +
                        "Popust se odnosi na sve iz našeg asortimana, osim već sniženih proizvoda :)",
                R.drawable.promotion3));
    }

    public Promotion(String name, String description, int image) {
        this.name = name;
        this.description = description;
        this.image = image;
    }

    public static int getStaticID() {
        return staticID;
    }

    public static void setStaticID(int staticID) {
        Promotion.staticID = staticID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
