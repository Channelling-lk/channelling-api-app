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

import lk.channelling.entity.Patient;
import lk.channelling.exception.RecordNotFoundException;
import lk.channelling.repository.PatientRepository;
import lk.channelling.services.PatientService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackFor = Exception.class)
@Log4j2
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    @Override
    public Patient findById(Long id) {
        Optional<Patient> patient = patientRepository.findById(id);
        if (patient.isEmpty()) throw new RecordNotFoundException("No patient found for the id: " + id);
        return patient.get();
    }

    @Override
    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public void delete(Long id) {
        Patient patient = findById(id);
        patientRepository.delete(patient);
    }

    @Override
    public Patient update(Long id, Patient newPatient) {
        Optional<Patient> updatedPatient = patientRepository.findById(id).map(patient -> {
            patient.setFirstName(newPatient.getFirstName());
            patient.setLastName(newPatient.getLastName());
            patient.setDisplayName(newPatient.getDisplayName());
            patient.setIdentificationMethod(newPatient.getIdentificationMethod());
            patient.setIdentificationValue(newPatient.getIdentificationValue());
            patient.setMobileNo1(newPatient.getMobileNo1());
            patient.setMobileNo2(newPatient.getMobileNo2());
            patient.setEmail(newPatient.getEmail());
            patient.setAddressLine1(newPatient.getAddressLine1());
            patient.setAddressLine2(newPatient.getAddressLine2());
            patient.setAddressLine3(newPatient.getAddressLine3());
            patient.setCityId(newPatient.getCityId());
            patient.setDateOfBirth(newPatient.getDateOfBirth());
            patient.setGender(newPatient.getGender());
            patient.setTitleId(newPatient.getTitleId());
            patient.setStatus(newPatient.getStatus());
            patient.setModifiedUser(newPatient.getModifiedUser());
            patient.setModifiedDate(newPatient.getModifiedDate());
            patient.setVersion(newPatient.getVersion());
            return patientRepository.save(patient);
        });
        if (updatedPatient.isPresent()) return updatedPatient.get();
        throw new RecordNotFoundException("No patient record found for the id: " + id);
    }
}
