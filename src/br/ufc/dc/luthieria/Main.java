package br.ufc.dc.luthieria;
import br.ufc.dc.luthieria.clientes.Cliente;
import br.ufc.dc.luthieria.instrumentos.EstadoInstrumento;
import br.ufc.dc.luthieria.instrumentos.InstrumentoAbstrato;
import br.ufc.dc.luthieria.instrumentos.InstrumentoCordas;
import br.ufc.dc.luthieria.instrumentos.InstrumentoPercussao;
import br.ufc.dc.luthieria.ordens.OrdemServico;
import br.ufc.dc.luthieria.ordens.RepositorioOrdensJson;

public class Main {
	 public static void main(String[] args) {
	Cliente cliente = new Cliente("roberto", "roberto@yahoo.com", "123", "pici");

	InstrumentoCordas violino = new InstrumentoCordas("violino", "fender", "codigo", EstadoInstrumento.EM_MANUTENCAO, 6, "materialCorda");
	OrdemServico ordem1 = new OrdemServico(123, cliente, violino, "descricaoServico", "dataEntrega", null);
	Notificacao notificacao1 = new Notificacao();
	notificacao1.imprimirNotificacao(ordem1);
	
	InstrumentoPercussao tambor = new InstrumentoPercussao("tambor", "tuntstunstuntstuntuntuns", "codigo", EstadoInstrumento.EM_FABRICACAO, "pele", "baqueta");
	OrdemServico ordem2 = new OrdemServico(124, cliente, tambor, "descricaoServico", "dataEntrega", "material");
	Notificacao notificacao2 = new Notificacao();
	notificacao2.imprimirNotificacao(ordem2);
	
	RepositorioOrdensJson repositorio = new RepositorioOrdensJson("C:\\Users\\giuli\\Downloads\\ordens.json");

	repositorio.inserir(ordem1);
	repositorio.inserir(ordem2);
	repositorio.listar();
	repositorio.atualizar(123, "codigo", EstadoInstrumento.PRONTO);
	repositorio.listar();

	
	/*Conta c;
	c = new ContaEspecial("222");
	Conta d;
	d = new ContaEspecial("333");
	AccountJson account = new AccountJson("C:\\Users\\giuli\\Downloads\\conta.json");
	account.inserir(c);
	account.renderJuros("222", 0.5);
	account.inserir(d);
	account.remover("333");
	account.listar();
	*/
	 }

}
