package dk.lyngby.dao.impl;

import dk.lyngby.dao.IDAO;
import dk.lyngby.dto.ResellerDTO;

import java.util.List;

public class ResellerDAOGeneric implements IDAO<ResellerDTO, ResellerDTO> {
    @Override
    public List<ResellerDTO> getAll() {
        return null;
    }

    @Override
    public ResellerDTO getById(int id) {
        return null;
    }

    @Override
    public List<ResellerDTO> getByType(String type) {
        return null;
    }

    @Override
    public ResellerDTO add(ResellerDTO resellerDTO) {
        return null;
    }
}
