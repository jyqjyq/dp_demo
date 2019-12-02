package com.dapeng.demo.util.response;

import java.util.function.BiConsumer;

@FunctionalInterface
public interface DTOConvert<T, U> extends BiConsumer<T, U>{

}
