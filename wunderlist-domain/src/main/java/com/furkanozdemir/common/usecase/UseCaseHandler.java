package com.furkanozdemir.common.usecase;

public interface UseCaseHandler<E, T> {

    E handle(T useCase);

}
