package com.grupocmc.protein.service.administration.scope;

import com.google.common.collect.Lists;
import com.grupocmc.protein.config.Constantes;
import com.grupocmc.protein.dao.model.administration.Scope;
import com.grupocmc.protein.dao.repository.administration.ScopeRepository;
import com.grupocmc.protein.mvc.exceptions.NotFoundException;
import com.grupocmc.protein.mvc.exceptions.ValidationException;
import com.grupocmc.protein.pojos.webservices.response.ResponseBaseWebService;
import com.grupocmc.protein.pojos.webservices.request.scope.ScopeRequestWebService;
import com.grupocmc.protein.pojos.webservices.response.scope.ListScopeResponseWebService;
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
public class ScopeService implements ScopeServiceOps {

    private ScopeRepository scopeRepository;

    @Autowired
    public ScopeService(ScopeRepository scopeRepository) {
        this.scopeRepository = scopeRepository;
    }

    @Override
    public ResponseBaseWebService create(ScopeRequestWebService peticion) {
        if (scopeRepository.findBySlug(peticion.getCode()) != null) {
            throw new ValidationException(Constantes.RETORNO_SCOPE_CODE_YA_EXISTE);
        }
        final Scope entity= peticion.buildEntityFromRequest(new Scope());
        final Scope entityCreated= scopeRepository.save(entity);
        final ResponseBaseWebService respuesta =  new ResponseBaseWebService();
        respuesta.setId(entityCreated.getId());
        respuesta.setCodigoRespuesta(Constantes.RETORNO_OK);
        respuesta.setError(false);
        return respuesta;
    }

    @Override
    public ScopeResponseWebService update(ScopeRequestWebService peticion) {
        final Scope oldEntity= Optional.ofNullable(scopeRepository.findBySlug(peticion.getCode()))
                .orElseThrow(() -> new NotFoundException(Constantes.RETORNO_SCOPE_NO_EXISTE));

        final Scope modified = peticion.buildEntityFromRequest(oldEntity);

        modified.setState(oldEntity.getState());
        modified.setId(oldEntity.getId());
        scopeRepository.save(modified);
        return getBeanFromEntity(modified);
    }

    @Override
    public ScopeResponseWebService read(Long idCatalog) {
        Optional<Scope> entity= ofNullable(scopeRepository.findById(idCatalog))
                .orElseThrow(() -> new NotFoundException(Constantes.RETORNO_SCOPE_NO_EXISTE));
        if (!entity.isPresent())
            throw new NotFoundException(Constantes.RETORNO_SCOPE_NO_EXISTE) ;

        return  getBeanFromEntity(entity.get());
    }

    @Override
    public ResponseBaseWebService delete(Long idCatalog) {
        Optional<Scope> entity = ofNullable(scopeRepository.findById(idCatalog))
                .orElseThrow(() -> new NotFoundException(Constantes.RETORNO_SCOPE_NO_EXISTE));

        if (entity.isPresent()){
            Scope entityDeleted = entity.get();
            entityDeleted.setState(false);
            scopeRepository.save(entityDeleted);
        }
        return new ResponseBaseWebService();
    }

    @Override
    public ListScopeResponseWebService readAll(Long[] ids) {
        final ListScopeResponseWebService beanRespuesta = new ListScopeResponseWebService();
        beanRespuesta.setListScopes(getScopes(ids)
                .stream()
                .map(entity-> getBeanFromEntity(entity))
                .collect(Collectors.toList()));
        return beanRespuesta;
    }

    private List<Scope> getScopes (Long[] listIds) {
        return (Optional.ofNullable(listIds)
                .map(ids -> scopeRepository.findByIdIn(Lists.newArrayList(ids)))
                .orElse(scopeRepository.findAll())
                .stream()
                .sorted(Comparator.comparing(Scope::getSlug).thenComparing(Scope::getName))
                .collect(Collectors.toList()));

    }
    private ScopeResponseWebService getBeanFromEntity(Scope entidad) {
        return Optional.ofNullable(entidad).map(entityCatalog -> new ScopeResponseWebService().buildRespuestaFromEntity(entityCatalog))
                .orElse(new ScopeResponseWebService());
    }
}
