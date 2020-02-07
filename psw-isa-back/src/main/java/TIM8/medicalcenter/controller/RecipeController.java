package TIM8.medicalcenter.controller;

import TIM8.medicalcenter.dto.RecipeDTORequest;
import TIM8.medicalcenter.dto.response.RecipeDTOResponse;
import TIM8.medicalcenter.model.Recipe;
import TIM8.medicalcenter.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/recipe",produces = MediaType.APPLICATION_JSON_VALUE)
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    /**
     * Funkcija kojom medicinska sestra moze da overi recept
     * @param recipeDTORequest
     * @return
     */
    @PreAuthorize("hasRole('MEDICAL_STAFF')")
    @RequestMapping(value = "/updateRecipe",method = RequestMethod.POST)
    public ResponseEntity<?> updateRecipe(@RequestBody RecipeDTORequest recipeDTORequest){
        int rows = recipeService.updateRecipe(recipeDTORequest.getNurse(),recipeDTORequest.getRecipeId());
        return new ResponseEntity<>(rows, HttpStatus.ACCEPTED);
    }

    /**
     * Funkcija kojom medicinska sestra moze da preuzme sve recepte koji cekaju na overu
     * @return
     */
    @RequestMapping(value= "/getAllPending",method = RequestMethod.GET)
    public ResponseEntity<?> getAllPending(){
        List<Recipe> recipeList = recipeService.findAll();
        List<RecipeDTOResponse> recipeDTOResponses = new ArrayList<>();
        for(Recipe r : recipeList){
            if(r.getNurse() == null){
                recipeDTOResponses.add(new RecipeDTOResponse(r.getPatient().getFirstName() +  ' '  + r.getPatient().getLastName(),
                                                             null,
                                                             r.getMedicine(),
                                                             r.getId()));
            }
        }
        return new ResponseEntity<>(recipeDTOResponses,HttpStatus.OK);

    }
}
