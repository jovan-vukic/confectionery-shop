package com.example.pki_mobile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class User {
    private static int staticID = 1;
    public static List<User> users = new ArrayList<>();

    private int id = staticID++;
    private String name;
    private String lastName;
    private String phone;
    private String address;
    private String type; // Allowed values: 'kupac', 'zaposleni'
    private String username;
    private String password;
    private List<CartItem> cart = new ArrayList<>();
    private List<List<CartItem>> orders = new ArrayList<>();
    private List<Notification> notifications = new ArrayList<>();

    static {
        users.add(new User(
                "Ana",
                "Anić",
                "0641122334",
                "Kralja Petra 25",
                "kupac",
                "kupac",
                "pass"
        ));

        users.add(new User(
                "Marko",
                "Marković",
                "0653344556",
                "Nikole Tesle 18",
                "zaposleni",
                "zaposleni",
                "pass"
        ));
    }

    public static class CartItem {
        private Product product;
        private int quantity;
        private int price;

        public CartItem(Product product, int quantity) {
            this.product = product;
            this.quantity = quantity;
            this.price = product.getPrice() * quantity;
        }

        public Product getProduct() {
            return product;
        }

        public void setProduct(Product product) {
            this.product = product;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }
    }

    public static class Notification {
        private String comment;
        private LocalDateTime date;
        private boolean read = false;

        public Notification(String comment, LocalDateTime date) {
            this.comment = comment;
            this.date = date;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public LocalDateTime getDate() {
            return date;
        }

        public void setDate(LocalDateTime date) {
            this.date = date;
        }

        public boolean isRead() {
            return read;
        }

        public void setRead(boolean read) {
            this.read = read;
        }
    }

    public User(String name, String lastName, String phone, String address, String type, String username, String password) {
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
        this.type = type;
        this.username = username;
        this.password = password;

        if (this.type.equals("zaposleni")) {
            this.cart = null;
            this.orders = null;
            this.notifications = null;
        }
    }

    // Methods
    public void addCartItem(Product product, int quantity) {
        cart.add(new CartItem(product, quantity));
    }

    public void addOrder(List<CartItem> order) {
        orders.add(order);
    }

    public void addNotification(String comment, LocalDateTime date) {
        notifications.add(new Notification(comment, date));
    }

    // Getters and setters
    public static int getStaticID() {
        return staticID;
    }

    public static void setStaticID(int staticID) {
        User.staticID = staticID;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<CartItem> getCart() {
        return cart;
    }

    public void setCart(List<CartItem> cart) {
        this.cart = cart;
    }

    public List<List<CartItem>> getOrders() {
        return orders;
    }

    public void setOrders(List<List<CartItem>> orders) {
        this.orders = orders;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }
}
