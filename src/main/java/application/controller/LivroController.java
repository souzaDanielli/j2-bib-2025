package application.controller;

import java.util.HashSet;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import application.model.Autor;
import application.model.Genero;
import application.model.Livro;
import application.repository.AutorRepository;
import application.repository.GeneroRepository;
import application.repository.LivroRepository;

@Controller
@RequestMapping("/livros")
public class LivroController {
    @Autowired
    private LivroRepository livroRepo;
    @Autowired
    private GeneroRepository generoRepo;
    @Autowired
    private AutorRepository autorRepo;

    @RequestMapping("/insert")
    public String insert(Model ui) {
        ui.addAttribute("generos", generoRepo.findAll());
        ui.addAttribute("autores", autorRepo.findAll());
        return "/livros/insert";
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insert(
        @RequestParam("titulo") String titulo,
        @RequestParam("id_genero") long id_genero,
        @RequestParam("autores") long[] ids_autores) {

        Optional<Genero> genero = generoRepo.findById(id_genero);

        if(genero.isPresent()) {
            Livro livro = new Livro();
            livro.setTitulo(titulo);
            livro.setGenero(genero.get());

            for (long a_id : ids_autores) {
                Optional<Autor> result_a = autorRepo.findById(a_id);
                if(result_a.isPresent()) {
                    livro.getAutores().add(result_a.get());
                }
            }

            livroRepo.save(livro);
        }

        return "redirect:/livros/list";
    }

    @RequestMapping("/list")
    public String list(Model ui) {
        ui.addAttribute("livros", livroRepo.findAll());

        return "/livros/list";
    }

    @RequestMapping("/update/{id}")
    public String update(@PathVariable long id, Model ui) {
        ui.addAttribute("generos", generoRepo.findAll());
        ui.addAttribute("autores", autorRepo.findAll());
        ui.addAttribute("livro", livroRepo.findById(id).get());
        return "/livros/update";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(
        @RequestParam("id") long id,
        @RequestParam("titulo") String titulo,
        @RequestParam("id_genero") long id_genero,
        @RequestParam("autores") long[] ids_autores){
        
        Optional<Livro> result_l = livroRepo.findById(id);
        if(result_l.isPresent()) {
            Livro livro = result_l.get();

            Optional<Genero> genero = generoRepo.findById(id_genero);

            if(genero.isPresent()) {
                livro.setTitulo(titulo);
                livro.setGenero(genero.get());
                livro.setAutores(new HashSet<Autor>());

                for (long a_id : ids_autores) {
                    Optional<Autor> result_a = autorRepo.findById(a_id);
                    if(result_a.isPresent()) {
                        livro.getAutores().add(result_a.get());
                    }
                }

                livroRepo.save(livro);
            }
        }

        return "redirect:/livros/list";
    }
}
