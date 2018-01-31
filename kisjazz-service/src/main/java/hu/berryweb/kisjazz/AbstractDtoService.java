package hu.berryweb.kisjazz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;

import java.util.List;

/**
 * Created by Nandi on 2018. 01. 24..
 */
@Slf4j
public abstract class AbstractDtoService<E, D> extends AbstractService {

    @Autowired
    protected ConversionService conversionService;

    private Class<E> entityClass;
    private Class<D> dtoClass;

    public AbstractDtoService(Class<E> entityClass, Class<D> dtoClass) {
        this.entityClass = entityClass;
        this.dtoClass = dtoClass;
    }

    public E convertDto(D dto) {
        log.debug("start");
        E entity = conversionService.convert(dto, entityClass);
        log.debug("stop");
        return entity;
    }

    public D convertEntity(E entity) {
        log.debug("start");
        D dto = conversionService.convert(entity, dtoClass);
        log.debug("stop");
        return dto;
    }

    public List<E> convertDtoList(List<D> dtoList) {
        log.debug("start");
        List<E> entityList = (List<E>) conversionService.convert(dtoList,
                TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(dtoClass)),
                TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(entityClass)));
        log.debug("stop");
        return entityList;
    }

    public List<D> convertEntityList(List<E> entityList) {
        log.debug("start");
        List<D> dtoList = (List<D>) conversionService.convert(entityList,
                TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(entityClass)),
                TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(dtoClass)));
        log.debug("stop");
        return dtoList;
    }

}
