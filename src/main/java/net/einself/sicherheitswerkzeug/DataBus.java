package net.einself.sicherheitswerkzeug;

import net.einself.sicherheitswerkzeug.core.data.Data;
import net.einself.sicherheitswerkzeug.member.Member;

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
