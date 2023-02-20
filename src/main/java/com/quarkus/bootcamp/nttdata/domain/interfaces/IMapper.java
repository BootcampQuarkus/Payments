package com.quarkus.bootcamp.nttdata.domain.interfaces;

public interface IMapper<T, U> {
  T toDto(U u);

  U toEntity(T t);
}
