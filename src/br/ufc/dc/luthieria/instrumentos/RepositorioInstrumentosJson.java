package br.ufc.dc.luthieria.instrumentos;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import br.ufc.dc.luthieria.ordens.OrdemServico;
import br.ufc.dc.luthieria.INEException;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class RepositorioInstrumentosJson implements IRepositorioInstrumentos{
	private List<InstrumentoAbstrato> instrumentos = new ArrayList<>();
    private String caminhoArquivo;
    private Gson gson = new Gson();
    
    public RepositorioInstrumentosJson(String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
        carregarInstrumentos();
    }

    @Override
    public void inserir(InstrumentoAbstrato instrumento) {
        instrumentos.add(instrumento);
        salvarInstrumentos();
    }

    @Override
    public void excluir(String codigo) {
        instrumentos.removeIf(instrumento -> instrumento.getCodigo().equals(codigo));
        salvarInstrumentos();
    }


	@Override
	public InstrumentoAbstrato buscarPorId(String codigo) {
        for (InstrumentoAbstrato instrumento : instrumentos) {
        	 if (instrumento.getCodigo().equals(codigo)) {
                return instrumento; 
            }
        }
        return null;
	}

	@Override
	public InstrumentoAbstrato[] listar() {
		return instrumentos.toArray(new InstrumentoAbstrato[0]);
	} 
	
    public String[] listarCodigos() {
        String[] codigos = new String[instrumentos.size()];
        for (int i = 0; i < instrumentos.size(); i++) {
            codigos[i] = instrumentos.get(i).getCodigo(); // Obtém o código de cada instrumento
        }
        return codigos;
    }
	
    private void carregarInstrumentos() {
    	File arquivo = new File(caminhoArquivo);
    	
    	if(arquivo.exists()) {
    		try (Reader reader = new FileReader(arquivo)){
    			Gson gson = new Gson();
    			
    			 InstrumentoAbstrato[] loadedInstrumentos = gson.fromJson(reader, InstrumentoAbstrato[].class);
                 if (loadedInstrumentos != null) {
                     for (InstrumentoAbstrato ordem : loadedInstrumentos) {
                         instrumentos.add(ordem);
                     }
    		}
    	} catch(IOException e) {
    		e.printStackTrace();
    	}
    		}
    }


    public void salvarInstrumentos() {
        try (Writer writer = new FileWriter(caminhoArquivo)) {
            gson.toJson(instrumentos, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void alterarNome(InstrumentoAbstrato instrumento, String novoNome) {
                instrumento.setNome(novoNome);
                salvarInstrumentos();
                return;
    }



}
