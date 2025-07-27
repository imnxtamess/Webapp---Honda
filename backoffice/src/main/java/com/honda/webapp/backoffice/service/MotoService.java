package com.honda.webapp.backoffice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.honda.webapp.backoffice.model.Moto;
import com.honda.webapp.backoffice.repository.MotoRepository;

@Service
public class MotoService {

  @Autowired
  private MotoRepository motoRepository;

  public List<Moto> findAll() {
    return motoRepository.findAll();
  }

  public Moto getById(Integer id) {
    return motoRepository.findById(id).get();
  }

  public Optional<Moto> findById(Integer id) {
    return motoRepository.findById(id);
  }

  public Moto create(Moto moto) {
    return motoRepository.save(moto);
  }

  public Moto update(Moto moto) {
    return motoRepository.save(moto);
  }

  public void deleteById(Integer id) {
    Moto moto = getById(id);

    motoRepository.delete(moto);
  }

  public List<Moto> findByCategories_Id(Integer id) {

    return motoRepository.findByCategories_Id(id);
  }

}
