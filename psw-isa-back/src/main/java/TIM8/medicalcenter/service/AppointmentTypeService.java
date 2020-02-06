package TIM8.medicalcenter.service;

import TIM8.medicalcenter.dto.AppointmentTypeDTO;
import TIM8.medicalcenter.model.AppointmentType;
import TIM8.medicalcenter.repository.AppointmentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentTypeService {
    @Autowired
    private AppointmentTypeRepository appointmentTypeRepository;

    public AppointmentType findOneByName(String name) {return appointmentTypeRepository.findByName(name);}
    public AppointmentType findOneById(Long id){
        return appointmentTypeRepository.findOneById(id);
    }
    public List<AppointmentType> findAll() { return appointmentTypeRepository.findAll();}
    public AppointmentType save(AppointmentTypeDTO appointmentTypeDTO) {
        AppointmentType m = new AppointmentType();
        m.setName(appointmentTypeDTO.getName());
        return appointmentTypeRepository.save(m);
    }

    public int updateAppointmentType(String name, long id) { return appointmentTypeRepository.updateAppointmentType(name,id); }
}
