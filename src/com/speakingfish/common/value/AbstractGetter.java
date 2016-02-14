package com.speakingfish.common.value;

import com.speakingfish.common.function.Getter;

abstract public class AbstractGetter<T> implements Getter<T>, Comparable<Getter<T>> {

              abstract public T       get      ();
    @Override abstract public String  toString ();
    @Override abstract public int     hashCode ();
    @Override abstract public boolean equals   (Object    o);
              abstract public int     compareTo(Getter<T> o);

}
