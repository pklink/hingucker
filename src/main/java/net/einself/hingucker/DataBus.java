package net.einself.hingucker;

import net.einself.hingucker.core.data.Data;
import net.einself.hingucker.member.Member;

import java.util.ArrayList;
import java.util.List;

public class DataBus {

    public static final DataBus INSTANCE = new DataBus();

    private final List<Member> subscribers = new ArrayList<>();

    private DataBus() { }

    public void publish(Data data) {
        subscribers.forEach(member -> member.accept(data));
    }

    public void subscribe(Member member) {
        subscribers.add(member);
    }


}
