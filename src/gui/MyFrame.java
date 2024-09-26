package gui;
import java.util.List;

import java.io.File;
import br.ufc.dc.luthieria.*;
import br.ufc.dc.luthieria.ordens.*;
import br.ufc.dc.luthieria.instrumentos.*;
import br.ufc.dc.luthieria.clientes.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MyFrame extends JFrame implements ActionListener {
    JPanel mainPanel; // Painel principal
    JPanel menuPanel; // Painel do primeiro menu
    JPanel instrumentPanel; // Painel secundário para Instrumentos
    JPanel notificacoesPanel; // Painel secundário para Instrumentos
    JPanel ordensPanel; // Painel secundário para Ordens
    JPanel clientesPanel; // Painel secundário para Clientes
    JButton instrumentosButton;
    JButton ordensButton;
    JButton notificacoesButton;
    JButton clientesButton;
    ImageIcon iconeInstrumento;
    ImageIcon iconeOrdem;
    ImageIcon iconeCliente;
    ImageIcon iconeNotificacao;
    JLabel titleLabel;
    JButton backButton;
    RepositorioOrdensJson repositorioOrdens;// = new RepositorioOrdensJson("C:\\Users\\giuli\\eclipse-workspace\\Luthieria\\ordens.json");
    RepositorioInstrumentosJson repositorioInstrumentos;
    RepositorioClientesJson repositorioClientes;


    MyFrame() {
    	
    	String filePath = "C:\\Users\\giuli\\eclipse-workspace\\LuthieriaLoja\\ordens.json";
    	String filePath2 = "C:\\Users\\giuli\\eclipse-workspace\\LuthieriaLoja\\instrumentos.json";
    	String filePath3 = "C:\\Users\\giuli\\eclipse-workspace\\LuthieriaLoja\\clientes.json";

        repositorioOrdens = new RepositorioOrdensJson(filePath);
        repositorioInstrumentos = new RepositorioInstrumentosJson(filePath2);
        repositorioClientes = new RepositorioClientesJson(filePath3);
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 600);
        
        mainPanel = new JPanel(new CardLayout()); // Usando CardLayout para alternar entre menus

        //Menu principal
        menuPanel = createMenuPanel();

        //Menus secundários
        instrumentPanel = createInstrumentPanel();
        ordensPanel = createOrdensPanel();
        clientesPanel = createClientesPanel();
        notificacoesPanel = createNotificacoesPanel();

        // Adicionando os painéis ao painel principal
        mainPanel.add(menuPanel, "Menu1");
        mainPanel.add(instrumentPanel, "InstrumentMenu");
        mainPanel.add(ordensPanel, "OrdensMenu");
        mainPanel.add(clientesPanel, "ClientesMenu");
        mainPanel.add(notificacoesPanel, "NotificacoesMenu");

        // Adicionando o painel principal ao JFrame
        this.add(mainPanel);
        
        this.setVisible(true);
    }
    
    private JPanel createMenuPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 30));
        
        //JPanel gridPanel = new JPanel(new GridLayout(2, 2, 20, 20));
        //gridPanel.setPreferredSize(new Dimension(200, 100));
        
        //ícones
        iconeInstrumento = new ImageIcon("Instrumento.png");
        iconeOrdem = new ImageIcon("Ordem.png");
        iconeCliente = new ImageIcon("Cliente.png");
        iconeNotificacao = new ImageIcon("Notificacao.png");
        
        //botões com ícones e texto
        instrumentosButton = new JButton("Instrumentos", iconeInstrumento);
        ordensButton = new JButton("Ordens", iconeOrdem);
        clientesButton = new JButton("Clientes", iconeCliente);
        notificacoesButton = new JButton("Notificações", iconeNotificacao);
        
        //tamanhos
        instrumentosButton.setPreferredSize(new Dimension(200, 200));
        ordensButton.setPreferredSize(new Dimension(200, 200));
        clientesButton.setPreferredSize(new Dimension(200, 200));
        notificacoesButton.setPreferredSize(new Dimension(200, 200));
        
        instrumentosButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        instrumentosButton.setHorizontalTextPosition(SwingConstants.CENTER);
        
        ordensButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        ordensButton.setHorizontalTextPosition(SwingConstants.CENTER);
        
        clientesButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        clientesButton.setHorizontalTextPosition(SwingConstants.CENTER);
        
        notificacoesButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        notificacoesButton.setHorizontalTextPosition(SwingConstants.CENTER);
        

        instrumentosButton.addActionListener(e -> showMenu("InstrumentMenu"));
        ordensButton.addActionListener(e -> showMenu("OrdensMenu"));
        clientesButton.addActionListener(e -> showMenu("ClientesMenu"));
        notificacoesButton.addActionListener(e -> showMenu("NotificacoesMenu"));
        
        //adicionando os botões ao painel
        buttonPanel.add(instrumentosButton);
        buttonPanel.add(ordensButton);
        buttonPanel.add(clientesButton);
        buttonPanel.add(notificacoesButton);
        
        titleLabel = new JLabel("LUTHIERIA", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 34));
        
        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createInstrumentPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 100, 20));
        JLabel label = new JLabel("Instrumentos Menu", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        
        JButton inputButton = new JButton("Inserir Instrumento");
        inputButton.addActionListener(e -> {
            JPanel inputPanel = new JPanel(new GridLayout(6, 2));
            JTextField codigoField = new JTextField();
            JTextField marcaField = new JTextField();
            JTextField nomeField = new JTextField();
            JComboBox<EstadoInstrumento> estadoComboBox = new JComboBox<>(EstadoInstrumento.values());

            inputPanel.add(new JLabel("Código:"));
            inputPanel.add(codigoField);
            inputPanel.add(new JLabel("Marca:"));
            inputPanel.add(marcaField);
            inputPanel.add(new JLabel("Nome:"));
            inputPanel.add(nomeField);
            inputPanel.add(new JLabel("Estado:"));
            inputPanel.add(estadoComboBox);
            
            int result = JOptionPane.showConfirmDialog(panel, inputPanel, "Digite os Dados da Ordem", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                try {
                    String nome = nomeField.getText().trim();
                    String marca = marcaField.getText().trim();
                    String codigo = codigoField.getText().trim();
                    EstadoInstrumento estado = (EstadoInstrumento) estadoComboBox.getSelectedItem();

                    // Verificações para campos vazios
                    if (codigo.isEmpty()) {
                        throw new IllegalArgumentException("O campo Código não pode estar vazio!");
                    }
                    if (marca.isEmpty()) {
                        throw new IllegalArgumentException("O campo Marca não pode estar vazio!");
                    }
                    if (nome.isEmpty()) {
                        throw new IllegalArgumentException("O campo Nome não pode estar vazio!");
                    }

                    if (repositorioInstrumentos.buscarPorId(codigo) != null) {
                        throw new IllegalArgumentException("Já existe um instrumento cadastrado com esse código!");
                    }
                    
                    InstrumentoAbstrato instrumentoNovo = new InstrumentoAbstrato(nome, marca, codigo, estado);
                    repositorioInstrumentos.inserir(instrumentoNovo);
                    
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(panel, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                System.out.println("Operação cancelada.");
            }
        });
        
        JButton backButton = new JButton("Voltar");
        backButton.addActionListener(e -> showMenu("Menu1"));

        JButton deleteButton = new JButton("Remover Instrumento");
        deleteButton.addActionListener(e -> {
            JPanel inputPanel = new JPanel(new GridLayout(6, 2));
            JTextField codigoField = new JTextField();
            
            inputPanel.add(new JLabel("Código:"));
            inputPanel.add(codigoField);
            
            int result = JOptionPane.showConfirmDialog(panel, inputPanel, "Digite o instrumento que deseja excluir", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                try {
                    String codigo = codigoField.getText().trim();
                    
                    if (codigo.isEmpty()) {
                        throw new IllegalArgumentException("O campo Código não pode estar vazio!");
                    }

                    if (repositorioInstrumentos.buscarPorId(codigo) == null) {
                        throw new IllegalArgumentException("Não existe um instrumento cadastrado com esse código!");
                    }
                    
                    repositorioInstrumentos.excluir(codigo);
                    
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(panel, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                System.out.println("Operação cancelada.");
            }
        });

        JButton alterarNomeButton = new JButton("Alterar nome");
        alterarNomeButton.addActionListener(e -> {
            JPanel inputPanel = new JPanel(new GridLayout(2, 2));
            JTextField codigoField = new JTextField();

            inputPanel.add(new JLabel("Código:"));
            inputPanel.add(codigoField);

            int result = JOptionPane.showConfirmDialog(null, inputPanel, "Buscar Instrumento", JOptionPane.OK_CANCEL_OPTION);
            
            if (result == JOptionPane.OK_OPTION) {
                String codigo = codigoField.getText().trim();

                try {
                    if (codigo.isEmpty()) {
                        throw new IllegalArgumentException("O campo Código não pode estar vazio!");
                    }

                    InstrumentoAbstrato instrumento = repositorioInstrumentos.buscarPorId(codigo);
                    if (instrumento != null) {
                        JTextField nomeField = new JTextField();
                        JPanel nomePanel = new JPanel(new GridLayout(2, 2));
                        nomePanel.add(new JLabel("Novo Nome:"));
                        nomePanel.add(nomeField);

                        int nomeResult = JOptionPane.showConfirmDialog(null, nomePanel, "Alterar Nome do Instrumento", JOptionPane.OK_CANCEL_OPTION);
                        if (nomeResult == JOptionPane.OK_OPTION) {
                            String novoNome = nomeField.getText().trim();
                            
                            if (novoNome.isEmpty()) {
                                throw new IllegalArgumentException("O campo Novo Nome não pode estar vazio!");
                            }
                            
                            OrdemServico[] ordensArray = repositorioOrdens.listar();
                            
                            for (OrdemServico ordem : ordensArray) {
                                if (ordem.getInstrumento() != null && ordem.getInstrumento().getCodigo().equals(instrumento.getCodigo())) {
                                    ordem.getInstrumento().setNome(novoNome);
                                }
                            }
                            repositorioOrdens.salvarOrdens(); 
                            repositorioInstrumentos.alterarNome(instrumento, novoNome);
                            
                            JOptionPane.showMessageDialog(null, "Nome alterado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else {
                        throw new INEException("Instrumento com código " + codigo + " não encontrado.");
                    }
                } catch (INEException | IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Ocorreu um erro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Definindo tamanhos preferidos dos botões
        inputButton.setPreferredSize(new Dimension(200, 100));
        backButton.setPreferredSize(new Dimension(200, 100));
        deleteButton.setPreferredSize(new Dimension(200, 100));
        alterarNomeButton.setPreferredSize(new Dimension(200, 100));
        
        buttonPanel.add(inputButton);
        buttonPanel.add(backButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(alterarNomeButton);
        
        panel.add(label, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.CENTER); 
        
        return panel;
    }

    
    private JPanel createOrdensPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 100, 20));
        JLabel label = new JLabel("Ordens Menu", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));

        JButton inputButton = new JButton("Inserir Ordem");
        inputButton.addActionListener(e -> {
            JPanel inputPanel = new JPanel(new GridLayout(6, 2));
            JTextField idField = new JTextField();
            JComboBox<String> clienteComboBox = new JComboBox<>(repositorioClientes.listarEmails());
            JComboBox<String> instrumentoComboBox = new JComboBox<>(repositorioInstrumentos.listarCodigos());
            JTextField descricaoServicoField = new JTextField();
            JTextField dataEntregaField = new JTextField();
            JTextField materialField = new JTextField();

            inputPanel.add(new JLabel("ID:"));
            inputPanel.add(idField);
            inputPanel.add(new JLabel("Cliente:"));
            inputPanel.add(clienteComboBox);
            inputPanel.add(new JLabel("Instrumento:"));
            inputPanel.add(instrumentoComboBox);
            inputPanel.add(new JLabel("Descrição do Serviço:"));
            inputPanel.add(descricaoServicoField);
            inputPanel.add(new JLabel("Data de Entrega:"));
            inputPanel.add(dataEntregaField);
            inputPanel.add(new JLabel("Material:"));
            inputPanel.add(materialField);

            int result = JOptionPane.showConfirmDialog(panel, inputPanel, "Digite os Dados da Ordem", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                try {
                    // Valida os campos
                    if (idField.getText().trim().isEmpty()) {
                        throw new IllegalArgumentException("O ID não pode estar vazio.");
                    }
                    if (descricaoServicoField.getText().trim().isEmpty()) {
                        throw new IllegalArgumentException("A descrição do serviço não pode estar vazia.");
                    }
                    if (dataEntregaField.getText().trim().isEmpty()) {
                        throw new IllegalArgumentException("A data de entrega não pode estar vazia.");
                    }


                    int id = Integer.parseInt(idField.getText().trim());
                    String selectedEmail = (String) clienteComboBox.getSelectedItem();
                    Cliente cliente = repositorioClientes.buscarPorEmail(selectedEmail);
                    String selectedCodigo = (String) instrumentoComboBox.getSelectedItem();
                    InstrumentoAbstrato instrumento = repositorioInstrumentos.buscarPorId(selectedCodigo);
                    String descricaoServico = descricaoServicoField.getText().trim();
                    String dataEntrega = dataEntregaField.getText().trim();
                    String material = materialField.getText().trim();

                    if (repositorioOrdens.buscarPorId(id) != null) {
                        throw new IllegalArgumentException("Já existe uma ordem cadastrada com esse ID!");
                    }

                    OrdemServico ordemNova = new OrdemServico(id, cliente, instrumento, descricaoServico, dataEntrega, material);

                    // salvando no arquivo
                    repositorioOrdens.inserir(ordemNova);

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(panel, "Por favor, insira um ID válido.", "Erro", JOptionPane.ERROR_MESSAGE);
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(panel, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                System.out.println("Operação cancelada.");
            }
        });

        JButton backButton = new JButton("Voltar");
        backButton.addActionListener(e -> showMenu("Menu1"));

        JButton deleteButton = new JButton("Remover Ordem");
        deleteButton.addActionListener(e -> {
            JPanel inputPanel = new JPanel(new GridLayout(6, 2));
            JTextField idField = new JTextField();

            inputPanel.add(new JLabel("ID:"));
            inputPanel.add(idField);

            int result = JOptionPane.showConfirmDialog(panel, inputPanel, "Digite a ordem que deseja excluir", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                try {
                    if (idField.getText().trim().isEmpty()) {
                        throw new IllegalArgumentException("O ID não pode estar vazio.");
                    }

                    int id = Integer.parseInt(idField.getText().trim());

                    if (repositorioOrdens.buscarPorId(id) == null) {
                        throw new IllegalArgumentException("Não existe uma ordem cadastrada com esse ID!");
                    }

                    // excluindo do arquivo
                    repositorioOrdens.excluir(id);

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(panel, "Por favor, insira um ID válido.", "Erro", JOptionPane.ERROR_MESSAGE);
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(panel, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                System.out.println("Operação cancelada.");
            }
        });

        JButton atualizarButton = new JButton("Atualizar Ordem");
        atualizarButton.addActionListener(e -> {
            JPanel inputPanel = new JPanel(new GridLayout(6, 2));
            JTextField idField = new JTextField();
            inputPanel.add(new JLabel("ID da ordem:"));
            inputPanel.add(idField);

            int result = JOptionPane.showConfirmDialog(null, inputPanel, "Atualizar Ordem", JOptionPane.OK_CANCEL_OPTION);

            if (result == JOptionPane.OK_OPTION) {
                try {
                    if (idField.getText().trim().isEmpty()) {
                        throw new IllegalArgumentException("O ID não pode estar vazio.");
                    }

                    int id = Integer.parseInt(idField.getText().trim());

                    OrdemServico ordemEncontrada = repositorioOrdens.buscarPorId(id);
                    if (ordemEncontrada != null) {
                        String instrumentoCodigo = ordemEncontrada.getInstrumento().getCodigo();

                        InstrumentoAbstrato instrumentoEncontrado = repositorioInstrumentos.buscarPorId(instrumentoCodigo);
                        if (instrumentoEncontrado != null) {
                            JPanel estadoPanel = new JPanel(new GridLayout(6, 2));
                            JComboBox<EstadoInstrumento> estadoComboBox = new JComboBox<>(EstadoInstrumento.values());
                            estadoPanel.add(new JLabel("Estado:"));
                            estadoPanel.add(estadoComboBox);

                            int estadoResult = JOptionPane.showConfirmDialog(null, estadoPanel, "Selecionar Estado", JOptionPane.OK_CANCEL_OPTION);
                            if (estadoResult == JOptionPane.OK_OPTION) {
                                EstadoInstrumento estado = (EstadoInstrumento) estadoComboBox.getSelectedItem();

                                // Atualiza o estado do instrumento
                                instrumentoEncontrado.setEstado(estado);

                                // Salva a alteração no repositório de instrumentos
                                repositorioInstrumentos.salvarInstrumentos();

                                // Atualiza a ordem com o novo estado
                                repositorioOrdens.atualizar(id, instrumentoCodigo, estado);
                                JOptionPane.showMessageDialog(null, "Ordem atualizada com sucesso! Estado: " + estado);
                            }
                        } else {
                            throw new INEException("Instrumento com código " + instrumentoCodigo + " não encontrado.");
                        }
                    } else {
                        throw new ONEException("Ordem com ID " + id + " não encontrada.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "ID inválido! Por favor, insira um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
                } catch (ONEException | INEException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        inputButton.setPreferredSize(new Dimension(200, 100));
        backButton.setPreferredSize(new Dimension(200, 100));
        deleteButton.setPreferredSize(new Dimension(200, 100));
        atualizarButton.setPreferredSize(new Dimension(200, 100));

        buttonPanel.add(inputButton);
        buttonPanel.add(backButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(atualizarButton);

        panel.add(label, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.CENTER);

        return panel;
    }


    
    private JPanel createClientesPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 100, 20));
        JLabel label = new JLabel("Clientes Menu", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        
        JButton inputButton = new JButton("Inserir Cliente");
        inputButton.addActionListener(e -> {
            JPanel inputPanel = new JPanel(new GridLayout(6, 2));
            JTextField nomeField = new JTextField();
            JTextField emailField = new JTextField();
            JTextField telefoneField = new JTextField();
            JTextField enderecoField = new JTextField();

            inputPanel.add(new JLabel("Nome:"));
            inputPanel.add(nomeField);
            inputPanel.add(new JLabel("Email:"));
            inputPanel.add(emailField);
            inputPanel.add(new JLabel("Telefone:"));
            inputPanel.add(telefoneField);
            inputPanel.add(new JLabel("Endereco:"));
            inputPanel.add(enderecoField);

            int result = JOptionPane.showConfirmDialog(panel, inputPanel, "Digite os Dados da Cliente", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                try {
                    String nome = nomeField.getText().trim();
                    String email = emailField.getText().trim();
                    String telefone = telefoneField.getText().trim();
                    String endereco = enderecoField.getText().trim();

                    // Verificações para campos vazios
                    if (nome.isEmpty()) {
                        throw new IllegalArgumentException("O campo Nome não pode estar vazio!");
                    }
                    if (email.isEmpty()) {
                        throw new IllegalArgumentException("O campo Email não pode estar vazio!");
                    }
                    if (telefone.isEmpty()) {
                        throw new IllegalArgumentException("O campo Telefone não pode estar vazio!");
                    }
                    if (endereco.isEmpty()) {
                        throw new IllegalArgumentException("O campo Endereço não pode estar vazio!");
                    }

                    if (repositorioClientes.buscarPorEmail(email) != null) {
                        throw new IllegalArgumentException("Já existe um cliente cadastrado com esse email!");
                    }

                    // Criando uma nova instância de Cliente
                    Cliente clienteNovo = new Cliente(nome, email, telefone, endereco);
                    
                    // Salvando no arquivo
                    repositorioClientes.inserir(clienteNovo);
                    
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(panel, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                System.out.println("Operação cancelada.");
            }
        });

        JButton backButton = new JButton("Voltar");
        backButton.addActionListener(e -> showMenu("Menu1"));

        JButton deleteButton = new JButton("Remover Cliente");
        deleteButton.addActionListener(e -> {
            JPanel inputPanel = new JPanel(new GridLayout(6, 2));
            JTextField emailField = new JTextField();
            
            inputPanel.add(new JLabel("Email:"));
            inputPanel.add(emailField);
            
            int result = JOptionPane.showConfirmDialog(panel, inputPanel, "Digite o cliente que deseja excluir", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                try {
                    String email = emailField.getText().trim();

                    if (email.isEmpty()) {
                        throw new IllegalArgumentException("O campo Email não pode estar vazio!");
                    }

                    if (repositorioClientes.buscarPorEmail(email) == null) {
                        throw new IllegalArgumentException("Não existe um cliente cadastrado com esse e-mail!");
                    }
                    
                    // Excluindo do arquivo
                    repositorioClientes.excluir(email);
                    
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(panel, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                System.out.println("Operação cancelada.");
            }
        });
        
        JButton alterarNomeButton = new JButton("Alterar nome");
        alterarNomeButton.addActionListener(e -> {
            JPanel inputPanel = new JPanel(new GridLayout(2, 2));
            JTextField emailField = new JTextField();

            inputPanel.add(new JLabel("Email:"));
            inputPanel.add(emailField);

            int result = JOptionPane.showConfirmDialog(null, inputPanel, "Buscar Cliente", JOptionPane.OK_CANCEL_OPTION);
            
            if (result == JOptionPane.OK_OPTION) {
                String email = emailField.getText().trim();

                try {
                    if (email.isEmpty()) {
                        throw new IllegalArgumentException("O campo Email não pode estar vazio!");
                    }

                    Cliente cliente = repositorioClientes.buscarPorEmail(email);
                    if (cliente != null) {
                        JTextField nomeField = new JTextField();
                        JPanel nomePanel = new JPanel(new GridLayout(2, 2));
                        nomePanel.add(new JLabel("Novo Nome:"));
                        nomePanel.add(nomeField);

                        int nomeResult = JOptionPane.showConfirmDialog(null, nomePanel, "Alterar Nome do Cliente", JOptionPane.OK_CANCEL_OPTION);

                        if (nomeResult == JOptionPane.OK_OPTION) {
                            String novoNome = nomeField.getText().trim();
                            
                            if (novoNome.isEmpty()) {
                                throw new IllegalArgumentException("O campo Novo Nome não pode estar vazio!");
                            }
                            
                            OrdemServico[] ordensArray = repositorioOrdens.listar();
                            
                            for (OrdemServico ordem : ordensArray) {
                                if (ordem.getCliente() != null && ordem.getCliente().getEmail().equals(cliente.getEmail())) {
                                    ordem.getCliente().setNome(novoNome);
                                }
                            }
                            repositorioOrdens.salvarOrdens(); 
                            repositorioClientes.alterarNome(cliente, novoNome);

                            JOptionPane.showMessageDialog(null, "Nome alterado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else {
                        throw new CNEException("Cliente com e-mail " + email + " não encontrado.");
                    }
                } catch (CNEException | IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Ocorreu um erro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Definindo tamanhos preferidos dos botões
        inputButton.setPreferredSize(new Dimension(200, 100));
        backButton.setPreferredSize(new Dimension(200, 100));
        deleteButton.setPreferredSize(new Dimension(200, 100));
        alterarNomeButton.setPreferredSize(new Dimension(200, 100));

        buttonPanel.add(inputButton);
        buttonPanel.add(backButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(alterarNomeButton);
        
        panel.add(label, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.CENTER); 
        
        return panel;
    }

    
    private JPanel createNotificacoesPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 100, 20));
        JLabel label = new JLabel("Notificações Menu", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));

        JButton inputButton = new JButton("Gerar Notificação");
        inputButton.addActionListener(e -> {
            JPanel inputPanel = new JPanel(new GridLayout(2, 2));
            JTextField idField = new JTextField();

            inputPanel.add(new JLabel("ID:"));
            inputPanel.add(idField);

            int result = JOptionPane.showConfirmDialog(panel, inputPanel, "Digite o ID da Ordem para gerar a notificação", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                try {
                    String idText = idField.getText().trim();
                    
                    // Verificação para campo vazio
                    if (idText.isEmpty()) {
                        throw new IllegalArgumentException("O campo ID não pode estar vazio!");
                    }
                    
                    int id = Integer.parseInt(idText);
                    OrdemServico ordemNova = repositorioOrdens.buscarPorId(id);

                    if (ordemNova == null) {
                        throw new IllegalArgumentException("Não existe uma ordem cadastrada com esse ID.");
                    }

                    Notificacao notificacao = new Notificacao();
                    JOptionPane.showMessageDialog(panel, notificacao.imprimirNotificacao(ordemNova), "Notificação", JOptionPane.INFORMATION_MESSAGE);

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(panel, "Por favor, insira um ID válido.", "Erro", JOptionPane.ERROR_MESSAGE);
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(panel, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(panel, "Ocorreu um erro inesperado: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                System.out.println("Operação cancelada.");
            }
        });

        JButton backButton = new JButton("Voltar");
        backButton.addActionListener(e -> showMenu("Menu1"));

        inputButton.setPreferredSize(new Dimension(200, 100));
        backButton.setPreferredSize(new Dimension(200, 100));

        buttonPanel.add(inputButton);
        buttonPanel.add(backButton);

        panel.add(label, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.CENTER);

        return panel;
    }


    
    private void showMenu(String menu) {
        CardLayout cl = (CardLayout) (mainPanel.getLayout());
        cl.show(mainPanel, menu);
    }

    public static void main(String[] args) {
        new MyFrame();
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == instrumentosButton) {
            System.out.println("*beep boop* Instrumento selecionado");
        }
        if (e.getSource() == ordensButton) {
            System.out.println("*beep boop* Ordem selecionada");
        }
        if (e.getSource() == clientesButton) {
            System.out.println("*beep boop* Cliente selecionado");
        }
    }
}