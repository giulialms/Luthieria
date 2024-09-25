package br.ufc.dc.luthieria.instrumentos;
import java.util.ArrayList;
import java.util.List;

import br.ufc.dc.luthieria.INEException;


public class RepositorioInstrumentosVector implements IRepositorioInstrumentos{
	private List<InstrumentoAbstrato> instrumentos = new ArrayList<>();

	@Override
	public void inserir(InstrumentoAbstrato instrumento) {
		instrumentos.add(instrumento);
		
	}

	@Override
	public void excluir(String codigo) {
		instrumentos.removeIf(instrumento -> instrumento.getCodigo() == codigo);
		
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
	

	@Override
	public void alterarNome(InstrumentoAbstrato instrumento, String novoNome) {
		// TODO Auto-generated method stub
		
	}



}
