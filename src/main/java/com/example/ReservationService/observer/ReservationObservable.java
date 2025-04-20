package com.example.ReservationService.observer;

public interface ReservationObservable {
    void addObserver(ReservationObserver observer);
    void removeObserver(ReservationObserver observer);
    void notifyObservers();
}