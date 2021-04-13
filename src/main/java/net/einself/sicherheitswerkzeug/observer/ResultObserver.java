package net.einself.sicherheitswerkzeug.observer;

@FunctionalInterface
public interface ResultObserver {

    void update(Result result);

}
