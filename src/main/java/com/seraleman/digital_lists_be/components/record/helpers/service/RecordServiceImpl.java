package com.seraleman.digital_lists_be.components.record.helpers.service;

import java.util.List;

import com.seraleman.digital_lists_be.components.record.IRecordDao;
import com.seraleman.digital_lists_be.components.record.Record;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecordServiceImpl implements IRecordService {

    @Autowired
    private IRecordDao recordDao;

    @Override
    public List<Record> getRecords() {
        return (List<Record>) recordDao.findAll();
    }

    @Override
    public Record getRecordById(Long id) {
        return recordDao.findById(id).orElse(null);
    }

    @Override
    public Record saveRecord(Record record) {
        return recordDao.save(record);
    }

    @Override
    public void deleteRecordById(Long id) {
        recordDao.deleteById(id);
    }

}
