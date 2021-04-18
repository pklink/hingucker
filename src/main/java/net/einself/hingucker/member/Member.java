package net.einself.hingucker.member;

import net.einself.hingucker.core.data.Data;

@FunctionalInterface
public interface Member {

    void accept(Data data);

}
