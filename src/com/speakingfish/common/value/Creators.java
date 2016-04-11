package com.speakingfish.common.value;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.TreeMap;

import com.speakingfish.common.function.Creator;
import com.speakingfish.common.function.Getter;
import com.speakingfish.common.function.Mapper;

public class Creators {
    
    public static final Creator<HashMap<Object, Object>, Void> CREATOR_defaultHashMap = new Creator<HashMap<Object, Object>, Void>() {
        public HashMap<Object, Object> apply(Void params) { return new HashMap<Object, Object>(); }
    };
    
    public static final Creator<TreeMap<Object, Object>, Void> CREATOR_defaultTreeMap = new Creator<TreeMap<Object, Object>, Void>() {
        public TreeMap<Object, Object> apply(Void params) { return new TreeMap<Object, Object>(); }
    };
    
    public static final Creator<LinkedList<Object>, Void> CREATOR_defaultLinkedList = new Creator<LinkedList<Object>, Void>() {
        public LinkedList<Object> apply(Void params) { return new LinkedList<Object>(); }
    };
    
    public static final Creator<ArrayList<Object>, Void> CREATOR_defaultArrayList = new Creator<ArrayList<Object>, Void>() {
        public ArrayList<Object> apply(Void params) { return new ArrayList<Object>(); }
    };
    
    @SuppressWarnings("unchecked")
    public static <K, V> Creator<HashMap<K, V>, Void> defaultHashMap() {
        return (Creator<HashMap<K, V>, Void>) (Object) CREATOR_defaultHashMap;
    }

    @SuppressWarnings("unchecked")
    public static <K, V> Creator<TreeMap<K, V>, Void> defaultTreeMap() {
        return (Creator<TreeMap<K, V>, Void>) (Object) CREATOR_defaultTreeMap;
    }

    @SuppressWarnings("unchecked")
    public static <V> Creator<LinkedList<V>, Void> defaultLinkedList() {
        return (Creator<LinkedList<V>, Void>) (Object) CREATOR_defaultLinkedList;
    }
    
    @SuppressWarnings("unchecked")
    public static <V> Creator<ArrayList<V>, Void> defaultArrayList() {
        return (Creator<ArrayList<V>, Void>) (Object) CREATOR_defaultArrayList;
    }

    public static final <RESULT, PARAMS> Creator<RESULT, PARAMS> creator(final Mapper<RESULT, PARAMS> mapper) {
        return new Creator<RESULT, PARAMS>() {
            public RESULT apply(PARAMS params) {
                return mapper.apply(params);
            }
        };
    }

    public static final <RESULT, PARAMS> Getter<RESULT> getter(final PARAMS params, final Mapper<? extends RESULT, PARAMS> mapper) {
        return new Getter<RESULT>() {
            public RESULT get() {
                return mapper.apply(params);
            }
        };
    }
    
//    public static final <RESULT, SRC> Mapper<RESULT, SRC> mapper(final Creator<RESULT, SRC> creator) {
////        return new Mapper<RESULT, SRC>() {
////            public RESULT apply(SRC src) {
////                return creator.apply(src);
////            }
////        };
//        return creator;
//    }
    
    
}
