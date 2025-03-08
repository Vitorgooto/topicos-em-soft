package com.campeonato.campeonato.service;

import com.campeonato.campeonato.model.Campeonato;
import com.campeonato.campeonato.model.Time;
import com.campeonato.campeonato.repository.CampeonatoRepository;
import com.campeonato.campeonato.repository.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampeonatoService {
    @Autowired
    private CampeonatoRepository campeonatoRepository;

    @Autowired
    private TimeRepository timeRepository;

    public List<Campeonato> listarCampeonatos() {
        return campeonatoRepository.findAll();
    }

    public Campeonato criarCampeonato(Campeonato campeonato) {
        return campeonatoRepository.save(campeonato);
    }

    public void adicionarTimeAoCampeonato(Long campeonatoId, Time time) {
        Campeonato campeonato = campeonatoRepository.findById(campeonatoId)
                .orElseThrow(() -> new RuntimeException("Campeonato não encontrado"));

        time.setCampeonato(campeonato);
        campeonato.getTimes().add(time);

        timeRepository.save(time);
        campeonatoRepository.save(campeonato);
    }
}