package webapp.SA2_final.Repository;

import org.springframework.data.repository.CrudRepository;

import webapp.SA2_final.Model.Administrador;

import java.util.List;


public interface AdministradorRepository extends CrudRepository<Administrador, String>{
    Administrador findByCpf(String cpf);
    Administrador findBySenha(String senha);
}
