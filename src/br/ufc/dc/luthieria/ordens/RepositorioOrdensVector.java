package br.ufc.dc.luthieria.ordens;

import java.util.ArrayList;
import java.util.List;

import br.ufc.dc.luthieria.instrumentos.EstadoInstrumento;
import br.ufc.dc.luthieria.instrumentos.InstrumentoAbstrato;

public class RepositorioOrdensVector implements IRepositorioOrdens{
	//vector que vai guardar as ordens
	private List<OrdemServico> vetorOrdens = new ArrayList<>();
	
	@Override
	public void inserir(OrdemServico ordem) {
		vetorOrdens.add(ordem);	
	}

	@Override
	public void excluir(int id) {
		vetorOrdens.removeIf(ordem -> ordem.getId() == id);
		
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
    
	@Override
	public void atualizar(int id, String codigoInstrumento, EstadoInstrumento novoEstado) {
		//atualiza o estado do instrumento, sendo o novo estado aquele dado no parametro
	    for (OrdemServico ordem : vetorOrdens) {
	        if (ordem.getId() == id) {
	            InstrumentoAbstrato instrumento = ordem.getInstrumento();
	            if (instrumento.getCodigo().equals(codigoInstrumento)) {
	                instrumento.setEstado(novoEstado);
	            }
	        }
	    }	
	}



}
