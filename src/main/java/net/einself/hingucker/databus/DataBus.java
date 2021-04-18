package net.einself.hingucker.databus;

import net.einself.hingucker.core.data.Data;
import net.einself.hingucker.core.Member;

import java.util.ArrayList;
import java.util.List;

public class DataBus {

    private final List<Member> subscribers = new ArrayList<>();

    public void publish(Data data) {
        subscribers.forEach(member -> member.accept(data));
    }

    public void subscribe(Member member) {
        subscribers.add(member);
    }

}
