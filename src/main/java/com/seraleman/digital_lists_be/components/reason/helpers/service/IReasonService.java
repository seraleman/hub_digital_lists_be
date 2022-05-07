package com.seraleman.digital_lists_be.components.reason.helpers.service;

import java.util.List;

import com.seraleman.digital_lists_be.components.reason.Reason;

public interface IReasonService {

    List<Reason> getReasons();

    Reason getReasonById(Long id);

    Reason saveReason(Reason reason);

    void deleteReasonById(Long id);
}
