package net.einself.hingucker.core;

import net.einself.hingucker.core.data.Data;

@FunctionalInterface
public interface Member {

    void accept(Data data);

}
