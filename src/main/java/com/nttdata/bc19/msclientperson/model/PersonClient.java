package com.nttdata.bc19.msclientperson.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonClient extends BaseModel {
    private String name;
    private String lastName;
    private String documentNumber;
    private String gender;
    private String address;
    private String phone;
    private String email;
    private DocumentType documentType;
}
