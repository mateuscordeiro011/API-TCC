package INF2BN_2024_0_EQUIPE02.api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import INF2BN_2024_0_EQUIPE02.api.domain.Cliente;
import INF2BN_2024_0_EQUIPE02.api.domain.Pedido;

@Service
public class NotificacaoService {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private EmailService emailService;

    private static final Logger logger = LoggerFactory.getLogger(NotificacaoService.class);

    public void notificarClienteSobreNovoPedido(Pedido pedido) {
        try {
            // ✅ Corrigido: extrai o Cliente do Optional
            Cliente cliente = clienteService.buscarPorId(pedido.getId_pedido()).orElse(null);

            if (cliente != null && cliente.getEmail() != null && !cliente.getEmail().isBlank()) {
                emailService.enviarEmailPedidoConcluido(
                        cliente.getEmail(),
                        cliente.getNome(),
                        pedido.getId_pedido(),
                        pedido.getValor_total()
                );
            }
        } catch (Exception e) {
            logger.error("Erro ao enviar e-mail de confirmação de pedido ID {}: {}",
                    pedido.getId_pedido(),
                    e.getMessage(),
                    e
            );
        }
    }
}