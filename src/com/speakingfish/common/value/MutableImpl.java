package com.speakingfish.common.value;

public class MutableImpl<T> extends DefaultGetter<T> implements Mutable<T> {

    protected T _value;

    public MutableImpl(T value) {
        super();
        _value = value;
    }

    @Override public T get() { return _value; }
    
    public void set(T value) { _value = value; }

}
