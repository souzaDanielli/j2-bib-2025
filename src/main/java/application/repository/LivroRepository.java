package application.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import application.model.Livro;

public interface LivroRepository extends CrudRepository<Livro, Long> {
    public List<Livro> findByTitulo(String titulo);
}
