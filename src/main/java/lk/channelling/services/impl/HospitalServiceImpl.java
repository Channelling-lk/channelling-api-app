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

import lk.channelling.entity.Hospital;
import lk.channelling.enums.Status;
import lk.channelling.exception.OldObjectException;
import lk.channelling.exception.RecordNotFoundException;
import lk.channelling.handlers.LoginAuthenticationHandler;
import lk.channelling.repository.HospitalRepository;
import lk.channelling.response.ApiResponse;
import lk.channelling.response.ApiResponseItem;
import lk.channelling.services.HospitalService;
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
public class HospitalServiceImpl implements HospitalService {

    private HospitalRepository hospitalRepository;

    @Autowired
    public void setHospitalRepository(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    @Override
    public ApiResponse findAll() {
        return new ApiResponse(true, hospitalRepository.findAll().stream()
                .map(hospital -> new ApiResponseItem(hospital.getName(), Long.toString(hospital.getId()), hospital.getDisplayName()))
                .toList());
    }

    @Override
    public Hospital findById(Long id) {
        Optional<Hospital> hospital = hospitalRepository.findById(id);

        if (hospital.isEmpty()) throw new RecordNotFoundException("No hospital record found for the id : " + id);

        return hospital.get();
    }

    @Override
    public List<Hospital> findByStatus(Status status) {
        return hospitalRepository.findByStatus(status);
    }

    @Override
    public Hospital save(Hospital hospital) {
        LoginAuthenticationHandler.validateUser();

        hospital.setCreatedUser(LoginAuthenticationHandler.getUserName());
        hospital.setStatus(Status.ACTIVE);
        hospital.setCreatedDate(TimeUtil.getCurrentTimeStamp());

        return hospitalRepository.saveAndFlush(hospital);
    }

    @Override
    public void delete(Long id) {
        Hospital fetchedInstitution = findById(id);

        if (fetchedInstitution == null)
            throw new RecordNotFoundException("No hospital record found for the id : " + id);

        hospitalRepository.delete(fetchedInstitution);
    }

    @Override
    public Hospital update(Long id, Hospital newHospital) {
        LoginAuthenticationHandler.validateUser();

        Optional<Hospital> updatedInstitution = hospitalRepository.findById(id).map(hospital -> {
            if (hospital.getVersion() != newHospital.getVersion()) throw new OldObjectException();

            hospital.setName(newHospital.getName());
            hospital.setDisplayName(newHospital.getDisplayName());
            hospital.setBrNo(newHospital.getBrNo());
            hospital.setAddressLine1(newHospital.getAddressLine1());
            hospital.setAddressLine2(newHospital.getAddressLine2());
            hospital.setAddressLine3(newHospital.getAddressLine3());
            hospital.setCityId(newHospital.getCityId());
            hospital.setHospitalFee(newHospital.getHospitalFee());
            hospital.setStatus(newHospital.getStatus());
            hospital.setModifiedUser(LoginAuthenticationHandler.getUserName());
            hospital.setModifiedDate(TimeUtil.getCurrentTimeStamp());

            return hospitalRepository.save(hospital);
        });

        if (updatedInstitution.isPresent()) return updatedInstitution.get();
        throw new RecordNotFoundException("No hospital record found for the id : " + id);
    }

    @Override
    public List<Hospital> findByCityId(Long cityId) {
        return hospitalRepository.findByCityId(cityId);
    }
}
