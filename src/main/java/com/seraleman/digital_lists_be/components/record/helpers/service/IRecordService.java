package com.seraleman.digital_lists_be.components.record.helpers.service;

import java.util.List;

import com.seraleman.digital_lists_be.components.record.Record;

public interface IRecordService {

    List<Record> getRecords();

    Record getRecordById(Long id);

    Record saveRecord(Record record);

    void deleteRecordById(Long id);

}
