package com.speakingfish.common.value;

import com.speakingfish.common.function.Getter;


public class ImmutableGetter<T> extends DefaultGetter<T> implements Getter<T> {

    protected final T _value;
    
    public ImmutableGetter(T value) {
        super();
        this._value = value;
    }

    @Override public T get() { return _value; }

}
