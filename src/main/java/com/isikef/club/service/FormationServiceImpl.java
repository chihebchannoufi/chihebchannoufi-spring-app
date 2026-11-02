package com.isikef.club.service;

import java.util.List;

import com.isikef.club.forms.PageParams;
import org.springframework.beans.factory.annotation.Autowired;
import com.isikef.club.entities.Club;
import com.isikef.club.entities.Formation;
import com.isikef.club.entities.ThemeFormation;
import com.isikef.club.forms.FormFormation; 
import com.isikef.club.repository.ClubRepository;
import com.isikef.club.repository.FormationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class FormationServiceImpl implements FormationService {

  @Autowired 
  FormationRepository formationRepository;

  @Autowired
  ClubRepository clubRepository;

  private Club getClubById(Long clubId) {
    return clubRepository.findById(clubId)
    .orElseThrow(() ->
     new IllegalArgumentException("Club not found with id: " + clubId));  
  }
  
    
    public Formation createFormation(Club club, ThemeFormation themeFormation) {
      Formation formation = new Formation();
      formation.setTheme(themeFormation);
      formation.setClub(club);
      return formationRepository.save(formation);
    }

    @Override 
    public Formation createFormation(FormFormation form) {
      Club club = getClubById(form.getClubId());
      Formation formation = createFormation(club, form.getTheme());
      return formation;
    }


    @Override
    public Formation updateFormation(Long id, FormFormation form) {
      Formation formation = getFormationById(id);
      // Update formation properties based on the form values
      if (form.getTheme() != null) {
        formation.setTheme(form.getTheme());
        return formationRepository.save(formation);
      }
       return formation;
      }


    @Override
    public void deleteFormation(Long id) {
      Formation formation = getFormationById(id);
      formationRepository.delete(formation);
    }


    @Override
    public Formation getFormationById(Long id) {
      return formationRepository.findById(id)
      .orElseThrow(() -> new IllegalArgumentException("Formation not found with id: " + id));
    
    }


    @Override
    public List<Formation> getAllFormations() {
      return formationRepository.findAll();
    }


    @Override
    public List<Formation> getFormationsByTheme(ThemeFormation theme) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getFormationsByTheme'");
    }

  @Override
  public Page<Formation> getFormationsWithPagination(PageParams pageParams) {
    Pageable pageable = PageRequest.of(
            pageParams.getPage(),
            pageParams.getSize());
            Sort.by(Sort.Direction.ASC, "FORMATION_THEME");
    return formationRepository.findAll(pageable);
  }


  @Override
    public List<Formation> getFormationsByTheme(String theme) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getFormationsByTheme'");
    }


    

}