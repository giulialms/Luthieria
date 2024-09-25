package br.ufc.dc.luthieria.clientes;



public interface IRepositorioClientes {
    void inserir(Cliente cliente);
    void excluir(String email);
    void alterarNome(Cliente cliente, String nome);
    Cliente buscarPorEmail(String email); 
    Cliente[] listar();
}