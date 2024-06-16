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

import lk.channelling.entity.Appointment;
import lk.channelling.enums.Status;
import lk.channelling.exception.RecordNotFoundException;
import lk.channelling.handlers.LoginAuthenticationHandler;
import lk.channelling.repository.AppointmentRepository;
import lk.channelling.services.AppointmentService;
import lk.channelling.util.TimeUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackFor = Exception.class)
@Log4j2
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public List<Appointment> findAll() {
        return appointmentRepository.findAll();
    }

    @Override
    public Appointment findById(Long id) {
        Optional<Appointment> appointment = appointmentRepository.findById(id);
        if (appointment.isEmpty()) throw new RecordNotFoundException("No appointment found for the id: " + id);
        return appointment.get();
    }

    @Override
    public Appointment save(Appointment appointment) {
        LoginAuthenticationHandler.validateUser();

        appointment.setCreatedUser(LoginAuthenticationHandler.getUserName());
        appointment.setStatus(Status.ACTIVE);
        appointment.setCreatedDate(TimeUtil.getCurrentTimeStamp());
        return appointmentRepository.save(appointment);
    }

    @Override
    public void delete(Long id) {
        Appointment appointment = findById(id);
        appointmentRepository.delete(appointment);
    }

    @Override
    public Appointment update(Long id, Appointment newAppointment) {
        Optional<Appointment> updatedAppointment = appointmentRepository.findById(id).map(appointment -> {
            appointment.setAppointmentStatus(newAppointment.getAppointmentStatus());
            appointment.setPatientId(newAppointment.getPatientId());
            appointment.setSessionId(newAppointment.getSessionId());
            appointment.setStatus(newAppointment.getStatus());
            appointment.setModifiedUser(LoginAuthenticationHandler.getUserName());
            appointment.setModifiedDate(TimeUtil.getCurrentTimeStamp());
            return appointmentRepository.save(appointment);
        });
        if (updatedAppointment.isPresent()) return updatedAppointment.get();
        throw new RecordNotFoundException("No appointment record found for the id: " + id);
    }
}
