package INF2BN_2024_0_EQUIPE02.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import jakarta.mail.internet.MimeMessage;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${app.email.from}")
    private String fromEmail;

    @Value("${app.email.name}")
    private String fromName;

    public void enviarEmailPedidoConcluido(String emailCliente, String nomeCliente, Long pedidoId, BigDecimal valor) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(fromEmail, fromName);
            helper.setTo(emailCliente);
            helper.setSubject("✅ Pedido Confirmado - PetShop SALSI");

            String htmlContent = criarEmailPedidoConcluido(nomeCliente, pedidoId, valor);
            helper.setText(htmlContent, true);

            mailSender.send(message);
            System.out.println("Email enviado para: " + emailCliente);

        } catch (Exception e) {
            System.err.println("Erro ao enviar email: " + e.getMessage());
        }
    }

    public void enviarEmailAdocaoIniciada(String emailCliente, String nomeCliente, String nomeAnimal) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(fromEmail, fromName);
            helper.setTo(emailCliente);
            helper.setSubject("🐾 Processo de Adoção Iniciado - PetShop SALSI");

            String htmlContent = criarEmailAdocao(nomeCliente, nomeAnimal);
            helper.setText(htmlContent, true);

            mailSender.send(message);

        } catch (Exception e) {
            System.err.println("Erro ao enviar email: " + e.getMessage());
        }
    }

    public void enviarEmailDoacaoCadastrada(String emailCliente, String nomeCliente, String nomeAnimal) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(fromEmail, fromName);
            helper.setTo(emailCliente);
            helper.setSubject("❤️ Animal Cadastrado para Doação - PetShop SALSI");

            String htmlContent = criarEmailDoacao(nomeCliente, nomeAnimal);
            helper.setText(htmlContent, true);

            mailSender.send(message);

        } catch (Exception e) {
            System.err.println("Erro ao enviar email: " + e.getMessage());
        }
    }

    public void enviarEmailBoasVindas(String emailCliente, String nomeCliente) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(fromEmail, fromName);
            helper.setTo(emailCliente);
            helper.setSubject("🎉 Bem-vindo ao PetShop SALSI!");

            String htmlContent = criarEmailBoasVindas(nomeCliente);
            helper.setText(htmlContent, true);

            mailSender.send(message);

        } catch (Exception e) {
            System.err.println("Erro ao enviar email: " + e.getMessage());
        }
    }

    private String criarEmailPedidoConcluido(String nomeCliente, Long pedidoId, BigDecimal valor) {
        String dataAtual = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));

        return String.format("""
            <html>
            <body style="font-family: Arial, sans-serif; color: #333;">
                <div style="max-width: 600px; margin: 0 auto; padding: 20px; border: 1px solid #ddd; border-radius: 10px;">
                    <div style="text-align: center; background: #00b7c2; color: white; padding: 20px; border-radius: 10px 10px 0 0;">
                        <h1>🐾 PetShop SALSI</h1>
                        <h2>✅ Pedido Confirmado!</h2>
                    </div>
                    
                    <div style="padding: 20px;">
                        <p>Olá <strong>%s</strong>,</p>
                        <p>Seu pedido foi confirmado com sucesso! 🎉</p>
                        
                        <div style="background: #f9f9f9; padding: 15px; border-radius: 5px; margin: 20px 0;">
                            <h3>📋 Detalhes do Pedido:</h3>
                            <p><strong>Número:</strong> #%d</p>
                            <p><strong>Valor:</strong> R$ %.2f</p>
                            <p><strong>Data:</strong> %s</p>
                        </div>
                        
                        <p>Obrigado por escolher o PetShop SALSI! ❤️</p>
                    </div>
                </div>
            </body>
            </html>
            """, nomeCliente, pedidoId, valor, dataAtual);
    }

    private String criarEmailAdocao(String nomeCliente, String nomeAnimal) {
        return String.format("""
            <html>
            <body style="font-family: Arial, sans-serif; color: #333;">
                <div style="max-width: 600px; margin: 0 auto; padding: 20px; border: 1px solid #ddd; border-radius: 10px;">
                    <div style="text-align: center; background: #d4af37; color: white; padding: 20px; border-radius: 10px 10px 0 0;">
                        <h1>🐾 PetShop SALSI</h1>
                        <h2>❤️ Processo de Adoção Iniciado!</h2>
                    </div>
                    
                    <div style="padding: 20px;">
                        <p>Olá <strong>%s</strong>,</p>
                        <p>Você iniciou o processo de adoção do <strong>%s</strong>! 🐕🐱</p>
                        
                        <div style="background: #f0f8ff; padding: 15px; border-radius: 5px; margin: 20px 0;">
                            <h3>📋 Próximos Passos:</h3>
                            <ul>
                                <li>Nossa equipe entrará em contato em até 24 horas</li>
                                <li>Agendaremos uma visita para conhecer o %s</li>
                                <li>Realizaremos uma entrevista rápida</li>
                                <li>Se tudo estiver ok, você poderá levar seu novo amigo!</li>
                            </ul>
                        </div>
                        
                        <p>Obrigado por dar uma nova chance ao %s! ❤️</p>
                    </div>
                </div>
            </body>
            </html>
            """, nomeCliente, nomeAnimal, nomeAnimal, nomeAnimal);
    }

    private String criarEmailDoacao(String nomeCliente, String nomeAnimal) {
        return String.format("""
            <html>
            <body style="font-family: Arial, sans-serif; color: #333;">
                <div style="max-width: 600px; margin: 0 auto; padding: 20px; border: 1px solid #ddd; border-radius: 10px;">
                    <div style="text-align: center; background: #4CAF50; color: white; padding: 20px; border-radius: 10px 10px 0 0;">
                        <h1>🐾 PetShop SALSI</h1>
                        <h2>❤️ Doação Cadastrada!</h2>
                    </div>
                    
                    <div style="padding: 20px;">
                        <p>Olá <strong>%s</strong>,</p>
                        <p>Obrigado por cadastrar o <strong>%s</strong> para doação! 🙏</p>
                        
                        <div style="background: #f0fff0; padding: 15px; border-radius: 5px; margin: 20px 0;">
                            <h3>📋 O que acontece agora:</h3>
                            <ul>
                                <li>O %s aparecerá em nosso site para adoção</li>
                                <li>Cuidaremos de toda a divulgação</li>
                                <li>Você será notificado quando alguém se interessar</li>
                            </ul>
                        </div>
                        
                        <p>Juntos, vamos encontrar uma família para o %s! ❤️</p>
                    </div>
                </div>
            </body>
            </html>
            """, nomeCliente, nomeAnimal, nomeAnimal, nomeAnimal);
    }

    private String criarEmailBoasVindas(String nomeCliente) {
        return String.format("""
            <html>
            <body style="font-family: Arial, sans-serif; color: #333;">
                <div style="max-width: 600px; margin: 0 auto; padding: 20px; border: 1px solid #ddd; border-radius: 10px;">
                    <div style="text-align: center; background: linear-gradient(135deg, #00b7c2, #d4af37); color: white; padding: 20px; border-radius: 10px 10px 0 0;">
                        <h1>🐾 PetShop SALSI</h1>
                        <h2>🎉 Bem-vindo!</h2>
                    </div>
                    
                    <div style="padding: 20px;">
                        <p>Olá <strong>%s</strong>,</p>
                        <p>Seja bem-vindo ao PetShop SALSI! 🎉</p>
                        
                        <div style="background: #fff8dc; padding: 15px; border-radius: 5px; margin: 20px 0;">
                            <h3>🌟 O que você pode fazer:</h3>
                            <ul>
                                <li>🛒 Comprar produtos para seu pet</li>
                                <li>❤️ Adotar um novo amigo</li>
                                <li>🐾 Cadastrar animais para doação</li>
                            </ul>
                        </div>
                        
                        <p>Estamos aqui para cuidar do seu melhor amigo! ❤️</p>
                    </div>
                </div>
            </body>
            </html>
            """, nomeCliente);
    }
}
