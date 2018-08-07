package br.com.guimaraes.curso.dao;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import br.com.guimaraes.curso.domain.TipoSexo;
import br.com.guimaraes.curso.domain.Usuario;

@Repository
public class UsuarioDaoImpl implements UsuarioDao {

	private static List<Usuario> us;
	
	public UsuarioDaoImpl() {
		createUserList();
	}
	
    private List<Usuario> createUserList() {
        if (us == null) {
            us = new LinkedList<>();
            us.add(new Usuario(System.currentTimeMillis()+1L, "Ana", "Paula de Sousa", LocalDate.of(1992, 5, 10), TipoSexo.FEMININO));
            us.add(new Usuario(System.currentTimeMillis()+2L, "Guimarães", "Neto", LocalDate.of(1979, 9, 7), TipoSexo.MASCULINO));
            us.add(new Usuario(System.currentTimeMillis()+3L, "Ligia", "de Sousa", LocalDate.of(1998, 9, 17), TipoSexo.FEMININO));
            us.add(new Usuario(System.currentTimeMillis()+4L, "Leticia", "de Sousa", LocalDate.of(1998, 9, 17), TipoSexo.FEMININO));
            us.add(new Usuario(System.currentTimeMillis()+5L, "Hellen", "de Sousa", LocalDate.of(2000, 10, 17), TipoSexo.FEMININO));
            us.add(new Usuario(System.currentTimeMillis()+6L, "Cicero", "José Neto"));
            us.add(new Usuario(System.currentTimeMillis()+7L, "Alasca", "Guimarães"));
            us.add(new Usuario(System.currentTimeMillis()+8L, "Fred", "Guimarães"));
            us.add(new Usuario(System.currentTimeMillis()+9L, "Beijamin", "Guimarães"));
            us.add(new Usuario(System.currentTimeMillis()+10L, "Coragem", "Guimarães"));
            us.add(new Usuario(System.currentTimeMillis()+11L, "Ralph", "Guimarães"));
            us.add(new Usuario(System.currentTimeMillis()+12L, "Magrelo", "Guimarães"));
            us.add(new Usuario(System.currentTimeMillis()+13L, "Sasha", "Guimarães"));
            us.add(new Usuario(System.currentTimeMillis()+14L, "Zell", "Guimarães"));
            us.add(new Usuario(System.currentTimeMillis()+15L, "Candy", "Guimarães"));
            us.add(new Usuario(System.currentTimeMillis()+16L, "Flor", "Guimarães"));
        }
        return us;
    }

	@Override
	public void salvar(Usuario usuario) {
		usuario.setId(System.currentTimeMillis());
		us.add(usuario);
	}

	@Override
	public void editar(Usuario usuario) {
		us.stream()
			.filter((u) -> u.getId().equals(usuario.getId()))
			.forEach((u) -> {
				u.setNome(usuario.getNome());
				u.setSobrenome(usuario.getSobrenome());
				u.setDtNascimento(usuario.getDtNascimento());
				u.setSexo(usuario.getSexo());
			});		
	}

	@Override
	public void excluir(Long id) {
		us.removeIf((u) -> u.getId().equals(id));		
	}

	@Override
	public Usuario getId(Long id) {
		return us.stream()
				.filter((u) -> u.getId().equals(id))
				.collect(Collectors.toList()).get(0);
	}

	@Override
	public List<Usuario> getTodos() {
		return us;
	}	
}
