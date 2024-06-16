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

import lk.channelling.entity.DoctorSession;
import lk.channelling.exception.RecordNotFoundException;
import lk.channelling.repository.DoctorSessionRepository;
import lk.channelling.services.DoctorSessionService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackFor = Exception.class)
@Log4j2
public class DoctorSessionServiceImpl implements DoctorSessionService {

    private final DoctorSessionRepository doctorSessionRepository;

    @Autowired
    public DoctorSessionServiceImpl(DoctorSessionRepository doctorSessionRepository) {
        this.doctorSessionRepository = doctorSessionRepository;
    }

    @Override
    public List<DoctorSession> findAll() {
        return doctorSessionRepository.findAll();
    }

    @Override
    public DoctorSession findById(Long id) {
        Optional<DoctorSession> doctorSession = doctorSessionRepository.findById(id);
        if (doctorSession.isEmpty()) throw new RecordNotFoundException("No doctor session found for the id: " + id);
        return doctorSession.get();
    }

    @Override
    public DoctorSession save(DoctorSession doctorSession) {
        return doctorSessionRepository.save(doctorSession);
    }

    @Override
    public void delete(Long id) {
        DoctorSession doctorSession = findById(id);
        doctorSessionRepository.delete(doctorSession);
    }

    @Override
    public DoctorSession update(Long id, DoctorSession newDoctorSession) {
        Optional<DoctorSession> updatedDoctorSession = doctorSessionRepository.findById(id).map(doctorSession -> {
            doctorSession.setDoctorId(newDoctorSession.getDoctorId());
            doctorSession.setHospitalId(newDoctorSession.getHospitalId());
            doctorSession.setSessionDateTime(newDoctorSession.getSessionDateTime());
            doctorSession.setMaxPatients(newDoctorSession.getMaxPatients());
            doctorSession.setStatus(newDoctorSession.getStatus());
            doctorSession.setModifiedUser(newDoctorSession.getModifiedUser());
            doctorSession.setModifiedDate(newDoctorSession.getModifiedDate());
            doctorSession.setVersion(newDoctorSession.getVersion());
            doctorSession.setTotalFee(newDoctorSession.getTotalFee());
            return doctorSessionRepository.save(doctorSession);
        });
        if (updatedDoctorSession.isPresent()) return updatedDoctorSession.get();
        throw new RecordNotFoundException("No doctor session record found for the id: " + id);
    }
}
