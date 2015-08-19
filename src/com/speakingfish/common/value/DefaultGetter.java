package com.speakingfish.common.value;

import com.speakingfish.common.Compares;
import com.speakingfish.common.Equals;
import com.speakingfish.common.function.Getter;

import static com.speakingfish.common.Hashes.*;

abstract public class DefaultGetter<T> extends AbstractGetter<T> {

    @Override public int compareTo(Getter<T> o) {
        return Compares.compareObjects(get(), o.get());
    }

    @Override public String toString() {
        return String.valueOf(get());
    }

    @Override public boolean equals(Object o) {
        return true
            && (o != null)
            && (o.getClass() == getClass())
            && Equals.equals(this.get(), ((Getter<?>) o).get())
            ;
    }

    @Override public int hashCode() {
        return hash(get());
    }

}
