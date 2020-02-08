package TIM8.medicalcenter.service;

import TIM8.medicalcenter.dto.AppointmentTypeDTO;
import TIM8.medicalcenter.model.AppointmentType;
import TIM8.medicalcenter.repository.AppointmentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public void deleteAppointmentType(Long id) {appointmentTypeRepository.deleteAppointmentType(id);}
    public AppointmentType save(AppointmentTypeDTO appointmentTypeDTO) {
        AppointmentType m = new AppointmentType();
        m.setName(appointmentTypeDTO.getName());
        return appointmentTypeRepository.save(m);
    }

    /**
     * Moze doci do slucaja u kom dva razlicita administratora zele da obrisu ili da izmene neki tip pregleda,i zbog toga je potrebno zatvoriti ovu funkcionalnost u transakciju
     * @param name
     * @param id
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public int updateAppointmentType(String name, long id) {
        AppointmentType a = appointmentTypeRepository.findOneById(id);
        a.setName(name);
        appointmentTypeRepository.save(a);
        return 1;
    }
}
