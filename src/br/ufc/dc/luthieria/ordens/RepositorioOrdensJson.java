package br.ufc.dc.luthieria.ordens;
import java.io.File;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import br.ufc.dc.luthieria.instrumentos.EstadoInstrumento;
import br.ufc.dc.luthieria.instrumentos.InstrumentoAbstrato;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class RepositorioOrdensJson implements IRepositorioOrdens {
	private List<OrdemServico> vetorOrdens = new ArrayList<>();
    private String caminhoArquivo;
    private Gson gson = new Gson();
    
 

    public RepositorioOrdensJson(String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
        carregarOrdens(); 
    }

    @Override
    public void inserir(OrdemServico ordem) {
        vetorOrdens.add(ordem);
        salvarOrdens();
    }

    @Override
    public void excluir(int id) {
        vetorOrdens.removeIf(ordem -> ordem.getId() == id);
        salvarOrdens();
    }

    @Override

    public OrdemServico buscarPorId(int id) {
        for (OrdemServico ordem : vetorOrdens) {
        	if (ordem.getId() == id) {
                return ordem; 
            }
        }
        return null;
    }

    
    @Override
    public OrdemServico[] listar() {
        return vetorOrdens.toArray(new OrdemServico[0]);
    }

    private void carregarOrdens() {
    	File arquivo = new File(caminhoArquivo);
    	
    	if(arquivo.exists()) {
    		try (Reader reader = new FileReader(arquivo)){
    			Gson gson = new Gson();
    			
    			 OrdemServico[] loadedOrdens = gson.fromJson(reader, OrdemServico[].class);
                 if (loadedOrdens != null) {
                     for (OrdemServico ordem : loadedOrdens) {
                         vetorOrdens.add(ordem);
                     }
    		}
    	} catch(IOException e) {
    		e.printStackTrace();
    	}
    		}

    	
    	/*
        try (Reader reader = new FileReader(caminhoArquivo)) {
            
            Type listType = new TypeToken<ArrayList<OrdemServico>>() {}.getType();
            return gson.fromJson(reader, listType);

        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }*/
    }

    private void salvarOrdens() {
        try (Writer writer = new FileWriter(caminhoArquivo)) {
            gson.toJson(vetorOrdens, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	@Override
	public void atualizar(int id, String codigoInstrumento, EstadoInstrumento novoEstado) {
		//atualiza o estado do instrumento, sendo o novo estado aquele dado no parametro
	    for (OrdemServico ordem : vetorOrdens) {
	        if (ordem.getId() == id) { 
	            InstrumentoAbstrato instrumento = ordem.getInstrumento(); 
	            if (instrumento != null && instrumento.getCodigo().equals(codigoInstrumento)) {
	                
	                instrumento.setEstado(novoEstado);
	                salvarOrdens();  
	                return;
	            }
	        }
	    }	
	}
	


}
