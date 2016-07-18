package com.pawmot.dam.rest.services;

import com.pawmot.dam.rest.domain.Asset;
import com.pawmot.dam.rest.repositories.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
class AssetServiceImpl implements AssetService {
    private final AssetRepository assetRepository;

    @Autowired
    public AssetServiceImpl(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    @Override
    @Transactional
    public Asset update(Asset toSave) {
        Asset original = assetRepository.findById(toSave.getId()); // TODO: deal with nonexistent ids

        original.setDescription(toSave.getDescription());
        original.setName(toSave.getName());

        return original;
    }

    @Override
    public List<Asset> getAll() {
        return assetRepository.findAll();
    }

    @Override
    public Asset getById(UUID id) {
        return assetRepository.findById(id);
    }
}
