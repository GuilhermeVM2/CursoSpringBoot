package webapp.SA2_final.Repository;

import org.springframework.data.repository.CrudRepository;

import webapp.SA2_final.Model.VerificaCadastroAdm;



public interface VerificaCadastroAdmRepository extends CrudRepository<VerificaCadastroAdm, String>{
    VerificaCadastroAdm findByCpf(String cpf);
}
