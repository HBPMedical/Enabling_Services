package api.services.enablingServices.service;


import api.services.enablingServices.repository.HospitalsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class HospitalsServiceImpl implements HospitalsService {

    @Autowired
    private HospitalsRepository hospitalsRepository;

    @Override
    public boolean fieldValueExists(Object value, String columnName) throws UnsupportedOperationException {
        Assert.notNull(columnName, "Column name should not be null when checking if the value exists.");

        if (value == null)
            return false;

        if (!columnName.equals("code")) {
            throw new UnsupportedOperationException("Column: " + columnName + " not supported for the Unique Constraint.");
        }
        return this.hospitalsRepository.findByCode(value.toString()) != null;
    }
}