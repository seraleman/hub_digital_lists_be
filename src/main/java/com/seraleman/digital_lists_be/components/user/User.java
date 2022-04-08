package com.seraleman.digital_lists_be.components.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
// import javax.validation.constrains.NotNull;
import javax.validation.constraints.NotNull;

@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "full_name")
    private String fullName;

    @NotNull
    @Column(name = "document_type")
    private String documentType;

    @NotNull
    @Column(name = "document_number")
    private String documentNumber;

    @NotNull
    private String email;

    @NotNull
    private String happening;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHappening() {
        return happening;
    }

    public void setHappening(String happening) {
        this.happening = happening;
    }

}
