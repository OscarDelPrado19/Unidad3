package utez.edu.mx.unidad3.modules.cede;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.unidad3.utils.APIResponse;

@RestController
@RequestMapping("/api/cede")
@Tag(name = "Controlador de cedes", description = "Operaciones relacionadas con cedes")
@SecurityRequirement(name = "bearerAuth")
public class CedeController {
    @Autowired
    private CedeService cedeService;

    @GetMapping("")
    @Operation(summary = "Traer todas las cedes", description = "Trae el listado de las cedes en el sistema")
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
        APIResponse response = cedeService.findAll();
        return new ResponseEntity<>(response,response.getStatus());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar cedes", description = "busca una cede en el sistema por ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
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
    public ResponseEntity<APIResponse> findById(@PathVariable("id")Long id){
        APIResponse response = cedeService.findById(id);
        return new ResponseEntity<>(response,response.getStatus());
    }

    @PostMapping("")
    @Operation(summary = "Registrar cedes", description = "Registra una cede en el sistema")
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
    public ResponseEntity<APIResponse> saveCede(@RequestBody Cede payload){
        APIResponse response = cedeService.saveCede(payload);
        return new ResponseEntity<>(response,response.getStatus());
    }

}
