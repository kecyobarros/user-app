package br.com.kecyo.userapp.http;

import br.com.kecyo.userapp.http.data.UserDataContract;
import br.com.kecyo.userapp.usescases.impl.UserSave;
import br.com.kecyo.userapp.usescases.impl.UserSearch;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(EndPointMapping.USER)
public class UserController {

    private final UserSave userSave;

    private final UserSearch userSearch;

    @ApiOperation(value = "Request All Users")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User found")
    })
    @RequestMapping(method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE,
            value = "/{page}")
    public ResponseEntity<Page<UserDataContract>> findAll(@PathVariable("page") final Integer page) {
        log.info("Endpoint: findAll");
        Page<UserDataContract> all = userSearch.findAll(page);
        return ResponseEntity.ok(all);
    }

    @ApiOperation(value = "Request User by Id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User found!"),
            @ApiResponse(code = 404, message = "User not found!")
    })
    @RequestMapping(method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE,
            value = "/id/{id}")
    public ResponseEntity<UserDataContract> findById(@PathVariable("id") final String id) {
        log.info("Endpoint: findById id={}", id);

        final UserDataContract user = userSearch.findById(id);
        return ResponseEntity.ok(user);
    }

    @ApiOperation(value = "Created User")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User created")
    })
    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> create(@Valid @RequestBody final UserDataContract userDataContract,
                                         final UriComponentsBuilder uriComponentsBuilder) {

        log.info("Endpoint: create value={}", userDataContract);
        String id = userSave.save(userDataContract);

        UriComponents uriComponents =
                uriComponentsBuilder.path(EndPointMapping.USER.concat("/{id}"))
                                    .buildAndExpand(id);

        return ResponseEntity
                .created(uriComponents.toUri())
                .body(id);
    }
}

