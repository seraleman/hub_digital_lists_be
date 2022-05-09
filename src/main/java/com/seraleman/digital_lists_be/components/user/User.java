package com.seraleman.digital_lists_be.components.user;

import java.time.LocalDateTime;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.seraleman.digital_lists_be.components.reason.Reason;

@Entity
@Table(name = "users")
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

    @ManyToOne
    @NotNull
    private Reason reason;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime created;

    @Column(name = "qr_byte", columnDefinition = "longblob", nullable = true)
    private byte[] qrByte;

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

    public Reason getReason() {
        return reason;
    }

    public void setReason(Reason reason) {
        this.reason = reason;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public byte[] getQrByte() {
        return qrByte;
    }

    public void setQrByte(byte[] qrByte) {
        this.qrByte = qrByte;
    }

    @Override
    public String toString() {
        return "User [created=" + created + ", documentNumber=" + documentNumber + ", documentType=" + documentType
                + ", email=" + email + ", fullName=" + fullName + ", id=" + id + ", qrByte=" + Arrays.toString(qrByte)
                + ", reason=" + reason + "]";
    }

}
