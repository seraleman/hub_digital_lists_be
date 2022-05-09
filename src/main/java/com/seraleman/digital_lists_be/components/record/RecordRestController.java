package com.seraleman.digital_lists_be.components.record;

import java.util.List;

import javax.validation.Valid;

import com.seraleman.digital_lists_be.components.record.helpers.service.IRecordService;
import com.seraleman.digital_lists_be.components.user.User;
import com.seraleman.digital_lists_be.components.user.helpers.service.IUserService;
import com.seraleman.digital_lists_be.helpers.localDateTime.ILocalDateTime;
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
@RequestMapping("/digitalLists/record")
public class RecordRestController {

    @Autowired
    private IRecordService recordService;

    @Autowired
    private IResponse response;

    @Autowired
    private IUserService userService;

    @Autowired
    private ILocalDateTime localDateTime;

    @GetMapping("/create/{userId}")
    public ResponseEntity<?> createReasonByQR(@PathVariable Long userId) {
        try {
            User user = userService.getUserById(userId);
            Record record = new Record(user, localDateTime.getLocalDateTime());
            return response.created(recordService.saveRecord(record));

        } catch (DataAccessException e) {
            return response.errorDataAccess(e);
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> getReasons() {

        try {
            List<Record> records = recordService.getRecords();
            if (records.isEmpty()) {
                return response.empty();
            }
            return response.list(records);
        } catch (DataAccessException e) {
            return response.errorDataAccess(e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getReasonById(@PathVariable Long id) {
        try {
            Record record = recordService.getRecordById(id);
            if (record == null) {
                return response.notFound(id);
            }
            return response.found(record);
        } catch (DataAccessException e) {
            return response.errorDataAccess(e);
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> createReason(@Valid @RequestBody Record record, BindingResult result) {

        if (result.hasErrors()) {
            return response.invalidObject(result);
        }
        try {
            return response.created(recordService.saveRecord(record));
        } catch (DataAccessException e) {
            return response.errorDataAccess(e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateReason(
            @PathVariable Long id,
            @Valid @RequestBody Record record,
            BindingResult result) {
        if (result.hasErrors()) {
            return response.invalidObject(result);
        }
        try {
            Record recordCurrent = recordService.getRecordById(id);
            if (recordCurrent == null) {
                return response.notFound(id);
            }
            recordCurrent.setCreated(record.getCreated());
            recordCurrent.setUser(record.getUser());
            return response.updated(recordService.saveRecord(recordCurrent));
        } catch (DataAccessException e) {
            return response.errorDataAccess(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReasonById(@PathVariable Long id) {

        try {
            Record record = recordService.getRecordById(id);
            if (record == null) {
                return response.notFound(id);
            }
            recordService.deleteRecordById(id);
            return response.deleted();
        } catch (DataAccessException e) {
            return response.errorDataAccess(e);
        }
    }
}
