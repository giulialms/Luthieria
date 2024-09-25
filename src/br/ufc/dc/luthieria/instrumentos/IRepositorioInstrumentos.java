package br.ufc.dc.luthieria.instrumentos;



public interface IRepositorioInstrumentos {
	  void inserir(InstrumentoAbstrato instrumento);
	  void excluir(String codigo);
	  void alterarNome(InstrumentoAbstrato instrumento, String novoNome);
	  InstrumentoAbstrato buscarPorId(String codigo);
	  InstrumentoAbstrato[] listar();
}
