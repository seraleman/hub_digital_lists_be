package com.seraleman.digital_lists_be.components.reason.helpers.service;

import java.util.List;

import com.seraleman.digital_lists_be.components.reason.IReasonDao;
import com.seraleman.digital_lists_be.components.reason.Reason;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReasonServiceImpl implements IReasonService {

    @Autowired
    private IReasonDao reasonDao;

    @Override
    public List<Reason> getReasons() {
        return (List<Reason>) reasonDao.findAll();
    }

    @Override
    public Reason getReasonById(Long id) {
        return reasonDao.findById(id).orElse(null);
    }

    @Override
    public Reason saveReason(Reason reason) {
        return reasonDao.save(reason);
    }

    @Override
    public void deleteReasonById(Long id) {
        reasonDao.deleteById(id);
    }

}
