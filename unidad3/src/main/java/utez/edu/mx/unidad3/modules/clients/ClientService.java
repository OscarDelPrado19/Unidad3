package utez.edu.mx.unidad3.modules.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utez.edu.mx.unidad3.utils.APIResponse;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Transactional(readOnly = true)
    public APIResponse findAll(){
        List<Client> list = new ArrayList<>();
        list = clientRepository.findAll();

        return new APIResponse("Operación exitosa", list, false, HttpStatus.OK);
    }

    //findById

    @Transactional
    public APIResponse findById(Long id){
        try {
            Client found = clientRepository.findById(id).orElse(null);
            if (found == null){
                return new APIResponse("El cliente no existe", true, HttpStatus.NOT_FOUND);
            }

            return new APIResponse("Operación exitosa",found, false, HttpStatus.OK);
        }catch (Exception ex){
            ex.printStackTrace();
            return new APIResponse("No se pudo consultar al cliente", true, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public APIResponse saveClient(Client payload){
        try {
            if (clientRepository.findByEmail(payload.getEmail()).isPresent()){
                return new APIResponse("El cliente ya existe", true, HttpStatus.BAD_REQUEST);
            }

            clientRepository.save(payload);
            return new APIResponse("Operación exitosa", false, HttpStatus.CREATED);
        }catch (Exception ex){
            ex.printStackTrace();
            return new APIResponse("Error al crear al cliente", true, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
}
