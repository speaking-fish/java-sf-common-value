package com.speakingfish.common.value;


public class Mutables {

    public  static <T> Mutable<T> mutable() { return mutable(null); }

    public  static <T> Mutable<T> mutable(T value) { return new MutableImpl<T>(value); }

}
