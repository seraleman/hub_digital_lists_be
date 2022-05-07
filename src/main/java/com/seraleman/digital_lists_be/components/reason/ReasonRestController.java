package com.seraleman.digital_lists_be.components.reason;

import java.util.List;

import javax.validation.Valid;

import com.seraleman.digital_lists_be.components.reason.helpers.service.IReasonService;
import com.seraleman.digital_lists_be.helpers.response.IResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/digitalLists/reason")
public class ReasonRestController {

    @Autowired
    private IReasonService reasonService;

    @Autowired
    private IResponse response;

    @GetMapping("/")
    public ResponseEntity<?> getReasons() {

        try {
            List<Reason> reasons = reasonService.getReasons();
            if (reasons.isEmpty()) {
                return response.empty();
            }
            return response.list(reasons);
        } catch (DataAccessException e) {
            return response.errorDataAccess(e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getReasonById(@PathVariable Long id) {
        try {
            Reason reason = reasonService.getReasonById(id);
            if (reason == null) {
                return response.notFound(id);
            }
            return response.found(reason);
        } catch (DataAccessException e) {
            return response.errorDataAccess(e);
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> createReason(@Valid @RequestBody Reason reason, BindingResult result) {

        if (result.hasErrors()) {
            return response.invalidObject(result);
        }
        try {
            return response.created(reasonService.saveReason(reason));
        } catch (DataAccessException e) {
            return response.errorDataAccess(e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateReason(
            @PathVariable Long id,
            @Valid @RequestBody Reason reason,
            BindingResult result) {
        if (result.hasErrors()) {
            return response.invalidObject(result);
        }
        try {
            Reason reasonCurrent = reasonService.getReasonById(id);
            if (reasonCurrent == null) {
                return response.notFound(id);
            }
            reasonCurrent.setDescription(reason.getDescription());
            // reasonCurrent.setEventDate(reason.getEventDate());
            reasonCurrent.setName(reason.getName());
            return response.updated(reasonService.saveReason(reasonCurrent));
        } catch (DataAccessException e) {
            return response.errorDataAccess(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReasonById(@PathVariable Long id) {

        try {
            Reason reason = reasonService.getReasonById(id);
            if (reason == null) {
                return response.notFound(id);
            }
            reasonService.deleteReasonById(id);
            return response.deleted();
        } catch (DataAccessException e) {
            return response.errorDataAccess(e);
        }
    }
}
