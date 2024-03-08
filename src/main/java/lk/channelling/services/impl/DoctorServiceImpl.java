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

import lk.channelling.entity.Doctor;
import lk.channelling.enums.Status;
import lk.channelling.exception.OldObjectException;
import lk.channelling.exception.RecordNotFoundException;
import lk.channelling.handlers.LoginAuthenticationHandler;
import lk.channelling.repository.DoctorRepository;
import lk.channelling.response.ApiResponse;
import lk.channelling.response.ApiResponseItem;
import lk.channelling.services.DoctorService;
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
public class DoctorServiceImpl implements DoctorService {

    private DoctorRepository doctorRepository;

    @Autowired
    public void setDoctorRepository(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public ApiResponse findAll() {
        return new ApiResponse(true, doctorRepository.findAll().stream().map(doctor -> new ApiResponseItem(doctor.getDisplayName(), Long.toString(doctor.getId()), doctor.getDisplayName())).toList());
    }

    @Override
    public Doctor findById(Long id) {
        Optional<Doctor> doctor = doctorRepository.findById(id);

        if (doctor.isEmpty()) throw new RecordNotFoundException("No doctor record found for the id : " + id);

        return doctor.get();
    }

    @Override
    public List<Doctor> findByStatus(Status status) {
        return doctorRepository.findByStatus(status);
    }

    @Override
    public Doctor save(Doctor doctor) {
        LoginAuthenticationHandler.validateUser();

        doctor.setCreatedUser(LoginAuthenticationHandler.getUserName());
        doctor.setStatus(Status.ACTIVE);
        doctor.setCreatedDate(TimeUtil.getCurrentTimeStamp());

        return doctorRepository.saveAndFlush(doctor);
    }

    @Override
    public void delete(Long id) {
        Doctor fetchedInstitution = findById(id);

        if (fetchedInstitution == null) throw new RecordNotFoundException("No doctor record found for the id : " + id);

        doctorRepository.delete(fetchedInstitution);
    }

    @Override
    public Doctor update(Long id, Doctor newDoctor) {
        LoginAuthenticationHandler.validateUser();

        Optional<Doctor> updatedDoctor = doctorRepository.findById(id).map(doctor -> {
            if (doctor.getVersion() != newDoctor.getVersion()) throw new OldObjectException();

            doctor.setFirstName(newDoctor.getFirstName());
            doctor.setLastName(newDoctor.getLastName());
            doctor.setDisplayName(newDoctor.getDisplayName());
            doctor.setIdentificationMethod(newDoctor.getIdentificationMethod());
            doctor.setIdentificationValue(newDoctor.getIdentificationValue());
            doctor.setMobileNo1(newDoctor.getMobileNo1());
            doctor.setMobileNo2(newDoctor.getMobileNo2());
            doctor.setEmail(newDoctor.getEmail());
            doctor.setAddressLine1(newDoctor.getAddressLine1());
            doctor.setAddressLine2(newDoctor.getAddressLine2());
            doctor.setAddressLine3(newDoctor.getAddressLine3());
            doctor.setCityId(newDoctor.getCityId());
            doctor.setDateOfBirth(newDoctor.getDateOfBirth());
            doctor.setGender(newDoctor.getGender());
            doctor.setStatus(newDoctor.getStatus());
            doctor.setModifiedUser(LoginAuthenticationHandler.getUserName());
            doctor.setModifiedDate(TimeUtil.getCurrentTimeStamp());

            return doctorRepository.save(doctor);
        });

        if (updatedDoctor.isPresent()) return updatedDoctor.get();
        throw new RecordNotFoundException("No doctor record found for the id : " + id);
    }


}
