package com.joofont.service.impl;

import com.joofont.dao.OperationRecordMapper;
import com.joofont.entity.OperationRecord;
import com.joofont.service.OperationRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author cui jun on 2018/11/20.
 * @version 1.0
 */
@Service
public class OperationRecordServiceImpl implements OperationRecordService {

    @Autowired
    private OperationRecordMapper operationRecordMapper;

    @Override
    public boolean addOperationRecord(OperationRecord operationRecord) {
        return operationRecordMapper.addOperationRecord(operationRecord);
    }

}
