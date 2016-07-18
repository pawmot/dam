package com.pawmot.dam.rest.services;

import com.pawmot.dam.rest.domain.Asset;

import java.util.List;
import java.util.UUID;

public interface AssetService {
    Asset update(Asset toSave);

    List<Asset> getAll();

    Asset getById(UUID id);
}
