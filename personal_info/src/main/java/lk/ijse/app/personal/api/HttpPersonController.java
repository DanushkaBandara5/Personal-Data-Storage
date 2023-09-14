package lk.ijse.app.personal.api;

import lk.ijse.app.personal.business.custom.PersonBO;
import lk.ijse.app.personal.dto.PersonDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/v1/persons")
public class HttpPersonController {
    private final PersonBO personBO;

    public HttpPersonController(PersonBO personBO) {
        this.personBO = personBO;
    }


    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    public List<PersonDTO> filterCustomers(@RequestParam(name = "q", required = false) String query) {
        if (query == null) query = "";
        query = "%" + query + "%";
        return personBO.findPersonByQuery(query);
    }


    @GetMapping("/{id}")
    public PersonDTO getPersonById(@PathVariable int id) {
        return personBO.getPersonById(id).get();
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable int id) {
        personBO.deletePersonById(id);
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public PersonDTO savePerson(@RequestBody @Valid PersonDTO personDTO) {

        return personBO.savePerson(personDTO);
    }


    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{id}")
    public PersonDTO updatePerson(@PathVariable String id, @RequestBody @Valid PersonDTO personDTO) {
        personDTO.setId(Integer.parseInt(id));
        return personBO.updatePerson(personDTO);
    }


}
