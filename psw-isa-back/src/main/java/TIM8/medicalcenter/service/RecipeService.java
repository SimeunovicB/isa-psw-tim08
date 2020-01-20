package TIM8.medicalcenter.service;

import TIM8.medicalcenter.controller.PersonController;
import TIM8.medicalcenter.dto.MedicalExaminationDTO;
import TIM8.medicalcenter.model.Medicine;
import TIM8.medicalcenter.model.Recipe;
import TIM8.medicalcenter.model.users.Nurse;
import TIM8.medicalcenter.model.users.Patient;
import TIM8.medicalcenter.repository.MedicineRepository;
import TIM8.medicalcenter.repository.PersonRepository;
import TIM8.medicalcenter.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private MedicineRepository medicineRepository;

    @Autowired
    private PersonRepository personRepository;




    public Recipe createNew(MedicalExaminationDTO medicalExaminationDTO) {

        Recipe recipe = new Recipe();
        Medicine m = medicineRepository.getOne(medicalExaminationDTO.getMedicine());
        String medicineName = m.getName();
        recipe.setMedicine(medicineName);
        recipe.setPatient((Patient) personRepository.findOneById(medicalExaminationDTO.getPatient()));
        return recipeRepository.save(recipe);

    }
    public int updateRecipe(Long nurse,Long id) {
        Nurse n = (Nurse) personRepository.findOneById(nurse);
        return recipeRepository.updateUserStatus(n,id);
    }
    public List<Recipe> findAll() {
        return recipeRepository.findAll();
    }
}
