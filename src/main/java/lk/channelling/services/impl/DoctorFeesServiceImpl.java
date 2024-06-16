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

import lk.channelling.entity.DoctorFees;
import lk.channelling.exception.RecordNotFoundException;
import lk.channelling.repository.DoctorFeesRepository;
import lk.channelling.services.DoctorFeesService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackFor = Exception.class)
@Log4j2
public class DoctorFeesServiceImpl implements DoctorFeesService {

    private final DoctorFeesRepository doctorFeesRepository;

    @Autowired
    public DoctorFeesServiceImpl(DoctorFeesRepository doctorFeesRepository) {
        this.doctorFeesRepository = doctorFeesRepository;
    }

    @Override
    public List<DoctorFees> findAll() {
        return doctorFeesRepository.findAll();
    }

    @Override
    public DoctorFees findById(Long id) {
        Optional<DoctorFees> doctorFees = doctorFeesRepository.findById(id);
        if (doctorFees.isEmpty()) throw new RecordNotFoundException("No doctor fees record found for the id: " + id);
        return doctorFees.get();
    }

    @Override
    public DoctorFees save(DoctorFees doctorFees) {
        return doctorFeesRepository.save(doctorFees);
    }

    @Override
    public void delete(Long id) {
        DoctorFees doctorFees = findById(id);
        doctorFeesRepository.delete(doctorFees);
    }

    @Override
    public DoctorFees update(Long id, DoctorFees newDoctorFees) {
        Optional<DoctorFees> updatedDoctorFees = doctorFeesRepository.findById(id).map(doctorFees -> {
            doctorFees.setAmount(newDoctorFees.getAmount());
            doctorFees.setTransactionId(newDoctorFees.getTransactionId());
            doctorFees.setEffectiveFrom(newDoctorFees.getEffectiveFrom());
            doctorFees.setEffectiveTo(newDoctorFees.getEffectiveTo());
            doctorFees.setStatus(newDoctorFees.getStatus());
            doctorFees.setModifiedUser(newDoctorFees.getModifiedUser());
            doctorFees.setModifiedDate(newDoctorFees.getModifiedDate());
            doctorFees.setVersion(newDoctorFees.getVersion());
            doctorFees.setDoctorId(newDoctorFees.getDoctorId());
            return doctorFeesRepository.save(doctorFees);
        });
        if (updatedDoctorFees.isPresent()) return updatedDoctorFees.get();
        throw new RecordNotFoundException("No doctor fees record found for the id: " + id);
    }
}
