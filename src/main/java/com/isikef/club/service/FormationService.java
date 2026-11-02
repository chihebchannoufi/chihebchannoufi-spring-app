package com.isikef.club.service;
import java.util.List;

import com.isikef.club.entities.Formation;
import com.isikef.club.entities.ThemeFormation;
import com.isikef.club.forms.FormFormation;
import com.isikef.club.forms.PageParams;
import org.springframework.data.domain.Page;

public interface FormationService {
    public Formation createFormation(FormFormation Form);
    public Formation updateFormation(Long id, FormFormation Form);
    public void deleteFormation(Long id);
    public Formation getFormationById(Long id);
    public List<Formation> getAllFormations();
    public List<Formation> getFormationsByTheme(String theme);
    List<Formation> getFormationsByTheme(ThemeFormation theme);
    Page<Formation> getFormationsWithPagination(PageParams pageParams);

}
