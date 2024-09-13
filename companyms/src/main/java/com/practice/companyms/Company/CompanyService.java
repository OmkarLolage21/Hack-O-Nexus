package com.practice.companyms.Company;

import com.practice.companyms.Company.dto.ReviewMessage;

import java.util.List;

public interface CompanyService {

    List<Company> getAllCompanies();
    boolean updateCompany(Company company, Long id);
    void createCompany(Company company);
    boolean deleteCompanyById(Long id);
    Company getCompanyById(Long id);

    void updateCompanyRating(ReviewMessage reviewMessage);
}
