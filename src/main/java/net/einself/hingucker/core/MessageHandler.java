package net.einself.hingucker.core;

public interface MessageHandler<T> {

    void accept(T message);

}
