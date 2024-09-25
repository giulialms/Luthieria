package br.ufc.dc.luthieria.clientes;

import java.util.ArrayList;
import java.util.List;


public class RepositorioClientesVector implements IRepositorioClientes{
	 private List<Cliente> clientes = new ArrayList<>();
	 

		@Override
		public void inserir(Cliente cliente) {
			clientes.add(cliente);		
		}

		@Override
		public void excluir(String nome) {
			clientes.removeIf(cliente -> cliente.getNome().equalsIgnoreCase(nome));
		}


	@Override
	public Cliente buscarPorEmail(String email) {
	    for (Cliente cliente : clientes) {
	        if (cliente.getEmail().equalsIgnoreCase(email)) {
	            return cliente;
	        }
	    }
	    return null; // Se nenhum cliente for encontrado
	}

	@Override
	public Cliente[] listar() {
		return clientes.toArray(new Cliente[0]);
	}

	@Override
	public void alterarNome(Cliente cliente, String nome) {
		// TODO Auto-generated method stub
		
	} 

}
