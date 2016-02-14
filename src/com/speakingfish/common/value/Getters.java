package com.speakingfish.common.value;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.speakingfish.common.function.Creator;
import com.speakingfish.common.function.Getter;
import com.speakingfish.common.function.Mapper;
import com.speakingfish.common.function.Invoker;

import static com.speakingfish.common.Invokers.*;

public class Getters {
    
    public static <T> Getter<T> getter(T value) { return new ImmutableGetter<T>(value); }

    public static <RESULT, PARAMS> Getter<RESULT> simpleFactory(final Creator<RESULT, PARAMS> creator, final PARAMS params) {
        return new Getter<RESULT>() {
            public RESULT get() {
                return creator.apply(params);
            }
        };
    }
    
    public static <T> Invoker<T> lazyInvoker(final Getter<? extends Invoker<T>> getter) {
        return new Invoker<T>() {
            public void invoke(T value) {
                getter.get().invoke(value);
            }};
    }
    
    public static <DEST, SRC> Invoker<SRC> lazyInvoker(final Mapper<DEST, SRC> mapper, final Getter<Invoker<DEST>> destInvoker) {
        return invoker(mapper, lazyInvoker(destInvoker));
        /*
        return new Invoker<SRC>() {
            @Override public void invoke(SRC value) {
                destInvoker.get().invoke(mapper.mapper(value));
            }};
        */
    }

    /*
    public static <DEST, SRC> Creator<Invoker<SRC>, Getter<Invoker<DEST>>> invokerCreator(final Mapper<DEST, SRC> mapper) {
        return new Creator<Invoker<SRC>, Getter<Invoker<DEST>>>() {
            @Override public Invoker<SRC> create(Getter<Invoker<DEST>> invokerParam) {
                return invoker(mapper, invokerParam);
            }
        };
    }
    */
    
    public static <DEST, SRC> Creator<Invoker<SRC>, Invoker<DEST>> invokerCreator(final Mapper<DEST, SRC> mapper) {
        return new Creator<Invoker<SRC>, Invoker<DEST>>() {
            public Invoker<SRC> apply(Invoker<DEST> invokerParam) {
                return invoker(mapper, invokerParam);
            }
        };
    }
    
    public static <RESULT, SRC> Mapper<RESULT, SRC> mapper(final Getter<? extends Mapper<RESULT, SRC>> getter) {
        return new Mapper<RESULT, SRC>() {
            public RESULT apply(SRC value) {
                return getter.get().apply(value);
            }};
    }
    
    public static <
        T_Collection__T_ELEMENT extends Collection<T_ELEMENT>,
        T_ELEMENT
    > T_Collection__T_ELEMENT lazyCollect(
        Getter<T_Collection__T_ELEMENT> destGetter,
        Iterator<T_ELEMENT> src
    ) {
        T_Collection__T_ELEMENT dest = null;
        while(src.hasNext()) {
            if(null == dest) {
                dest = destGetter.get();
            }
            dest.add(src.next());
        }
        return dest;
    }
    
    
    public static <
        T_Map__T_KEY_T_VALUE extends Map<T_KEY, T_VALUE>,
        T_KEY                                           ,
        T_VALUE
    > T_Map__T_KEY_T_VALUE lazyCollectMap(
        Getter<T_Map__T_KEY_T_VALUE> destGetter,
        Iterator<Entry<T_KEY, T_VALUE>> src
    ) {
        T_Map__T_KEY_T_VALUE dest = null;
        while(src.hasNext()) {
            if(null == dest) {
                dest = destGetter.get();
            }
            final Entry<T_KEY, T_VALUE> entry = src.next(); 
            dest.put(entry.getKey(), entry.getValue());
        }
        return dest;
    }
    
    public static final Mapper<Object, Getter<Object>> MAPPER_GETTER = new Mapper<Object, Getter<Object>>() {
        public Object apply(Getter<Object> value) {
            return value.get();
        }
    };
    
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static <T> Mapper<T, Getter<T>> mapperGetter() {
        return (Mapper<T, Getter<T>>) (Mapper) MAPPER_GETTER;
    }
    
}
