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

    public Vacation findOneById(Long id) {return vacationRepository.findOneById(id);}
    public List<Vacation> findByStatus(String status) { return vacationRepository.findByStatus(status); }
    public List<Vacation> findPending() { return vacationRepository.findPending(); }
    public int updateVacationStatus(String status,Long id) {return vacationRepository.updateVacationStatus(status,id);}

    public Vacation save(Vacation v) {
        return vacationRepository.save(v);
    }
}
