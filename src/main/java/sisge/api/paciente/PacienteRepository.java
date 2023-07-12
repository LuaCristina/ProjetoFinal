package sisge.api.paciente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    @Transactional
    @Query(
        value = "SELECT p.* FROM pacientes p WHERE LOWER(p.nome) LIKE LOWER(CONCAT('%', ?1,'%'))",
        nativeQuery = true
    )
    Page<List<Paciente>> buscarPorNome(String nome, Pageable pageable);

}
