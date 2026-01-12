package org.informatics.dao;

import org.informatics.entity.Company;

public interface CompanyDao extends BaseDao<Company, Long>{
    Long count();
}