package br.ufc.dc.luthieria.ordens;
import br.ufc.dc.luthieria.instrumentos.EstadoInstrumento;

public interface IRepositorioOrdens {
    void inserir(OrdemServico ordem);
    void excluir(int id);
    OrdemServico buscarPorId(int id);
    OrdemServico[] listar();
    void atualizar(int id, String codigoInstrumento, EstadoInstrumento novoEstado);
}
