package br.ufc.dc.luthieria.clientes;

import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import br.ufc.dc.luthieria.instrumentos.InstrumentoAbstrato;

import java.io.*;
import java.lang.reflect.Type;
import java.util.List;
import java.util.ArrayList;

public class RepositorioClientesJson implements IRepositorioClientes{
	 private List<Cliente> clientes = new ArrayList<>();
	 private String caminhoArquivo;
	 private Gson gson = new Gson();
	 
	 public RepositorioClientesJson(String caminhoArquivo) {
	        this.caminhoArquivo = caminhoArquivo;
	        carregarClientes();
	    }

	@Override
	public void inserir(Cliente cliente) {
		clientes.add(cliente);
	    salvarClientes();		
	}

	@Override
	public void excluir(String email) {
		clientes.removeIf(cliente -> cliente.getEmail().equalsIgnoreCase(email));
	    salvarClientes();
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

 
    public String[] listarEmails() {
        String[] emails = new String[clientes.size()];
        for (int i = 0; i < clientes.size(); i++) {
            emails[i] = clientes.get(i).getEmail(); // Obtém o e-mail de cada cliente
        }
        return emails;
    }
    
	@Override
	public void alterarNome(Cliente cliente, String novoNome) {
	    // Verifica se o cliente está na lista
	    Cliente clienteExistente = buscarPorEmail(cliente.getEmail());
	    
	    if (clienteExistente != null) {
	        // Alterar o nome do cliente
	        clienteExistente.setNome(novoNome);
	        
	        // Salvar as alterações no arquivo
	        salvarClientes();
	    }
	}
	
    private void carregarClientes() {
    	File arquivo = new File(caminhoArquivo);
    	
    	if(arquivo.exists()) {
    		try (Reader reader = new FileReader(arquivo)){
    			Gson gson = new Gson();
    			
    			 Cliente[] loadedClientes = gson.fromJson(reader, Cliente[].class);
                 if (loadedClientes != null) {
                     for (Cliente ordem : loadedClientes) {
                         clientes.add(ordem);
                     }
    		}
    	} catch(IOException e) {
    		e.printStackTrace();
    	}
    		}
    }



    private void salvarClientes() {
        try (Writer writer = new FileWriter(caminhoArquivo)) {
            gson.toJson(clientes, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
