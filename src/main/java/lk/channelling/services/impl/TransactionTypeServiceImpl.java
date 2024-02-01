/*
 * Copyright 2024 Channelling.lk
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package lk.channelling.services.impl;

import lk.channelling.entity.TransactionType;
import lk.channelling.enums.Status;
import lk.channelling.exception.ObjectNotUniqueException;
import lk.channelling.exception.OldObjectException;
import lk.channelling.exception.RecordNotFoundException;
import lk.channelling.handlers.LoginAuthenticationHandler;
import lk.channelling.repository.TransactionTypeRepository;
import lk.channelling.services.TransactionTypeService;
import lk.channelling.util.TimeUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@Transactional(rollbackFor = Exception.class)
@Log4j2
public class TransactionTypeServiceImpl implements TransactionTypeService {

    private TransactionTypeRepository transactionTypeRepository;

    @Autowired
    public void setTransactionTypeRepository(TransactionTypeRepository transactionTypeRepository) {
        this.transactionTypeRepository = transactionTypeRepository;
    }

    @Override
    public List<TransactionType> findAll() {
        return transactionTypeRepository.findAll();
    }

    @Override
    public TransactionType findById(Long id) {
        Optional<TransactionType> country = transactionTypeRepository.findById(id);

        if (country.isEmpty()) throw new RecordNotFoundException("No Transaction Type record found for the id : " + id);

        return country.get();
    }

    @Override
    public TransactionType findByCode(String code) {
        Optional<TransactionType> country = transactionTypeRepository.findByCode(code);

        if (country.isEmpty())
            throw new RecordNotFoundException("No Transaction Type record found for the code : " + code);

        return country.get();
    }

    @Override
    public List<TransactionType> findByStatus(Status status) {
        return transactionTypeRepository.findByStatus(status);
    }

    @Override
    public TransactionType save(TransactionType transactionType) {
        LoginAuthenticationHandler.validateUser();
        Optional<TransactionType> fetchedTransactionType = transactionTypeRepository.findByCode(transactionType.getCode());

        if (fetchedTransactionType.isPresent())
            throw new ObjectNotUniqueException("The entered Transaction Type already exists in the database.");

        transactionType.setCreatedUser(LoginAuthenticationHandler.getUserName());
        transactionType.setStatus(Status.ACTIVE);
        transactionType.setCreatedDate(TimeUtil.getCurrentTimeStamp());

        return transactionTypeRepository.saveAndFlush(transactionType);
    }

    @Override
    public void delete(Long id) {
        TransactionType fetchedTransactionType = findById(id);

        if (fetchedTransactionType == null)
            throw new RecordNotFoundException("No Transaction Type record found for the id : " + id);

        transactionTypeRepository.delete(fetchedTransactionType);
    }

    @Override
    public TransactionType update(Long id, TransactionType newTransactionType) {
        LoginAuthenticationHandler.validateUser();

        Optional<TransactionType> updatedTransactionType = transactionTypeRepository.findById(id).map(cm -> {
            if (cm.getVersion() != newTransactionType.getVersion())
                throw new OldObjectException();

            cm.setDescription(newTransactionType.getDescription());
            cm.setStatus(newTransactionType.getStatus());
            cm.setCalculationMethod(newTransactionType.getCalculationMethod());
            cm.setAmountRate(newTransactionType.getAmountRate());
            cm.setModifiedUser(LoginAuthenticationHandler.getUserName());
            cm.setModifiedDate(TimeUtil.getCurrentTimeStamp());

            return transactionTypeRepository.save(cm);
        });

        if (updatedTransactionType.isPresent()) return updatedTransactionType.get();
        throw new RecordNotFoundException("No Transaction Type record found for the id : " + id);
    }
}
