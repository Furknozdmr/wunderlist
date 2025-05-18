package com.furkanozdemir.common.usecase;


import com.furkanozdemir.common.model.UseCase;

public interface VoidUseCaseHandler<T> {

    void handle(T useCase);

}
