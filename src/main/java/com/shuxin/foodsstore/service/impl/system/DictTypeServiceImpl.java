package com.shuxin.foodsstore.service.impl.system;

import com.shuxin.foodsstore.entity.system.DictType;
import com.shuxin.foodsstore.repository.system.DictTypeRepository;
import com.shuxin.foodsstore.service.system.DictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;


@Service("dictTypeService")
@Transactional(rollbackFor = Exception.class)
public class DictTypeServiceImpl implements DictTypeService {

    @Autowired
    private DictTypeRepository dictTypeRepository;

    @Override
    public DictType findOne(String id) {
        return dictTypeRepository.findOne(id);
    }

    @Override
    public Page<DictType> findAll(PageRequest request) {
        return dictTypeRepository.findAll(request);
    }

    @Override
    public Page<DictType> findAllByDictType(String dictType, PageRequest request) {
        return dictTypeRepository.findAllByDictType(dictType, request);
    }

    @Override
    public Page<DictType> findAllByDictTypeAndDictName(String dictType, String dictName, PageRequest request) {
        return dictTypeRepository.findAllByDictTypeAndDictName(dictType, dictName, request);
    }

    @Override
    public Page<DictType> getListPage(String dictType, String dictName,PageRequest request) {


        Page page = dictTypeRepository.findAll(new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {


                Path<String> namePath = root.get("dictName");
                Path<String> typePath = root.get("dictType");

                query.where(cb.like(namePath, "%" + dictName + "%"), cb.like(typePath, "%" + dictType + "%"));
                return null;
            }
        }, request);

        return page;


    }

    @Override
    public DictType save(DictType dictType) {

        return dictTypeRepository.save(dictType);
    }

    @Override
    public void delete(String id) {
        dictTypeRepository.delete(id);
    }
}
