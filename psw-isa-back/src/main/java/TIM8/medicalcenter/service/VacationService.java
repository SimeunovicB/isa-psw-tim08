package TIM8.medicalcenter.service;

import TIM8.medicalcenter.model.Vacation;
import TIM8.medicalcenter.repository.VacationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VacationService {

    @Autowired
    private VacationRepository vacationRepository;

    public List<Vacation> findByStatus(String status) { return vacationRepository.findByStatus(status); }
    public int updateVacationStatus(String status,Long id) {return vacationRepository.updateVacationStatus(status,id);}
}
