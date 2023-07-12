package sisge.api.Controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import sisge.api.medico.DadosAtualizacaoMedicos;
import sisge.api.medico.DadosCadastroMedico;
import sisge.api.paciente.*;

import java.util.List;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @Autowired
    private PacienteModelAssembler pacienteModelAssembler;

    @Autowired
    private PagedResourcesAssembler<Paciente> pagedResourcesAssembler;

    @PostMapping
    @Transactional
    public void cadastrarPaciente(@RequestBody @Valid DadosCadastroPaciente dados){
        repository.save(new Paciente(dados));
    }

    @GetMapping
    public Page<DadosListagemPacientes> listar(Pageable paginacao){
        return repository.findAll(paginacao).map(DadosListagemPacientes::new);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoPacientes dados) {
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id) {
        repository.deleteById(id);
    }


    @GetMapping("/buscar")
    public  Page<List<Paciente>>  buscar(@RequestParam String nome, Pageable paginacao) {
        return repository.buscarPorNome(nome, paginacao);
    }



}
