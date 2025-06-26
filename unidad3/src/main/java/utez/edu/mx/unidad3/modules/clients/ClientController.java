package utez.edu.mx.unidad3.modules.clients;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.unidad3.utils.APIResponse;

@RestController
@RequestMapping("/api/client")
@Tag(name = "Controlador de clientes", description = "Operaciones relacionadas con clientes")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping("")
    @Operation(summary = "Traer todos los clientes", description = "Trae el listado de los clientes en el sistema")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Respuesta de operación exitosa",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = APIResponse.class)),
                            @Content(mediaType = "application/xml", schema = @Schema(implementation = APIResponse.class)),
                    }
            )
    })
    public ResponseEntity<APIResponse> findAll(){
        APIResponse response = clientService.findAll();
        return new ResponseEntity<>(response, response.getStatus());
    }

    @PostMapping("")
    @Operation(summary = "Registrar clientes", description = "Registra un cliente en el sistema")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Respuesta de operación exitosa",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = APIResponse.class)),
                            @Content(mediaType = "application/xml", schema = @Schema(implementation = APIResponse.class)),
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Respuesta de operación erronea",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = APIResponse.class)),
                            @Content(mediaType = "application/xml", schema = @Schema(implementation = APIResponse.class)),
                    }
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Respuesta de error interno en el servidor",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = APIResponse.class)),
                            @Content(mediaType = "application/xml", schema = @Schema(implementation = APIResponse.class)),
                    }
            )
    })
    public ResponseEntity<APIResponse> saveClient(@RequestBody Client payload){
        APIResponse response = clientService.saveClient(payload);
        return new ResponseEntity<>(response,response.getStatus());
    }
    @GetMapping("/{id}")
    @Operation(summary = "Buscar cliente", description = "busca un cliente en el sistema por ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "400",
                    description = "Respuesta de operación erronea",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = APIResponse.class)),
                            @Content(mediaType = "application/xml", schema = @Schema(implementation = APIResponse.class)),
                    }
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Respuesta de error interno en el servidor",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = APIResponse.class)),
                            @Content(mediaType = "application/xml", schema = @Schema(implementation = APIResponse.class)),
                    }
            )
    })
    public ResponseEntity<APIResponse> findById(@PathVariable("id")Long id){
        APIResponse response = clientService.findById(id);
        return new ResponseEntity<>(response,response.getStatus());
    }
    @PutMapping("")
    @Operation(summary = "Modificar clientes", description = "modifica un cliente en el sistema")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "400",
                    description = "Respuesta de operación erronea",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = APIResponse.class)),
                            @Content(mediaType = "application/xml", schema = @Schema(implementation = APIResponse.class)),
                    }
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Respuesta de error interno en el servidor",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = APIResponse.class)),
                            @Content(mediaType = "application/xml", schema = @Schema(implementation = APIResponse.class)),
                    }
            )
    })
    public ResponseEntity<APIResponse> updateClient(@RequestBody Client payload){
        APIResponse response = clientService.updateClient(payload);
        return new ResponseEntity<>(response,response.getStatus());
    }

    @DeleteMapping("")
    @Operation(summary = "Eliminar clientes", description = "Elimina un cliente en el sistema")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "400",
                    description = "Respuesta de operación erronea",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = APIResponse.class)),
                            @Content(mediaType = "application/xml", schema = @Schema(implementation = APIResponse.class)),
                    }
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Respuesta de error interno en el servidor",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = APIResponse.class)),
                            @Content(mediaType = "application/xml", schema = @Schema(implementation = APIResponse.class)),
                    }
            )
    })
    public ResponseEntity<APIResponse> deleteClient(@RequestBody Client payload){
        APIResponse response = clientService.deleteClient(payload);
        return new ResponseEntity<>(response,response.getStatus());
    }
}
