package com.quarkus.bootcamp.nttdata.infraestructure.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import java.util.List;

public interface IRepository<T> extends PanacheRepository<T> {
  /**
   * Se encarga de devolver todos los elementos no eliminados (softdelete)
   * de la BD.
   *
   * @return Lista de elementos no eliminados.
   */
  public List<T> getAll();

  /**
   * Se encarga de devolver el elemento solicitado por el identificador
   * siempre y cuando no este eliminado (softDelete).
   *
   * @param id Identificador del elemento a devolver.
   * @return Elemento encontrado.
   */
  public T getById(Long id);

  /**
   * Guarda el elemento en la base de datos (creacion y actualización).
   *
   * @param t Elemento a guardar.
   * @return Retorna el elemento guardado.
   */
  public T save(T t);

  /**
   * Realiza la eliminación logica del elemento
   *
   * @param t Elemento a borrar logicamente.
   * @return Elemento borrado logicamente.
   */
  public T softDelete(T t);
}
