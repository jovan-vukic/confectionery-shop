package com.example.pki_mobile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Product {
    private static int staticID = 1;
    public static final List<Product> products = new ArrayList<>();

    private int id = staticID++;
    private String name;
    private String image;
    private String description;
    private int price;
    private String type; // Allowed values: 'torte', 'kolaci'
    private final List<String> ingredients = new ArrayList<>();
    private final List<Comment> comments = new ArrayList<>();

    static {
        // Add cakes
        products.add(new Product("Jagodinka", "cakes/img3.jpg", "Torta od jagode je ukusna poslastica napravljena od sočnih jagoda, kremastog fila i mekanog biskvita.", 1500, "torte"));
        products.add(new Product("Karamelno blaženstvo", "cakes/img5.jpg", "Torta od karamele je neodoljiva poslastica koja kombinuje bogat ukus karamelizovanog šećera sa nežnim slojevima sočnog biskvita, prelivena kremastim karamelnim filom.", 2500, "torte"));
        products.add(new Product("Čoko simfonija", "cakes/img6.jpg", "Torte od čokolade posute voćem su ukusni kolači koji kombinuju bogat ukus čokolade sa svežim voćem, pružajući savršenu harmoniju slatkoće i osveženja.", 2800, "torte"));
        products.add(new Product("Citrusni prasak", "cakes/img7.jpg", "Torte od limuna su osvežavajuće slatkiši sa sočnim slojevima kiselkastog limunovog fila, obično ukrašene šlagom ili koricama limuna.", 1900, "torte"));
        products.add(new Product("Lešnje", "cakes/img8.jpg", "Torte od lešnika su poslastice od kremastog fila od lešnika, slojeva mekanog biskvita i obično su dekorisane mlečnom čokoladom ili sitno seckanim lešnicima.", 3100, "torte"));

        products.get(0).addIngredient("jagode");
        products.get(0).addIngredient("šećer");
        products.get(0).addIngredient("brašno");
        products.get(0).addIngredient("jaja");
        products.get(0).addIngredient("mleko");

        products.get(1).addIngredient("šećer");
        products.get(1).addIngredient("puter");
        products.get(1).addIngredient("brašno");
        products.get(1).addIngredient("jaja");
        products.get(1).addIngredient("mleko");

        products.get(2).addIngredient("čokolada");
        products.get(2).addIngredient("brašno");
        products.get(2).addIngredient("šećer");
        products.get(2).addIngredient("jaja");
        products.get(2).addIngredient("puter");

        products.get(3).addIngredient("brašno");
        products.get(3).addIngredient("šećer");
        products.get(3).addIngredient("jaja");
        products.get(3).addIngredient("limunov sok");
        products.get(3).addIngredient("limunova kora");

        products.get(4).addIngredient("lešnici");
        products.get(4).addIngredient("jaja");
        products.get(4).addIngredient("šećer");
        products.get(4).addIngredient("brašno");
        products.get(4).addIngredient("puter");

        // Add cookies
        products.add(new Product("Brownie", "cookies/img10.jpg", "Brownie je bogati i čokoladni kolač, često sa gustim i sočnim srednjim delom, koji se često služi posut prah šećerom.", 700, "kolaci"));
        products.add(new Product("Vanilice", "cookies/img11.jpg", "Kolač vanilice je ukusan kolač sa mekanim testom obogaćenim aromom vanile.", 600, "kolaci"));
        products.add(new Product("Čupavci", "cookies/img12.jpg", "Čupavci su tradicionalni slatkiši sastavljeni od mekane baze od čokolade ili kakaa, obložene kokosovim brašnom i često natopljene ukusnim sirupom.", 750, "kolaci"));
        products.add(new Product("Medenjaci", "cookies/img13.jpg", "Kolači od meda i začina, često oblikovani u različite oblike i ukrašeni glazurom.", 500, "kolaci"));
        products.add(new Product("Orahnjača", "cookies/img14.jpeg", "Orahnjača je tradicionalni slatki kolač s punjenjem od mlevenih oraha, uvijen u testo.", 900, "kolaci"));

        products.get(5).addIngredient("brašno");
        products.get(5).addIngredient("šećer");
        products.get(5).addIngredient("puter");
        products.get(5).addIngredient("jaja");
        products.get(5).addIngredient("čokolada");

        products.get(6).addIngredient("brašno");
        products.get(6).addIngredient("šećer");
        products.get(6).addIngredient("puter");
        products.get(6).addIngredient("jaja");
        products.get(6).addIngredient("ekstrakt vanile");

        products.get(7).addIngredient("čokolada");
        products.get(7).addIngredient("šećer");
        products.get(7).addIngredient("jaja");
        products.get(7).addIngredient("brašno");
        products.get(7).addIngredient("kokosovo brašno");

        products.get(8).addIngredient("brašno");
        products.get(8).addIngredient("smeđi šećer");
        products.get(8).addIngredient("med");
        products.get(8).addIngredient("puter");
        products.get(8).addIngredient("jaja");

        products.get(9).addIngredient("brašno");
        products.get(9).addIngredient("mleko");
        products.get(9).addIngredient("orasi");
        products.get(9).addIngredient("šećer");
        products.get(9).addIngredient("kvasac");

        // Add comments to all products
        for (Product product : products) {
            product.addComment(-1, "Izuzetno!");
            product.addComment(-1, "Preporuka. Veoma kvalitetno napravljeno. Sjajna saradnja!");
            product.addComment(-1, "Zadovoljna.");
            product.addComment(-1, "Vrhunski kolač.");
            product.addComment(-1, "Nije isporučeno na vreme.");
            product.addComment(-1, "Odlično...");
        }
    }

    public static class Comment {
        private final int userId;
        private final String comment;
        private final LocalDateTime date;

        public Comment(int userId, String comment, LocalDateTime date) {
            this.userId = userId;
            this.comment = comment;
            this.date = date;
        }

        public int getUserId() {
            return userId;
        }

        public String getComment() {
            return comment;
        }

        public LocalDateTime getDate() {
            return date;
        }
    }

    public Product(String name, String image, String description, int price, String type) {
        this.name = name;
        this.image = image;
        this.description = description;
        this.price = price;
        this.type = type;
    }

    // Methods
    public void addComment(int userId, String comment) {
        comments.add(new Comment(userId, comment, LocalDateTime.now()));
    }

    public void addIngredient(String ingredient) {
        ingredients.add(ingredient);
    }

    // Getters and setters
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public List<Comment> getComments() {
        return comments;
    }
}
