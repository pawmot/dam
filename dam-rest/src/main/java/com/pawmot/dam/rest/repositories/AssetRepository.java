package com.pawmot.dam.rest.repositories;

import com.pawmot.dam.rest.domain.Asset;
import org.springframework.data.repository.Repository;

import java.util.UUID;

public interface AssetRepository extends Repository<Asset, UUID> {
    Asset findById(UUID id);
}
