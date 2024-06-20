package webapp.SA2_final.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import webapp.SA2_final.Model.Patrimonio;

public interface PatrimonioRepository extends CrudRepository<Patrimonio, Long> {
    Patrimonio findByCodigo(String codigo);
    List<Patrimonio> findByBloco(String bloco);
    // Adicione outros métodos de consulta se necessário
}

