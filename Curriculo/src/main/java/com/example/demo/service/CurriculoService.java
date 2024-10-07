package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Curriculo;
import com.example.demo.repository.CurriculoRepository;

import jakarta.transaction.Transactional;

@Service
public class CurriculoService {

    @Autowired
    private CurriculoRepository curriculoRepository;

    public Curriculo criarCurriculo(Curriculo curriculo) {

        return curriculoRepository.save(curriculo);
    }

    public List<Curriculo> listarTodos() {
        return curriculoRepository.findAll();
    }

    public Curriculo buscarPorId(Long id) {
        return curriculoRepository.findById(id).orElseThrow(() -> new RuntimeException("Currículo não encontrado!"));
    }

    @Transactional
    public Curriculo atualizarCurriculo(Long id, Curriculo curriculoAtualizado) {
        Curriculo curriculo = buscarPorId(id);
        curriculo.setNome(curriculoAtualizado.getNome());
        curriculo.setTelefone(curriculoAtualizado.getTelefone());
        curriculo.setEmail(curriculoAtualizado.getEmail());
        curriculo.setObjetivo(curriculoAtualizado.getObjetivo());
        curriculo.setApresentacao(curriculoAtualizado.getApresentacao());

        curriculo.getExperiencias().clear();
        curriculo.getExperiencias().addAll(curriculoAtualizado.getExperiencias());

        curriculo.getFormacoes().clear();
        curriculo.getFormacoes().addAll(curriculoAtualizado.getFormacoes());

        curriculo.getHabilidades().clear();
        curriculo.getHabilidades().addAll(curriculoAtualizado.getHabilidades());
        
        return curriculoRepository.save(curriculo);
    }

    public void deletarCurriculo(Long id) {
        curriculoRepository.deleteById(id);
    }
}
