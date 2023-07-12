package sisge.api.paciente;

import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class PacienteModelAssembler  extends RepresentationModelAssemblerSupport<Paciente, Paciente> {
    public PacienteModelAssembler() {
        super(Paciente.class, Paciente.class);
    }

    @Override
    public Paciente toModel(Paciente entity) {
        Paciente model = new Paciente();
        // Both CustomerModel and Customer have the same property names. So copy the values from the Entity to the Model
        BeanUtils.copyProperties(entity, model);
        return model;
    }
}