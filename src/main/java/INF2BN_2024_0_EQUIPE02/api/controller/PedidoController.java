package INF2BN_2024_0_EQUIPE02.api.controller;

import INF2BN_2024_0_EQUIPE02.api.dto.CriarPedidoDTO;
import INF2BN_2024_0_EQUIPE02.api.dto.PedidoDTO;
import INF2BN_2024_0_EQUIPE02.api.domain.Cliente;
import INF2BN_2024_0_EQUIPE02.api.domain.Pedido;
import INF2BN_2024_0_EQUIPE02.api.domain.Usuario;
import INF2BN_2024_0_EQUIPE02.api.service.ClienteService;
import INF2BN_2024_0_EQUIPE02.api.service.EmailService;
import INF2BN_2024_0_EQUIPE02.api.service.PedidoService;
import INF2BN_2024_0_EQUIPE02.api.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api-salsi/pedidos")
@CrossOrigin(origins = "http://localhost:5173")
public class PedidoController {

    @Autowired
    private PedidoService service;
    @Autowired
    private EmailService emailService;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private UsuarioService usuarioService;


    // Lista todos os pedidos como DTO
    @GetMapping
    public ResponseEntity<List<PedidoDTO>> listarPedidos() {
        List<PedidoDTO> pedidos = service.listarPedidos();
        return ResponseEntity.ok(pedidos);
    }

    // Busca um pedido por ID e retorna como DTO
    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> get(@PathVariable Long id) {
        Optional<PedidoDTO> pedido = service.getPedidoById(id);
        return pedido.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    // Atualiza um pedido existente
    @PutMapping("/{id}")
    public ResponseEntity<Pedido> atualizar(@PathVariable Long id, @RequestBody Pedido pedido) {
        try {
            Pedido pedidoAtualizado = service.atualizar(id, pedido);
            if (pedidoAtualizado != null) {
                // Enviar email quando pedido for concluído
                if ("CONCLUIDO".equals(pedidoAtualizado.getStatus()) && pedidoAtualizado.getCliente() != null) {
                    emailService.enviarEmailStatusPedido(
                            pedidoAtualizado.getCliente().getEmail(),
                            pedidoAtualizado.getCliente().getNome(),
                            pedidoAtualizado.getId_pedido(),
                            "Concluído"
                    );
                }
                return ResponseEntity.ok(pedidoAtualizado);
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            System.err.println("Erro ao atualizar pedido: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Sistema de enviar email
    @PostMapping
    public ResponseEntity<Pedido> criarPedido(@RequestBody CriarPedidoDTO dto) {
        System.out.println("🔄 Iniciando criação de pedido...");
        System.out.println("📋 DTO recebido - ID Usuario: " + dto.getIdUsuario() + ", Total: " + dto.getTotal());

        try {
            // Buscar usuário primeiro
            Optional<Usuario> usuarioOpt = usuarioService.findById(dto.getIdUsuario());
            if (!usuarioOpt.isPresent()) {
                System.err.println("❌ Usuário não encontrado com ID: " + dto.getIdUsuario());
                return ResponseEntity.badRequest().build();
            }

            Usuario usuario = usuarioOpt.get();
            if (!usuario.isCliente() || usuario.getIdCliente() == null) {
                System.err.println("❌ Usuário não é um cliente válido");
                return ResponseEntity.badRequest().build();
            }

            // Buscar cliente vinculado ao usuário
            Optional<Cliente> clienteOpt = clienteService.buscarPorId(usuario.getIdCliente());
            if (!clienteOpt.isPresent()) {
                System.err.println("❌ Cliente não encontrado com ID: " + usuario.getIdCliente());
                return ResponseEntity.badRequest().build();
            }

            Cliente cliente = clienteOpt.get();
            System.out.println("✅ Cliente encontrado: " + cliente.getNome() + " - Email: " + cliente.getEmail());

            // Criar pedido
            Pedido pedido = new Pedido();
            pedido.setCliente(cliente);
            pedido.setValor_total(BigDecimal.valueOf(dto.getTotal()));
            pedido.setStatus("Pendente");

            Pedido novoPedido = service.incluir(pedido);
            System.out.println("✅ Pedido criado com ID: " + novoPedido.getId_pedido());

            // Enviar email
            System.out.println("📧 Tentando enviar email...");
            try {
                if (cliente.getEmail() != null && !cliente.getEmail().isBlank()) {
                    System.out.println("📧 Enviando email para: " + cliente.getEmail());
                    emailService.enviarEmailPedidoConcluido(
                            cliente.getEmail(),
                            cliente.getNome(),
                            novoPedido.getId_pedido(),
                            novoPedido.getValor_total()
                    );
                    System.out.println("✅ Email enviado com sucesso!");
                } else {
                    System.err.println("❌ Email do cliente está vazio ou nulo");
                }
            } catch (Exception e) {
                System.err.println("❌ Erro ao enviar email: " + e.getMessage());
                e.printStackTrace();
            }

            return ResponseEntity.status(HttpStatus.CREATED).body(novoPedido);

        } catch (Exception e) {
            System.err.println("❌ Erro geral ao criar pedido: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Endpoint para testar email
    @GetMapping("/teste-email")
    public ResponseEntity<String> testarEmail() {
        System.out.println("🔧 Testando configuração de email...");

        boolean sucesso = emailService.testarConexaoEmail();

        if (sucesso) {
            return ResponseEntity.ok("Email de teste enviado com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao enviar email de teste. Verifique os logs.");
        }
    }
}
