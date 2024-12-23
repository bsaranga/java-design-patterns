package designpatterns;

// Every object has state and behavior
// State = Data
// Behavior = Methods

// Examples of the Observer Pattern key players:

// Subject = Pizzeria
// Observer = Customer

// Subject = Weather Station
// Observer = Weather App

// Subject = Stock Market
// Observer = Stock Broker

// Subject = Social Media User
// Observer = Follower

// Subject = News Agency
// Observer = News Reader

// Subject = Online Store
// Observer = Shopper

// Subject = Chat Room
// Observer = Chat User

// Subject = Auction House
// Observer = Bidder

// Subject = Game Server
// Observer = Player

import java.util.ArrayList;
import java.util.List;

// Subjects notify observers
interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}

// Observers subscribe subjects
interface Observer {
    void notifyFollower();
}

class User implements Subject {
    private String name;
    private String latestPost;
    private List<Observer> followers;
    
    public User(String name) {
        this.name = name;
        this.followers = new ArrayList<Observer>();
    }

    @Override
    public void registerObserver(Observer observer) {
        followers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        followers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer follower : followers) {
            follower.notifyFollower();
        }
    }

    public void postUpdate(String post) {
        this.latestPost = post;
        notifyObservers();
    }

    public String getName() {
        return name;
    }

    public String getLatestPost() {
        return latestPost;
    }
}

class Follower implements Observer {
    private User user;
    private String followerName;

    public Follower(User user, String followerName) {
        this.user = user;
        this.followerName = followerName;
        user.registerObserver(this);
    }

    @Override
    public void notifyFollower() {
        System.out.println(this + " received a new update from " + user.getName() + ": " + user.getLatestPost());
    }

    @Override
    public String toString() {
        return this.followerName;
    }
}

public class ObserverPattern {
    public static void main(String[] args) {
        User bob = new User("Bob");
        Follower alice = new Follower(bob, "Alice");
        Follower charlie = new Follower(bob, "Charlie");
        Follower david = new Follower(bob, "David");

        bob.removeObserver(charlie);
        bob.postUpdate("Hello, world!");
    }
}