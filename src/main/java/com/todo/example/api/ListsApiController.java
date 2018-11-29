package com.todo.example.api;

import com.todo.example.model.TodoList;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-28T17:57:59.353+05:30")

@Controller
public class ListsApiController implements ListsApi {

    private static final Logger log = LoggerFactory.getLogger(ListsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public ListsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> addList(@ApiParam(value = "ToDo list to add"  )  @Valid @RequestBody TodoList todoList) {
        String accept = request.getHeader("Accept");
        
        todoList.setId(todoList.getId());
        todoList.setName(todoList.getName());
        todoList.setDescription(todoList.getDescription());
        todoList.setTasks(todoList.getTasks());
        return new ResponseEntity("Product saved successfully", HttpStatus.OK);
       // return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<TodoList>> searchLists(@ApiParam(value = "pass an optional search string for looking up a list") @Valid @RequestParam(value = "searchString", required = false) String searchString,@Min(0)@ApiParam(value = "number of records to skip for pagination") @Valid @RequestParam(value = "skip", required = false) Integer skip,@Min(0) @Max(50) @ApiParam(value = "maximum number of records to return") @Valid @RequestParam(value = "limit", required = false) Integer limit) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
            	final List<TodoList> listoftodo = new ArrayList<TodoList>( limit );
        		
                final Iterator<TodoList> iterator = listoftodo.iterator();
                for( int i = 0; listoftodo.size() < limit && iterator.hasNext(); ) {
                	if( ++i > ( ( limit - 1 ) * limit ) ) {
                		listoftodo.add( iterator.next() );
                	}
                }
        		
                return new ResponseEntity<List<TodoList>>(objectMapper.readValue("[ {  \"name\" : \"Home\",  \"description\" : \"The list of things that need to be done at home\n\",  \"id\" : \"d290f1ee-6c54-4b01-90e6-d701748f0851\",  \"tasks\" : [ {    \"name\" : \"mow the yard\",    \"id\" : \"0e2ac84f-f723-4f24-878b-44e63e7ae580\",    \"completed\" : true  }, {    \"name\" : \"mow the yard\",    \"id\" : \"0e2ac84f-f723-4f24-878b-44e63e7ae580\",    \"completed\" : true  } ]}, {  \"name\" : \"Home\",  \"description\" : \"The list of things that need to be done at home\n\",  \"id\" : \"d290f1ee-6c54-4b01-90e6-d701748f0851\",  \"tasks\" : [ {    \"name\" : \"mow the yard\",    \"id\" : \"0e2ac84f-f723-4f24-878b-44e63e7ae580\",    \"completed\" : true  }, {    \"name\" : \"mow the yard\",    \"id\" : \"0e2ac84f-f723-4f24-878b-44e63e7ae580\",    \"completed\" : true  } ]} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<TodoList>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<TodoList>>(HttpStatus.NOT_IMPLEMENTED);
    }

}
