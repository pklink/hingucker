package net.einself.hingucker.core;

import net.einself.hingucker.core.message.Message;

@FunctionalInterface
public interface Member {

    void accept(Message message);

}
