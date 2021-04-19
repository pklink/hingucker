package net.einself.hingucker.core.messagebus;

import net.einself.hingucker.core.message.Message;
import net.einself.hingucker.core.Member;

import java.util.ArrayList;
import java.util.List;

public class MessageBus {

    private final List<Member> subscribers = new ArrayList<>();

    public void publish(Message message) {
        subscribers.forEach(member -> member.accept(message));
    }

    public void subscribe(Member member) {
        subscribers.add(member);
    }

}
