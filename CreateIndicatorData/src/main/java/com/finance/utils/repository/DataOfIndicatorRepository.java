package com.finance.utils.repository;

import com.finance.utils.model.DataOfIndicator;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DataOfIndicatorRepository extends CrudRepository<DataOfIndicator, Long> {

    Optional<DataOfIndicator> getDataOfIndicatorByUniqueIdentifier(String uniqueIdentifier);

}
