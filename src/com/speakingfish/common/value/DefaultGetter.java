package com.speakingfish.common.value;

import com.speakingfish.common.function.Getter;

import static com.speakingfish.common.Hashes.*;
import static com.speakingfish.common.Equals.*;
import static com.speakingfish.common.Compares.*;

abstract public class DefaultGetter<T> extends AbstractGetter<T> {

    @Override public int compareTo(Getter<T> o) {
        return compareObjects(get(), o.get());
    }

    @Override public String toString() {
        return String.valueOf(get());
    }

    @SuppressWarnings("unchecked")
    @Override public boolean equals(Object o) {
        return true
            && (o != null)
            && (o.getClass() == getClass())
            && equalsOf(this.get(), ((Getter<T>) o).get())
            ;
    }

    @Override public int hashCode() {
        return hash(get());
    }

}
