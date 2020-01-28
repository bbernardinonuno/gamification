package com.grupocmc.protein.service.administration.level;

import com.google.common.collect.Lists;
import com.grupocmc.protein.config.Constantes;
import com.grupocmc.protein.dao.model.administration.Level;
import com.grupocmc.protein.dao.model.administration.Scope;
import com.grupocmc.protein.dao.repository.administration.LevelRepository;
import com.grupocmc.protein.mvc.exceptions.NotFoundException;
import com.grupocmc.protein.mvc.exceptions.ValidationException;
import com.grupocmc.protein.pojos.webservices.request.level.LevelRequestWebService;
import com.grupocmc.protein.pojos.webservices.response.ResponseBaseWebService;
import com.grupocmc.protein.pojos.webservices.response.level.LevelResponseWebService;
import com.grupocmc.protein.pojos.webservices.response.level.ListLevelResponseWebService;
import com.grupocmc.protein.pojos.webservices.response.scope.ScopeResponseWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

@Service
@Transactional
public class LevelService  implements  LevelServiceOps{

    private LevelRepository levelRepository;

    @Autowired
    public LevelService (LevelRepository levelRepository){
        this.levelRepository = levelRepository;
    }

    @Override
    public ResponseBaseWebService create(LevelRequestWebService peticionBean) {
        if (levelRepository.findBySlug(peticionBean.getCode()) != null) {
            throw new ValidationException(Constantes.RETORNO_LEVEL_CODE_YA_EXISTE);
        }
        final Level entity= peticionBean.buildEntityFromRequest(new Level());
        final Level entityCreated= levelRepository.save(entity);
        final ResponseBaseWebService respuesta =  new ResponseBaseWebService();
        respuesta.setId(entityCreated.getId());
        respuesta.setCodigoRespuesta(Constantes.RETORNO_OK);
        respuesta.setError(false);
        return respuesta;
    }

    @Override
    public LevelResponseWebService update(LevelRequestWebService peticionBean) {
        final Level oldEntity= Optional.ofNullable(levelRepository.findBySlug(peticionBean.getCode()))
                .orElseThrow(() -> new NotFoundException(Constantes.RETORNO_SCOPE_NO_EXISTE));

        final Level modified = peticionBean.buildEntityFromRequest(oldEntity);

        modified.setState(oldEntity.getState());
        modified.setId(oldEntity.getId());
        levelRepository.save(modified);
        return getBeanFromEntity(modified);
    }

    @Override
    public LevelResponseWebService read(Long idCatalog) {
        Optional<Level> entity= ofNullable(levelRepository.findById(idCatalog))
                .orElseThrow(() -> new NotFoundException(Constantes.RETORNO_SCOPE_NO_EXISTE));
        if (!entity.isPresent())
            throw new NotFoundException(Constantes.RETORNO_SCOPE_NO_EXISTE) ;

        return  getBeanFromEntity(entity.get());
    }

    @Override
    public ResponseBaseWebService delete(Long idCatalog) {
        Optional<Level> entity = Optional.ofNullable(levelRepository.findById(idCatalog))
                .orElseThrow(() -> new NotFoundException(Constantes.RETORNO_LEVEL_NO_EXISTE));

        if (entity.isPresent()){
            Level entityDeleted = entity.get();
            entityDeleted.setState(false);
            levelRepository.save(entityDeleted);
        }
        return new ResponseBaseWebService();
    }

    @Override
    public ListLevelResponseWebService readAll(Long[] ids) {
        final ListLevelResponseWebService beanRespuesta = new ListLevelResponseWebService();
        beanRespuesta.setListLevels(getLevels(ids)
                .stream()
                .map(entity-> getBeanFromEntity(entity))
                .collect(Collectors.toList()));
        return beanRespuesta;
    }

    private List<Level> getLevels (Long[] listIds) {
        return (Optional.ofNullable(listIds)
                .map(ids -> levelRepository.findByIdIn(Lists.newArrayList(ids)))
                .orElse(levelRepository.findAll())
                .stream()
                .sorted(Comparator.comparing(Level::getSlug).thenComparing(Level::getName))
                .collect(Collectors.toList()));

    }
    private LevelResponseWebService getBeanFromEntity(Level entidad) {
        return Optional.ofNullable(entidad).map(entityCatalog -> new LevelResponseWebService().buildRespuestaFromEntity(entityCatalog))
                .orElse(new LevelResponseWebService());
    }
}
