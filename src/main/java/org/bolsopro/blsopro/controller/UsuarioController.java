package org.bolsopro.blsopro.controller;

import org.bolsopro.blsopro.dto.LoginResponse;

import org.bolsopro.blsopro.Usuario;
import org.bolsopro.blsopro.Receita;
import org.bolsopro.blsopro.Despesa;
import org.bolsopro.blsopro.MetaFinanceira;
import org.bolsopro.blsopro.PerfilComportamental;
import org.bolsopro.blsopro.SugestaoInvestimento;
import org.bolsopro.blsopro.ResumoFinanceiro;
import org.bolsopro.blsopro.Notificacao;
import org.bolsopro.blsopro.dto.LoginRequest;
import org.bolsopro.blsopro.repository.UsuarioRepository;
import org.bolsopro.blsopro.service.GerenciadorNotificacoes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private GerenciadorNotificacoes gerenciadorNotificacoes;

    @GetMapping
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    @PostMapping
    public Usuario criarUsuario(@RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Usuario usuario = usuarioRepository.findAll().stream()
                .filter(u -> u.getEmail().equals(request.getEmail()) && u.getSenha().equals(request.getSenha()))
                .findFirst()
                .orElse(null);

        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
        }

        return ResponseEntity.ok(new LoginResponse(usuario.getId(), usuario.getNome(), usuario.getEmail()));
    }
    
    
    @GetMapping("/{id}")
    public Usuario buscarPorId(@PathVariable Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Usuario atualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuarioAtualizado) {
        return usuarioRepository.findById(id).map(usuario -> {
            usuario.setNome(usuarioAtualizado.getNome());
            usuario.setEmail(usuarioAtualizado.getEmail());
            usuario.setSenha(usuarioAtualizado.getSenha());
            return usuarioRepository.save(usuario);
        }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deletarUsuario(@PathVariable Long id) {
        usuarioRepository.deleteById(id);
    }

    @PostMapping("/{id}/receitas/adicionar")
    public ResponseEntity<Usuario> adicionarReceita(@PathVariable Long id, @RequestBody Receita receita) {
    return usuarioRepository.findById(id)
        .map(usuario -> {
            usuario.cadastrarReceita(receita);
            usuarioRepository.save(usuario);
            Usuario atualizado = usuarioRepository.findById(id).orElse(null);
            return ResponseEntity.ok(atualizado);
        })
        .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
}


    @PostMapping("/{id}/despesas")
    public Usuario adicionarDespesa(@PathVariable Long id, @RequestBody Despesa despesa) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario != null) {
            usuario.cadastrarDespesa(despesa);
            return usuarioRepository.save(usuario);
        }
        return null;
    }

    @PostMapping("/{id}/metas")
    public Usuario definirMeta(@PathVariable Long id, @RequestBody MetaFinanceira meta) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario != null) {
            usuario.definirMeta(meta);
            return usuarioRepository.save(usuario);
        }
        return null;
    }

    @GetMapping("/{id}/perfil")
    public SugestaoInvestimento avaliarPerfil(@PathVariable Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario != null) {
            PerfilComportamental perfil = new PerfilComportamental();

            float totalReceitas = usuario.getReceitas().stream()
                    .map(Receita::getValor)
                    .reduce(0f, Float::sum);

            float totalDespesas = usuario.getDespesas().stream()
                    .map(Despesa::getValor)
                    .reduce(0f, Float::sum);

            perfil.avaliarPerfil(totalReceitas, totalDespesas);
            usuario.setPerfilComportamental(perfil);
            usuarioRepository.save(usuario);

            return SugestaoInvestimento.gerarSugestoes(perfil.getTipoPerfil());
        }
        return null;
    }



    @GetMapping("/{id}/sugestao")
    public ResponseEntity<?> gerarSugestao(@PathVariable Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);

        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado com ID: " + id);
        }

        PerfilComportamental perfil = usuario.getPerfilComportamental();
        if (perfil == null || perfil.getTipoPerfil() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Perfil comportamental do usuário ainda não foi definido.");
        }

        SugestaoInvestimento sugestao = SugestaoInvestimento.gerarSugestoes(perfil.getTipoPerfil());
        return ResponseEntity.ok(sugestao);
    }



    @GetMapping("/{id}/resumo")
    public ResumoFinanceiro gerarResumo(@PathVariable Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario != null) {
            ResumoFinanceiro resumo = new ResumoFinanceiro(0, 0f, 0f, 0f, 0);
            resumo.calcularTotalReceitas(usuario.getReceitas());
            resumo.calcularTotalDespesas(usuario.getDespesas());
            resumo.calcularSaldo();
            return resumo;
        }
        return null;
    }

    @GetMapping("/{id}/notificacoes")
    public List<Notificacao> gerarNotificacoes(@PathVariable Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario != null) {
            gerenciadorNotificacoes.verificarDespesasComMetas(usuario.getDespesas(), usuario.getMetasFinanceiras());
            gerenciadorNotificacoes.enviarNotificacaoParaUsuario(usuario);
            return usuario.getNotificacoes();
        }
        return null;
    }
}
