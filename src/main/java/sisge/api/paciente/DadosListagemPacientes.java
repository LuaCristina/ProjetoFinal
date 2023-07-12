package sisge.api.paciente;

import sisge.api.endereco.Endereco;

public record DadosListagemPacientes(String nome, String telefone, Endereco endereco) {

    public DadosListagemPacientes(Paciente paciente){
        this(paciente.getNome(), paciente.getTelefone(), paciente.getEndereco());
    }
}
