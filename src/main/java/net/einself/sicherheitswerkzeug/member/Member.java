package net.einself.sicherheitswerkzeug.member;

import net.einself.sicherheitswerkzeug.core.data.Data;

@FunctionalInterface
public interface Member {

    void accept(Data data);

}
